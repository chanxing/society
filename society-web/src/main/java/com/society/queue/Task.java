package com.society.queue;

/**
 * 队列任务
 * 
 * @author zhenzhijian
 * 
 * @param <T>
 */
public class Task<T> {

	/**
	 * 任务
	 */
	private T t;

	/**
	 * 重试次数
	 */
	private int retry;

	public Task(T t, int retry) {
		super();
		this.t = t;
		this.retry = retry;
	}

	public T getT() {
		return t;
	}

	public int getRetry() {
		return retry;
	}

	public void decrRetry() {
		this.retry--;
	}

}
