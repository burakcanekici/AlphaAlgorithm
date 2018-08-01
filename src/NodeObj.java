
public class NodeObj {
	private String startEvent;
	private String finishEvent;
	private String value;
	private int connectionNumber;
	private boolean isStart;
	private boolean isFinish;
	
	public int getConnectionNumber() {
		return connectionNumber;
	}
	public void setConnectionNumber(int connectionNumber) {
		this.connectionNumber = connectionNumber;
	}
	public boolean isStart() {
		return isStart;
	}
	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}
	public boolean isFinish() {
		return isFinish;
	}
	public void setFinish(boolean isFinish) {
		this.isFinish = isFinish;
	}
	public String getStartEvent() {
		return startEvent;
	}
	public void setStartEvent(String startEvent) {
		this.startEvent = startEvent;
	}
	public String getFinishEvent() {
		return finishEvent;
	}
	public void setFinishEvent(String finisEvent) {
		this.finishEvent = finisEvent;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	NodeObj(String pEvent1, String pEvent2, boolean pIsStart, boolean pIsFinish){
		this.startEvent = pEvent1;
		this.finishEvent = pEvent2;
		this.value = pEvent1 + ">" + pEvent2;
		this.isStart = pIsStart;
		this.isFinish = pIsFinish;
		this.connectionNumber = 1;
	}
	
	void IncreaseNumber(){
		this.connectionNumber++;
	}
}
