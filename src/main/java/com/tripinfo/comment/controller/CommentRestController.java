package com.tripinfo.comment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tripinfo.comment.model.CommentDto;
import com.tripinfo.comment.model.service.CommentServiceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/comment")
@AllArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class CommentRestController {

//	- (GET)getCommentByNo(@PathVariable("articleno") int articleNo) : articleNo로 CommentList
//	- (POST)writeComment(@RequestBody CommentDto comment) : Comment 작성
//	- (DELETE)deleteComment(@PathVariable("commentno") int commentNo) : commentNo로 comment삭제 처리
	
	private final CommentServiceImpl commentService;
	
	@GetMapping("/{articleno}")
	public ResponseEntity<Map<String, Object>> getCommentByNo(@PathVariable("articleno") int articleNo){
		
		List<CommentDto> list = commentService.getCommentByNo(articleNo);
        if (list == null) return handleError(list);
        else return handleSuccess(list);
	}
	
	@PostMapping("/write")
	public ResponseEntity<Map<String, Object>> writeComment(@RequestBody CommentDto comment){
		System.out.println("writeComment!!");
		int result = commentService.writeComment(comment);
    	if(result != 1) return handleError(result);
    	else return handleSuccess(result);
		
	}
	
	@DeleteMapping("/{commentno}")
	public ResponseEntity<Map<String, Object>> deleteComment(@PathVariable("commentno") int commentNo){
		int result = commentService.deleteComment(commentNo);
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
