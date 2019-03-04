package util;

public class PrintUtil {
	public static void print() {
		print(20,"-");
	}
	public static void print(int cnt) {
		print(cnt,"-");
	}
	public static void print(String ch) {
		print(20,ch);
	}
	public static void print(int cnt,String ch) {
		for(int i=0;i<cnt;i++) {
			System.out.print(ch);
		}
		System.out.println();
	}
	/*
	public void print() {
		System.out.println("-------------------------");
	}
	public void print(int cnt) {
		for(int i=0;i<cnt;i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	public void print(int cnt,String ch) {
		for(int i=0;i<cnt;i++) {
			System.out.print(ch);
		}
		System.out.println();
	}
	*/
}
