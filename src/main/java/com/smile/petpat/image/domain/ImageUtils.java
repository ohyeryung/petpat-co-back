package com.smile.petpat.image.domain;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ImageUtils {

    /* 파일명 난수화 */
    public String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

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
