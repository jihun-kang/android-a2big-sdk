package com.a2big.android.library.core;

/*
 * In order to reduce the number of threads inside the application,
 * SharedTask class enables different tasks handler class to share the same thread.
 * The tasks may not be executed straight away.
 *
 * @author jihun,kang
 *
 */

public class SharedTask extends AbstractTaskThread {

	public SharedTask(String pThreadName) {
		super(pThreadName);
	}
	
	/**
	 * It enables to encapsulate an ITaskHandler with its parameters in order to resue the AbstractTaskThread.
	 * 
	 * @author manyeon
	 *
	 */
	private static final class GenericTask {
		private ITaskHandler mTaskHandler = null;
		private Object mParam = null;
		
		public GenericTask(ITaskHandler pTaskHandler, Object pParam) {
			mTaskHandler = pTaskHandler;
			mParam = pParam;
		}
		
		public ITaskHandler getHandler() {
			return mTaskHandler;
		}
		
		public Object getParam() {
			return mParam;
		}
	}
	
	public void addTask(ITaskHandler pTaskHandler, ITaskType pTaskType, Object pParam) {
		super.addTask(pTaskType, new GenericTask(pTaskHandler, pParam));
	}
	
	public void stopThread() {
		super.terminated();
	}
	
	/**
	 * It is called in a separate thread to handle the task by the ITaskHandler
	 * given in parameter of addTask()
	 */
	@Override
	protected void handleTask(ITaskType pTaskType, Object pParam) {
		GenericTask task = (GenericTask)pParam;
		task.getHandler().handleTask(pTaskType, task.getParam());
	}

}
