package com.yangjie.util;

public class Shuzuhebing {

	public static void main(String[] args) {
		String[] num1 = new String[] { "1", "2" };
		String[] num2 = new String[] { "3", "4" };
		String[] num3 = hebing(num1, num2);
		for (int i = 0; i < num3.length; i++) {
			System.out.println(num3[i]);
		}
	}

	public static String[] hebing(String[] num1, String[] num2) {
		String newhebingshuzu[] = new String[num1.length + num2.length];
		int index = 0;
		for (int i = 0; i < num1.length; i++) {
			newhebingshuzu[index] = num1[i];
			index++;
		}
		for (int i = 0; i < num2.length; i++) {
			newhebingshuzu[index] = num2[i];
			index++;
		}
		return newhebingshuzu;

	}

}
