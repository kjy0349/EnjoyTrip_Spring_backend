package com.tripinfo.tripcomment.controller;

import com.tripinfo.tripcomment.model.TripCommentDto;
import com.tripinfo.tripcomment.model.service.TripCommentServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tcomment")
@AllArgsConstructor
@Slf4j
public class TripCommentRestController {

//	- (GET)getCommentByNo(@PathVariable("articleno") int articleNo) : articleNo로 CommentList
//	- (POST)writeComment(@RequestBody CommentDto comment) : Comment 작성
//	- (DELETE)deleteComment(@PathVariable("commentno") int commentNo) : commentNo로 comment삭제 처리
	
	private final TripCommentServiceImpl commentService;
	
	@GetMapping("/{articleno}")
	public ResponseEntity<Map<String, Object>> getCommentByNo(@PathVariable("articleno") int articleNo){
		
		List<TripCommentDto> list = commentService.getCommentByNo(articleNo);
        if (list == null) return handleError(list);
        else return handleSuccess(list);
	}
	
	@PostMapping("/write")
	public ResponseEntity<Map<String, Object>> writeComment(@RequestBody TripCommentDto comment){
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

	@PutMapping("/{commentno}")
	public ResponseEntity<Map<String, Object>> updateSelectedStatus(@PathVariable("commentno") int tripCommentNo){
		int result = commentService.updateSelectedStatus(tripCommentNo);
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
