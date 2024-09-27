package domain.common.entities.clientservice;

public class BloodDrawStatus {
	
    private boolean isWaiting;
    private int positionQueue;
    
	public boolean isWaiting() {
		return isWaiting;
	}
	public void setWaiting(boolean isWaiting) {
		this.isWaiting = isWaiting;
	}
	public int getPositionQueue() {
		return positionQueue;
	}
	public void setPositionQueue(int positionQueue) {
		this.positionQueue = positionQueue;
	}
    
    
}
