package kr.co.jinkyu.pcroom.machine.controller;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;

import kr.co.jinkyu.pcroom.common.db.MyAppSqlConfig;
import kr.co.jinkyu.pcroom.controller.Controllers;
import kr.co.jinkyu.pcroom.pc.dao.MemberDAO;
import kr.co.jinkyu.pcroom.pc.dao.SeatDAO;
import kr.co.jinkyu.pcroom.vo.Member;
import kr.co.jinkyu.pcroom.vo.Seat;
import util.PrintUtil;

public class FarePaymentController2 extends Controllers {
	
	private Member member;
	private MemberDAO mMapper;
	private SeatDAO sMapper;
	
	public FarePaymentController2() {}
	public FarePaymentController2(Member member) {
		this.member = member;
		SqlSession session = MyAppSqlConfig.getSqlSession();
		mMapper = session.getMapper(MemberDAO.class);
		sMapper = session.getMapper(SeatDAO.class);
	}
	private void timeCharging() {
		if(member.getLoginPresence().equals("y")) {
			Seat seat = sMapper.selectSeatInfoByMemNo(member.getMemNo());
			// seat.getMember().getMemTime() : memTime
			// member.getChargingTime().getTime() : 충전시간
			int useTime = (int)(Math.round((new Date().getTime() - seat.getChargingTime().getTime())/1000.0/60.0));
			int remainingTime = seat.getMember().getMemTime() - useTime;
			if(remainingTime < 0) {
				member.setMemTime(0);
			}else {
				member.setMemTime(remainingTime);
			}
			// chargingTime update
			sMapper.updateSeatChargingTimeByMemNo(member.getMemNo());
		}
		mMapper.updateMemberTime(member);
		System.out.printf("%d시간을 충전하였습니다.%n",member.getMemFare().getFareTime());
	}
	
	private String choiceMenu() {
		PrintUtil.print();
		System.out.printf("결제 금액은 %d원입니다.%n",member.getMemFare().getFarePrice());
		PrintUtil.print();
		System.out.println("1. 카드");
		System.out.println("2. 현금");
		System.out.println("0. 이전");
		PrintUtil.print();
		return input("번호를 입력하세요 : ");
	}
	private void card() {
		System.out.println("카드를 투입해주세요.");
		while(true) {
			PrintUtil.print();
			System.out.println("1. 카드투입");
			System.out.println("0. 이전");
			PrintUtil.print();
			switch(input("번호를 입력하세요 : ")) {
			case "1":
				System.out.println("카드투입이 되었습니다.");
				timeCharging(); //  시간 충전하는 메서드
				System.out.printf("카드로 %d원이 결제되었습니다.%n",member.getMemFare().getFarePrice());
				TimeChargingController.back = true;
				return;
			case "0":
				return;
			default:
				System.out.println("번호를 잘못 입력하셨습니다.");
				System.out.println("다시 입력해주세요.");
			}
		}
	}
	private void cash() {
		System.out.println("현금을 투입해주세요");
		while(true) {
			PrintUtil.print();
			System.out.println("1. 현금투입");
			System.out.println("0. 이전");
			PrintUtil.print();
			switch(input("번호를 입력하세요 : ")) {
			case "1":
				System.out.println("현금이 투입되었습니다.");
				timeCharging(); //  시간 충전하는 메서드
				System.out.printf("현금으로 %d원이 결제되었습니다.%n",member.getMemFare().getFarePrice());
				TimeChargingController.back = true;
				return;
			case "0":
				return;
			default:
				System.out.println("번호를 잘못 입력하셨습니다.");
				System.out.println("다시 입력해주세요.");
			}
		}
	}
	public void service() {
		while(true)  {
			if(TimeChargingController.back)return;
			switch(choiceMenu()) {
			case "1":
				card();
				break;
			case "2":
				cash();
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
