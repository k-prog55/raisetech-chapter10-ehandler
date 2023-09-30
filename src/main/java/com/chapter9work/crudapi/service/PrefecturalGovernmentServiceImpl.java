package com.chapter9work.crudapi.service;

import com.chapter9work.crudapi.entity.PrefecturalGovernment;
import com.chapter9work.crudapi.exception.ResourceNotFoundException;
import com.chapter9work.crudapi.mapper.PrefecturalGovernmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Spring Frameworkによるサービスクラスであることを示す
// PrefecturalGovernmentServiceImplクラス: PrefecturalGovernmentServiceインターフェースを実装し、実際のデータベース操作を行う
public class PrefecturalGovernmentServiceImpl implements PrefecturalGovernmentService {

    private final PrefecturalGovernmentMapper prefecturalGovernmentMapper;  // データベース操作用のMapperクラス

    // コンストラクタ: PrefecturalGovernmentMapperを自動的に注入
    //@Autowired を使うことで、コンストラクタの引数に対するDIを明示的に示す。（Autowiredアノテーションはなくても大丈夫）
    @Autowired
    public PrefecturalGovernmentServiceImpl(PrefecturalGovernmentMapper prefecturalGovernmentMapper) {
        this.prefecturalGovernmentMapper = prefecturalGovernmentMapper;
    }

    // すべての都道府県庁情報を取得するメソッド
    @Override
    public List<PrefecturalGovernment> findAll() {
        return prefecturalGovernmentMapper.findAll(); // Mapperを使用してデータベースから都道府県情報を取得
    }

    // 指定された郵便番号の都道府県庁情報を取得するメソッド(もし都道府県情報が存在しない場合は"resource not found"を返す)
    @Override
    public PrefecturalGovernment findBypostalCode(String postalCode) {
        Optional<PrefecturalGovernment> prefecturalGovernment = this.prefecturalGovernmentMapper.findBypostalCode(postalCode);
        if (prefecturalGovernment.isPresent()) {
            return prefecturalGovernment.get();
        } else {
            throw new ResourceNotFoundException("resource not found");
        }
    }

    @Override
    public PrefecturalGovernment findById(int id) {
        Optional<PrefecturalGovernment> prefecturalGovernment = prefecturalGovernmentMapper.findById(id);
        //指定したidに紐づく値がある場合はその値を返す。値がなければ、"resource not found: id番号"を返す）
        return prefecturalGovernment.orElseThrow(() -> new ResourceNotFoundException("resource not found: " + id));
    }

    @Override
    public PrefecturalGovernment createPrefecturalGovernment(String name, String postalCode) {
        //name と postalCode を使用して新しい PrefecturalGovernment オブジェクトを作成
        PrefecturalGovernment prefecturalGovernmentData = new PrefecturalGovernment(name, postalCode);
        //上記で作成した PrefecturalGovernment オブジェクトをデータベースに挿入
        prefecturalGovernmentMapper.createPrefecturalGovernment(prefecturalGovernmentData);
        //このメソッドを呼び出すと、新しく作成された都道府県庁の情報が返されるようにする
        return prefecturalGovernmentData;
    }

    @Override
    public void updatePrefecturalGovernment(int id, String name, String postalCode) {
        //MapperのfindByIdメソッドを持ってきている。idが見つからない場合、orElseThrow メソッドが呼び出されて例外（ResourceNotFoundException）をスローする。
        PrefecturalGovernment prefecturalGovernmentUpdate = prefecturalGovernmentMapper.findById(id).orElseThrow(() -> new ResourceNotFoundException("resource not found"));
        //findByIdメソッドで呼び出された都道府県庁情報を更新
        prefecturalGovernmentUpdate.setName(name);
        prefecturalGovernmentUpdate.setpostalCode(postalCode);
        prefecturalGovernmentMapper.updatePrefecturalGovernment(prefecturalGovernmentUpdate);
    }

    @Override
    public void deletePrefecturalGovernment(int id) {
        prefecturalGovernmentMapper.deletePrefecturalGovernment(id);
    }

}
