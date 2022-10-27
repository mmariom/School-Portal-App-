package com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.repository;

import com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
