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
 * Servlet implementation class MemberDeleteController
 */
@WebServlet("/delete.me")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteController() {
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
		request.setCharacterEncoding("UTF-8");	// 데이터 추출 전 인코딩 처리!!
		
		// * 전달된 데이터 추출(id, pwd)
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		// * Service 객체에 전달
		MemberService mService = new MemberServiceImpl();
		int result = mService.deleteMember(userId, userPwd);
		
		// * 결과에 따라
		if ( result > 0 ) {
			// 탈퇴 성공 시 
			HttpSession session = request.getSession();
			
			session.invalidate();
			// => session 객체 자체를 아예 삭제함
			// => 그 이후 session 위에 객체를 사용할 수 없다.!!

			// "회원이 삭제되었습니다. 그동안 감사합니다." 메세지 저장
			request.getSession().setAttribute("alertMsg", "회원이 삭제되었습니다. 그동안 감사합니다.");
			
			// session에 저장되어있는 loginUser를 삭제
//			session.removeAttribute("loginUser");
			// => 위에 invalidate()를 사용하거나 removeAttribute("loginUser")를 사용해야 한다.			

			// 메인 페이지로 url 재요청 -> 로그인 정보가 제거되어야 할 것임!!
			response.sendRedirect( request.getContextPath() );	// => /mybatis 요청
			
		} else {
			// 삭제 실패 시 "삭제가 정상적으로 진행되지 않았습니다." 메세지 저장
			request.setAttribute("errorMsg", "삭제가 정상적으로 진행되지 않았습니다.");
			
			// 에러페이지로 응답
			request.getRequestDispatcher("WEB-INF/viewscommon/errorPage.jsp").forward(request, response);
		}
		
		
	}

}
