package com.smile.petpat.post.rehoming.repository;

import com.smile.petpat.post.rehoming.domain.Rehoming;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RehomingRepository extends JpaRepository<Rehoming, Long> {
//    Rehoming findByUserId(Long id);
}
