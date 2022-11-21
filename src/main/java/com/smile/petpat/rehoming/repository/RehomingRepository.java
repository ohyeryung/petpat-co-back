package com.smile.petpat.rehoming.repository;

import com.smile.petpat.rehoming.domain.Rehoming;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RehomingRepository extends JpaRepository<Rehoming, Long> {
//    Rehoming findByUserId(Long id);
}
