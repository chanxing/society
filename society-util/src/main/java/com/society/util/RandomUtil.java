package com.society.util;

public class RandomUtil {
	private static final int[] NUMBER_SEEDS = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	public static String randomNumbers(int len) {
		if (len <= 0) {
			return "";
		}
		StringBuilder builder = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			int index = (int) (Math.random() * NUMBER_SEEDS.length);
			builder.append(NUMBER_SEEDS[index]);
		}
		return builder.toString();
	}

	public static void main(String[] args) {
		System.out.println("----randomNumbers: " + randomNumbers(10));
	}
}
