package com.urise.webapp;

import com.urise.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, ClassNotFoundException, InstantiationException {
        Resume resume = new Resume("gummy");
        Class<? extends Resume> resumeClass = resume.getClass();
        Class<?> c = Class.forName("Resume");
        Object object = c.newInstance();
        Field field = resumeClass.getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(resume));
        field.set(resume, "newUuid");
        System.out.println(resume);

        Method method = resumeClass.getMethods()[1];
        System.out.println(method.invoke(resume));
    }
}
