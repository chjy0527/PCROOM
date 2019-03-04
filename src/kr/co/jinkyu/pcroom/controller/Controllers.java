package kr.co.jinkyu.pcroom.controller;

import java.util.Scanner;

public abstract class Controllers implements Controller {
	public Scanner sc = new Scanner(System.in);
	protected String input(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}
}
