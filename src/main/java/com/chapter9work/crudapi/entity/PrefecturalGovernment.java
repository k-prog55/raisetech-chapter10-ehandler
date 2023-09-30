package com.chapter9work.crudapi.entity;

public class PrefecturalGovernment {
    private int id; //都道府県庁の一意の識別子
    private String name; //都道府県庁の名前
    private String postalCode; //都道府県庁の郵便番号

    //デフォルトコンストラクタ
    public PrefecturalGovernment() {
    }

    //コンストラクタ：都道府県庁の全ての情報を持つ都道府県庁オブジェクトを作成　
    public PrefecturalGovernment(int id, String name, String postalCode) {
        this.id = id;
        this.name = name;
        this.postalCode = postalCode;
    }

    //コンストラクタ：都道府県庁の名前と郵便番号だけを持つ都道府県庁オブジェクトを作成
    public PrefecturalGovernment(String name, String postalCode) {
        this.name = name;
        this.postalCode = postalCode;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getpostalCode() {
        return postalCode;
    }

    public void setpostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
