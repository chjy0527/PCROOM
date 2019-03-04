package kr.co.jinkyu.pcroom.pc.member.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.ibatis.session.SqlSession;

import kr.co.jinkyu.pcroom.common.db.MyAppSqlConfig;
import kr.co.jinkyu.pcroom.controller.Controllers;
import kr.co.jinkyu.pcroom.pc.dao.SeatDAO;
import kr.co.jinkyu.pcroom.vo.Member;
import kr.co.jinkyu.pcroom.vo.Seat;

public class TimeCheckController extends Controllers {
	private Member member;
	private SeatDAO sMapper;
	private SimpleDateFormat sdf = new SimpleDateFormat("aa hh:mm");
	
	public TimeCheckController() {}
	public TimeCheckController(Member member) {
		this.member = member;
		SqlSession session = MyAppSqlConfig.getSqlSession();
		sMapper = session.getMapper(SeatDAO.class);
	}
	
	private void printTime(int remainingTime) {
		if(remainingTime > 0) {
			System.out.printf("남은 시간 : (%02d:%02d)\n",remainingTime/60,remainingTime%60);
		}
		else {
			System.out.println("남은 시간 : (00:00)");
		}
	}
	
	public void service() {
		/*
		현재 시간 : 오후 11:10
		시작 시간 : 오후 10:40
		남은 시간 : 1시간 36분
		종료 시간 : 오전 12:36분
		 */
		Seat seat = sMapper.selectSeatInfoByMemNo(member.getMemNo());
		System.out.println("회원 ID : "+member.getMemId());
		System.out.println("회원이름 : "+member.getMemName());
		System.out.println("시작 시간 : "+sdf.format(seat.getStartTime()));
		System.out.println("현재 시간 : "+sdf.format(new Date()));
		int useTime = (int)(Math.round((new Date().getTime() - seat.getChargingTime().getTime())/1000.0/60.0));
		int remainingTime = seat.getMember().getMemTime() - useTime;
		// 남은 시간 출력
		printTime(remainingTime);
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		if(remainingTime>0)
			cal.add(Calendar.MINUTE, remainingTime);
		System.out.println("종료 시간 : "+sdf.format(cal.getTime()));

		
	}
}
