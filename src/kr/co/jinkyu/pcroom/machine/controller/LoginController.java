package kr.co.jinkyu.pcroom.machine.controller;

import org.apache.ibatis.session.SqlSession;

import kr.co.jinkyu.pcroom.common.db.MyAppSqlConfig;
import kr.co.jinkyu.pcroom.controller.Controllers;
import kr.co.jinkyu.pcroom.pc.dao.MemberDAO;
import kr.co.jinkyu.pcroom.vo.Member;

public class LoginController extends Controllers{
	
	private MemberDAO mMapper;
	
	public LoginController() {
		SqlSession session = MyAppSqlConfig.getSqlSession();
		mMapper = session.getMapper(MemberDAO.class);
	}
	
	public void service() {
		/*
		 * SelectFareListController
		 */
		Member member = new Member();
		member.setMemId(input("ID : "));
		member.setMemPw(input("Pw : "));
		
		Member m = mMapper.checkMemberLogin(member);
		
		if(m!=null) {
			new TimeChargingController2(m).service();
			return;
		}
		System.out.println("ID 또는 PW가 잘못 되었습니다.");
		System.out.println("ID가 없을 시 컴퓨터에 접속해 회원가입을 해주세요.");
	}
}
