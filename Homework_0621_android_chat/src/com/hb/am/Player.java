package com.hb.am;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


import com.hb.ex01.a0621_chat.Protocol;

public class Player extends Thread {
	Socket s;
	ChatServer server;
	ObjectInputStream ois = null;
	ObjectOutputStream oos = null;
	public Player() {}
	public Player(Socket s, ChatServer server) {
		this.s = s;
		this.server = server;
		System.out.println("플레이어 등장");
		try {
			System.out.println("스트림 등장");
			ois = new ObjectInputStream(s.getInputStream());
			oos = new ObjectOutputStream(s.getOutputStream());
			System.out.println("스트림 등장 끝");
		} catch (Exception e) {
		}
	}
	@Override
	public void run() {
		try {
			System.out.println("플레이어 등장2");
			Protocol pro = (Protocol)ois.readObject();
			int cmd = pro.getCmd();
			System.out.println(cmd);
			switch (cmd) {
			case 200:
				server.sendMsg(pro);
				break;
			case 300:
				server.delMsg(this);
				break;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
