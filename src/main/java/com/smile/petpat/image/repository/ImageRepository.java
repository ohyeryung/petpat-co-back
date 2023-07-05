package com.smile.petpat.image.repository;

import com.smile.petpat.image.domain.Image;
import com.smile.petpat.post.category.domain.PostType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Long> {
    List<Image> findAllByPostIdAndPostTypeOrderByPostId(Long postId, PostType postType);
    void deleteByPostIdAndPostType(Long postId, PostType postType);
    Image findTop1ByPostIdAndPostTypeOrderByImageIdAsc(Long postId, PostType postType);
}
