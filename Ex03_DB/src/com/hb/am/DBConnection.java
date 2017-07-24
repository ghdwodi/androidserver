package com.hb.am;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBConnection {
	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;
	public DBConnection() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@10.10.10.133:1521:xe";
			String user = "hr";
			String password = "1111";
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
		}
	}
	
	public ArrayList<String> getSelectAll(){
		ArrayList<String> resultList = new ArrayList<>();
		String sql = "select * from hbmember order by idx";
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				StringBuffer sb = new StringBuffer();
				sb.append(String.valueOf(rs.getInt(1))+"/");
				sb.append(rs.getString(2)+"/");
				sb.append(rs.getString(3)+"/");
				sb.append(rs.getString(4)+"/");
				sb.append(String.valueOf(rs.getInt(5))+"/");
				sb.append(rs.getString(6));
				resultList.add(sb.toString());
			}
		} catch (Exception e) {
		}finally {
			try {
				if(rs!=null){
					rs.close();
				}
				if(pstm!=null){
					pstm.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (Exception e2) {
			}
		}
		return resultList;
	}
}
