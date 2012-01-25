package workers;

public class StopBit {
	private boolean stopped = false;
	
	public void stop(){
		stopped = true;
	}
	
	public boolean isStopped(){
		return stopped;
	}
}
