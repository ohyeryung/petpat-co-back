package com.smile.petpat.image.util;

import com.smile.petpat.common.exception.CustomException;
import com.smile.petpat.common.response.ErrorCode;
import com.smile.petpat.image.domain.Image;
import com.smile.petpat.image.domain.ImagePriority;
import com.smile.petpat.post.category.domain.PostType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;


@Component
@RequiredArgsConstructor
public class ImageUtils {
    private static final Set<String> VALID_EXTENSIONS = new HashSet<>(Arrays.asList("gif", "png", "jpg", "jpeg",
                                                                                    "GIF", "PNG", "JPG", "JPEG"));
    private  final S3Uploader s3Uploader;

    /* 파일명 난수화 */
    public String generateRandomFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    /* 파일 확장명 체크 로직 */
    public String getFileExtension(String fileName) {
        try {
            int idx = fileName.lastIndexOf(".");
            if (idx == -1 || idx == fileName.length() - 1) {
                throw new CustomException(ErrorCode.WRONG_TYPE_IMAGE);
            }

            String extension = fileName.substring(idx + 1);

            if (!VALID_EXTENSIONS.contains(extension)) {
                throw new CustomException(ErrorCode.WRONG_TYPE_IMAGE);
            }
            return "." + extension;
        }
        catch (IndexOutOfBoundsException e) {
            throw new CustomException(ErrorCode.WRONG_TYPE_IMAGE);
        }
    }
    /* 이미지 우선순위 부여 로직 & 사진 최대 갯수 체크*/
    public static List<Image> setPriority(List<Image> images){
        if(images.size()>5) throw new IllegalArgumentException("사진은 최대 5장까지 저장 가능합니다");

        for(int i=0; i<images.size(); i++){
            ImagePriority priority = ImagePriority.fromIndexToPriority(i+1);
            images.get(i).setImagePriority(priority);
        }

        return images;
    }
    /*MultipartFile 을 Image Entity 형태로 변경*/
    Image toImageEntity(Long postId, PostType postType, MultipartFile multipartFile) {
        String fakeFileName = generateRandomFileName(multipartFile.getOriginalFilename());
        String originalFileName = multipartFile.getOriginalFilename();
        String filePath = s3Uploader.uploadFile(multipartFile, fakeFileName);

        return new Image(originalFileName, fakeFileName,  filePath, postId, postType);
    }


}
