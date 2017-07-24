package com.hb.am;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

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
			ss = new ServerSocket(9797);
			
			System.out.println("서버 대기중...");
			while(true){
				s = ss.accept();
				System.out.println("접속");
				br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
				System.out.println("리더, 라이터 생성");
				
				String msg = readProcess();
				System.out.println(msg);
				if(msg.equalsIgnoreCase("test")){
					sendProcess("Connect success");
				}else if(msg.equalsIgnoreCase("db")){
					dbProcess();
				}else{
					System.out.println("???");
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
	
	// 접속 성공 메시지 보내기
	public void sendProcess(String msg){
		try {
			msg = msg+System.getProperty("line.separator");
			bw.write(msg);
			bw.flush();
			System.out.println("전송 성공");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void dbProcess(){
		DBConnection db = new DBConnection();
		ArrayList<String> list = db.getSelectAll();
		if(list.size()>0){
			StringBuffer sb = new StringBuffer();
			for (String k : list) {
				sb.append(k+",");
			}
			sendProcess(sb.toString());
		}else{
			sendProcess("No data");
		}
	}
}