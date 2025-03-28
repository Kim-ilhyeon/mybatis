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

<style>
	#my-page-area {
		margin: auto;
	}
</style>

</head>
<body>
	<tr%-- menubar.jsp 포함 --%>
	<jsp:include page="../common/menubar.jsp"/>
	마이 페이지##
	
	<div class="outer">
		<br>

		<h2 align="center">마이 페이지</h2>

		<form action="update.me" method="post" id="my-page-area">
			<table align="center">
				<tr>
					<td>* 아이디</td>
					<td>
						<input type="text" class="form-control" name="userId" maxlength="30" readonly value="${ loginUser.userId }">
					</td>
				</tr>
				<tr>
					<td>* 이름</td>
					<td>
						<input type="text" class="form-control" name="userName" readonly value="${ loginUser.userName }">
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;이메일</td>
					<td>
						<input type="email" class="form-control" name="email" value="${ loginUser.email }">
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;성별</td>
					<td>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="gender" value="M" id="genderM" >
							<label class="form-check-label" for="genderM">
							  남자
							</label>
						  </div>

						  <div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="gender" value="F" id="genderF" >
							<label class="form-check-label" for="genderF">
							  여자
							</label>
						  </div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;생년월일</td>
					<td>
						<input type="date" class="form-control" id="birth" readonly value="${ loginUser.birthday }"/>
						<input type="hidden" name="birthday" />
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;연락처</td>
					<td>
						<input type="tel" class="form-control" name="phone" 
								pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" placeholder="- 포함하여 작성하시오." value="${ loginUser.phone }"/>
					</td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;주소</td>
					<td>
						<input type="text" class="form-control" name="address" value="${ loginUser.address }">
					</td>
				</tr>
			</table>
			<br><br>

			<div align="center">
				<button type="submit" class="btn btn-primary">정보 수정</button>
				<button type="button" class="btn btn-secondary"
						data-bs-toggle="modal" data-bs-target="#updatePwdModal">비밀번호 변경</button>
				
				<button type="button" class="btn btn-danger" 
						data-bs-toggle="modal" data-bs-target="#deleteMemModal" >회원 탈퇴</button>
			</div>

		</form>

	</div>
	
	<!-- 회원 비밀번호 변경 모달(modal -->
	<div class="modal fade" id="updatePwdModal" aria-labelledby="updatePwdModal" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-4" id="updatePwdModal">비밀번호 변경</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      
      <div class="modal-body">
        <form action="updatePwd.me" method="post">
        
        	<input type="hidden" name="userId" value="${ loginUser.userId }"/>
        
        	<div class="mb-3">
            	<label for="passward" class="col-form-label">현재 비밀번호 :</label>
            	<input type="passward" class="form-control" id="passward" name="userPwd"/>
          	</div>
          	<div class="mb-3">
            	<label for="passwardUpdate" class="col-form-label">변경할 비밀번호 :</label>
            	<input type="text" class="form-control" id="passwardUpdate" name="newPwd">
          	</div>
          	<div class="mb-3">
            	<label for="passwardCheck" class="col-form-label">변경할 비밀번호 확인 :</label>
            	<input type="text" class="form-control" id="passwardCheck">
          	</div>
         	 	<button type="submit" class="btn btn-sm btn-warning float-end" onclick="return checkPwd();">비밀번호 변경</button>
        </form>
      </div>
      
    </div>
  </div>
</div>
	
	<!-- 회원 탈퇴 모달(modal) -->
	<div class="modal fade" id="deleteMemModal" tabindex="-1" aria-labelledby="deleteMemModal" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-4" id="deleteMemModal">회원 탈퇴</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <form action="delete.me" method="post">
        	<%-- 회원ID  : 따로 입력받지 않을 것임 (전달만) --%>
        	<input type="hidden" name="userId" value="${ loginUser.userId }" />
        	<p>
        		탈퇴 후 복구가 불가능 합니다. <br>
        		그래도 탈퇴 하시겠습니까?
        	</p>
        	<%-- 회원PWD : 입력받기 --%>
        	<div class="mb-3">
        		<label for="password" class="col-form-label">비밀번호 :</label>
        		<input type="password" id="password" class="form-control" name="userPwd" required/>
        	</div>
        	
        	<button type="submit" class="btn btn-sm btn-danger float-end">탈퇴하기</button>
        </form>
      </div>
    </div>
  </div>
</div>
	
	<script>
		// * 모든 요소들이 로드되었을 때 (화면에 표시되었을 때)
		onload = function() {
			// * --- 성별 항목을 적용 --- *
			const gender = "${ loginUser.gender }";	// 로그인 한 사용자의 성별 정보
			
			console.log(gender);
			
			// 성별(name=gender) 입력 요소들에 접근
			const gArr = document.querySelectorAll("input[name=gender]");
			// => [E, E]
			
			for (let gEle of gArr) {
				console.log(gEle.value);
				if (gEle.value == gender) {
					// 로그인 사용자의 성별 정보와 해당 요소(gender)의 value 속성값이 같은 경우 체크되도록 처리
					gEle.checked = true;
					break;
				}
			}
			
			
			// * -- 생년월일 데이터 가공 -- *
			makeBirth();
			
		}
		
		function makeBirth(target) {
			// 생년월일 변경 허용을 하지 않는 경우 => DB 조회한 데이터 사용
			const data = "${ loginUser.birthday }";
			
			// 생년월일 변경 허용 경우 => 입력요소의 저장된 값을 사용
			// const data = document.getElementById("birth").value;
			
			const dArr = data.split('-'); // ['yyyy', 'mm', 'dd']
			yy = dArr[0].slice(-2);
			mm = dArr[1];
			dd = dArr[2];
			
			console.log(yy, mm, dd);
			
			console.log(yy+mm+dd);
			
			// yy, mm, dd 를 하나로 합쳐서 name 속성이 birthday인 요소에 값을 저장
			document.querySelector("#my-page-area input[name=birthday]").value = yy+mm+dd;
			
		}
		
		// 입력된 '비밀번호' 값과 '비밀번호 확인' 값이 같을 경우 true
		// 다를 경우 false 를 리턴
		function checkPwd() {
			// 변경할 비밀번호 입력 값 --> name = userPwd
			const newPwd = document.querySelector("#updatePwdModal #passwardUpdate").value;
			
			// 변경할 비밀번호 확인 입력값 --> id = userPwdCheck
			const newPwdC = document.querySelector("#updatePwdModal #passwardCheck").value;
			
			// console.log(newPwd, newPwdC);
			
			// * 모두 입력되지 않았을 때 변경 요청하지 않도록 처리
			const userPwd = document.querySelector("#updatePwdModal #passward").value;
			
			if (userPwd == "" || newPwd == "" || newPwdC== "") {
				alert("입력되지 않은 값이 있습니다. 확인해주세요.");
				return false;
			}
			// => 입력 input요소에 required 속성을 추가해도됨
			
			// 두 값이 다를 경우 false 리턴
			if (newPwd != newPwdC) {
				alert("변경할 비밀번호와 비밀번호 확인값이 다릅니다. 다시 확인해주세요.");
				return false;
			}			
		}
	</script>
</body>
</html>