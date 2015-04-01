package com.lishaodong.drcore.tasks;

import com.lishaodong.drcore.DRCore;
import com.lishaodong.drcore.RunnableTask;
import com.lishaodong.drcore.data.DRData;

public class SaveDataTask extends RunnableTask<DRCore>{

	public SaveDataTask(DRCore plugin) {
		super(plugin);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run() {
		DRData.saveAll();
	}
	

}
