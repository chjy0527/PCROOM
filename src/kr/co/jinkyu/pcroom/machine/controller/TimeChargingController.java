package kr.co.jinkyu.pcroom.machine.controller;

import kr.co.jinkyu.pcroom.controller.Controller;
import kr.co.jinkyu.pcroom.controller.Controllers;
import util.PrintUtil;

public class TimeChargingController extends Controllers {
	
	public static Boolean back;
	
	public TimeChargingController() {
		back = false;
	}
	
	private String choiceMenu() {
		PrintUtil.print();
		System.out.println("1. 로그인");
		System.out.println("0. 이전");
		PrintUtil.print();
		return input("번호를 입력하세요 : "); 
	}
	public void service() {
		PrintUtil.print();
		System.out.println("시간충전");
		while(true) {
			if(back) {
				back = false;
				return;
			}
			Controller ctrl = null;
			switch(choiceMenu()) {
			case "1":
				ctrl = new LoginController();
				break;
			case "0":
				return;
			default :
				System.out.println("잘못 입력하셨습니다.");
				System.out.println("다시 입력해주세요.");
			}
			if(ctrl!=null) {
				ctrl.service();
			}
		}
	}
}
