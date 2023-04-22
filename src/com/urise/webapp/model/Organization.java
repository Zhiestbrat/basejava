package com.urise.webapp.model;


import com.google.gson.annotations.JsonAdapter;
import com.urise.webapp.util.JsonLocalDateAdapter;
import com.urise.webapp.util.XmlLocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.urise.webapp.util.DateUtil.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Link homePage;
    private List<Period> periods = new ArrayList<>();

    public Organization() {
    }

    public Organization(String name, String url, Period... period) {
        this(new Link(name, url), List.of(period));
    }

    public Organization(Link homePage, List<Period> periods) {
        this.homePage = homePage;
        this.periods = periods;
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(homePage, that.homePage) && Objects.equals(periods, that.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, periods);
    }

    @Override
    public String toString() {
        return "Organization(" + homePage + "," + periods + ')';
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Period implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;
        @JsonAdapter(JsonLocalDateAdapter.class)
        @XmlJavaTypeAdapter(XmlLocalDateAdapter.class)
        private LocalDate startDate;
        @JsonAdapter(JsonLocalDateAdapter.class)
        @XmlJavaTypeAdapter(XmlLocalDateAdapter.class)
        private LocalDate endDate;
        private String title;
        private String description;

        public Period() {
        }

        public Period(int startYear, Month startMonth, String title, String description) {
            this(of(startYear, startMonth), NOW, title, description);
        }

        public Period(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
            this(of(startYear, startMonth), of(endYear, endMonth), title, description);
        }

        public Period(LocalDate startDate, LocalDate endDate, String title, String description) {
            Objects.requireNonNull(startDate, "start must not be null");
            Objects.requireNonNull(endDate, "end must not be null");
            Objects.requireNonNull(title, "title must not be null");
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.description = description;
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
}
