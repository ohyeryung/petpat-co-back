package com.smile.petpat.post.rehoming.repository;

import com.smile.petpat.post.rehoming.domain.Rehoming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface RehomingRepository extends JpaRepository<Rehoming, Long> {
//    Rehoming findByUserId(Long id);
    @Transactional
    @Modifying
    @Query("update Rehoming r set r.viewCnt = r.viewCnt + 1 where r.rehomingId = :id")
    int updateViews(Long id);
}
