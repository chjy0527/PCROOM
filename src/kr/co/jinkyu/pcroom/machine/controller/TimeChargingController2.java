package kr.co.jinkyu.pcroom.machine.controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.jinkyu.pcroom.common.db.MyAppSqlConfig;
import kr.co.jinkyu.pcroom.controller.Controllers;
import kr.co.jinkyu.pcroom.pc.dao.FareDAO;
import kr.co.jinkyu.pcroom.vo.Fare;
import kr.co.jinkyu.pcroom.vo.Member;
import util.PrintUtil;

public class TimeChargingController2 extends Controllers {
	private Member member;
	private FareDAO fareMapper;
	/*
	 * 컴퓨터에 접속되어있냐 아니냐에 따라
	 * 시간을 충전하는 경우도 나눠야함!
	 */
	public TimeChargingController2() {}
	public TimeChargingController2(Member member) {
		this.member = member;
		SqlSession session = MyAppSqlConfig.getSqlSession();
		fareMapper = session.getMapper(FareDAO.class);
	}
	public void service() {
		
		PrintUtil.print();
		System.out.printf("회원 ID(%s)로 로그인되었습니다.\n",member.getMemId());
		List<Fare> list = fareMapper.selectFareList();
		PrintUtil.print("*");
		// 시간 출력
		for(int i=0;i<list.size();i++) {
			Fare f = list.get(i);
			System.out.printf("%d. %d시간 충전(%d원) \n",i+1,f.getFareTime(),f.getFarePrice());
		}
		System.out.println("0. 이전");
		PrintUtil.print("*");
		
		Boolean checkBool = true; // 번호가 제대로 입력되었는지 확인하는 boolean 값
		while(checkBool) {
			if(TimeChargingController.back)return;
			String choice = input("번호를 입력하세요 : ");
			for(int i=0;i<list.size();i++) {
				if(Integer.toString(i+1).equals(choice)) {
					checkBool = false;
					Fare fare = list.get(i);
					member.setMemFare(fare);
					new FarePaymentController(member).service();
					return;
				}
				else if(choice.equals("0")) return;
			}
			PrintUtil.print();
			System.out.println("잘못 입력하셨습니다.");
			System.out.println("다시 입력해주세요.");
			PrintUtil.print();
		}
	}
	
}
