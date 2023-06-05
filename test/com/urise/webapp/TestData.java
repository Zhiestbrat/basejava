package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.Month;
import java.util.UUID;

/**
 * @author p.kondakov
 */
public class TestData {
    public static final String UUID_1 = UUID.randomUUID().toString();
    public static final String UUID_2 = UUID.randomUUID().toString();
    public static final String UUID_3 = UUID.randomUUID().toString();
    public static final String UUID_4 = UUID.randomUUID().toString();

    public static final Resume R1;
    public static final Resume R2;
    public static final Resume R3;
    public static final Resume R4;

    static {
        R1 = new Resume(UUID_1, "Name1");
        R2 = new Resume(UUID_2, "Name2");
        R3 = new Resume(UUID_3, "Name3");
        R4 = new Resume(UUID_4, "Name4");

        R1.setContactType(ContactType.MAIL, "mail1@ya.ru");
        R1.setContactType(ContactType.PHONE, "11111");

        R4.setContactType(ContactType.PHONE, "44444");
        R4.setContactType(ContactType.SKYPE, "Skype");

        R1.setSectionType(SectionType.OBJECTIVE, new TextSection("Objective1"));
        R1.setSectionType(SectionType.PERSONAL, new TextSection("Personal data"));
        R1.setSectionType(SectionType.ACHIEVEMENT, new ListSection("Achivment11", "Achivment12", "Achivment13"));
        R1.setSectionType(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
        R1.setSectionType(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization11", "http://Organization11.ru",
                                new Organization.Period(2005, Month.JANUARY, "position1", "content1"),
                                new Organization.Period(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        R1.setSectionType(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization2", "http://Organization2.ru",
                                new Organization.Period(2015, Month.JANUARY, "position1", "content1"))));
        R1.setSectionType(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Institute", null,
                                new Organization.Period(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Organization.Period(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Organization("Organization12", "http://Organization12.ru")));

        R2.setContactType(ContactType.SKYPE, "skype2");
        R2.setContactType(ContactType.PHONE, "22222");
    }
}
