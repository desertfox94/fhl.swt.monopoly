package fhl.swt.monopoly.model;

public class Field {

	private FieldType type;
	private EventTrigger trigger;
	public FieldType getType() {
		return type;
	}
	public void setType(FieldType type) {
		this.type = type;
	}
	public EventTrigger getTrigger() {
		return trigger;
	}
	public void setTrigger(EventTrigger trigger) {
		this.trigger = trigger;
	}
	
}
