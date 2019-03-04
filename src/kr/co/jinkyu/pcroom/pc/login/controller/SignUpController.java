package kr.co.jinkyu.pcroom.pc.login.controller;

import org.apache.ibatis.session.SqlSession;

import kr.co.jinkyu.pcroom.common.db.MyAppSqlConfig;
import kr.co.jinkyu.pcroom.controller.Controllers;
import kr.co.jinkyu.pcroom.pc.dao.MemberDAO;
import kr.co.jinkyu.pcroom.vo.Member;

/**
 * 회원가입
 * 회원가입 하기전에 동일한 ID 있는지 확인
 *
 */
public class SignUpController extends Controllers{
	
	private MemberDAO mMapper;
	
	public SignUpController() {
		SqlSession session = MyAppSqlConfig.getSqlSession();
		mMapper = session.getMapper(MemberDAO.class);
	}
	private boolean checkSignUp(Member member) {
		Member m = mMapper.checkMemberSignUp(member.getMemId());
		return m==null;
	}
	
	public void service() {
		Member member = new Member();
		member.setMemId(input("ID : "));
		if(checkSignUp(member)) {
			member.setMemPw(input("PW : "));
			member.setMemName(input("이름 : "));
			member.setMemPhone(input("PHONE : "));
			member.setMemEmail(input("EMAIL : "));
			mMapper.insertMember(member);
			System.out.println("회원가입이 완료되었습니다. ");
			System.out.println("로그인 화면으로 이동해주세요.");
			return;
		}
		System.out.println("이미 가입중이시거나 중복된 ID가 존재합니다.");
	}
}
