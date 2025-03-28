package com.kh.mybatis.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mybatis.member.service.MemberService;
import com.kh.mybatis.member.service.MemberServiceImpl;

/**
 * Servlet implementation class MemberPwdUpdateController
 */
@WebServlet("/updatePwd.me")
public class MemberPwdUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberPwdUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 전달받은 값(데이터) 추출 => userId, userPwd, newPwd
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String newPwd = request.getParameter("newPwd");
		
		// Service 객체에 전달
		MemberService mService = new MemberServiceImpl();
		int result = mService.updatePassword(userId, userPwd, newPwd);
		
		if ( result > 0 ) {
			// 비밀번호 변경 성공 시
			HttpSession session = request.getSession();
			// "비밀번호 변경 성공했습니다." 메세지 저장
			session.setAttribute("alertMsg", "비밀번호가 정상적으로 변경되었습니다.");
			
			// 로그아웃 처리 --> Session 영역의 loginUser 삭제(제거)
			session.removeAttribute("loginUser");
			
			// 메인 페이지로 url 재요청 --> /mybatis
			response.sendRedirect( request.getContextPath() );
		} else {
			// 비밀번호 변경 실패 시
			// "비밀번호 병경 실패했습니다." 메세지 저장
			request.setAttribute("errorMsg", "비밀번호 변경에 실패하였습니다.");
			// common/errorPage.jsp 페이지 응답(포워딩)
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
		
		}
		
		
	}

}
