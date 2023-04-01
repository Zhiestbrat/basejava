package com.urise.webapp.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

public class Period {
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
    private String description;

    public Period() {
    }

    public Period(int startYear, Month startMonth, String title, String description) {
        this(LocalDate.of(startYear, startMonth, 1), LocalDate.now(), title, description);
    }

    public Period(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
        this(LocalDate.of(startYear, startMonth, 1), LocalDate.of(endYear, endMonth, 1), title, description);
    }

    public Period(LocalDate startDate, LocalDate endDate, String title, String description) {
        Objects.requireNonNull(startDate, "start must not be null");
        Objects.requireNonNull(endDate, "end must not be null");
        Objects.requireNonNull(title, "title must not be null");
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description == null ? " " : description;
    }

    public LocalDate getStart() {
        return startDate;
    }

    public LocalDate getEnd() {
        return endDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return Objects.equals(startDate, period.startDate) &&
                Objects.equals(endDate, period.endDate) &&
                Objects.equals(title, period.title) &&
                Objects.equals(description, period.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate, title, description);
    }

    @Override
    public String toString() {
        return "Period(" + startDate + ',' + endDate + ',' + title + ',' + description + ')';
    }
}
