package states;

import java.awt.Color;
import java.awt.Point;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

public class MenuItem {

	private String text;
	private UnicodeFont font;

	private Point position;
	private int destinationStateId;
	
	@SuppressWarnings("unchecked")
	public MenuItem(String text_, Point pos, UnicodeFont font, int destination) {
		this.text = text_;
		this.position = pos;
		this.destinationStateId = destination;
		
		this.font = font;
		this.font.addAsciiGlyphs();
		this.font.addGlyphs(400, 600);
		this.font.getEffects().add(new ColorEffect(Color.WHITE));
		try {
			this.font.loadGlyphs();
		} catch(SlickException e){
			
		}
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public int getDestinationStateId() {
		return destinationStateId;
	}

	public void setDestinationStateId(int destinationStateId) {
		this.destinationStateId = destinationStateId;
	}	
	
	public UnicodeFont getFont() {
		return font;
	}

	public void setFont(UnicodeFont font) {
		this.font = font;
	}
}
