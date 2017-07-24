package com.hb.am;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class NetServer extends Thread{
	ServerSocket ss;
	Socket socket;
	static NetServer server = new NetServer();
	public static void main(String[] args) {
		if(server!=null){
			server.start();
		}
	}
	@Override
	public void run() {
		BufferedReader br = null;
		BufferedWriter bw = null;
		ss = null;
		socket = null;
		try {
			ss = new ServerSocket(7878);
			System.out.println("서버 접속중...");
			while(true){
				socket = ss.accept();
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				
				String msg = br.readLine();
				if(msg.equalsIgnoreCase("exit")){
					System.out.println("종료");
					break;
				}
				System.out.println("메시지 : "+msg);
				bw.write(msg+System.getProperty("line.separator"));
				bw.flush();
			}
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			try {
				bw.close();
				br.close();
				socket.close();
				ss.close();
			} catch (Exception e2) {
			}
		}
	}
}
