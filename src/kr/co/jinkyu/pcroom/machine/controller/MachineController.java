package kr.co.jinkyu.pcroom.machine.controller;

import kr.co.jinkyu.pcroom.controller.Controller;
import kr.co.jinkyu.pcroom.controller.Controllers;
import kr.co.jinkyu.pcroom.controller.SeatListController;
import util.PrintUtil;

public class MachineController extends Controllers{
	
	private String choiceMenu() {
		PrintUtil.print();
		System.out.println("1. 자리조회");
		System.out.println("2. 시간충전");
		System.out.println("0. 종료");
		PrintUtil.print();
		System.out.print("번호를 입력하세요 : ");
		return sc.nextLine();
	}
	private void exit() {
		System.out.println("무인기가 종료되었습니다.");
		System.exit(0);
	}
	public void service() {
		/*
		 * 1. 자리조회 SeatListController
		 * 2. 시간충전 TimeChargingController
		 * 3. 종료
		 */
		System.out.println("무인기 접속");
		while(true) {
			Controller ctrl = null;
			switch(choiceMenu()) {
			case "1":
				ctrl = new SeatListController();
				break;
			case "2":
				ctrl = new TimeChargingController();
				break;
			case "0":
				exit();
			default :
				System.out.println("잘못입력하셨습니다.");	
				System.out.println("다시 입력해주세요.");	
			}
			if(ctrl!=null) {
				ctrl.service();
			}
		}
	}
}
