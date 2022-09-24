package com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.Controller;


import com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.model.Holiday;
import com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidaysController {

    @Autowired
    private HolidayRepository holidayRepository;

//    RequestParam pouzite
//
//    @GetMapping("/holidays")
//    public String displayHolidays(
//            Model model,
//            @RequestParam(required = false) boolean festival,
//            @RequestParam(required = false) boolean federal)
//    {
//        model.addAttribute("festival", festival);
//        model.addAttribute("federal", federal);

//
//

    @GetMapping("/holidays/{display}")
    public String displayHolidays(@PathVariable String display,Model model){

        if (display != null && display.equals("all")){
            model.addAttribute("federal",true);
            model.addAttribute("festival",true);
        } else if (display != null && display.equals("federal")) {
            model.addAttribute("federal",true);
        } else if (display != null && display.equals("festival")) {
            model.addAttribute("festival",true);

        }


        List<Holiday> holidays = holidayRepository.findAllHolidays();
        Holiday.Type[] types = Holiday.Type.values();
        System.out.println(types);

        for (Holiday.Type type : types) {
            System.out.println("tot je type v forloope "+type.toString());

            model.addAttribute(type.toString(),
                    (holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
        }
        return "holidays.html";
    }
}
