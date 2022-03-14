package com.miage.vepick.controller;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.miage.vepick.repository.StationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController{

    @Autowired
    private StationRepository sr;

    private static final String[] ADRESSES = new String[]{"grenoble","lyon","marrakech"};

    @ResponseBody
    @RequestMapping("/")
    public String home(){
        String html = "";
        html += "<ul>";
        html += " <li><a href='/testInsert'>Test Insert</a></li>";
        html += " <li><a href='/showAllStations'>Show All Employee</a></li>";
        html += " <li><a href='/deleteAllEmployee'>Delete All Employee</a></li>";
        html += "</ul>";
        return html;
    }

    @ResponseBody
    @RequestMapping("/testInsert")
    public String testInsert(){
        
    }

}