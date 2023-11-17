package com.tripinfo.board.controller;

import com.tripinfo.board.dto.BoardDto;
import com.tripinfo.board.service.BoardServiceImpl;
import com.tripinfo.member.model.MemberDto;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/board")
@AllArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class BoardRestController {

    private final BoardServiceImpl boardService;

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getList(@RequestParam int pgno, @RequestParam int pageSize) {
    	System.out.println("pgno : " + pgno);
    	System.out.println("pageSize : " + pageSize);
        List<BoardDto> list = boardService.getAllArticles(pgno, pageSize);
        if (list == null) return handleError(list);
        else return handleSuccess(list);
    }
    
    @GetMapping("/total")
    public ResponseEntity<Map<String, Object>> getTotal() {
    	int total = boardService.getTotal();
    	if (total != 0) return handleSuccess(total);
    	else return handleError(total);
    }

    @GetMapping("/list/no/{articleno}")
    public ResponseEntity<Map<String, Object>> getArticleByNo(@PathVariable("articleno") int articleno) {
//    	System.out.println("getArticle로 "+articleno+" 넘어왔음");
        BoardDto boardDto = boardService.getArticleByNumber(articleno);
        if (boardDto == null) return handleError(boardDto);
        else {
        	return handleSuccess(boardDto);
        }
    }

    @GetMapping("/list/subject/{subject}")
    public ResponseEntity<Map<String, Object>> getArticleBySubject(@PathVariable("subject") String subject){
        List<BoardDto> result = boardService.getArticlesBySubject(subject);
        if(result != null) return handleError(result);
        else return handleSuccess(result);
    }
//  수정
    @PutMapping("")
    public ResponseEntity<Map<String, Object>> modifyArticle(@RequestBody BoardDto article) {
    	System.out.println(article);
    	int result = boardService.modifyArticle(article);
    	if(result != 1) return handleError(result);
    	else return handleSuccess(result);
    }
    
//    글 작성
    @PostMapping("/write")
    public ResponseEntity<Map<String, Object>> writeArticle(@RequestBody BoardDto article) {
    	System.out.println(article);
    	int result = boardService.insertArticle(article);
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
