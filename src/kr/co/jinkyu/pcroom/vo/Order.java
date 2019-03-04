package kr.co.jinkyu.pcroom.vo;

import java.util.Date;

public class Order {
   private int orderNo; // 주문번호
   private int foodSelNo; // 음식주문번호
   private int seatNo; // 자리번호
   private int orderQuantity; // 음식수량
   private Date orderDate; // 음식주문날짜
   private int foodNo; // 음식고유번호
   private int memNo; // 회원번호
   
   public int getOrderNo() {
      return orderNo;
   }
   public void setOrderNo(int orderNo) {
      this.orderNo = orderNo;
   }
   public int getFoodSelNo() {
      return foodSelNo;
   }
   public void setFoodSelNo(int foodSelNo) {
      this.foodSelNo = foodSelNo;
   }
   public int getSeatNo() {
      return seatNo;
   }
   public void setSeatNo(int seatNo) {
      this.seatNo = seatNo;
   }
   public int getOrderQuantity() {
      return orderQuantity;
   }
   public void setOrderQuantity(int orderQuantity) {
      this.orderQuantity = orderQuantity;
   }
   public Date getOrderDate() {
      return orderDate;
   }
   public void setOrderDate(Date orderDate) {
      this.orderDate = orderDate;
   }
   public int getFoodNo() {
      return foodNo;
   }
   public void setFoodNo(int foodNo) {
      this.foodNo = foodNo;
   }
   public int getMemNo() {
      return memNo;
   }
   public void setMemNo(int memNo) {
      this.memNo = memNo;
   }
   
}

