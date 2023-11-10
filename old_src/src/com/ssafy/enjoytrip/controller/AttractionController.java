package com.ssafy.enjoytrip.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.enjoytrip.model.AttractionInfoDto;
import com.ssafy.enjoytrip.model.SidoDto;
import com.ssafy.enjoytrip.model.service.AttractionServiceImpl;
import com.ssafy.enjoytrip.model.service.AttractionSidoServiceImpl;

@WebServlet("/info")
public class AttractionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String action = request.getParameter("action");
			
			if (action == null) {
				redirect(request, response, "/index.jsp");
			} else if ("attInfo".equals(action)) {
				attInfo(request, response);
			} else if ("sidoInfo".equals(action)) {
				sidoInfo(request, response);
			} else if ("randomPick".equals(action)) {
				randomPick(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void randomPick(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<AttractionInfoDto> list = null;
		try {
			list = AttractionServiceImpl.getAttractionService().randomAttList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(list);
			// 직접 데이터를 전달한다.
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(json);
		}
	}

	protected void sidoInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<SidoDto> list = null;
		try {
			list = AttractionSidoServiceImpl.getInstance().sidoList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(list);
			// 직접 데이터를 전달한다.
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(json);
		}
	}
	
	protected void attInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<AttractionInfoDto> list = null;
		AttractionInfoDto attractionInfoDto = null;
		try {
			attractionInfoDto = new AttractionInfoDto();
			attractionInfoDto.setContentId(Integer.parseInt(request.getParameter("contentId")));
			attractionInfoDto.setSidoCode(Integer.parseInt(request.getParameter("sidoCode")));
			list = AttractionServiceImpl.getAttractionService().attractionList(attractionInfoDto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(list);
			// 직접 데이터를 전달한다.
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write(json);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	protected void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + path);
	}

	protected void template(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
}
