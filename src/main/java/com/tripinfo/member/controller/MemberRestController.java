package com.tripinfo.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tripinfo.board.controller.BoardRestController;
import com.tripinfo.board.dto.BoardDto;
import com.tripinfo.board.service.BoardServiceImpl;
import com.tripinfo.member.model.MemberDto;
import com.tripinfo.member.model.service.MemberServiceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class MemberRestController {

	private final MemberServiceImpl memberService;

	@ResponseBody
	@GetMapping("/idcheck/{checkid}")
	public ResponseEntity<Map<String, Object>> idcheck(@PathVariable("checkid") String checkid) throws Exception {
		// TODO : 입력한 아이디의 사용여부 체크 (0 : 사용 X, 1 : 사용 O)
		String result = memberService.idCheck(checkid);
		if (checkid.equals(result)) {
			return handleError(0);
		} else {
			return handleSuccess(1);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody String userId, @RequestBody String password ) throws Exception {
		MemberDto member = memberService.loginMember(userId, password);
		if (member != null) {
			return handleSuccess(member);
		}
		else {
			return handleError(member);
		}
	}

// 회원가입
	@Transactional
	@PostMapping("/join")
	public ResponseEntity<Map<String, Object>> joinMember(@RequestBody MemberDto member) throws Exception {
		System.out.println(member);
		int result = memberService.joinMember(member);
		if (result != 1)
			return handleError(result);
		else
			return handleSuccess(result);
	}

//  회원수정
//수정
	@Transactional
	@PutMapping("/modify")
	public ResponseEntity<Map<String, Object>> modifyMember(@RequestBody MemberDto member) throws Exception {
		System.out.println(member);
		int result = memberService.modify(member);
		if (result != 1)
			return handleError(result);
		else
			return handleSuccess(result);
	}

// 삭제
	@DeleteMapping("/{userId}")
	public ResponseEntity<Map<String, Object>> deleteMember(@PathVariable("userId") String userId) throws Exception {
		int result = memberService.delete(userId);
		if (result != 1)
			return handleError(result);
		else
			return handleSuccess(result);
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
