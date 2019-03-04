package kr.co.jinkyu.pcroom.vo;

public class Member {
	private int memNo; // 회원번호
	private String memId; // 회원아이디
	private String memPw; // 회원비밀번호
	private String memName; // 회원이름
	private String memPhone; // 회원번호
	private String memEmail; // 회원메일
	private int memTime; // 회원 남은 시간
	private String managerPresence;
	private String loginPresence;
	private Fare memFare;
	private int seatNo;
	
	public int getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}
	public Fare getMemFare() {
		return memFare;
	}
	public void setMemFare(Fare memFare) {
		this.memFare = memFare;
	}
	public String getManagerPresence() {
		return managerPresence;
	}
	public void setManagerPresence(String managerPresence) {
		this.managerPresence = managerPresence;
	}
	public String getLoginPresence() {
		return loginPresence;
	}
	public void setLoginPresence(String loginPresence) {
		this.loginPresence = loginPresence;
	}
	public int getMemNo() {
		return memNo;
	}
	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPw() {
		return memPw;
	}
	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemPhone() {
		return memPhone;
	}
	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public int getMemTime() {
		return memTime;
	}
	public void setMemTime(int memTime) {
		this.memTime = memTime;
	}
}
