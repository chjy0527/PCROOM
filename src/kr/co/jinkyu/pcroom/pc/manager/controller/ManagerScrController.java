package kr.co.jinkyu.pcroom.pc.manager.controller;

import org.apache.ibatis.session.SqlSession;

import kr.co.jinkyu.pcroom.common.db.MyAppSqlConfig;
import kr.co.jinkyu.pcroom.controller.Controller;
import kr.co.jinkyu.pcroom.controller.Controllers;
import kr.co.jinkyu.pcroom.controller.SeatListController;
import kr.co.jinkyu.pcroom.pc.dao.MemberDAO;
import kr.co.jinkyu.pcroom.pc.dao.SeatDAO;
import kr.co.jinkyu.pcroom.pc.login.controller.LogoutController;
import kr.co.jinkyu.pcroom.vo.Member;
import kr.co.jinkyu.pcroom.vo.Seat;
import util.PrintUtil;

public class ManagerScrController extends Controllers {
	
	private Member member;
	private MemberDAO mMapper;
	private SeatDAO sMapper;
	
	public ManagerScrController() {}
	public ManagerScrController(Member member) {
		this.member = member;
		SqlSession session = MyAppSqlConfig.getSqlSession();
		mMapper = session.getMapper(MemberDAO.class);
		mMapper.updateLoginPresenceWhenLogin(member.getMemNo());
		sMapper = session.getMapper(SeatDAO.class);
		sMapper.insertSeat(member);
	}
	
	private String choiceMenu(){
		PrintUtil.print();
		System.out.println("1. 전체자리조회");
		System.out.println("2. 자리선택");
		System.out.println("3. 회원정보");
		System.out.println("4. 음식메뉴 추가하기");
		System.out.println("0. 로그아웃");
		PrintUtil.print();
		return input("번호를 입력하세요 : ");
	}
	
	public void service() {
		/*
		 * 1. 전체자리조회 : SeatListController
		 * 2. 자리선택 : ChoiceSeatController
		 * 3. 회원정보 : MemberInfoController
		 * 0. 로그아웃 : LogoutController
		 */
		
		PrintUtil.print();
		System.out.println("관리자화면");
		while(true) {
			// 로그아웃 상태라면 초기화면으로 돌아감
			Seat seat = sMapper.selectSeatInfoByMemNo(member.getMemNo());
			if(seat==null) return;
			
			Controller ctrl = null;
			switch(choiceMenu()) {
			case "1":
				ctrl = new SeatListController();
				break;
			case "2":
				ctrl = new ChoiceSeatController();
				break;
			case "3" :
				ctrl = new MemberInfoController();
				break;
			case "4" :
				ctrl = new AddFoodMenuController();
				break;
			case "0":
				ctrl = new LogoutController(member);
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
