package com.moadbus.wheelmenu.utils;

/**
 * SessionValues - Holds initial data, session based
 * 
 * version: 1.0
 */
public class SessionValues { 
	
	private static SessionValues instance;
	private int screenWidth = 800; 
	private int screenHeight = 480;
	private String wheelMenuPressedButton="";
	
	private SessionValues() { 
	} 
	
	public int getInsamplesize() {
		int insamplesize = 1;
		if ( this.screenWidth < 400 )
			insamplesize = 2;
		
		return (insamplesize);
	}
	
	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}
	
	// For lazy initialization 
	public static synchronized SessionValues getInstance() { 
		if (instance == null) { 
			instance = new SessionValues(); 
		} 
		return instance; 
	} 
	
	public String getWheelMenuPressedButton() {
		return wheelMenuPressedButton;
	}

	public void setWheelMenuPressedButton(String wheelMenuPressedButton) {
		this.wheelMenuPressedButton = wheelMenuPressedButton;
	}	
	
} 
