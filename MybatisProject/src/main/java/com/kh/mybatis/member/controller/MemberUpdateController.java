package com.kh.mybatis.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.mybatis.member.model.vo.Member;
import com.kh.mybatis.member.service.MemberService;
import com.kh.mybatis.member.service.MemberServiceImpl;

/**
 * Servlet implementation class MemberUpdateController
 */
@WebServlet("/update.me")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateController() {
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
		
		// * 전달된 데이터 추출 (id, email, gender, phone, address 필수임)
		String userId = request.getParameter("userId");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		Member m = new Member(userId, email, gender, phone, address);
		
		
		// * Service 객체에 전달
		MemberService mService = new MemberServiceImpl();
		int result = mService.updateMember(m);
		
		// * 결과에 따라 
		if ( result > 0 ) {
			// 변경된 회원 정보를 조회하여 session 영역에 저장 (loginUser)
			HttpSession session = request.getSession();
			
			// id, pwd --> session 영역에 저장되어있는 loginUser 키값에 대한 데이터
			Member mem = (Member)session.getAttribute("loginUser");
			
			Member updMem = mService.selectMember(mem);
			session.setAttribute("loginUser", updMem);
			
			
			//	 	수정 성공 시 "수정 성공했습니다." 메세지 저장
			session.setAttribute("alertMsg", "회원정보 수정 성공했습니다.");

			//	 	마이페이지로 url 재요청 --> /mybatis/mypage.me 재요청
			response.sendRedirect( request.getContextPath() + "/mypage.me" );
			
		} else {
			// 		수정 실패 시 "수정 실패했습니다." 메세지 저장
			request.setAttribute("errorMsg", "회원정보 수정 실패했습니다.");

			// 		에러페이지로 응답
			request.getRequestDispatcher("WEB-INF/views/common/errorPage.jsp").forward(request, response);
			
			
		}
		
				
				
	}

}
