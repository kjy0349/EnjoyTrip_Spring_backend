package com.tripinfo.tripboard.controller;

import com.tripinfo.tripboard.service.TripBoardServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tboard")
@AllArgsConstructor
@Controller
public class TripBoardController {
    private final TripBoardServiceImpl tripBoardService;

    @GetMapping("/view")
    public String viewArticle(int articleno, Model model) {
        model.addAttribute("articleno", articleno);
        return "board/view";
    }
    
    @GetMapping("/modify")
    public String viewModify(int articleno, Model model) {
    	return "board/modify";
    }
}
