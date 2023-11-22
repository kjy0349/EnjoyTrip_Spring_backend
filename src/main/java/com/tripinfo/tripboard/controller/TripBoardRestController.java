package com.tripinfo.tripboard.controller;

import com.tripinfo.tripboard.dto.TripBoardDto;
import com.tripinfo.tripboard.dto.TripRouteDto;
import com.tripinfo.tripboard.service.TripBoardServiceImpl;
import io.swagger.models.Response;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tboard")
@AllArgsConstructor
@Slf4j
public class TripBoardRestController {

    private final TripBoardServiceImpl tripBoardService;

    @GetMapping("/route/{userId}")
    public ResponseEntity<Map<String, Object>> getTripRouteByUserId(@PathVariable("userId") String userId) {
        List<TripRouteDto> list = tripBoardService.getTripRouteByUserId(userId);
        if (list != null) return handleSuccess(list);
        else return handleError(null);
    }
    
    @GetMapping("/userinfo/{userId}")
    public ResponseEntity<Map<String, Object>> getArticleUserInfo(@PathVariable("userId") String userId) {
//        List<TripRouteDto> list = tripBoardService.getArticleUserInfo(userId);
//        if (list != null) return handleSuccess(list);
//        else return handleError(null);
    	return null;
    }
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getTripList(@RequestParam int pgno, @RequestParam int pageSize) {
    	System.out.println("pgno : " + pgno);
    	System.out.println("pageSize : " + pageSize);
        List<TripBoardDto> list = tripBoardService.getAllTripArticles(pgno, pageSize);
        if (list != null) return handleSuccess(list);
        else return handleError(null);
    }
    
    @GetMapping("/total")
    public ResponseEntity<Map<String, Object>> getTripTotal() {
    	int total = tripBoardService.getTripTotal();
    	if (total != 0) return handleSuccess(total);
    	else return handleError(null);
    }

    @GetMapping("/list/no/{articleno}")
    public ResponseEntity<Map<String, Object>> getTripArticleByNo(@PathVariable("articleno") int articleno) {
//    	System.out.println("getArticle로 "+articleno+" 넘어왔음");
        TripBoardDto tripBoardDto = tripBoardService.getTripArticleByNumber(articleno);
        if (tripBoardDto == null) return handleError(null);
        else {
        	return handleSuccess(tripBoardDto);
        }
    }

    @GetMapping("/list/subject/{subject}")
    public ResponseEntity<Map<String, Object>> getArticleBySubject(@PathVariable("subject") String subject){
        List<TripBoardDto> result = tripBoardService.getTripArticlesBySubject(subject);
        if(result != null) return handleError(result);
        else return handleSuccess(null);
    }
//  수정
    @PutMapping("")
    public ResponseEntity<Map<String, Object>> modifyArticle(@RequestBody TripBoardDto article) {
    	System.out.println(article);
    	int result = tripBoardService.modifyTripArticle(article);
    	if(result != 1) return handleError(result);
    	else return handleSuccess(result);
    }
    
//    글 작성
    @PostMapping("/write")
    public ResponseEntity<Map<String, Object>> writeArticle(@RequestBody TripBoardDto article) {
    	System.out.println(article);
    	int result = tripBoardService.insertTripArticle(article);
    	if(result != 1) return handleError(result);
    	else return handleSuccess(result);
    }
    
    @DeleteMapping("/{articleno}")
    public ResponseEntity<Map<String, Object>> deleteArticle(@PathVariable("articleno") int articleno){
    	int result = tripBoardService.deleteTripArticle(articleno);
    	if(result != 1) return handleError(result);
    	else return handleSuccess(result);
    }

    @GetMapping("/plan/{planId}")
    public ResponseEntity<Map<String, Object>> getRouteDetails(@PathVariable("planId") int planId){
        int result = tripBoardService.getRouteDetails(planId);
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
