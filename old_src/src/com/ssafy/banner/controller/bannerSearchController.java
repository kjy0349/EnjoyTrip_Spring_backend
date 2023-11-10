package com.ssafy.banner.controller;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.banner.model.bannerDto;
import com.ssafy.banner.model.service.bannerServiceImpl;

@WebServlet("/banner")
public class bannerSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 응답에 대해서도 한글 처리
		response.setContentType("text/html;charset=UTF-8");
		// 사용자의 의도
		String action = request.getParameter("action");
		if (action == null) {
			redirect(request, response, "/index.jsp");
		} else if (action.equals("bannerSearch")) {
			try {
				bannerSearch(request, response);
			} catch (SQLException e) {
				redirect(request, response, "/error/error.jsp");
				e.printStackTrace();
			}
			
		}
	}
	
	
	protected void bannerSearch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		String subject = request.getParameter("bannerSearch");
		List<bannerDto> list = bannerServiceImpl.getimpl().listSights(subject);
		request.setAttribute("list", list);
		forward(request, response, "/user/bannerSearch.jsp");		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}
	
	private void forward(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {

		RequestDispatcher disp = request.getRequestDispatcher(path);
		disp.forward(request, response);
	}
	private void redirect(HttpServletRequest request, HttpServletResponse response, String path)
			throws ServletException, IOException {

		response.sendRedirect(request.getContextPath() + path);
	}

}
