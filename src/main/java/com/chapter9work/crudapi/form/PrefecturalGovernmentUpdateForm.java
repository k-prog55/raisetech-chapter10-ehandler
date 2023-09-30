package com.chapter9work.crudapi.form;

public class PrefecturalGovernmentUpdateForm {

    private String name;
    private String postalCode;

    public PrefecturalGovernmentUpdateForm(String name, String postalCode) {
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
