package com.urise.webapp.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;

/**
 * @author p.kondakov
 */
public class JsonLocalDateAdapter extends TypeAdapter<LocalDate> {
    @Override
    public void write(JsonWriter jsonWriter, LocalDate localDate) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("localDate");
        jsonWriter.value(String.valueOf(localDate));
        jsonWriter.endObject();
    }

    @Override
    public LocalDate read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        jsonReader.nextName();
        String nameParts = jsonReader.nextString();
        jsonReader.endObject();
        return LocalDate.parse(nameParts);
    }
}
