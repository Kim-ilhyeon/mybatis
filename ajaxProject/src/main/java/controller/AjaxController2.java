package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class AjaxController2
 */
@WebServlet("/jqAjax2.do")
public class AjaxController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxController2() {
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
		// request.setCharaterEncoding("UTF-8");
		// => ajax를 사용하여 요청 시 위에 인코딩 설정 생략 가능!!
		
		// * 데이터 추출
		String name = request.getParameter("name");
		String strAge = request.getParameter("age");
		// strAge를 String을 int로 변경
		int age = -1;
		if ( !strAge.isEmpty() ) {
			age = Integer.parseInt(strAge);
		}

		// * 결과 데이터 추출
		String result = "이름: [ " + name + " ], 나이: [ " + (age == -1 ? "알수 없음" : age) + " ]";
		
		/*
		// * 데이터로 응답
		response.setContentType("text/html; charset=UTF-8");
		// => 문서 형식(결과 데이터 형식), 인코딩 설정
		
		response.getWriter().print(result);
		// => 데이터 출력
		*/
		
		// -------------------
		
		/*
		// * 여러 개의 데이터를 응답
		response.setContentType("text/html; charset=UTF-8");
		
		response.getWriter().print(name);
		response.getWriter().print(age);
		// => 하나의 문자열로 합쳐져서 응답 데이터가 전달됨!!
		*/
		
		// --------------------
		
		// * 객체 형태로 응답
		/**
		 * 여러 개의 데이터를 응답하고자 할 때, "JSON"형태로 응답
		 * 		-JSON : JavaScript Object Notation (자바스크립트 객체 표기법)
		 * => ajax 통신할 때 자주 사용되는 형식 중 하나!
		 * 
		 * >> 자바스크립트에서의 배열 객체: [값1, 값2, 값3, ...] => JSONArray
		 * >> 자바스크립트에서의 일반 객체: {키값1:밸류값1, 키값2:밸류값2, ...} => JSONObject
		 * 
		 * * 라이브러리 추가가 필요하다. (json-simple-x.x.x.jar)
		 * => https://code.google.com/archive/p/json-simple/downloads 해당 링크에서 [ json-simple-1.1.1.jar ] 다운로드
		 */
		/*
		// ** 배열 객체(JSONArray)에 담아 응답 --> ArrayList와 유사
		JSONArray jsonArr = new JSONArray();	// [] -> 비어있는 배열
		jsonArr.add(name);						// ["이름"]
		jsonArr.add(age);						// ["이름", 20]
		
//		response.setContentType("text/html; charset=UTF-8");
		// => 응답 데이터가 문자열(String)타입으로 전달됨!!

		response.setContentType("application/json; charset=UTF-8");
		// => 응답 데이터를 JSON(객체) 형태로 전달하고자 할 때에는 문자형식(mime type)을
		// 					JSON 형태(application/json)로 설정해야 함!
		
		response.getWriter().print(jsonArr);
		*/
		
		// * 일반 객체 (JSONObject)에 담아 응답 --> HashMap 과 유사
		JSONObject jsonObj = new JSONObject();		// {} -> 비어있는 객체
		jsonObj.put("name", name);					// {name: "이름"}
		jsonObj.put("age", age);					// {name: "이름", age: 20}
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonObj);
		
		
		
	}

}
