package kr.co.jinkyu.pcroom.pc.food.controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.jinkyu.pcroom.common.db.MyAppSqlConfig;
import kr.co.jinkyu.pcroom.controller.Controllers;
import kr.co.jinkyu.pcroom.pc.dao.OrderDAO;
import kr.co.jinkyu.pcroom.pc.member.controller.MemberScrController;
import kr.co.jinkyu.pcroom.vo.Member;
import kr.co.jinkyu.pcroom.vo.Order;
import kr.co.jinkyu.pcroom.vo.OrderList;
import util.PrintUtil;

public class PaymentController extends Controllers {
	private List<OrderList> oList; // 장바구니
	private Member member;
	private OrderDAO oMapper;

	public PaymentController(List<OrderList> oList,Member member) {
		this.oList = oList;
		this.member = member;
		SqlSession session = MyAppSqlConfig.getSqlSession();
		oMapper = session.getMapper(OrderDAO.class);
	}
	
	private int choiceMenu() {
		int sum = 0;
		// oList 장바구니 계산
		for (int k = 0; k < oList.size(); k++) {
			sum += oList.get(k).getFood().getFoodPrice() * oList.get(k).getQuantity();
		}
		PrintUtil.print();
		System.out.printf("주문금액은 %d원 입니다.%n",sum);
		PrintUtil.print();
		System.out.println("1. 카드");
		System.out.println("2. 현금");
		PrintUtil.print();
		System.out.print("결제 방법을 선택해주세요 : ");
		return Integer.parseInt(sc.nextLine());
	}
	private void order() {
		// insert!!
		// for문
		for (int i = 0; i < oList.size(); i++) {
			Order order = new Order();
			order.setFoodSelNo(i + 1);
			order.setSeatNo(member.getSeatNo());
			order.setOrderQuantity(oList.get(i).getQuantity());
			order.setFoodNo(oList.get(i).getFood().getFoodNo());
			order.setMemNo(member.getMemNo());
			oMapper.insertOrderList(order);
		}
	}


	public void service() {
		while (true) {
			if(MemberScrController.foodCompulsion) return;
			switch (choiceMenu()) {
			case 1 :
				System.out.println("카드결제를 선택했습니다.");
				System.out.println("카드결제가 완료되었습니다.");
				// 메서드(order table에 insert)
				order();
				MemberScrController.foodCompulsion = true;
				break;
			case 2 :
				System.out.println("현금결제를 선택했습니다.");
				System.out.println("현금결제가 완료되었습니다.");
				// 메서드(order table에 insert)
				order();
				MemberScrController.foodCompulsion = true;
				break;
			default:
				System.out.println("잘못 입력됐습니다.");
				System.out.println("다시 입력해주세요.");
			}
		}
	}
}


