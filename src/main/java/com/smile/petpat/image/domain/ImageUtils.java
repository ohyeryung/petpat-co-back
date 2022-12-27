package com.smile.petpat.image.domain;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ImageUtils {


    public String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    // file 형식이 잘못된 경우를 확인하기 위해 만들어진 로직이며, 파일 타입과 상관없이 업로드할 수 있게 하기 위해 .의 존재 유무만 판단하였습니다.
    public String getFileExtension(String fileName) {
        try {
            int idx = fileName.lastIndexOf(".");
            String fileExtension = fileName.substring(idx+1);
            // 파일 확장자 체크로직
            switch (fileExtension) {
                case "gif" : break;
                case "png" : break;
                case "jpg" : break;
                case "jpge" : break;
                default: throw new IllegalArgumentException("파일 타입을 확인해주세요");
            }
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("파일 타입을 확인해주세요");
        }

    }
}
