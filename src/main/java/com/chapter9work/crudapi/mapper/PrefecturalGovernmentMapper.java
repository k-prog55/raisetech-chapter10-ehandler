package com.chapter9work.crudapi.mapper;

import com.chapter9work.crudapi.entity.PrefecturalGovernment;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PrefecturalGovernmentMapper {

    //データベース内の全ての都道府県庁データを取得するためのクエリ（CRUD処理の"Read（読み取り）"）
    @Select("SELECT * FROM prefecturalGovernments")
    List<PrefecturalGovernment> findAll();  // 都道府県庁のリストを取得するfindAllメソッド。このメソッドを使うと、上記のSQLクエリが作動する

    //指定された郵便番号の都道府県庁データを取得するためのクエリ（CRUD処理の"Read（読み取り）"）
    @Select("SELECT * FROM prefecturalGovernments WHERE postalCode = #{postalCode}")
    Optional<PrefecturalGovernment> findBypostalCode(@Param("postalCode") String postalCode);  // 郵便番号を指定して都道府県庁のリストを取得するfindBypostalCodeメソッド
    // @Paramアノテーションを使用して、引数の名前（"postalCode"）とSQLクエリ内のプレースホルダ（#{postalCode}）を関連付ける。
    // 引数の名前（"postalCode"）＝　@Paramアノテーション内のpostalCode　=　SQLクエリ内のプレースホルダ（#{postalCode}）となるようにコードを書く。

    //指定されたidの都道府県庁データを取得するためのクエリ（CRUD処理の"Read（読み取り）"）
    @Select("SELECT * FROM prefecturalGovernments WHERE id = #{id}")
    Optional<PrefecturalGovernment> findById(int id);

    //都道府県庁の名前、郵便番号を登録する（CRUD処理の"Create（登録）"）
    @Insert("INSERT INTO prefecturalGovernments (name, postalCode) VALUES (#{name}, #{postalCode})")
    //@Options=MyBatisのオプション。「useGeneratedKeys = true」は「データベースが自動生成した主キー値を使用」の意味。
    // 「keyProperty = "id"」は生成された主キー値を「PrefecturalGovernment」オブジェクトの"id"と紐づけている。
    @Options(useGeneratedKeys = true, keyProperty = "id")
    //「void」＝登録なので戻り値なし。引数は挿入したいname,postalCode情報を持つPrefecturalGovernmentオブジェクト。
    void createPrefecturalGovernment(PrefecturalGovernment prefecturalGovernment);

    //指定したidで登録されている都道府県庁テーブルの名前、郵便番号を更新する（CRUD処理の"Update（更新）"）
    @Update("UPDATE prefecturalGovernments SET name = #{name}, postalCode = #{postalCode} WHERE id = #{id}")
    void updatePrefecturalGovernment(PrefecturalGovernment prefecturalGovernment);

    //指定したidで登録されている都道府県庁情報を削除する（CRUD処理の"Delete（削除）"）
    @Delete("DELETE FROM prefecturalGovernments WHERE id = #{id}")
    //引数はidのみなので、引数のところにはint idを記載（引数が複数の場合、PrefecturalGovernmentオブジェクトにする）
    void deletePrefecturalGovernment(int id);

}
