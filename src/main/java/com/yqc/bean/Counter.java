package com.yqc.bean;

/**
 * @author Rickon
 * @date 2023/4/6 20:10
 * @description
 */

public class Counter {
	private int count;

	public synchronized void add() {
		count++;
	}

	public synchronized void sub() {
		count--;
	}

	public synchronized int getCount() {
		return this.count;
	}
}
