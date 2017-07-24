package com.hb.am;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
	Connection conn;
	PreparedStatement ptmt;
	ResultSet rs;
	
	public DAO() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@192.168.0.133:1521:xe";
			String user = "hr";
			String password = "1111";
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
		}
	}
	
	public ArrayList<VO> getSelectAll(){
		ArrayList<VO> resList = new ArrayList<>();
		String sql = "select * from hbmember order by idx";
		try {
			ptmt = conn.prepareStatement(sql);
			rs = ptmt.executeQuery();
			while(rs.next()){
				VO vo = new VO();
				vo.setIdx(rs.getString(1));
				vo.setId(rs.getString(2));
				vo.setPwd(rs.getString(3));
				vo.setName(rs.getString(4));
				vo.setAge(rs.getString(5));
				vo.setAddr(rs.getString(6));
				resList.add(vo);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				rs.close();
				ptmt.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return resList;
	}
}