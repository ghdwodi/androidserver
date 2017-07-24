package com.hb.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import com.hb.mybatis.DAO;
import com.hb.mybatis.VO;

public class NetworkDB {
	ServerSocket ss;
	Socket s;
	BufferedReader br = null;
	BufferedWriter bw = null;
	public static void main(String[] args) {
		new NetworkDB();
	}
	
	public NetworkDB() {
		try {
			ss = new ServerSocket(7890);
			System.out.println("서버 대기중...");
			while(true){
				System.out.println("접속전");
				s = ss.accept();
				System.out.println("접속");
				br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
				System.out.println("리더, 라이터 생성");
				
				String msg = readProcess();
				System.out.println(msg);
				String[] msgArr = msg.split(",");
				VO vo = new VO();
				VO vo2 = new VO();
				// cmd 목록
		        // 100 : ID와 패스워드로 회원정보 하나 조회
		        // 200 : 회원정보 입력
		        // 300 : 회원정보 삭제
		        // 400 : 회원정보 수정
		        // 101 : 회원정보 전체 표시
				switch (msgArr[0]) {
				case "100":
					vo.setId(msgArr[1]);
					vo.setPwd(msgArr[2]);
					vo2 = DAO.getSelectOne(vo);
					String responseMsg = vo2.getIdx()+","+vo2.getId()+","+vo2.getPwd()+","+vo2.getName()+","+vo2.getAge()+","+vo2.getAddr();
					sendProcess(responseMsg);										
					break;
				case "101":
					List<VO> list = DAO.getList();
					String resMsg = "";
					for (VO k : list) {
						resMsg += k.getIdx()+","+k.getId()+","+k.getPwd()+","+k.getName()+","+k.getAge()+","+k.getAddr()+"#";
					}
					sendProcess(resMsg);
					break;
				case "200":
					vo.setIdx(msgArr[1]);
					vo.setId(msgArr[2]);
					vo.setPwd(msgArr[3]);
					vo.setName(msgArr[4]);
					vo.setAge(msgArr[5]);
					vo.setAddr(msgArr[6]);
					int result = DAO.getInsert(vo);
					if(result>0){
						resMsg = "Insert Success";
					}else{
						resMsg = "Insert Fail";
					}
					sendProcess(resMsg);
					break;
				case "300":
					vo.setId(msgArr[1]);
					vo.setPwd(msgArr[2]);
					result = DAO.getDelete(vo);
					if(result>0){
						resMsg = "Delete Success";
					}else{
						resMsg = "Delete Fail";
					}
					sendProcess(resMsg);
					break;
				case "400":
					vo.setIdx(msgArr[1]);
					vo.setId(msgArr[2]);
					vo.setPwd(msgArr[3]);
					vo.setName(msgArr[4]);
					vo.setAge(msgArr[5]);
					vo.setAddr(msgArr[6]);
					result = DAO.getUpdate(vo);
					if(result>0){
						resMsg = "Update Success";
					}else{
						resMsg = "Update Fail";
					}
					sendProcess(resMsg);
					break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	
	// 외부 정보 읽기
	public String readProcess(){
		String c_msg = null;
		try {
			System.out.println("readProcess 실행");
			c_msg = br.readLine();
			System.out.println(c_msg);
		} catch (Exception e) {
			System.out.println(e);
		}
		return c_msg;
	}
	public void sendProcess(String msg){
		System.out.println("보낼 것 : "+msg);
		try {
			msg = msg+System.getProperty("line.separator");
			bw.write(msg);
			bw.flush();
			System.out.println("전송 성공");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}