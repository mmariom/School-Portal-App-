package com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.repository;

import com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RolesRepository extends JpaRepository<Roles,Integer> {


    Roles getByRoleName(String roleName);
}
