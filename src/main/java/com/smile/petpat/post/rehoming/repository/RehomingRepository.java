package com.smile.petpat.post.rehoming.repository;

import com.smile.petpat.post.rehoming.domain.Rehoming;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RehomingRepository extends JpaRepository<Rehoming, Long> {
//    Rehoming findByUserId(Long id);
}
