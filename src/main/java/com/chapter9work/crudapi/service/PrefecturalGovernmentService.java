package com.chapter9work.crudapi.service;

import com.chapter9work.crudapi.entity.PrefecturalGovernment;

import java.util.List;

// PrefecturalGovernmentServiceインターフェース: 都道府県庁関連のデータを操作するためのメソッドを定義
public interface PrefecturalGovernmentService {

    //以下のメソッドについて、「戻り値、メソッド名、引数」はServiceImplクラスと同期させる必要あり！（Mapperとは必ずしも一致しない）

    //データベース内のすべての都道府県庁の情報を取得するメソッド
    List<PrefecturalGovernment> findAll();

    //指定された郵便番号の都道府県庁の情報を取得するメソッド
    PrefecturalGovernment findBypostalCode(String postalCode);

    PrefecturalGovernment findById(int id);

    PrefecturalGovernment createPrefecturalGovernment(String name, String postalCode);

    void updatePrefecturalGovernment(int id, String name, String postalCode);

    void deletePrefecturalGovernment(int id);

}
