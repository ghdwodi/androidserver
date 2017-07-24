package com.hb.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

// DB 관련된 모든 것을 DAO에서 처리한다.
public class DAO {
	private static SqlSession ss;
	
	// 싱글 턴 패턴 : 프로그램이 끝날 때까지 해당 객체는 하나로 유지한다.
	private synchronized static SqlSession getSql() {
		if(ss==null){
			ss = DBService.getFactory().openSession(false);
		}
		return ss;
	}
	
	// list 처리 메소드
	public static List<VO> getList(){
		return getSql().selectList("list");
	}
	
	// insert 메소드
	public static int getInsert(VO vo){
		int result = getSql().insert("insert", vo);
		ss.commit();
		return result;
	}
	
	// 상세보기 처리 메소드
	public static VO getSelectOne(VO vo){
		return getSql().selectOne("onelist", vo);
	}
	
	// update 메소드
	public static int getUpdate(VO vo){
		int result = getSql().update("update", vo);
		ss.commit();
		return result;
	}
	
	// delete 메소드
	public static int getDelete(VO vo){
		int result = getSql().delete("delete", vo);
		ss.commit();
		return result;
	}
}
