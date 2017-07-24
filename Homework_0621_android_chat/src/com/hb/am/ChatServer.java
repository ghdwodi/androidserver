package com.hb.am;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.hb.ex01.a0621_chat.Protocol;

public class ChatServer extends Thread{
	ServerSocket ss;
	Socket socket;
	ArrayList<Player> playerList;
	public ChatServer() {
		playerList = new ArrayList<>();
	}
	static ChatServer server = new ChatServer();
	public static void main(String[] args) {
		if(server!=null){
			server.start();
		}
	}
	
	@Override
	public void run() {
		ss = null;
		socket = null;
		try {
			ss = new ServerSocket(7979);
			System.out.println("서버 접속중...");
			while(true){
				socket = ss.accept();
				Player player = new Player(socket, server);
				System.out.println("새 접속");
				playerList.add(player);
				player.start();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	// 메시지 뿌리기
	public void sendMsg(Protocol pro){
		System.out.println(pro.getPlayerName()+" : "+pro.getMessage());
		System.out.println("접속자수 : "+playerList.size());
		try {
			for (Player k : playerList) {
				k.oos.writeObject(pro);
				k.oos.flush();
			}
			System.out.println("전송완료");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	// 나가기
	public void delMsg(Player player) {
		playerList.remove(player);
	}
}
