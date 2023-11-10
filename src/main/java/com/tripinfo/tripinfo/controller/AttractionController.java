package com.tripinfo.tripinfo.controller;

import com.tripinfo.tripinfo.dto.SidoDto;
import com.tripinfo.tripinfo.service.AttractionServiceImpl;
import com.tripinfo.tripinfo.service.AttractionSidoServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/view")
public class AttractionController {

    @GetMapping("")
    public String goHome() {
        return "index";
    }

    @GetMapping("attinfo")
    public String goAttInfo() {
        return "trip/place";
    }
}
