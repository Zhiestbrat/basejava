package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author p.kondakov
 */
public class DataStreamSerializer implements StreamSerializer {
    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContactType();
            Map<SectionType, AbstractSection> sections = resume.getSectionType();
            writeContactType(dos, contacts);
            writeSectionType(dos, sections);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readContactType(dis, resume);
            readSectionType(dis, resume);
            return resume;
        }
    }

    private void writeContactType(DataOutputStream dos, Map<ContactType, String> contact) throws IOException {
        dos.writeInt(contact.size());
        contact.forEach((key, value) -> {
            writeUTF(dos, key.name());
            writeUTF(dos, value);
        });
    }

    private void writeSectionType(DataOutputStream dos, Map<SectionType, AbstractSection> section) throws IOException {
        dos.writeInt(section.size());
        section.forEach((sectionType, abstractSection) -> {
            writeUTF(dos, sectionType.name());
            switch (sectionType) {
                case PERSONAL, OBJECTIVE -> writeUTF(dos, ((TextSection) abstractSection).getContact());
                case ACHIEVEMENT, QUALIFICATIONS -> writeCollection(dos, ((ListSection) abstractSection).getList()).
                        forEach(item -> writeUTF(dos, item));
                case EXPERIENCE, EDUCATION ->
                        writeCollection(dos, ((OrganizationSection) abstractSection).getOrganizations()).forEach(item -> {
                            writeUTF(dos, item.getHomePage().getName());
                            writeUTF(dos, item.getHomePage().getUrl());
                            writeCollection(dos, item.getPeriods()).forEach(period -> {
                                writeLocalDate(dos, period.getStart());
                                writeLocalDate(dos, period.getEnd());
                                writeUTF(dos, period.getTitle());
                                writeUTF(dos, period.getDescription());
                            });
                        });

            }
        });
    }

    private void writeUTF(DataOutputStream dos, String item) {
        try {
            dos.writeUTF(item);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private <E> Collection<E> writeCollection(DataOutputStream dos, Collection<E> collection) {
        try {
            dos.writeInt(collection.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return collection;
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate ld) {
        try {
            dos.writeInt(ld.getYear());
            dos.writeInt(ld.getMonth().getValue());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }

    private void readContactType(DataInputStream dis, Resume resume) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            resume.setContactType(ContactType.valueOf(dis.readUTF()), dis.readUTF());
        }
    }

    private void readSectionType(DataInputStream dis, Resume resume) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            SectionType sectionType = SectionType.valueOf(dis.readUTF());
            resume.setSectionType(sectionType, readSection(dis, sectionType));
        }
    }

    private AbstractSection readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        return switch (sectionType) {
            case PERSONAL, OBJECTIVE -> new TextSection(dis.readUTF());
            case ACHIEVEMENT, QUALIFICATIONS -> new ListSection(readList(dis, dis::readUTF));
            case EXPERIENCE, EDUCATION -> new OrganizationSection(
                    readList(dis, () -> new Organization(
                            new Link(dis.readUTF(), dis.readUTF()),
                            readList(dis, () -> new Organization.Period(
                                    readLocalDate(dis), readLocalDate(dis), dis.readUTF(), dis.readUTF()
                            ))
                    )));

        };
    }
    private <T> List<T> readList(DataInputStream dis, Supplier<T> supplier) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(supplier.get());
        }
        return list;
    }
    private interface Supplier<T> {
        T get() throws IOException;
    }
}


