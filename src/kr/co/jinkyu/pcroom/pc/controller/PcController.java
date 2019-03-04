package kr.co.jinkyu.pcroom.pc.controller;

import java.util.Scanner;

import kr.co.jinkyu.pcroom.controller.Controller;
import kr.co.jinkyu.pcroom.controller.Controllers;
import kr.co.jinkyu.pcroom.pc.login.controller.LoginController;
import kr.co.jinkyu.pcroom.pc.login.controller.SignUpController;
import util.PrintUtil;

public class PcController extends Controllers{
	
	Scanner sc = new Scanner(System.in);
	
	private void exit() {
		System.out.println("컴퓨터 접속을 종료합니다.");
		// 프로세스 중단.
		System.exit(0);
	}
	
	private String choiceMenu() {
		PrintUtil.print(20);
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("0. 종료");
		PrintUtil.print(20);
		System.out.print("번호를 입력하세요 : ");
		return sc.nextLine();
	}
	
	
	public void service() {
		System.out.println("컴퓨터 접속");
		while(true) {
			Controller ctrl = null;
			switch(choiceMenu()) {
			case "1":
				ctrl = new LoginController();
				break;
			case "2":
				ctrl = new SignUpController();
				break;
			case "0":
				exit();
				break;
			default : 
				System.out.println("번호가 잘못 되었습니다.");
				System.out.println("다시 선택해주세요.");
			}
			if(ctrl!=null) {
				ctrl.service();
			}
		}
		
	}
}
