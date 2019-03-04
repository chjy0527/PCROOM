package kr.co.jinkyu.pcroom.pc.food.controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.jinkyu.pcroom.common.db.MyAppSqlConfig;
import kr.co.jinkyu.pcroom.controller.Controllers;
import kr.co.jinkyu.pcroom.pc.dao.FoodDAO;
import kr.co.jinkyu.pcroom.vo.Food;
import kr.co.jinkyu.pcroom.vo.OrderList;
import util.PrintUtil;

public class BeverageController extends Controllers {
	private FoodDAO foodMapper;
	List<OrderList> oList;

	public BeverageController(List<OrderList> oList) {
		this.oList = oList;
		SqlSession session = MyAppSqlConfig.getSqlSession();
		foodMapper = session.getMapper(FoodDAO.class);
	}

	public void service() {
		System.out.println("카페류를 선택했습니다.");
		System.out.println();
		List<Food> fList = foodMapper.selectFoodList(3);

		for (int i = 0; i < fList.size(); i++) {
			Food f = fList.get(i);
			System.out.print(i + 1);
			System.out.print(". " + f.getFoodName() + ", ");
			System.out.println(f.getFoodPrice());
		}

		PrintUtil.print();
	      int menu, quantity;
	      try {
	         System.out.print("원하는 메뉴를 선택하세요 : ");
	         menu = Integer.parseInt(sc.nextLine());
	         System.out.print("주문 수량을 입력하세요 : ");
	         quantity = Integer.parseInt(sc.nextLine());
	      }catch(Exception e) {
	         System.out.println("숫자를 입력하세요!");
	         return;
	      }
	      PrintUtil.print();

		Boolean flag = true;
		for (int i = 0; i < fList.size(); i++) {
			Food food = fList.get(i);
			if ((i + 1) == menu) {
				boolean flag1 = true;
				OrderList orderList = new OrderList();
				orderList.setFood(food);
				orderList.setQuantity(quantity);
				for (int j = 0; j < oList.size(); j++) {
					if (food.getFoodNo() == oList.get(j).getFood().getFoodNo()) {
						oList.get(j).setQuantity(quantity + oList.get(j).getQuantity());
						flag1 = false;
					}
				}
				if (flag1) {
					oList.add(orderList);
				}

				System.out.printf("%s가(이) %d개 주문되었습니다.", food.getFoodName(), quantity);
				System.out.println();
				flag = false;
			}
		}

		if (flag) {
			System.out.println("해당하는 메뉴가 없습니다.");
			System.out.println("다시 입력해주세요.");
		}
		printMenuList();

		calSum();

	}

	public void printMenuList() {
		PrintUtil.print();
		System.out.println("주문내역");
		PrintUtil.print();
		for (int i = 0; i < oList.size(); i++) {
			OrderList orderList = oList.get(i);
			System.out.println(orderList.getFood().getFoodName() + orderList.getQuantity() + "개");
		}
	}

	public void calSum() {
		int sum = 0;
		for (int k = 0; k < oList.size(); k++) {
			sum += oList.get(k).getFood().getFoodPrice() * oList.get(k).getQuantity();
		}
		System.out.printf("총 금액: %d원%n", sum);
		PrintUtil.print();
	}
}




