package com.example.ashipdalauncher;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class test {

	ActivityManager activityManager;
	ComponentName beforeRunComponentName;
	Context context;

	// BindClass bc;

	public test(Context context)
	{
		this.context = context;
		activityManager = (ActivityManager) this.context.getSystemService(Context.ACTIVITY_SERVICE);
		// startAppCountService();
	}

	Timer timer;
	TimerTask task;
	ArrayList<ActivityManager.RunningTaskInfo> beforeRunningList;

	public void startAppCountService()
	{
		timer = new Timer();
		task = new TimerTask() {

			@Override
			public void run()
			{
				try
				{
					ArrayList<ActivityManager.RecentTaskInfo> info = (ArrayList<RecentTaskInfo>) activityManager.getRecentTasks(1, Intent.FLAG_ACTIVITY_NEW_TASK);
					ArrayList<ActivityManager.RunningTaskInfo> infotask = (ArrayList<RunningTaskInfo>) activityManager.getRunningTasks(Integer.MAX_VALUE);
					if (info != null)
					{
						RecentTaskInfo recent = info.get(0);
						Intent intent = recent.baseIntent;
						ComponentName componentName = intent.getComponent();
						//String pkageName = componentName.getPackageName();
						//      L.e(beforeRunComponentName + "");
						if (!componentName.equals(beforeRunComponentName))
						{
							beforeRunComponentName = componentName;
						}
					}

					if (infotask != null)
					{
						RunningTaskInfo rInfo = infotask.get(0);
						ComponentName componentNamei = rInfo.baseActivity;
						existBeforeRunningAppList(componentNamei);
						beforeRunningList = infotask;
					}

				} catch (Exception e)
				{
					if (timer != null)
						timer.cancel();

					//     L.e("타이머 동작중 예외발생 타이머 정지됨" + e.getMessage());
				}
			}
		};
		timer.schedule(task, 0, 1000);
	}

	private boolean existBeforeRunningAppList(ComponentName i)
	{
		if (beforeRunningList != null)
		{
			for (int j = 0; j < beforeRunningList.size(); j++)
			{
				if (i == beforeRunningList.get(0).baseActivity)
				{
					return true;
				}
			}
		}

		//  L.e(i + " 종료");
		return false;
	}

	public Timer getTimer()
	{
		return timer;
	}

	public void setTimer(Timer timer)
	{
		this.timer = timer;
	}
}
