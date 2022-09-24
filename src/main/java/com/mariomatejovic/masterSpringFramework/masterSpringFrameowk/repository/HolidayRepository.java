package com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.repository;


import com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.model.Holiday;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HolidayRepository {


    @Autowired
    private  JdbcTemplate jdbcTemplate;


    public List<Holiday> findAllHolidays() {
        String sql = "SELECT * FROM HOLIDAYS";

        // BeanPropertyRowMapper moze byt pouzity vtedy ak hodnoty v db maju presne ten isty
        // nazov ako instance variables classi do ktorej sa mapuje....
        var rowMapper = BeanPropertyRowMapper.newInstance(Holiday.class);
        return jdbcTemplate.query(sql, rowMapper);
    }


}
