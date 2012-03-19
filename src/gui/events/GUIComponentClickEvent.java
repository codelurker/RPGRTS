package gui.events;

import java.util.EventObject;

public class GUIComponentClickEvent extends EventObject {
	
	private int x;
	private int y;

	public GUIComponentClickEvent(Object source, int x, int y) {
		super(source);
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
