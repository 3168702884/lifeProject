package com.example.demo.controller;

import com.example.demo.Dao.SchoolMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchoolController {
    @Autowired
    private SchoolMapper schoolMapper;

    /**
     *
     */
    @PostMapping("/createCol")
    public String createCol(@RequestParam("universityname") String universityname,@RequestParam("address") String address,
                            @RequestParam("schoolcode") String schoolcode){
//        System.out.println(universityname+address+schoolcode);
        String matter="success";
        try{
            schoolMapper.schoolInsert(universityname,address,schoolcode);
        }catch (Exception e){
            e.printStackTrace();
            matter = "false";
            return matter;
        }
        return matter;
    }
}
