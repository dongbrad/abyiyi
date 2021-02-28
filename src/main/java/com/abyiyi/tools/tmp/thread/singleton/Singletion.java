package com.abyiyi.tools.tmp.thread.singleton;

/**
 * 静态的内部类
 */
public class Singletion {

	private static class InnerSingletion {
		private static Singletion single = new Singletion();
	}
	
	public static Singletion getInstance(){
		return InnerSingletion.single;
	}
	
}
