package kr.co.jinkyu.pcroom.pc.login.controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.jinkyu.pcroom.common.db.MyAppSqlConfig;
import kr.co.jinkyu.pcroom.controller.Controllers;
import kr.co.jinkyu.pcroom.pc.dao.MemberDAO;
import kr.co.jinkyu.pcroom.pc.dao.SeatDAO;
import kr.co.jinkyu.pcroom.pc.manager.controller.ManagerScrController;
import kr.co.jinkyu.pcroom.pc.member.controller.MemberScrController;
import kr.co.jinkyu.pcroom.vo.Member;
import kr.co.jinkyu.pcroom.vo.Seat;
import util.PrintUtil;

public class LoginController extends Controllers{
	
	private MemberDAO mMapper; 
	private SeatDAO sMapper;
	
	public LoginController() {
		SqlSession session = MyAppSqlConfig.getSqlSession();
		mMapper = session.getMapper(MemberDAO.class);
		sMapper = session.getMapper(SeatDAO.class);
	}
	public void service() {
		/*
		 * 1. 관리자 ID로 접속하면 관리자 화면 보여주기(ManagerController)
		 * 2. 회원 로그인시 회원 화면 보여주기 (MemberController)
		 */
		while(true) {
			
			Member m = new Member();
			m.setMemId(input("ID : "));
			m.setMemPw(input("PW : "));
			// 1. 존재하는 ID와 PW가 있는지 확인
			// 2. 존재한다면 현재 접속중인지 확인
			// 3. 관리자 ID인지 회원ID인지
			Member member = mMapper.checkMemberLogin(m);
			if(member!=null) {
				if(member.getLoginPresence().equals("y")) {
					PrintUtil.print();
					System.out.println("해당 ID는 이미 로그인이 되어있습니다.");
					System.out.println("다른 ID를 입력해주세요.");
					PrintUtil.print();
					continue;
				}
				else if(member.getManagerPresence().equals("y")){
					String seatNo = input("자리번호(0번을 입력하세요) : ");
					if(seatNo.equals("0")) {
						PrintUtil.print();
						System.out.printf("관리자 ID(%s)로 로그인되었습니다.\n",member.getMemId());
						PrintUtil.print();
						new ManagerScrController(member).service();
						return;
					}
					else {
						System.out.println("자리번호를 잘못 입력하셨습니다.");
						continue;
					}
				}
				else {
					try {
						int seatNo = Integer.parseInt(input("자리번호(1~50) : "));
						List<Seat> list = sMapper.selectSeatList();
						boolean flag = true; // 중복된 자리번호가 있는지 확인
						for(int i=0;i<list.size();i++) {
							if(list.get(i).getSeatNo()==seatNo)
								flag = false;
						}
						if(flag) {
							PrintUtil.print();
							System.out.printf("회원 ID(%s)로 로그인되었습니다.\n",member.getMemId());
							PrintUtil.print();
							member.setSeatNo(seatNo);
							new MemberScrController(member).service();
							return;
						}
						System.out.println("이미 사용중인 자리번호입니다.");
						continue;
					}catch(Exception e) {
						System.out.println("error 메세지 : "+e.getMessage());
						continue;
					}
				}
			}
			System.out.println("ID 또는 PW가 잘못되었습니다.");
			System.out.println("(ID가 없을 시 회원가입을 해주세요!)");
			return;
		}
	}
}
