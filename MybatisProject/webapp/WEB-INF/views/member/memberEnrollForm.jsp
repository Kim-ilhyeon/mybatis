<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mybatis project</title>

<!-- 부트스트랩 CDN -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<!-------------------->
<!-- jQuery CDN -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js" 
		integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" 
		crossorigin="anonymous"></script>
<style>
	#mem-enroll-area {
		margin: auto;
	}
</style>

</head>
<body>
	<tr%-- menubar.jsp 포함 --%>
	<jsp:include page="../common/menubar.jsp"/>
	회원가입 페이지##
	
	<div class="outer">
		<br>

		<h2 align="center">회원가입</h2>

		<form action="insert.me" method="post" id="mem-enroll-area">
			<table align="center">
				<tr>
					<td>* 아이디</td>
					<td>
						<input type="text" class="form-control" name="userId" maxlength="30" required >
					</td>
					<td>
						<input type="button" class="btn btn-sm btn-outline-dark" value="중복체크" onclick="idCheck();">
					</td>
				</tr>
				<tr>
					<td>* 비밀번호</td>
					<td>
						<input type="password" class="form-control" name="userPwd" required>
					</td>
				</tr>
				<tr>
					<td>* 비밀번호 확인</td>
					<td>
						<input type="password" class="form-control" id="userPwdCheck" required>
					</td>
				</tr>
				<tr>
					<td>* 이름</td>
					<td>
						<input type="text" class="form-control" name="userName" >
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;이메일</td>
					<td>
						<input type="email" class="form-control" name="email">
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;성별</td>
					<td>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="gender" value="M" id="genderM">
							<label class="form-check-label" for="genderM">
							  남자
							</label>
						  </div>

						  <div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="gender" value="F" id="genderF" checked>
							<label class="form-check-label" for="genderF">
							  여자
							</label>
						  </div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;생년월일</td>
					<td>
						<input type="date" class="form-control" onchange="makeBirth(this);"/>
						<input type="hidden" name="birthday"/>
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;연락처</td>
					<td>
						<input type="tel" class="form-control" name="phone" pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" placeholder="- 포함하여 작성하시오."/>
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;주소</td>
					<td>
						<input type="text" class="form-control" name="address">
					</td>
				</tr>
			</table>
			<br><br>

			<div align="center">
				<button class="btn btn-primary" type="submit" onclick="return checkPwd();" disabled>회원가입</button>
				<button class="btn btn-danger" type="reset">초기화</button>
			</div>

		</form>

	</div>
	
	<script>
		function makeBirth(target) {
			// 입력된 값 가져오기
			const data = target.value;
			// document.getElementById("아이디 값")
			// document.querySelector("선택자")
			
			// document.getElementsClassName("클래스명")
			// document.querySelectorAll("선택자")
			
			console.log(data);
			
			// yyyy-mm-dd 형식에서 yymmdd 형식으로 변경
			// (1) Date 객체 사용
			const birth = new Date(data);
			
			console.log(birth);
			
			let yy = birth.getFullYear() % 100;
			let mm = ("0" + (birth.getMonth() + 1)).slice(-2);
			let dd = ("0" + birth.getDate()).slice(-2);
			
			console.log(yy, mm, dd);
			
			// (2) String 객체 메소드 사용 => split 메소드
			const dArr = data.split('-'); // ['yyyy', 'mm', 'dd']
			yy = dArr[0].slice(-2);
			mm = dArr[1];
			dd = dArr[2];
			
			console.log(yy, mm, dd);
			
			console.log(yy+mm+dd);
			
			// yy, mm, dd 를 하나로 합쳐서 name 속성이 birthday인 요소에 값을 저장
			document.querySelector("#mem-enroll-area input[name=birthday]").value = yy+mm+dd;
			
		}
		/*
		const makeBirrth = (target) => {
			
		}
		*/
		
		
		// 입력된 '비밀번호' 값과 '비밀번호 확인' 값이 같을 경우 true
		// 다를 경우 false 를 리턴
		function checkPwd() {
			// 비밀번호 입력 값 --> name = userPwd
			const userP = document.querySelector("#mem-enroll-area input[name=userPwd]").value;
			
			// 비밀번호 확인 입력값 --> id = userPwdCheck
			const userPC = document.getElementById("userPwdCheck").value;
			
			// console.log(userP, userPC);
			
			// 두 값이 다를 경우 false 리턴
			if (userP != userPC) {
				alert("비밀번호와 비밀번호 확인 입력값이 다릅니다.");
				return false;
			}			
		}
		
//		function makePhone(target) {
//			const data = target.value;
//			
//			console.log(data);
//			
//			if (!data.includes('-')) {
//				data.replace()
//			}
//		}

	// 아이디 중복체크 (비동기 통신, ajax)
		function idCheck() {
			// [중복체크] 클릭 시 사용자가 입력한 아이디값을 전달하여 조회
			// => 존재하는지 하지않는지 여부를 확인 후 응답 데이터를 받을 것임!
			
			// * 입력된 아이디 값 조회(추출) (jQuery)
			const $userId = $("#mem-enroll-area input[name=userId]");
//			console.log( $userId, $userId.val() );
			
			console.log("*** ajax 요청 전 ***");
			
			
			// $.ajax(요청 정보);
			// * 요청정보 : 객체(json)형태로  key-value형태로 작성
			/*
				- url : 요청주소
				- type / method : 요청 방식 (get, post, . . . )
				- data : 전달할 데이터(json)형태로 {키:밸류, 키:밸류, . . .}
				
				- success : 요청(통신) 성공 시 처리할 내용(함수)
				- errir   : 요청(통신) 실패 시 처리할 내용(함수)
				
				
				---- 참고 ----
				- async : 서버와의 비동기통신 처리여부 (기본값 : true)
				- contentType : 요청 시 데이터 인코딩 방식
				- dataType : 서버에서 응답 시 전달되는 데이터 형식 설정
							(설정하지 않을 경우 자동으로 판단)
					+ xml  : 트리형태
					+ json : 객체(Map 형식) -- .일반적인 데이터 구조
					+ html : html 형식(태크)
					+ text : 문자열 데이터
			*/
			$.ajax({
				url: 'idCheck',
				data: {
					userId: $userId.val()
				},
				type: 'get', // 생략 가능!!
				
				
				
				success: function(count) {
					// 요청(통신) 성공 시
					// alert(count);
					
					console.log("[" + count + "]");
					
					if (count == 'YYY') {
						// 결과(count)가 사용가능할 때 ('YYY')
						// "사용 가능한 아이디입니다." 메세지 출력
						alert("사용 가능한 아이디입니다.");
						
						// [회원가입]을 활성화
						$("#mem-enroll-area button[type=submit]").removeAttr("disabled");
						
						// * 아이디 입력 요소를 읽기 전용으로 변경
						$("#mem-enroll-area button[type=submit]").attr("readonly", true);
						
					} else {
						// 결과(count)가 사용가능할 때 ('NNN')
						// "이미 사용중인 아이디입니다." 메세지 출력
						alert("이미 사용중인 아이디입니다.");
						// [회원가입]을 그대로 비활성화 유지
						$("#mem-enroll-area button[type=submit]").attr("disabled", true);
						
					}
					
					
				}, 
				error: function(err) {
					// 요청(통신) 실패 시
					console.log("* --- 아이디 중복체크 실패 --- *")
					cosole.log(err);
					
				}
			});
	
			
		}
	</script>
</body>
</html>