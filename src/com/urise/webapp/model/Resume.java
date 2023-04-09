package com.urise.webapp.model;


import java.io.Serial;
import java.io.Serializable;
import java.util.*;


/**
 * Initial resume class
 */
public class Resume implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    // Unique identifier
    private final String uuid;
    private final String fullName;
    private final Map<ContactType, String> contactType = new EnumMap<>(ContactType.class);
    private final Map<SectionType, AbstractSection> sectionType = new EnumMap<>(SectionType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        Objects.requireNonNull(fullName, "fullName must not be null");
        Objects.requireNonNull(uuid, "uuid must not be null");
        this.uuid = uuid;
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName) &&
                Objects.equals(contactType, resume.contactType) &&
                Objects.equals(sectionType, resume.sectionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, contactType, sectionType);
    }

    @Override
    public String toString() {
        return uuid + '(' + fullName + ')';
    }

    public String getUuid() {
        return uuid;
    }

    public void setContactType(ContactType type, String contact) {
        contactType.put(type, contact);
    }

    public void setSectionType(SectionType type, AbstractSection section) {
        sectionType.put(type, section);
    }

    public String getContactType(ContactType type) {
        return contactType.get(type);
    }

    public AbstractSection getSectionType(SectionType type) {
        return sectionType.get(type);
    }

    public String getFullName() {
        return fullName;
    }
}
