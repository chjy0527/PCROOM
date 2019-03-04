package kr.co.jinkyu.pcroom.pc.member.controller;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;

import kr.co.jinkyu.pcroom.common.db.MyAppSqlConfig;
import kr.co.jinkyu.pcroom.controller.Controllers;
import kr.co.jinkyu.pcroom.pc.dao.MemberDAO;
import kr.co.jinkyu.pcroom.pc.dao.SeatDAO;
import kr.co.jinkyu.pcroom.pc.login.controller.LogoutController;
import kr.co.jinkyu.pcroom.vo.Member;
import kr.co.jinkyu.pcroom.vo.Seat;
import util.PrintUtil;

public class MemberScrController extends Controllers{
	
	private Member member;
	private MemberDAO mMapper;
	private SeatDAO sMapper;
	private Seat seat;
	public static Boolean foodCompulsion;
	
	public MemberScrController() {} 
	public MemberScrController(Member member) {
		foodCompulsion = false;
		this.member = member;
		SqlSession session = MyAppSqlConfig.getSqlSession();
		mMapper = session.getMapper(MemberDAO.class);
		mMapper.updateLoginPresenceWhenLogin(member.getMemNo());
		sMapper = session.getMapper(SeatDAO.class);
		sMapper.insertSeat(member);
		
		Thread t1 = new Thread() {
			public void run() {
				while(true) {
					try {
						Thread.sleep(1000*60);
					}catch(Exception e) {}
					Member mem = mMapper.selectMemberByMemNo(member.getMemNo());
					// 로그아웃되면 쓰레드 종료
					if(mem.getLoginPresence().equals("n")) {
						break;
					}
					try {
						Seat seatThreadLogout = sMapper.selectSeatInfoByMemNo(member.getMemNo());;
						int useTime = (int)(Math.round((new Date().getTime() - seatThreadLogout.getChargingTime().getTime())/1000/60));
						int remainingTime = seatThreadLogout.getMember().getMemTime() - useTime;
						if(remainingTime == 59) {
							System.out.println();
							System.out.printf("%d분 남았습니다.\n",remainingTime); 
						}
					}catch(Exception e) {}
				}
			}
		};
		t1.setDaemon(true);
		t1.start();
		Thread t2 = new Thread() {
			public void run() {
				while(true) {
					try {
						Thread.sleep(3000);
					}catch(Exception e) {}
					Member mem = mMapper.selectMemberByMemNo(member.getMemNo());
					// 로그아웃되면 쓰레드 종료
					if(mem.getLoginPresence().equals("n")) {
						System.out.println();
						System.out.println("로그 로그아웃데스");
						System.exit(0);
					}
				}
			}
		};
		t2.setDaemon(true);
		t2.start();
	} 
	private void useComputer() {
		PrintUtil.print();
		// 남은 시간 0분이면 이용못하게 하기!
		seat = sMapper.selectSeatInfoByMemNo(member.getMemNo());
		int useTime = (int)(Math.round((new Date().getTime() - seat.getChargingTime().getTime())/1000/60));
		int remainingTime = seat.getMember().getMemTime() - useTime;
		if(remainingTime <= 0) {
			System.out.println("컴퓨터를 이용할 수 없습니다.");
			System.out.println("시간을 충전하세요.");
		}else {
			System.out.println("컴퓨터 이용중!!");
		}
	}
	private Boolean useFoodMenu() {
		PrintUtil.print();
		// 남은 시간 0분이면 이용못하게 하기!
		seat = sMapper.selectSeatInfoByMemNo(member.getMemNo());
		int useTime = (int)(Math.round((new Date().getTime() - seat.getChargingTime().getTime())/1000/60));
		int remainingTime = seat.getMember().getMemTime() - useTime;
		if(remainingTime <= 0) {
			System.out.println("음식메뉴를 이용할 수 없습니다.");
			System.out.println("시간을 충전하세요.");
			return false;
		}
		return true;
	}
	private void exit() {
		System.out.println("컴퓨터가 종료되었습니다.");
		System.exit(0);
	}
	private String choiceMenu() {
		PrintUtil.print();
		System.out.println("1. 시간확인");
		System.out.println("2. 컴퓨터 이용하기");
		System.out.println("3. 음식메뉴");
		System.out.println("0. 사용종료");
		PrintUtil.print();
		return input("번호를 입력해주세요 : ");
	}
	
	public void service() {
		/*
		 * 1. 시간확인 TimeCheckController
		 * 2. 컴퓨터 이용하기
		 * 3. 음식메뉴 FoodMenuController
		 * 0. 로그아웃 
		 */
		while(true) {
			// 로그아웃 상태라면 초기화면으로 돌아감
			seat = sMapper.selectSeatInfoByMemNo(member.getMemNo());
			if(seat==null) return;
			switch(choiceMenu()) {
			case "1":
				new TimeCheckController(member).service();;
				break;
			case "2":
				useComputer();
				break;
			case "3":
				if(useFoodMenu()) {
					new FoodMenuController(member).service();
					foodCompulsion = false;
				}
				break;
			case "0":
				new LogoutController(member).service();
				exit();
				break;
			default :
				System.out.println("번호를 잘못 입력하셨습니다.");
				System.out.println("다시 입력해주세요.");
			}
			
		}
	}
}
