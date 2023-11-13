package com.tripinfo.member.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tripinfo.member.model.MemberDto;
import com.tripinfo.member.model.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class MemberController {
	
	private final MemberService service;
	
//	@GetMapping("/mvjoin")
//	public String joinView() {
//		return "user/join";
//	}
	
//	@PostMapping("/join")
//	public String join(@ModelAttribute MemberDto member, Model model, RedirectAttributes red) {
//		try {
//			service.joinMember(member);
//			red.addFlashAttribute("msg", "회원 가입 성공!");
//			return "redirect:/";
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			red.addFlashAttribute("msg", "회원 가입 실패!");
//			return "redirect:/";
//		}
//	}
	
	@GetMapping("/mvlogin")
	public String loginView() {
		return "user/login";
	}
	
	@PostMapping("/login")
	public String login(String userId, String userPass, HttpSession session, Model model, 
		HttpServletResponse response, HttpServletRequest request, @RequestParam(name = "saveid", required = false) String saveid) {
		try {
			MemberDto member = service.loginMember(userId, userPass);
			if(member != null) {
				session.setAttribute("userinfo", member);
				
				Cookie cookie = new Cookie("ssafy_id", userId);
				cookie.setPath(request.getContextPath());
				if("ok".equals(saveid)) {
					cookie.setMaxAge(60*60*24*365*40);
				} else {
					cookie.setMaxAge(0);
				}
				response.addCookie(cookie);
				return "redirect:/";
			}
			else {
				model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
				return "user/login";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "로그인 중 문제 발생!!!");
			return "error/error";
		}
	}
	
	@GetMapping("/mvusermodify")
	public String userModifyView() {
		return "user/usermodify";
	}
	
	@PostMapping("/usermodify")
	public String userModify(MemberDto member, Model model, RedirectAttributes red, HttpSession session) {
		System.out.println(member.getUserId());
		try {
			service.modify(member);
			red.addFlashAttribute("msg", "회원 정보 수정 성공!!");
			session.setAttribute("userinfo", member);
			return "redirect:/";
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "업데이트 중 문제 발생!!!");
			return "error/error";
		}
	}
	
	@ResponseBody
	@GetMapping("/idcheck")
	public int idcheck(String checkid) {
		// TODO : 입력한 아이디의 사용여부 체크 (0 : 사용 X, 1 : 사용 O)
		System.out.println(checkid);
		log.debug("checkid : {}",checkid);
		try {
			String result = service.idCheck(checkid);
			log.debug("result : {}",result);
			if(checkid.equals(result)) {
				return 1;
			}
			else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 500;
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session, RedirectAttributes red) {
		session.invalidate();
		red.addFlashAttribute("msg", "로그아웃!");
		
		return "redirect:/";
	}
	
	@GetMapping("/delete")
	public String delete(String userId, RedirectAttributes red, HttpSession session) {
		try {
			service.delete(userId);
			red.addFlashAttribute("msg", "회원 탈퇴 처리 하였습니다.");
			session.invalidate();
			return "redirect:/";
		} catch (Exception e) {
			e.printStackTrace();
			red.addAttribute("msg", "탈퇴 중 문제 발생!!!");
			return "error/error";
		}
		
	}

}
