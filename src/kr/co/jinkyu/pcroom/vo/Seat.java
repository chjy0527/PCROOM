package kr.co.jinkyu.pcroom.vo;

import java.util.Date;

public class Seat {
	private int seatUseNo; // 자리사용번호
	private int seatNo; // 자리번호
	private int memNo; // 회원번호
	private Date startTime; // 시작시간
	private Date endTime; // 종료시간
	private Member member; // 자리마다 회원에 대한 정보 받기
	private Date chargingTime;
	
	public Date getChargingTime() {
		return chargingTime;
	}
	public void setChargingTime(Date chargingTime) {
		this.chargingTime = chargingTime;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public int getSeatUseNo() {
		return seatUseNo;
	}
	public void setSeatUseNo(int seatUseNo) {
		this.seatUseNo = seatUseNo;
	}
	public int getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}