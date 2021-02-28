package com.abyiyi.dynamic.demo2;

public class CountServiceImpl implements CountService {

	private int count = 0;

	public int count() {
        return count ++;
	}
}
