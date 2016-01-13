package com.a2big.android.library.core;

/*
 * ITaskHandler represents task within a separate thread.
 * It is used used only when different tasks handler classes share
 * a common thread to run their tasks.
 *
 * @author jihun,kang
 *
 */
public interface ITaskHandler {

	/**
	 * It is called in a separate thread to handle the task.
	 * Parameters are thoes given in the addTask()
	 * This method should not been called directly.
	 * 
	 * @param pTaskType
	 * @param pParam
	 * @throws IllegalArgumentException
	 */
	void handleTask(ITaskType pTaskType, Object pParam) throws IllegalArgumentException;
}
