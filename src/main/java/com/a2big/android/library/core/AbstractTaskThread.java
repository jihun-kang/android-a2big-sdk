package com.a2big.android.library.core;

import com.a2big.android.library.utils.DevLog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * when a new task is added.
 * The thread must be started after instantiation.
 *
 * @author jihun,kang
 *
 */
public abstract class AbstractTaskThread extends Thread {
	private boolean mTerminated = false;
	private List<Task> mTaskList;			// Task list for the thread
	
	private static final class Task {
		private ITaskType mTaskType = null;
		private Object mParam = null;
		
		public Task(ITaskType pTaskType, Object pParam) {
			mTaskType = pTaskType;
			mParam = pParam;
		}
		
		public ITaskType getTaskType() {
			return mTaskType;
		}
		
		public Object getParam() {
			return mParam;
		}
	}
	
	protected AbstractTaskThread(String pThreadName) {
		super(pThreadName);
		mTaskList = Collections.synchronizedList(new ArrayList<Task>());
	}

	@Override
	public void run() {
		while(!mTerminated) {
			
			while(!mTaskList.isEmpty() && !mTerminated) {
				Task task = mTaskList.remove(0);
				
				try {
					handleTask(task.getTaskType(), task.getParam());
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			try {
				
				synchronized(mTaskList) {
					if(mTaskList.isEmpty() && !mTerminated) {
						mTaskList.wait();
					}
				}
				
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * Add task into the list.
	 * Task is handled by a separate thread.
	 * 
	 * @param pTaskType
	 * @param pParams
	 */
	protected void addTask(ITaskType pTaskType, Object pParams) {
		if(mTerminated) {
			DevLog.LoggingError("Exception: AbstractTaskThread addTask", "Cannot add task because thread is terminated");
			return;
		}
		
		synchronized(mTaskList) {
			mTaskList.add(new Task(pTaskType, pParams));
			mTaskList.notify();
		}
	}
	
	/**
	 * Terminate the thread after the current executed task.
	 * All remaining tasks are not executed.
	 */
	protected void terminated() {
		synchronized(mTaskList) {
			mTerminated = true;
			mTaskList.notify();
		}
	}
	
	/**
	 * It is called in a separate thread to handle the task.
	 * Parameters are thoes given in the addTask()
	 * 
	 * @param pTaskType
	 * @param pParam
	 */
	protected abstract void handleTask(ITaskType pTaskType, Object pParam);
}
