package com.smile.petpat.image.domain;

import com.smile.petpat.common.exception.CustomException;
import com.smile.petpat.common.response.ErrorCode;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

@Component
public class ImageUtils {

    /* 파일명 난수화 */
    public String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    /* 파일 확장명 체크 로직 */
    public String getFileExtension(String fileName) throws CustomException {
        try {
            int idx = fileName.lastIndexOf(".");
            if (idx == -1 || idx == fileName.length() - 1) {
                throw new CustomException(ErrorCode.WRONG_TYPE_IMAGE);
            }
            String fileExtension = fileName.substring(idx + 1).toLowerCase();

            Set<String> validExtensions = Set.of("gif", "png", "jpg", "jpeg");
            if (!validExtensions.contains(fileExtension)) {
                throw new CustomException(ErrorCode.WRONG_TYPE_IMAGE);
            }
            return "." + fileExtension;
        } catch (IndexOutOfBoundsException e) {
            throw new CustomException(ErrorCode.WRONG_TYPE_IMAGE);
        }
    }
}
