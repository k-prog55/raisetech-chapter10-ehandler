package com.chapter9work.crudapi.form;

public class PrefecturalGovernmentCreateForm {

    private String name;
    private String postalCode;

    public PrefecturalGovernmentCreateForm(String name, String postalCode) {
        this.name = name;
        this.postalCode = postalCode;
    }

    public String getName() {
        return name;
    }

    public String getpostalCode() {
        return postalCode;
    }
}
