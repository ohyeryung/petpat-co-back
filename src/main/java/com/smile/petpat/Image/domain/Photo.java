package com.smile.petpat.image.domain;

import com.smile.petpat.post.category.domain.PostType;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "TB_IMAGE")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "original_file_name")
    private String originalFileName;

    @Column(name = "fake_file_name")
    private String fakeFileName;

    @Column(name = "post_type")
    @Enumerated(EnumType.STRING)
    private PostType postType;

    @Column(name = "post_id")
    private Long postId;

    public Photo() {
    }

    public Photo(Long imageId, String filePath, String originalFileName, String fakeFileName, PostType postType, Long postId) {
        this.imageId = imageId;
        this.filePath = filePath;
        this.originalFileName = originalFileName;
        this.fakeFileName = fakeFileName;
        this.postType = postType;
        this.postId = postId;
    }

    public Photo(String originalFilename, String fakeFileName, String filePath) {

    }
}
