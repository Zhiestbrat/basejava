package com.urise.webapp.model;

public enum ContectType {
    TELEPHONE("Тел.: "),
    SKYPE("Skype: "),
    MAIL("Почта: "),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STACKOVERFLOW("Профиль Stackoverflow"),
    HOMEPAGE("Домашняя страница");

    private String title;

    ContectType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
