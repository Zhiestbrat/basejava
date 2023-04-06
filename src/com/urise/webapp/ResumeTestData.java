package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.Month;
import java.util.List;

public class ResumeTestData {
    public static Resume getResume(String uuid, String fullName) {

        Resume resume = new Resume(uuid, fullName);

        resume.setContactType(ContactType.TELEPHONE, "+7(921) 855-0482");
        resume.setContactType(ContactType.SKYPE, "skype:grigory.kislin");
        resume.setContactType(ContactType.MAIL, "gkislin@yandex.ru");
        resume.setContactType(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        resume.setContactType(ContactType.GITHUB, "https://github.com/gkislin");
        resume.setContactType(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        resume.setContactType(ContactType.HOME_PAGE, "http://gkislin.ru/");

        resume.setSectionType(SectionType.OBJECTIVE,
                new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        resume.setSectionType(SectionType.PERSONAL,
                new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        resume.setSectionType(SectionType.ACHIEVEMENT, new ListSection(List.of("Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет",
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.",
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.",
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.",
                "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.",
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).",
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."
        )));
        resume.setSectionType(SectionType.QUALIFICATIONS, new ListSection(List.of("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce\n",
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB",
                "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy",
                "XML/XSD/XSLT, SQL, C/C++, Unix shell scripts",
                "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements)."
        )));
        resume.setSectionType(SectionType.EXPERIENCE, new OrganizationSection(List.of(new Organization("Java Online Projects", "http://javaops.ru/", new Organization.Period(2013, Month.of(10), "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок.")),
                new Organization("Wrike", "Wrike", new Organization.Period(2014, Month.of(10), 2016, Month.of(1), "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."))
        )));
        resume.setSectionType(SectionType.EDUCATION, new OrganizationSection(List.of(new Organization("Coursera", "https://www.coursera.org/course/progfun", new Organization.Period(2013, Month.of(3), 2013, Month.of(5), "'Functional Programming Principles in Scala' by Martin Odersky", " ")),
                new Organization("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366", new Organization.Period(2011, Month.of(3), 2011, Month.of(4), "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'", " ")),
                new Organization("Siemens AG", "http://www.siemens.ru/", new Organization.Period(2005, Month.of(1), 2005, Month.of(4), "3 месяца обучения мобильным IN сетям (Берлин)", " ")),
                new Organization(new Link("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/"), List.of(new Organization.Period(1993, Month.of(9), 1996, Month.of(7), "Аспирантура (программист С, С++)", " "), new Organization.Period(1987, Month.of(9), 1993, Month.of(7), "Инженер (программист Fortran, C)", "")))
        )));
        return resume;
    }
}
