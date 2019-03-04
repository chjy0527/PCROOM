package kr.co.jinkyu.pcroom.pc.member.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.jinkyu.pcroom.common.db.MyAppSqlConfig;
import kr.co.jinkyu.pcroom.controller.Controller;
import kr.co.jinkyu.pcroom.controller.Controllers;
import kr.co.jinkyu.pcroom.pc.dao.FoodKindDAO;
import kr.co.jinkyu.pcroom.pc.food.controller.BeverageController;
import kr.co.jinkyu.pcroom.pc.food.controller.FingerFoodController;
import kr.co.jinkyu.pcroom.pc.food.controller.PaymentController;
import kr.co.jinkyu.pcroom.pc.food.controller.SnackController;
import kr.co.jinkyu.pcroom.vo.FoodKind;
import kr.co.jinkyu.pcroom.vo.Member;
import kr.co.jinkyu.pcroom.vo.OrderList;
import util.PrintUtil;

public class FoodMenuController extends Controllers{
	private FoodKindDAO fdkMapper; // mapper를 만들면 생성자를 만들어야함, DB에 접근해야하기 때문에
	private Member member; // member 정보가 있어야 한다
	private List<OrderList> oList;
	
	public FoodMenuController() {}
	public FoodMenuController(Member member) {
		this.member = member;
		SqlSession session = MyAppSqlConfig.getSqlSession(); // DB에 접근
		fdkMapper = session.getMapper(FoodKindDAO.class); // FoodDAO가 interface이니까 .class를 해줘서 자동으로 클래스 생성
		oList = new ArrayList<>();
	}
	
	public String choiceMenu() {
		System.out.println("음식 주문");
		PrintUtil.print();
		
		List<FoodKind> list = fdkMapper.selectFoodkindList();
		for(FoodKind fdk : list) {
			System.out.print(fdk.getFdkNo() + ". ");
			System.out.println(fdk.getFdkKinds());
		}
		
		System.out.println("4. 결제");
		System.out.println("0. 이전");
		PrintUtil.print();
		System.out.print("종류를 선택하세요 : ");
		return sc.nextLine();
	}
	
	/**
	   1. 분식류 FingerFoodController
	   2. 과자류 SnackController
	   3. 카페류 BeverageController
	   */
	 
	public void service() {
		while(true) {
			if(MemberScrController.foodCompulsion) return;
			Controller ctrl = null;  
			switch(choiceMenu()){
				case "1" : 
					ctrl = new FingerFoodController(oList); // FingerFoodController를 불러온다
					break;
				case "2":
					ctrl = new SnackController(oList); // SnackController를 불러온다
					break;
				case "3" :
					ctrl = new BeverageController(oList); //BeverageController를 불러온다
					break;
				case "4" : 
					ctrl = new PaymentController(oList,member); // PaymentController를 불러온다
					break;
				case "0":
					return;
				default : 
					System.out.println();
					System.out.println("잘못 입력하셨습니다.");
					System.out.println("다시 선택해주세요.");
			}
			if(ctrl != null) {
				ctrl.service(); // ctrl이 null이 아니면 service를 호출
			}
		}
	}
}



