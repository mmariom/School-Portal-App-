package com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.repository;


import com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.model.Holiday;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayRepository  extends CrudRepository<Holiday,String> {
}
