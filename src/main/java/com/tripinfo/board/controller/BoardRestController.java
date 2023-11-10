package com.tripinfo.board.controller;

import com.tripinfo.board.dto.BoardDto;
import com.tripinfo.board.service.BoardServiceImpl;
import com.tripinfo.member.model.MemberDto;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/board")
@AllArgsConstructor
@Slf4j
public class BoardRestController {

    private final BoardServiceImpl boardService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getList() {
        List<BoardDto> list = boardService.getAllArticles();
        if (list == null) return handleError(list);
        else return handleSuccess(list);
    }

    @GetMapping("/list/{articleno}")
    public ResponseEntity<Map<String, Object>> getArticle(@PathVariable("articleno") int articleno) {
//    	System.out.println("getArticle로 "+articleno+" 넘어왔음");
        BoardDto boardDto = boardService.getArticleByNumber(articleno);
        if (boardDto == null) return handleError(boardDto);
        else {
        	return handleSuccess(boardDto);
        }
    }
//  수정
    @PostMapping("")
    public ResponseEntity<Map<String, Object>> modifyArticle(@RequestBody BoardDto article) {
    	System.out.println(article);
    	int result = boardService.modifyArticle(article);
    	if(result != 1) return handleError(result);
    	else return handleSuccess(result);
    }
    
    @DeleteMapping("/{articleno}")
    public ResponseEntity<Map<String, Object>> deleteArticle(@PathVariable("articleno") int articleno){
    	int result = boardService.deleteArticle(articleno);
    	if(result != 1) return handleError(result);
    	else return handleSuccess(result);
    }
    
    private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
        Map<String, Object> result = new HashMap<>();

        result.put("success", true);
        result.put("data", data);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }

    private ResponseEntity<Map<String, Object>> handleError(Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", false);
        result.put("data", data);
        return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
    }
}
