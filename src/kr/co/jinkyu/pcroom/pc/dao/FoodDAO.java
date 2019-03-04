package kr.co.jinkyu.pcroom.pc.dao;

import java.util.List;

import kr.co.jinkyu.pcroom.vo.Food;

public interface FoodDAO {
   List<Food> selectFoodList(int fdkNo);
   Food selectFoodByFoodName(String foodName);
   void insertFood(Food food);
}   