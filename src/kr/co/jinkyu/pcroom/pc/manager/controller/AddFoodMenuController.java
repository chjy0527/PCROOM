package kr.co.jinkyu.pcroom.pc.manager.controller;

import org.apache.ibatis.session.SqlSession;

import kr.co.jinkyu.pcroom.common.db.MyAppSqlConfig;
import kr.co.jinkyu.pcroom.controller.Controllers;
import kr.co.jinkyu.pcroom.pc.dao.FoodDAO;
import kr.co.jinkyu.pcroom.pc.dao.FoodKindDAO;
import kr.co.jinkyu.pcroom.vo.Food;
import kr.co.jinkyu.pcroom.vo.FoodKind;

public class AddFoodMenuController extends Controllers {
	private FoodDAO foodMapper;
	private FoodKindDAO fdkMapper; // mapper를 만들면 생성자를 만들어야함, DB에 접근해야하기 때문에
	
	public AddFoodMenuController() {
		SqlSession session = MyAppSqlConfig.getSqlSession();
		foodMapper = session.getMapper(FoodDAO.class);
		fdkMapper = session.getMapper(FoodKindDAO.class);
	}
	
	public void service() {
		while(true) {
			FoodKind foodkind = new FoodKind();
			Food food = new Food();
			String foodKinds = input("음식종류를 입력해주세요 : ");
			// 음식종류 추가
			// 음식종류가 디비에 있다면 추가하지 않는다.
			foodkind.setFdkKinds(foodKinds);
			FoodKind fk = fdkMapper.selectFoodkindByFoodkindName(foodkind.getFdkKinds());
			if(fk==null) {
				System.out.println("입력하는 음식종류가 존재하지 않습니다.");
				continue;
			}
			
			// 음식추가
			// 음식추가 역시 동일한 이름의 음식이 존재한다면 추가하지 않는다.
			food.setFoodKinds(foodKinds);
			food.setFoodName(input("음식이름을 입력하세요 : "));
			Food f = foodMapper.selectFoodByFoodName(food.getFoodName());
			if(f==null) {
				int price;
				try {
					price = Integer.parseInt(input("음식가격을 입력해주세요 : "));
				}catch(Exception e) {
					System.out.println("숫자를 입력하세요.");
					continue;
				}
				food.setFoodPrice(price);
				foodMapper.insertFood(food);
				System.out.println("음식이 등록되었습니다.");
				return;
			}else {
				System.out.println("동일한 음식이름이 존재합니다.");
				return;
			}
		}
	}
}
