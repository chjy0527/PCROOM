package kr.co.jinkyu.pcroom.machine.controller;

import kr.co.jinkyu.pcroom.controller.Controllers;
import kr.co.jinkyu.pcroom.vo.Member;
import util.PrintUtil;

public class FarePaymentController extends Controllers {
	
	private Member member;
	
	public FarePaymentController() {}
	public FarePaymentController(Member member) {
		this.member = member;
	}
	
	private String choiceMenu() {
		PrintUtil.print();
		System.out.println("1. 결제");
		System.out.println("0. 이전");
		PrintUtil.print();
		return input("번호를 입력하세요 : ");
	}
	public void service() {
		while(true) {
			if(TimeChargingController.back)return;
			switch(choiceMenu()) {
			case "1":
			    new FarePaymentController2(member).service();
				break;
			case "0":
				return;
			default :
				System.out.println("잘못 입력하셨습니다.");
				System.out.println("번호를 다시 입력해주세요.");
			}
		}
	}
}
