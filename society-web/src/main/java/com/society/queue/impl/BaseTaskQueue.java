package com.society.queue.impl;

import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.society.queue.Task;
import com.society.queue.TaskQueue;

public abstract class BaseTaskQueue<T> implements TaskQueue<T> {

	private static Logger logger = LoggerFactory.getLogger(BaseTaskQueue.class);

	private LinkedBlockingQueue<Task<T>> QUEUE = new LinkedBlockingQueue<>();

	{
		for (int i = 0; i < threadNum(); i++) {
			Thread thread = new Thread() {
				@Override
				public void run() {
					work();
				}

			};
			thread.setName("thread" + i);
			thread.start();
		}
	}

	private void work() {

		while (true) {
			Task<T> task = take();
			if (task == null) {
				continue;
			}
			boolean result = false;
			try {
				result = work(task.getT(), task.getRetry());
			} catch (Exception e) {
				logger.warn("task error", e);
			}

			if (!result) {
				task.decrRetry();
				if (task.getRetry() > 0) {
					push(task);
				}
			}

		}
	}

	private void push(Task<T> task) {
		try {
			QUEUE.put(task);
		} catch (InterruptedException e) {
		}
	}

	@Override
	public void push(T t) {
		push(t, 1);
	}

	@Override
	public void push(T t, int retry) {
		push(new Task<T>(t, retry));
	}

	private Task<T> take() {
		try {
			return QUEUE.take();
		} catch (InterruptedException e) {
			return null;
		}
	}

	protected abstract boolean work(T t, int left);

	protected int threadNum() {
		return 1;
	}

	@Override
	public int size() {
		return QUEUE.size();
	}

}
