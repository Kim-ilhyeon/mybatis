package com.kh.mybatis.member.model.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.member.model.vo.Member;

public class MemberDao {

	public int insertMember(SqlSession sqlSession, Member m) {
		
		int result = sqlSession.insert("memberMapper.insertMember", m);
		
		return result;
	}

	public Member selectMember(SqlSession sqlSession, Member m) {
		// select --> selectOne 	: 한 행만 조회
		// 			  selectList	: 여러 행 조회
		// => 해당 SQL문에 조회되는 결과가 몇행인지는 DB의 제약조건을 생각하며 확인 ㄱㄱ
		
		// * 데이터 조회 시 결과가 없을 경우 null을 반환한다.
		
		
		Member loginUser = sqlSession.selectOne("memberMapper.selectMember", m);
		
		return loginUser;
		
	}

	public int updateMember(SqlSession sqlSession, Member m) {
		
		int result = sqlSession.update("memberMapper.updateMember", m);
		
		return result;
	}

	public int deleteMember(SqlSession sqlSession, Member m) {
		
		int result = sqlSession.update("memberMapper.deleteMember", m);
		
		return result;
	}

	public int updatePassword(SqlSession sqlSession, HashMap hMap) {
		
		int result = sqlSession.update("memberMapper.updatePassword", hMap);
		
		return result;
	}

	public int countMemberByUserId(SqlSession sqlSession, HashMap hMap) {

		int count = sqlSession.selectOne("memberMapper.countMemberByUserId", hMap);
		
		return count;
	}

	
	
}
