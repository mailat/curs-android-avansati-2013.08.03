package com.moadbus.wheelmenu.customcontrols;

public class WheelTextProperties {
	
	int textWidth;
	int textHeight;
	String text;

	public WheelTextProperties(String text, int textWidth, int textHeight) {
		super();
		this.textWidth = textWidth;
		this.textHeight = textHeight;
		this.text = text;
	}

	public int getTextWidth() {
		return textWidth;
	}

	public void setTextWidth(int textWidth) {
		this.textWidth = textWidth;
	}

	public int getTextHeight() {
		return textHeight;
	}

	public void setTextHeight(int textHeight) {
		this.textHeight = textHeight;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}