package gui.events;

import java.util.EventListener;

public interface GUIComponentClickEventListener extends EventListener {
	public void componentClicked(GUIComponentClickEvent e);
}
