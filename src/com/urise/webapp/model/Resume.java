package com.urise.webapp.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;


/**
 * Initial resume class
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Serializable {

    public static final Resume EMPTY = new Resume();

    static {
        EMPTY.setSectionType(SectionType.OBJECTIVE, TextSection.EMPTY);
        EMPTY.setSectionType(SectionType.PERSONAL, TextSection.EMPTY);
        EMPTY.setSectionType(SectionType.ACHIEVEMENT, ListSection.EMPTY);
        EMPTY.setSectionType(SectionType.QUALIFICATIONS, ListSection.EMPTY);
        EMPTY.setSectionType(SectionType.EXPERIENCE, new OrganizationSection(Organization.EMPTY));
        EMPTY.setSectionType(SectionType.EDUCATION, new OrganizationSection(Organization.EMPTY));
    }
    @Serial
    private static final long serialVersionUID = 1L;
    // Unique identifier
    private String uuid;
    private String fullName;
    private final Map<ContactType, String> contactType = new EnumMap<>(ContactType.class);
    private final Map<SectionType, AbstractSection> sectionType = new EnumMap<>(SectionType.class);

    public Resume() {
    }

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
    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public Map<ContactType, String> getContactType() {
        return contactType;
    }

    public Map<SectionType, AbstractSection> getSectionType() {
        return sectionType;
    }

    public String getFullName() {
        return fullName;
    }
}
