package kr.co.jinkyu.pcroom.pc.dao;

import java.util.List;

import kr.co.jinkyu.pcroom.vo.Member;
import kr.co.jinkyu.pcroom.vo.Seat;

public interface MemberDAO {
	Member checkMemberLogin(Member member);
	Member checkMemberSignUp(String memId);
	
	Member selectMemberByMemNo(int memNo);
	List<Member> selectMemberList();
	int selectMemNoBySeatNo(int seatNo);
	Seat selectSeatList(int memNo);
	
	void insertMember(Member member);
	
	void updateMemberTime(Member member);
	void updateEndTime(int memNo);
	void updateLoginPresenceWhenLogin(int memNo);
	void updateLoginPresenceWhenLogout(int memNo);
	void updateMemberTimeLogout(Member member);
}

