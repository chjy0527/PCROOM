package kr.co.jinkyu.pcroom.pc.login.controller;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;

import kr.co.jinkyu.pcroom.common.db.MyAppSqlConfig;
import kr.co.jinkyu.pcroom.controller.Controllers;
import kr.co.jinkyu.pcroom.pc.dao.MemberDAO;
import kr.co.jinkyu.pcroom.pc.dao.SeatDAO;
import kr.co.jinkyu.pcroom.vo.Member;
import kr.co.jinkyu.pcroom.vo.Seat;
import util.PrintUtil;

public class LogoutController extends Controllers{
	
	private Member member;
	private MemberDAO mMapper;
	private SeatDAO sMapper;
	
	public LogoutController() {}
	public LogoutController(Member member) {
		this.member = member;
		SqlSession session = MyAppSqlConfig.getSqlSession();
		mMapper = session.getMapper(MemberDAO.class);
		sMapper = session.getMapper(SeatDAO.class);
	}
	private void updateMemTime() {
		Seat seat = sMapper.selectSeatInfoByMemNo(member.getMemNo());
		int useTime = (int)(Math.round((new Date().getTime() - seat.getChargingTime().getTime())/1000/60));
		int remainingTime = seat.getMember().getMemTime() - useTime;
		if(remainingTime < 0) {
			member.setMemTime(0);
		}else {
			member.setMemTime(remainingTime);
		}
		mMapper.updateMemberTimeLogout(member);
	}
	private void updateEndTime() {
		mMapper.updateEndTime(member.getMemNo());
	}
	private void updateLoginPresence() {
		mMapper.updateLoginPresenceWhenLogout(member.getMemNo());
	}
	
	public void service() {
		updateMemTime();
		updateEndTime();
		updateLoginPresence();
		PrintUtil.print();
		System.out.println("정상적으로 로그아웃 되었습니다.");
		PrintUtil.print();
	}
	
	
}
