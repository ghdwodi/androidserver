package com.hb.am;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ChatThread implements Runnable{
	MultiChat mc ;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	Socket s;
	public ChatThread() {}
	public ChatThread(MultiChat mc) {
		this.mc = mc ;
	}
	
	@Override
	public synchronized void run() {
		try {
			s = mc.getS();
			ois = new ObjectInputStream(s.getInputStream());
			oos = new ObjectOutputStream(s.getOutputStream());
			String msg = "";
			while(true){
				msg = (String)ois.readObject();
				String[] str = msg.split("#");
				if(str[1].equalsIgnoreCase("exit")){
					break;
				}else{
					for(ChatThread ct : mc.getList()){
						ct.oos.writeObject(msg);
						ct.oos.flush();
						System.out.println("메시지 전송 완료.");
					}
				}
			}
			mc.getList().remove(this);
			System.out.println(s.getInetAddress()+" : 정상 종료");
		} catch (Exception e) {
		} finally {
			try {
			} catch (Exception e2) {
			}
		}
	}
}
