package com.urise.webapp.model;

import java.util.Objects;

public class TextSection extends AbstractSection {
    private String contact;

    public TextSection() {

    }

    public TextSection(String contact) {
        Objects.requireNonNull(contact, "contact must not be null");
        this.contact = contact;
    }

    public String getContact() {
        return contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextSection that = (TextSection) o;
        return Objects.equals(contact, that.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contact);
    }

    @Override
    public String toString() {
        return contact;
    }
}
