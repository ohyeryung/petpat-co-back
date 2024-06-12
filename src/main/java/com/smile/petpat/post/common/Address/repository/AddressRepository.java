package com.smile.petpat.post.common.Address.repository;

import com.smile.petpat.post.common.Address.domain.Address;
import com.smile.petpat.post.common.Address.repository.querydsl.AddressRepositoryQuerydsl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,Long>, AddressRepositoryQuerydsl {
    Optional<Address> findAddressByProvinceAndCityAndDistrictAndTown(String province, String city, String district, String town);
}
