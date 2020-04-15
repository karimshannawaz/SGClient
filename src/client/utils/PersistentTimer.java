package client.utils;

/**
 * Used for registering any per-tick timers
 * 
 * @author Karimshan
 * 
 */
public abstract class PersistentTimer implements Runnable {
	

	/**
	 * How many seconds the event should wait between each cycle.
	 * 
	 * @delay
	 */
	protected double delay;

	/**
	 * if the task has been stopped
	 * 
	 * @param stop
	 */
	protected boolean isStopped;

	/**
	 * Create an event with the specified tick time.
	 * 
	 * @param tick
	 */
	public PersistentTimer(double delay) {
		this.delay = delay;
	}

	public PersistentTimer(String string, double d) {
		this.delay = d;
	}

	/**
	 * Gets the current delay.
	 * 
	 * @param delay
	 * @return
	 */
	public double getDelay() {
		return delay;
	}

	/**
	 * Sets the Task delay
	 * 
	 * @param d
	 */
	public void setDelay(double d) {
		this.delay = d;
	}

	/**
	 * Checks if the task has stopped
	 * 
	 * @param stop
	 * @return
	 */
	public boolean isStopped() {
		return isStopped;
	}

	/**
	 * Stops current Task.
	 */
	public void stop() {
		whenStopped();
		isStopped = true;
	}
	
	/**
	 * What to do after the game tick gets stopped.
	 * @return
	 */
	public void whenStopped() {
		
	}

	/**
	 * Schedules task.
	 * 
	 * @param task
	 * @param run
	 */
	public void executeTask() {
		if(isStopped())
			return;
		try {
			run();
		} catch (Throwable t) {
			t.printStackTrace();
			stop();
		}
	}

	/**
	 * Abstract void run.
	 * 
	 * @param run
	 */
	public abstract void run();
}