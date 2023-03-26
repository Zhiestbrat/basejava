package com.urise.webapp.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Organization {
    private String nameOrganization;
    private String website;
    private List<Period> periods = new ArrayList<>();

    public Organization() {

    }

    public Organization(String nameOrganization, String website, Period period) {
        this(nameOrganization, website, List.of(period));
    }

    public Organization(String nameOrganization, String website, List<Period> periods) {
        this.periods = periods;
        this.website = website;
        this.nameOrganization = nameOrganization;
    }

    public String getNameOrganization() {
        return nameOrganization;
    }

    public String getWebsite() {
        return website;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(nameOrganization, that.nameOrganization) && Objects.equals(website, that.website) && Objects.equals(periods, that.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOrganization, website, periods);
    }

    @Override
    public String toString() {
        return "Organization(" + nameOrganization + ","  + website + "," + periods + ')';
    }
}
