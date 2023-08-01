package com.smile.petpat.post.common.likes.repository;

import com.smile.petpat.post.category.domain.PostType;
import com.smile.petpat.post.common.likes.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    @Query(value = "SELECT * FROM TB_LIKES a WHERE a.post_id =:postId And a.post_type =:postType AND a.user_id =:userId",nativeQuery = true)
    Likes findUserLikeQuery(@Param("postId") Long postId, @Param("postType") String postType, @Param("userId") Long userId);

    @Modifying
    @Query(value = "DELETE FROM TB_LIKES WHERE post_id =:postId AND post_type =:postType AND user_id =:userId",nativeQuery = true)
    void deleteByUser_UserIdAndPost_PostIdAndPostType(@Param("postId") Long postId, @Param("postType") String postType, @Param("userId") Long userId);

    List<Likes> findByPostIdAndPostType(Long postId, PostType postType);

}
