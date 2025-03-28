package com.kh.mybatis.member.service;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.member.model.dao.MemberDao;
import com.kh.mybatis.member.model.vo.Member;
import com.kh.mybatis.template.Template;

public class MemberServiceImpl implements MemberService {

	private MemberDao mDao = new MemberDao();
	
	@Override
	public int insertMember(Member m) {
		/*
		 * 기존(JDBC) 방식
		 * 
		 * - Connection 객체 생성
		 * - DAO에게 Connection 객체와 전달받은 데이터 전달(요청)
		 * - 결과에 따라 (DML) --> 트랜잭션 처리
		 * - Connection 객체 반납(close)
		 * - 결과를 리턴
		 */
		
		/* Mybatis */
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = mDao.insertMember(sqlSession, m);
		
		if (result > 0) {
			sqlSession.commit();
		} else {
			// rollback의 경우 여러 개의 DML을 실행할 경우만 작성하고 --> (ex) 게시판 등록 시 (게시글 정보 + 첨부파일)
			// 단일 행일 경우 생략 가능!
		}
		sqlSession.close();
		
		return result;
	}

	@Override
	public Member selectMember(Member m) {
		// * SqlSession 객체 생성
		SqlSession sqlSession = Template.getSqlSession();
		
		// * Dao 객체에게 작업 요청 후 결과 받기
		Member loginUser = mDao.selectMember(sqlSession, m);
		
		// * SqlSession 객체 반납(close)
		sqlSession.close();
		
		// * 결과 리턴
		return loginUser;
	}

	@Override
	public int updateMember(Member m) {
		// * SqlSession 객체 생성
		SqlSession sqlSession = Template.getSqlSession();
		
		// * Dao 객체에게 작업 요청 후 결과 받기
		int result = mDao.updateMember(sqlSession, m);
		
		// * DML 실행되는 경우, 트랜잭션 처리 (commit만!! rollback 생략 가능)
		if ( result > 0 ) {
			sqlSession.commit();
		}
		
		// * SqlSession 객체 반납(close)
		sqlSession.close();
		
		// * 결과 리턴
		return result;
	}

	@Override
	public int deleteMember(String userId, String userPwd) {
		// * SqlSession 객체 생성
		SqlSession sqlSession = Template.getSqlSession();
		
		// * Dao 객체에게 작업 요청 후 결과 받기 (탈퇴요청)
		// (1) vo 객체로 가공처리 후 요청
		Member m = new Member();
		m.setUserId(userId);
		m.setUserPwd(userPwd);
		
		int result = mDao.deleteMember(sqlSession, m);
		
		/*
		// (2) Map 형태로 가공처리 후 요청
		HashMap hMap = new HashMap();
		hMap.put("id", userId);
		hMap.put("pwd", userPwd);
		
		int result = mDao.deleteMember(sqlSession, hMap);
		*/
		
		
		// * DML 실행되는 경우, 트랜잭션 처리 (commit만!! rollback 생략 가능)
		if ( result > 0 ) {
			sqlSession.commit();
		}
		
		
		// * SqlSession 객체 반납(close)
		sqlSession.close();
		
		// * 결과 리턴
		return result;
	}
	
	public int updatePassword(String userId, String userPwd, String newPwd) {
		// * SqlSession 객체 생성
		SqlSession sqlSession = Template.getSqlSession();
		
		// -> Map 형태로 가공처리 후 요청
		HashMap hMap = new HashMap();
		hMap.put("userId", userId);
		hMap.put("userPwd", userPwd);
		hMap.put("newPwd", newPwd);
		
		// * Dao 객체에게 작업 요청 후 결과받기
		int result = mDao.updatePassword(sqlSession, hMap);
		
		
		// * DML 실행되는 경우, 트랜잭션 처리 (commit만 !! rollback 생략 가능)
		if ( result > 0 ) {
			sqlSession.commit();
		}
		
		// * SqlSession 객체 반납(close)
		sqlSession.close();
		
		// * 결과 리턴
		return result;
	}
	
	public int countMemberByUserId(String checkId) {
		// * SqlSession 객체 생성
		SqlSession sqlSession = Template.getSqlSession();
		
		HashMap hMap = new HashMap();
		hMap.put("checkId", checkId);
		// * Dao에게 sqlSession과 데이터 전달하여 요청하여 결과 받기
		int count = mDao.countMemberByUserId(sqlSession, hMap);
		
		// * SqlSession 객체 반납
		sqlSession.close();
		
		// * 결과 반납
		return count;
	}

	
	
}
