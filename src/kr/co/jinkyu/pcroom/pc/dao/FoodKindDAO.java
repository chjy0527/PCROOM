package kr.co.jinkyu.pcroom.pc.dao;

import java.util.List;

import kr.co.jinkyu.pcroom.vo.FoodKind;

public interface FoodKindDAO {
   List<FoodKind> selectFoodkindList();
   FoodKind selectFoodkindByFoodkindName(String fdkName);
   void insertFoodkind(FoodKind foodkind);
}