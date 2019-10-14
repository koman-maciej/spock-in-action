package com.oracle.trs.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TrsModel {

    @Id
    private String title;
    private String description;
    private String presenterEmail;

    public TrsModel(String title, String description, String presenterEmail) {
        this.title = title;
        this.description = description;
        this.presenterEmail = presenterEmail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPresenterEmail() {
        return presenterEmail;
    }

    public void setPresenterEmail(String presenterEmail) {
        this.presenterEmail = presenterEmail;
    }
}
