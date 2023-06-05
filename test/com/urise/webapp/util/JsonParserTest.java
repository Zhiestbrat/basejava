package com.urise.webapp.util;

import com.urise.webapp.model.AbstractSection;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;
import com.urise.webapp.model.TextSection;
import org.junit.Test;

import static com.urise.webapp.TestData.R1;
import static org.junit.Assert.assertEquals;

/**
 * @author p.kondakov
 */
public class JsonParserTest {

    @Test
    public void read() {
        String json = JsonParser.write(R1);
        System.out.println(json);
        Resume resume = JsonParser.read(json, Resume.class);
        assertEquals(R1, resume);
    }

    @Test
    public void write() {
        AbstractSection section = new TextSection("Objective1");
        String json = JsonParser.write(section, AbstractSection.class);
        System.out.println(json);
        AbstractSection section1 = JsonParser.read(json, AbstractSection.class);
        assertEquals(section1, section);
    }
}