package com.tripinfo.member.controller;

import java.util.HashMap;
import java.util.Map;


import com.tripinfo.util.JWTUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tripinfo.member.model.MemberDto;
import com.tripinfo.member.model.service.MemberServiceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Slf4j
public class MemberRestController {

	private final MemberServiceImpl memberService;
	private final JWTUtil jwtUtil;

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

// 회원가입
//	@Transactional
	@PostMapping("/join")
	public ResponseEntity<Map<String, Object>> joinMember(@RequestBody MemberDto member) throws Exception {
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
		log.debug("{}", member);
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

	@ApiOperation(value = "로그인", notes = "아이디와 비밀번호를 이용하여 로그인 처리.")
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) Map<String, String> loginInput) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;

		try {
			MemberDto loginUser = memberService.login(loginInput.get("userId"), loginInput.get("userPwd"));
			if(loginUser != null) {
				String accessToken = jwtUtil.createAccessToken(loginUser.getUserId());
				String refreshToken = jwtUtil.createRefreshToken(loginUser.getUserId());
				log.debug("access token : {}", accessToken);
				log.debug("refresh token : {}", refreshToken);

//				발급받은 refresh token을 DB에 저장.
				memberService.saveRefreshToken(loginUser.getUserId(), refreshToken);

//				JSON으로 token 전달.
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);

				status = HttpStatus.CREATED;
			} else {
				resultMap.put("message", "아이디 또는 패스워드를 확인해주세요.");
				status = HttpStatus.NO_CONTENT;
			}

		} catch (Exception e) {
			log.debug("로그인 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userId}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userId") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userId,
			HttpServletRequest request) {
//		logger.debug("userId : {} ", userId);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
			log.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDto memberDto = memberService.SearchMemberById(userId);
				resultMap.put("userInfo", memberDto);
				status = HttpStatus.OK;
			} catch (Exception e) {
				log.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			log.error("사용 불가능 토큰!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.", response = Map.class)
	@GetMapping("/logout/{userId}")
	public ResponseEntity<?> removeToken(@PathVariable ("userId") @ApiParam(value = "로그아웃할 회원의 아이디.", required = true) String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.deleRefreshToken(userId);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급받는다.", response = Map.class)
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody MemberDto memberDto, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refreshToken");
		log.debug("token : {}, memberDto : {}", token, memberDto);
		if (jwtUtil.checkToken(token)) {
			if (token.equals(memberService.getRefreshToken(memberDto.getUserId()))) {
				String accessToken = jwtUtil.createAccessToken(memberDto.getUserId());
				log.debug("token : {}", accessToken);
				log.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				status = HttpStatus.CREATED;
			}
		} else {
			log.debug("리프레쉬토큰도 사용불가!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
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
