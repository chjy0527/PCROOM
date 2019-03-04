package kr.co.jinkyu.pcroom.pc.manager.controller;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;

import kr.co.jinkyu.pcroom.common.db.MyAppSqlConfig;
import kr.co.jinkyu.pcroom.controller.Controllers;
import kr.co.jinkyu.pcroom.pc.dao.SeatDAO;
import kr.co.jinkyu.pcroom.vo.Seat;
import util.PrintUtil;

public class SelectSeatBySeatNoController extends Controllers {
	
	private SeatDAO sMapper;
	public SelectSeatBySeatNoController() {
		SqlSession session = MyAppSqlConfig.getSqlSession();
		sMapper = session.getMapper(SeatDAO.class);
	}
	
	private void printTime(int minute) {
		if(minute > 0) {
			System.out.printf("사용시간 : (%02d:%02d)",minute/60,minute%60);
		}
		else {
			System.out.print("(00:00)");
		}
	}
	
	public void service() {
		try {
			PrintUtil.print();
			int seatNo = Integer.parseInt(input("자리번호를 입력하세요 : "));
			PrintUtil.print();
			Seat seat = sMapper.selectSeatInfoBySeatNo(seatNo);
			PrintUtil.print();
			if(seat!=null) {
				System.out.println("자리번호 : "+seat.getSeatNo());
				System.out.println("ID : "+seat.getMember().getMemId());
				System.out.println("회원이름 : "+seat.getMember().getMemName());
				System.out.println("PHONE : : "+seat.getMember().getMemPhone());
				System.out.println("EMAIL : "+seat.getMember().getMemEmail());
				int useTime = (int)(Math.round((new Date().getTime() - seat.getChargingTime().getTime())/1000.0/60.0));
				int remainingTime = seat.getMember().getMemTime() - useTime;
				printTime(remainingTime);
				System.out.println();
				return;
			}
			System.out.println("해당 자리에 사용자가 존재하지 않습니다.");
			PrintUtil.print();
			return;
		}catch(Exception e) {
			System.out.println("해당하는 자리번호가 존재하지 않습니다.");
		}
	}
}
