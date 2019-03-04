package kr.co.jinkyu.pcroom.controller;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.jinkyu.pcroom.common.db.MyAppSqlConfig;
import kr.co.jinkyu.pcroom.pc.dao.SeatDAO;
import kr.co.jinkyu.pcroom.vo.Seat;
import util.PrintUtil;

public class SeatListController extends Controllers{
	
	private SeatDAO sMapper;
	private List<Seat> list;
	
	public SeatListController() {
		SqlSession session = MyAppSqlConfig.getSqlSession();
		sMapper = session.getMapper(SeatDAO.class);
	}
	private void printTime(int remainingTime) {
		if(remainingTime > 0) {
			System.out.printf("(%02d:%02d)",remainingTime/60,remainingTime%60);
		}
		else {
			System.out.print("(00:00)");
		}
	}
	private int checkSeat(int seatNo) {
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getSeatNo() == seatNo) {
				return i;
			}
		}
		return -1;
	}
	public void service() {
		list = sMapper.selectSeatList();
		PrintUtil.print();
		System.out.println("자리조회");
		PrintUtil.print(40,"-");
		System.out.println("좌석번호\t\t * : 비었음");
		System.out.println("(남은시간)");
		System.out.println("(시간:분)");
		System.out.println();
		
		for(int i=1;i<=50;i++) {
			System.out.print(i+"\t");
			if(i%5==0) {
				System.out.println();
				int j = i/5;
				for(int k=5*(j-1);k<5*j;k++) {
					int idx = checkSeat(k+1);
					if(idx!=-1) {
						Seat seat = list.get(idx);
						int useTime = (int)(Math.round((new Date().getTime() - seat.getChargingTime().getTime())/1000.0/60.0));
						int remainingTime = seat.getMember().getMemTime() - useTime;
						printTime(remainingTime);
					}
					else {
						System.out.print("*");
					}
					System.out.print("\t");
				}
				System.out.println();
			}
		}
		PrintUtil.print(40,"-");
	}
}
