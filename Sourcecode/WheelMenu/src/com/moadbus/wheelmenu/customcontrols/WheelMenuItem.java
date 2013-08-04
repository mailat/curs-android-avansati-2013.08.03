package com.moadbus.wheelmenu.customcontrols;

import java.util.Comparator;
import java.util.List;

import android.graphics.Bitmap;

/**
 * WheelMenuItem - pojo for the custom control wheel
 * 
 * version: 1.0
 */
public class WheelMenuItem {

	private String name;                         //Name of the item.
	private float x;                             //Margin Left Coordinate
    private float y;                             //Margin Top Coordinate
    private int speed;                           //Speed for moving on each FPS 
    private double angle;                           //Angle in case rotation in needed
    private Bitmap bitmapResource;               //Regular bitmap resource
    private Bitmap bitmapTouchedResource;        //Touched bitmap resource
    private boolean isTouched;                   //flag to know which bitmap to draw
    private List<WheelTextProperties> lstText;                         //text that will be drawn
   
	//Constructor for dynamic items as buttons and other elements that will interact with Touch events 
	public WheelMenuItem(String name, int x, int y, int speed, int angle, Bitmap bitmapResource, Bitmap bitmapTouchedResource, boolean isTouched, List<WheelTextProperties> lstText) {
		super();
		
		this.name = name;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.angle = angle;
		this.bitmapResource = bitmapResource;
		this.bitmapTouchedResource = bitmapTouchedResource;
		this.isTouched = isTouched;
		this.lstText = lstText;
		
	}
	
	public WheelMenuItem(String name, int x, int y) {
		super();
		
		this.name = name;
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
		if (this.angle>360)
		{
			this.angle = 360-this.angle;
		}
		
	}
	public Bitmap getBitmapResource() {
		return bitmapResource;
	}
	public void setBitmapResource(Bitmap bitmapResource) {
		this.bitmapResource = bitmapResource;
	}
	public boolean isTouched() {
		return isTouched;
	}
	public void setTouched(boolean isTouched) {
		this.isTouched = isTouched;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Bitmap getBitmapTouchedResource() {
		return bitmapTouchedResource;
	}

	public void setBitmapTouchedResource(Bitmap bitmapTouchedResource) {
		this.bitmapTouchedResource = bitmapTouchedResource;
	}
	
	public List<WheelTextProperties> getLstText() {
		return lstText;
	}

	public void setLstText(List<WheelTextProperties> lstText) {
		this.lstText = lstText;
	}

	@SuppressWarnings("rawtypes")
	//Used when deciding which item is closest to the fork
	public static Comparator SortByTotal = new Comparator<WheelMenuItem>() {
			public int compare(WheelMenuItem one, WheelMenuItem other) {
	            return new Integer((int) (one.getX()+one.getY())).compareTo(new Integer((int) (other.getX()+other.getY())));
	        }
		};
}
