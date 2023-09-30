package com.chapter9work.crudapi.controller;

import com.chapter9work.crudapi.entity.PrefecturalGovernment;
import com.chapter9work.crudapi.exception.ResourceNotFoundException;
import com.chapter9work.crudapi.form.PrefecturalGovernmentCreateForm;
import com.chapter9work.crudapi.form.PrefecturalGovernmentUpdateForm;
import com.chapter9work.crudapi.service.PrefecturalGovernmentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@Validated
@RestController
public class PrefecturalGovernmentController {

    //Controller -> Service -> Mapperの流れで処理を行う

    // PrefecturalGovernmentServiceという都道府県庁データに関するビジネスロジックを提供するサービスを注入
    private final PrefecturalGovernmentService prefecturalGovernmentService;

    // コンストラクタを使用してPrefecturalGovernmentServiceをインジェクト
    @Autowired
    public PrefecturalGovernmentController(PrefecturalGovernmentService prefecturalGovernmentService) {
        this.prefecturalGovernmentService = prefecturalGovernmentService;
    }

    //localhost:8080/names にアクセスするとDBに登録されている都道府県庁の名前をレスポンスとして返す。
    @GetMapping("/names")
    public List<String> names() {
        // prefecturalGovernmentServiceを使用して、すべての都道府県庁情報を取得
        List<PrefecturalGovernment> prefecturalGovernments = prefecturalGovernmentService.findAll();
        // 都道府県庁情報から都道府県庁の名前だけを抽出してリストにする
        List<String> response = prefecturalGovernments.stream().map(PrefecturalGovernment::getName).toList();
        return response;
    }

    //localhost:8080/prefecturalGovernments?postalCode=060-8588 にアクセスするとDBに登録されている都道府県庁情報から
    //郵便番号が060-8588と一致する都道府県情報を取得する
    @GetMapping("/prefecturalGovernments")
    public String getprefecturalGovernmentsBypostalCode(@RequestParam("postalCode") String postalCode) {
        // prefecturalGovernmentServiceを使用して、指定された郵便番号にある都道府県庁を取得
        PrefecturalGovernment prefecturalGovernment = prefecturalGovernmentService.findBypostalCode(postalCode);
        return prefecturalGovernment.getName();
    }

    //例外処理を実装
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoResourceFound(
            ResourceNotFoundException e, HttpServletRequest request) {
        Map<String, String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.NOT_FOUND.value()),
                "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity(body, HttpStatus.NOT_FOUND);
    }


    @GetMapping("/prefecturalGovernments/{id}")
    public ResponseEntity<PrefecturalGovernmentResponse> findById(@PathVariable("id") int id) {
        PrefecturalGovernment prefecturalGovernment = prefecturalGovernmentService.findById(id);
        PrefecturalGovernmentResponse response = new PrefecturalGovernmentResponse(prefecturalGovernment);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/prefecturalGovernments")
    public ResponseEntity<PrefecturalGovernment> createprefecturalGovernment(@RequestBody PrefecturalGovernmentCreateForm prefecturalGovernmentCreateForm, UriComponentsBuilder uriBuilder) {
        PrefecturalGovernment prefecturalGovernment = prefecturalGovernmentService.createPrefecturalGovernment(prefecturalGovernmentCreateForm.getName(), prefecturalGovernmentCreateForm.getpostalCode());
        URI url = uriBuilder
                .path("/prefecturalGovernments/" + prefecturalGovernment.getId())
                .build()
                .toUri();
        return ResponseEntity.created(url).body(prefecturalGovernment);
    }

    @PatchMapping("prefecturalGovernments/{id}")
    public ResponseEntity<Map<String, String>> updatePrefecturalGovernment(@PathVariable int id, @RequestBody PrefecturalGovernmentUpdateForm prefecturalGovernmentUpdateForm) throws Exception {
        prefecturalGovernmentService.updatePrefecturalGovernment(id, prefecturalGovernmentUpdateForm.getName(), prefecturalGovernmentUpdateForm.getpostalCode());
        return ResponseEntity.ok(Map.of("message", "successfully updated"));
    }

    @DeleteMapping("prefecturalGovernments/{id}")
    public ResponseEntity<Map<String, String>> deletePrefecturalGovernment(@PathVariable int id) {
        prefecturalGovernmentService.deletePrefecturalGovernment(id);
        return ResponseEntity.ok(Map.of("message", "successfully deleted"));
    }
}
