package kr.co.jinkyu.pcroom.pc.manager.controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.jinkyu.pcroom.common.db.MyAppSqlConfig;
import kr.co.jinkyu.pcroom.controller.Controllers;
import kr.co.jinkyu.pcroom.pc.dao.MemberDAO;
import kr.co.jinkyu.pcroom.vo.Member;
import util.PrintUtil;

public class MemberInfoController extends Controllers {
	private MemberDAO mMapper;
	
	public MemberInfoController() {
		SqlSession session = MyAppSqlConfig.getSqlSession();
		mMapper = session.getMapper(MemberDAO.class);
	}
	
	/**
	 * 보여주고 싶은 것
	 * 멤버 전체를 보여주되, 멤버정보 + 로그인유무 + 자리번호
	 */
	public void service() {
		PrintUtil.print();
		System.out.println("회원정보출력");
		PrintUtil.print();
		System.out.println("번호\tID\tName\tPhone\t\t로그인유무\t자리번호");
		List<Member> list = mMapper.selectMemberList();
		for(Member m : list) {
			if(m.getManagerPresence().equals("n")) {
				System.out.print(m.getMemNo()+"\t");
				System.out.print(m.getMemId()+"\t");
				System.out.print(m.getMemName()+"\t");
				System.out.print(m.getMemPhone()+"\t");
				System.out.print(m.getLoginPresence()+"\t\t");
				if(m.getSeatNo()!=0) {
					System.out.println(m.getSeatNo()+"\t");
				}else {
					System.out.println("*"+"\t");
				}
			}
		}
		PrintUtil.print();
	}
}
