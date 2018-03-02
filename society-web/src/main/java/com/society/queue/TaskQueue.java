package com.society.queue;

/**
 * 任务队列
 * 
 * @author zhenzhijian
 * 
 * @param <T>
 */
public interface TaskQueue<T> {

	/**
	 * @param t
	 *            任务
	 * @param retry
	 *            重试次数
	 */
	void push(T t, int retry);

	/**
	 * @param t
	 *            任务
	 */
	void push(T t);

	/**
	 * 队列长度
	 * 
	 * @return
	 */
	public int size();

}
