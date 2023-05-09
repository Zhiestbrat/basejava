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
            readWithException(dis, () -> {
                ContactType contactType = ContactType.valueOf(dis.readUTF());
                resume.setContactType(contactType, dis.readUTF());
            });
            readWithException(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.setSectionType(sectionType, readSection(dis, sectionType));
            });
            return resume;
        }
    }

    private void writeContactType(DataOutputStream dos, Map<ContactType, String> contact) throws IOException {
        writeWithException(dos, contact.entrySet(), entry -> {
            dos.writeUTF(entry.getKey().name());
            dos.writeUTF(entry.getValue());
        });
    }

    private void writeSectionType(DataOutputStream dos, Map<SectionType, AbstractSection> section) throws IOException {
        writeWithException(dos, section.entrySet(), entry -> {
            SectionType sectionType = entry.getKey();
            AbstractSection abstractSection = entry.getValue();
            dos.writeUTF(sectionType.name());
            switch (sectionType) {
                case PERSONAL, OBJECTIVE -> dos.writeUTF(((TextSection) abstractSection).getContact());
                case ACHIEVEMENT, QUALIFICATIONS ->
                        writeWithException(dos, ((ListSection) abstractSection).getList(), dos::writeUTF);
                case EXPERIENCE, EDUCATION ->
                        writeWithException(dos, ((OrganizationSection) abstractSection).getOrganizations(), organization -> {
                            dos.writeUTF(organization.getHomePage().getName());
                            dos.writeUTF(organization.getHomePage().getUrl());
                            writeWithException(dos, organization.getPeriods(), period -> {
                                writeLocalDate(dos, period.getStart());
                                writeLocalDate(dos, period.getEnd());
                                dos.writeUTF(period.getTitle());
                                dos.writeUTF(period.getDescription());
                            });
                        });
            }
        });
    }

    private <T> void writeWithException(DataOutputStream dos, Collection<T> collection, Consumer<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.accept(item);
        }
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate ld) throws IOException {
        dos.writeInt(ld.getYear());
        dos.writeInt(ld.getMonth().getValue());
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }

    private void readWithException(DataInputStream dis, Runnable runnable) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            runnable.run();
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

    @FunctionalInterface
    private interface Supplier<T> {
        T get() throws IOException;
    }

    @FunctionalInterface
    private interface Consumer<T> {
        void accept(T item) throws IOException;
    }

    @FunctionalInterface
    private interface Runnable {
        void run() throws IOException;
    }
}


