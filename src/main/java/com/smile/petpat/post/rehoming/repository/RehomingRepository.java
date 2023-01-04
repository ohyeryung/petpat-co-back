package com.smile.petpat.post.rehoming.repository;

import com.smile.petpat.post.rehoming.domain.Rehoming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
public interface RehomingRepository extends JpaRepository<Rehoming, Long> {
    @Transactional
    @Modifying
    @Query("update Rehoming set viewCnt = viewCnt + 1 where rehomingId = :postId")
    void updateViews(Long postId);
}
