package kr.co.jinkyu.pcroom.pc.login.controller;

public class TT {
	
	public static void main(String[] args) {
		Thread t = new Thread() {
			public void run() {
				while(true) {				
					try {
						Thread.sleep(5000);
					}catch(Exception e) {}
					System.out.println("쓰레드 실행중!");
					System.exit(0);
					System.out.println("종료??");
				}
			}
		};
		t.start();
		while(true) {
			try {
				Thread.sleep(1000);
			}catch(Exception e) {}
			
			System.out.println("메인 쓰레드 실행중!");
		}
	}
	
}

class TThread extends Thread{
	public void run() {
		try {
			Thread.sleep(3000);
		}catch(Exception e) {}
		System.exit(1);
	}
}
