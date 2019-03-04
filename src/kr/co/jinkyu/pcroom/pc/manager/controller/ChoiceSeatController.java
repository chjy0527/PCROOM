package kr.co.jinkyu.pcroom.pc.manager.controller;

import kr.co.jinkyu.pcroom.controller.Controller;
import kr.co.jinkyu.pcroom.controller.Controllers;
import util.PrintUtil;

public class ChoiceSeatController extends Controllers{
	private String choiceMenu() {
		PrintUtil.print();
		System.out.println("1. 자리정보");
		System.out.println("2. 자리끄기");
		System.out.println("0. 이전");
		PrintUtil.print();
		return input("번호를 입력하세요 : ");
	}
	public void service() {
		while(true) {
			Controller ctrl = null;
			switch(choiceMenu()) {
			case "1":
				ctrl = new SelectSeatBySeatNoController();
				break;
			case "2":
				ctrl = new DeleteSeatBySeatNoController();
				break;
			case "0":
				return;
			default : 
				System.out.println("번호를 잘못 입력하셨습니다.");
				System.out.println("다시 입력해주세요.");
			}
			if(ctrl!=null) {
				ctrl.service();
			}
		}
	}
}
