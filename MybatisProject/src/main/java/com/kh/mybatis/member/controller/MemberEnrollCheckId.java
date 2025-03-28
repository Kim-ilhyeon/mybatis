package com.kh.mybatis.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.mybatis.member.model.vo.Member;
import com.kh.mybatis.member.service.MemberService;
import com.kh.mybatis.member.service.MemberServiceImpl;

/**
 * Servlet implementation class MemberEnrollCheckId
 */
@WebServlet("/idCheck")
public class MemberEnrollCheckId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEnrollCheckId() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 전달받은 아이디 데이터 추출
		String checkId = request.getParameter("userId");
		
		MemberService mService = new MemberServiceImpl();
		int count = mService.countMemberByUserId(checkId);
		
		// * 결과에 따른 처리
		if (count == 0) {
			// count == 0 -> "YYY" 값을 응답
			response.getWriter().print("YYY");
		} else {
			// count != 0 -> "NNN" 값을 응답
			response.getWriter().print("NNN");			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
