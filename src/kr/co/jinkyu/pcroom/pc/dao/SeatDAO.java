package kr.co.jinkyu.pcroom.pc.dao;

import java.util.List;

import kr.co.jinkyu.pcroom.vo.Member;
import kr.co.jinkyu.pcroom.vo.Seat;

public interface SeatDAO {
	List<Seat> selectSeatList();
	Seat selectSeatInfoByMemNo(int memNo);
	void insertSeat(Member member);
	Seat selectSeatInfoBySeatNo(int seatNo);
	void updateSeatChargingTimeByMemNo(int memNo);
}
