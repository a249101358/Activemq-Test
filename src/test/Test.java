package test;

import java.util.Arrays;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,7,8,9,10);
		long num = list.stream().filter( b ->  b > 4 ).count();
		System.out.println(num);
		System.out.println(num);
		System.out.println(num);
	}
}
