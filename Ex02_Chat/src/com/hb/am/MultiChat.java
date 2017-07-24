package com.hb.am;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MultiChat {
	private ArrayList<ChatThread> list;
	private Socket s;

	public static void main(String[] args) {
		new MultiChat();
	}

	public MultiChat() {
		list = new ArrayList<>();
		try {
			ServerSocket ss = new ServerSocket(7878);
			ChatThread chatThread = null;
			System.out.println("서버 서비스 중...");
			while (true) {
				s = ss.accept();
				chatThread = new ChatThread(this);
				
				list.add(chatThread);
				new Thread(chatThread).start();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {

			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

	}

	// get/set
	public ArrayList<ChatThread> getList() {
		return list;
	}

	public void setList(ArrayList<ChatThread> list) {
		this.list = list;
	}

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

}
