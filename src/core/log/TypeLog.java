package core.log;

public enum TypeLog {
	DEBUGGING, INFO, ESSENTIAL, WARNING, SEVERE, CRITICAL;
	
	public boolean isErrorTypeLog() {
		return equals(WARNING) || equals(SEVERE);
	}
	
	public boolean isPriorityTypeLog() {
		return equals(ESSENTIAL) || equals(CRITICAL);
	}
}
