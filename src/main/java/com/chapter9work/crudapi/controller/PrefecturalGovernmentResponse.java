package com.chapter9work.crudapi.controller;

import com.chapter9work.crudapi.entity.PrefecturalGovernment;

public class PrefecturalGovernmentResponse {

    private String name;
    private String postalCode;

    public PrefecturalGovernmentResponse(PrefecturalGovernment prefecturalGovernment) {
        this.name = prefecturalGovernment.getName();
        this.postalCode = prefecturalGovernment.getpostalCode();
    }

    public String getName() {
        return name;
    }

    public String getpostalCode() {
        return postalCode;
    }
}
