package com.smile.petpat.image.domain;

import com.smile.petpat.post.category.domain.PostType;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "TB_IMAGE")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IMAGE_ID")
    private Long imageId;

    @Column(name = "FILE_PATH")
    private String filePath;

    @Column(name = "ORIGINAL_FILE_NAME")
    private String originalFileName;

    @Column(name = "FAKE_FILE_NAME")
    private String fakeFileName;

    @Column(name = "POST_TYPE")
    @Enumerated(EnumType.STRING)
    private PostType postType;

    @Column(name = "POST_ID")
    private Long postId;

    public Image() {
    }

    public Image(Long imageId, String filePath, String originalFileName, String fakeFileName, PostType postType, Long postId) {
        this.imageId = imageId;
        this.filePath = filePath;
        this.originalFileName = originalFileName;
        this.fakeFileName = fakeFileName;
        this.postType = postType;
        this.postId = postId;
    }

    public Image(String originalFilename, String fakeFileName, String filePath) {
        this.originalFileName = originalFilename;
        this.fakeFileName = fakeFileName;
        this.filePath = filePath;
    }
}
