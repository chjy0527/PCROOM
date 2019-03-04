package kr.co.jinkyu.pcroom.pc.manager.controller;

import org.apache.ibatis.session.SqlSession;

import kr.co.jinkyu.pcroom.common.db.MyAppSqlConfig;
import kr.co.jinkyu.pcroom.controller.Controllers;
import kr.co.jinkyu.pcroom.pc.dao.MemberDAO;
import kr.co.jinkyu.pcroom.pc.login.controller.LogoutController;
import kr.co.jinkyu.pcroom.vo.Member;
import util.PrintUtil;

public class DeleteSeatBySeatNoController extends Controllers{
	
	private MemberDAO mMapper;
	
	public DeleteSeatBySeatNoController() {
		SqlSession session = MyAppSqlConfig.getSqlSession();
		mMapper = session.getMapper(MemberDAO.class);
	}
	public void service() {
		try {
			PrintUtil.print();
			int seatNo = Integer.parseInt(input("자리번호를 입력하세요(끄기) : "));
			PrintUtil.print();
			int memNo = mMapper.selectMemNoBySeatNo(seatNo);
			if(memNo!=0) {
				Member member = mMapper.selectMemberByMemNo(memNo);
				new LogoutController(member).service();
			}else {
				System.out.println("해당 자리에 회원이 존재하지 않습니다.");
			}
			PrintUtil.print();
		}catch(Exception e) {
			System.out.println("해당하는 자리번호가 존재하지 않습니다.");
		}
	}
}
