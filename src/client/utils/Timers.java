package client.utils;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Karimshan
 *
 */
public class Timers {
	
	/**
	 * @param task
	 */
	public static ScheduledExecutorService tasks;

	/**
	 * 
	 * @param runnable
	 * @param delay
	 * @param unit
	 */
	public static void scheduleTask(final Runnable runnable, long delay, TimeUnit unit) {
		tasks.schedule(new Runnable() {
			@Override
			public void run() {
				try {
					runnable.run();
				} catch (Exception e) {
				} catch (Throwable t) {
				}
			}
		}, delay, unit);
	}

	/**
	 * 
	 * @param task
	 */
	public static void submit(final Runnable task) {
		tasks.submit(new Runnable() {
			@Override
			public void run() {
				try {
					task.run();
				} catch (Exception e) {
				} catch (Throwable t) {
				}
			}
		});
	}
	
	/**
	 * Registers an event.
	 * 
	 * @param event
	 */
	public static void submit(final PersistentTimer task) {
		submit(task, task.getDelay());
	}

	/**
	 * Registers an event.
	 * 
	 * @param event
	 */
	public static void submit(final PersistentTimer tick, final double delay) {
		scheduleTask(new Runnable() {
			@Override
			public void run() {
				if (!tick.isStopped())
					tick.executeTask();
				else
					return;
				if (!tick.isStopped())
					submit(tick, tick.getDelay());
			}
		}, (long) (delay * 1000), TimeUnit.MILLISECONDS);
	}

}
