package com.hb.am;

import java.util.ArrayList;

public class ListXml {
	public ArrayList<VO> exec(){
		ArrayList<VO> list = new ArrayList<>();
		DAO dao = new DAO();
		list = dao.getSelectAll();
		return list;
	}
}
