package com.tripinfo.board.controller;

import com.tripinfo.board.dto.BoardDto;
import com.tripinfo.board.service.BoardServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/board")
@AllArgsConstructor
@Controller
public class BoardController {
    private final BoardServiceImpl boardService;

    @GetMapping("")
    public String goList() {
        return "board/list";
    }

    @GetMapping("/write")
    public String writeForm() {
        return "board/write";
    }

    @PostMapping("/write")
    public String writeArticle(BoardDto boardDto) {
        int result = boardService.insertArticle(boardDto);
        return "redirect:/board";
    }

    @GetMapping("/view")
    public String viewArticle(int articleno, Model model) {
        model.addAttribute("articleno", articleno);
        return "board/view";
    }
    
    @GetMapping("/modify")
    public String viewModify(int articleno, Model model) {
    	return "board/modify";
    }
    
//    @GetMapping("/delete/{articleno}")
//    public String delete(@PathVariable("articleno") int articleno) {
//    	System.out.println("삭제 요청 이리로 들어와버림");
//    	return"";
//    }
}
