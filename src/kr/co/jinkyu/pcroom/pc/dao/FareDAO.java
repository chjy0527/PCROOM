package kr.co.jinkyu.pcroom.pc.dao;

import java.util.List;

import kr.co.jinkyu.pcroom.vo.Fare;

public interface FareDAO {
	List<Fare> selectFareList();
}
