package com.sesacthon.infra.s3.controller;

import com.sesacthon.infra.s3.dto.S3Dto;
import com.sesacthon.infra.s3.service.S3Uploader;
import com.sesacthon.global.response.DataResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name="S3", description = "S3 관련 api")
@RestController
@RequiredArgsConstructor
public class S3Controller {

  private final S3Uploader s3Uploader;

  @Operation(summary = "이미지 업로드", description = "촬영 사진을 S3에 업로드하여 이미지 url로 변환할 수 있습니다.")
  @PostMapping(value = "/api/v1/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<DataResponse<S3Dto>> uploadEventImg (
      @RequestPart("img") MultipartFile multipartFile) {
    S3Dto savedImgUrl = s3Uploader.uploadFile(multipartFile);
    return new ResponseEntity<>(
        DataResponse.of(HttpStatus.CREATED, "촬영 이미지 업로드 성공", savedImgUrl), HttpStatus.CREATED);
  }

}
