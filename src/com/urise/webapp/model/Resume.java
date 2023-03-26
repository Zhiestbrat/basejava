package com.urise.webapp.model;


import java.util.*;


/**
 * Initial resume class
 */
public class Resume {

    // Unique identifier
    private final String uuid;
    private final String fullName;
    private final Map<ContectType, String> contectType = new EnumMap<>(ContectType.class);
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
                Objects.equals(contectType, resume.contectType) &&
                Objects.equals(sectionType, resume.sectionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, contectType, sectionType);
    }

    @Override
    public String toString() {
        return uuid + '(' + fullName + ')';
    }

    public String getUuid() {
        return uuid;
    }

    public void setContectType(ContectType type, String contect) {
        contectType.put(type, contect);
    }

    public void setSectionType(SectionType type, AbstractSection section) {
        sectionType.put(type, section);
    }

    public String getContectType(ContectType type) {
        return contectType.get(type);
    }

    public AbstractSection getSectionType(SectionType type) {
        return sectionType.get(type);
    }

    public Map<ContectType, String> getContectsType() {
        return contectType;
    }

    public Map<SectionType, AbstractSection> getSectionsType() {
        return sectionType;
    }

    public String getFullName() {
        return fullName;
    }
}
