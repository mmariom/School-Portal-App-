package com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.repository;


import com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.model.SmartClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<SmartClass,Integer> {

}
