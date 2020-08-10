package com.colin.client.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadPool {

	private final static Logger logger = LoggerFactory.getLogger(ThreadPool.class);

	private int corePoolSize = 31;
	private int maxPoolSize = 31;
	private int keepAliveTime = 60;

	public void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}

	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

	public void setKeepAliveTime(int keepAliveTime) {
		this.keepAliveTime = keepAliveTime;
	}

	private static final BlockingQueue<Runnable> blockQueue = new LinkedBlockingQueue<Runnable>();
	private static ExecutorService exec = null;

	private static final ThreadPool threadPool = new ThreadPool();

	private ThreadPool(){
		init();
	}

	public static ThreadPool getInstance(){
		return threadPool;
	}

	/**
	 * 初始化线程池
	 */
	private void init() {
		if (exec == null) {
			logger.info("ThreadPool init thread_count = " + corePoolSize);
			//固定大小不用Executors.newFixedThreadPool(threadCount),原理也是通过如下方式实现 corePoolSize = maxPoolSize
			exec = new ThreadPoolExecutor(corePoolSize,
					maxPoolSize, 
					keepAliveTime, TimeUnit.SECONDS,
					blockQueue,
					new ThreadPoolExecutor.CallerRunsPolicy());
		}
	}

	/**
	 * 关闭线程池
	 */
	private void shutdown() {
		exec.shutdown();
		while (!exec.isShutdown()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
				logger.error("ThreadPool destroy catch exception:" + e.getMessage());
			}
		}
	}

	/**
	 * 往线程池中添加线程
	 */
	public Future<?> putThread(Runnable r) {
		if (exec.isShutdown()) {
			return null;
		}
		Future<?> futrue = exec.submit(r);
		
		return futrue;
	}

	/**
	 * 实例销毁前关闭线城池
	 * @throws Throwable
	 */
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		shutdown();
	}
}
