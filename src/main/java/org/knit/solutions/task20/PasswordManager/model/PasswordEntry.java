package org.knit.solutions.task20.PasswordManager.model;

import java.util.UUID;


public class PasswordEntry {
    private UUID id;
    private String site;
    private String login;
    private String encryptedPassword;

    public PasswordEntry(String site, String login, String encryptedPassword) {
        this.id = UUID.randomUUID();
        this.site = site;
        this.login = login;
        this.encryptedPassword = encryptedPassword;
    }

    public UUID getId() {
        return id;
    }

    public String getSite() {
        return site;
    }

    public String getLogin() {
        return login;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    @Override
    public String toString() {
        return "Сайт: " + site + ", логин: " + login;
    }
}
