package com.moadbus.wheelmenu.customcontrols;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.moadbus.wheelmenu.R;
import com.moadbus.wheelmenu.utils.SessionValues;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * WheelMenuSurface - SurfaceView to implement the custom control wheel
 * 
 * version: 1.1
 */
public class WheelMenuSurface extends SurfaceView implements SurfaceHolder.Callback {

	private class SessionVariables
	{
		private int wheelRotationAngle=0;
		private int wheelRotationSpeed=0;
		private float oldTouchedX=0;
		private float oldTouchedY=0;
		
		
		private int oldRotationAngle=0;
	    private int currentRotationAngle=0;
	    private int snapAngle;
		
	    private int buttonWidth=0;
	    private int buttonHeight=0;
	    private int wheelMenuDiameter=0;
	    private int wheelMenuRadius=0;
	    private int wheelMenuCenterX=0;
	    private int wheelMenuCenterY=0;
	    
	    private int wheelForkWidth=0;
	    private int wheelForkHeight=0;
	    private int wheelForkValidX=0;
	    private int wheelForkValidY=0;
	    
	    private Matrix rotationMatrix;
	    private Paint textPaint;
	    private Paint rectanglePaint;
	    private int textSize;
	    
	    private Rect textRectangle;
	    private int maxTextWidth;
	    
	    private int buttonCircleImageWidth;
	    private int buttonCircleImageHeight;
	    private int buttonMargin;
	    
	    private int logoX;
	    private int logoY;
	    
	    private long touchedTime;
	    
	    private boolean buttonPressed;
	    private boolean forkButtonPressed;
		
		public int getWheelRotationAngle() {
			return wheelRotationAngle;
		}
		public void setWheelRotationAngle(int wheelRotationAngle) {
			this.wheelRotationAngle = wheelRotationAngle;
		}
		public int getButtonWidth() {
			return buttonWidth;
		}
		public void setButtonWidth(int buttonWidth) {
			this.buttonWidth = buttonWidth;
		}
		public int getButtonHeight() {
			return buttonHeight;
		}
		public void setButtonHeight(int buttonHeight) {
			this.buttonHeight = buttonHeight;
		}
		public int getWheelMenuDiameter() {
			return wheelMenuDiameter;
		}
		public void setWheelMenuDiameter(int wheelMenuDiameter) {
			this.wheelMenuDiameter = wheelMenuDiameter;
		}
		public int getWheelForkWidth() {
			return wheelForkWidth;
		}
		public void setWheelForkWidth(int wheelForkWidth) {
			this.wheelForkWidth = wheelForkWidth;
		}
		public int getWheelForkHeight() {
			return wheelForkHeight;
		}
		public void setWheelForkHeight(int wheelForkHeight) {
			this.wheelForkHeight = wheelForkHeight;
		}
		public Matrix getRotationMatrix() {
			return rotationMatrix;
		}
		public void setRotationMatrix(Matrix rotationMatrix) {
			this.rotationMatrix = rotationMatrix;
		}
		public int getWheelRotationSpeed() {
			return wheelRotationSpeed;
		}
		public void setWheelRotationSpeed(int wheelRotationSpeed) {
			this.wheelRotationSpeed = wheelRotationSpeed;
		}
		public int getWheelMenuCenterX() {
			return wheelMenuCenterX;
		}
		public void setWheelMenuCenterX(int wheelMenuCenterX) {
			this.wheelMenuCenterX = wheelMenuCenterX;
		}
		public int getWheelMenuCenterY() {
			return wheelMenuCenterY;
		}
		public void setWheelMenuCenterY(int wheelMenuCenterY) {
			this.wheelMenuCenterY = wheelMenuCenterY;
		}
		public int getWheelForkValidX() {
			return wheelForkValidX;
		}
		public void setWheelForkValidX(int wheelForkValidX) {
			this.wheelForkValidX = wheelForkValidX;
		}
		public int getWheelForkValidY() {
			return wheelForkValidY;
		}
		public void setWheelForkValidY(int wheelForkValidY) {
			this.wheelForkValidY = wheelForkValidY;
		}
		public Paint getTextPaint() {
			return textPaint;
		}
		public void setTextPaint(Paint textPaint) {
			this.textPaint = textPaint;
		}
		public int getTextSize() {
			return textSize;
		}
		public void setTextSize(int textSize) {
			this.textSize = textSize;
		}
		public int getOldRotationAngle() {
			return oldRotationAngle;
		}
		public void setOldRotationAngle(int oldRotationAngle) {
			this.oldRotationAngle = oldRotationAngle;
		}
		public int getCurrentRotationAngle() {
			return currentRotationAngle;
		}
		public void setCurrentRotationAngle(int currentRotationAngle) {
			this.currentRotationAngle = currentRotationAngle;
		}
		public int getWheelMenuRadius() {
			return wheelMenuRadius;
		}
		public void setWheelMenuRadius(int wheelMenuRadius) {
			this.wheelMenuRadius = wheelMenuRadius;
		}
		public Rect getTextRectangle() {
			return textRectangle;
		}
		public void setTextRectangle(Rect textRectangle) {
			this.textRectangle = textRectangle;
		}
		public Paint getRectanglePaint() {
			return rectanglePaint;
		}
		public void setRectanglePaint(Paint rectanglePaint) {
			this.rectanglePaint = rectanglePaint;
		}
		public boolean isButtonPressed() {
			return buttonPressed;
		}
		public void setButtonPressed(boolean buttonPressed) {
			this.buttonPressed = buttonPressed;
		}
		public int getSnapAngle() {
			return snapAngle;
		}
		public void setSnapAngle(int snapAngle) {
			this.snapAngle = snapAngle;
		}
		public long getTouchedTime() {
			return touchedTime;
		}
		public void setTouchedTime(long l) {
			this.touchedTime = l;
		}
		public boolean isForkButtonPressed() {
			return forkButtonPressed;
		}
		public void setForkButtonPressed(boolean forkButtonPressed) {
			this.forkButtonPressed = forkButtonPressed;
		}
		public int getMaxTextWidth() {
			return maxTextWidth;
		}
		public void setMaxTextWidth(int maxTextWidth) {
			this.maxTextWidth = maxTextWidth;
		}

		public int getButtonCircleImageWidth() {
			return buttonCircleImageWidth;
		}
		public void setButtonCircleImageWidth(int buttonCircleImageWidth) {
			this.buttonCircleImageWidth = buttonCircleImageWidth;
		}
		public int getButtonCircleImageHeight() {
			return buttonCircleImageHeight;
		}
		public void setButtonCircleImageHeight(int buttonCircleImageHeight) {
			this.buttonCircleImageHeight = buttonCircleImageHeight;
		}
		public int getButtonMargin() {
			return buttonMargin;
		}
		public void setButtonMargin(int buttonMargin) {
			this.buttonMargin = buttonMargin;
		}
		public int getLogoX() {
			return logoX;
		}
		public void setLogoX(int logoX) {
			this.logoX = logoX;
		}
		public int getLogoY() {
			return logoY;
		}
		public void setLogoY(int logoY) {
			this.logoY = logoY;
		}
		public float getOldTouchedX() {
			return oldTouchedX;
		}
		public void setOldTouchedX(float oldTouchedX) {
			this.oldTouchedX = oldTouchedX;
		}
		public float getOldTouchedY() {
			return oldTouchedY;
		}
		public void setOldTouchedY(float oldTouchedY) {
			this.oldTouchedY = oldTouchedY;
		}
		
	}
	
	private Map<Integer, Bitmap> lstStaticItems = new HashMap<Integer, Bitmap>(); 
	private ArrayList<WheelMenuItem> lstDynamicItems = new ArrayList<WheelMenuItem>();
	private SessionVariables var;
    
	public WheelMenuSurface(Context context) {
		super(context);
	
		init();
    }

	
	public WheelMenuSurface(Context context, AttributeSet attrs) {
		super(context, attrs);

		init();
	}
	
	public WheelMenuSurface(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		init();
	}
	
	private void init()
	{
		getHolder().addCallback(this);
		setFocusable(true);
		
		var = new SessionVariables();
		var.setRotationMatrix(new Matrix());
		
		var.setTextPaint(new Paint());
		var.setTextSize(13);	 
		
		var.getTextPaint().setStyle(Style.FILL); 
		var.getTextPaint().setColor(Color.BLACK); 
		var.getTextPaint().setTypeface(Typeface.DEFAULT_BOLD); 
		
		var.getTextPaint().setAntiAlias(true);
		
		//Text size will be set in dpi in order to look good an all screen resolutions
		//var.getTextPaint().setTextSize((int) (var.getTextSize() * getResources().getDisplayMetrics().density + 0.5f)); 
		
		var.getTextPaint().setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,(float) var.getTextSize(), getResources().getDisplayMetrics()));		 
		
		var.setTextRectangle(new Rect());
		var.setRectanglePaint(new Paint());
		var.getRectanglePaint().setStyle(Style.FILL);
		var.getRectanglePaint().setColor(Color.WHITE);
		
		var.setButtonMargin((int) (10* getResources().getDisplayMetrics().density + 0.5f));
		var.setWheelRotationSpeed(15);
	}
	
	private void prepareCache()
	{
	
		//The wheel menu image resource need to be exactly the size of the plate, no spare around it, in order to render buttons on proper positions
		lstStaticItems.put(R.drawable.default_small_wheel_menu, BitmapFactory.decodeResource(getResources(), R.drawable.default_small_wheel_menu));
	
		lstStaticItems.put(R.drawable.default_small_wheel_fork, BitmapFactory.decodeResource(getResources(), R.drawable.default_small_wheel_fork));
		lstStaticItems.put(R.drawable.default_small_center_logo, BitmapFactory.decodeResource(getResources(), R.drawable.default_small_center_logo));
		lstStaticItems.put(R.drawable.default_small_wheel_menu_background, BitmapFactory.decodeResource(getResources(), R.drawable.default_small_wheel_menu_background));
		

		
		//Get size of the buttons in order to draw them in the correct initial position
		var.setButtonHeight(lstStaticItems.get(R.drawable.default_small_wheel_menu_background).getHeight());
		var.setButtonWidth(lstStaticItems.get(R.drawable.default_small_wheel_menu_background).getWidth());
		
		Bitmap temp = BitmapFactory.decodeResource(getResources(), R.drawable.default_small_wheel_menu_everyday_treats);
		var.setButtonCircleImageHeight(temp.getHeight());
		var.setButtonCircleImageWidth(temp.getWidth());
		
		var.setMaxTextWidth(var.getButtonCircleImageWidth()+var.getButtonWidth()/3); 
		temp.recycle();
		temp=null;
		
		//Get size of the fork
		var.setWheelForkWidth(lstStaticItems.get(R.drawable.default_small_wheel_fork).getWidth());
		var.setWheelForkHeight(lstStaticItems.get(R.drawable.default_small_wheel_fork).getHeight());
				
		//Get the various helper values of the circle menu
		var.setWheelMenuDiameter(lstStaticItems.get(R.drawable.default_small_wheel_menu).getHeight());
		var.setWheelMenuRadius(var.getWheelMenuDiameter()/2);
		var.setWheelMenuCenterX(this.getWidth()/2);
		var.setWheelMenuCenterY(3*(var.getWheelForkHeight()/4)+var.getWheelMenuRadius());
		
		var.setWheelForkValidX(this.getWidth()/2- var.getButtonWidth()/2);
		var.setWheelForkValidY(3*(var.getWheelForkHeight()/4));
		
		var.setLogoX(var.getWheelMenuCenterX()-lstStaticItems.get(R.drawable.default_small_center_logo).getWidth()/2);
		var.setLogoY(var.getWheelMenuCenterY()-lstStaticItems.get(R.drawable.default_small_center_logo).getHeight()/2);
		
		
		//Button from 3 hour
		lstDynamicItems.add(new WheelMenuItem("btnHappenings", 
								               this.getWidth()/2+var.getWheelMenuRadius()-var.getButtonWidth()-7, 
								               3*(var.getWheelForkHeight()/4)+var.getWheelMenuRadius()-var.getButtonHeight()/2, 
								               5, 
								               0, 
								               BitmapFactory.decodeResource(getResources(), R.drawable.default_small_wheel_menu_happenings),
								               BitmapFactory.decodeResource(getResources(), R.drawable.default_small_wheel_menu_happenings_pressed),
								               false,
								               getButtonText(getResources().getString(R.string.wheel_happenings))));
		
		//Button from 0 hour
		lstDynamicItems.add(new WheelMenuItem("btnPromotions", 
				                               this.getWidth()/2-var.getButtonWidth()/2, 
				                               3*(var.getWheelForkHeight()/4)+7,
				                               5, 
				                               -90, 
				                               BitmapFactory.decodeResource(getResources(), R.drawable.default_small_wheel_menu_promotions),
				                               BitmapFactory.decodeResource(getResources(), R.drawable.default_small_wheel_menu_promotions_pressed),
				                               false,
				                               getButtonText(getResources().getString(R.string.wheel_promotions))));
		
		//Button from 9 hour
		lstDynamicItems.add(new WheelMenuItem("btnSpecialTreats", 
								               this.getWidth()/2-var.getWheelMenuRadius()+7, 
								               3*(var.getWheelForkHeight()/4)+var.getWheelMenuRadius()-var.getButtonHeight()/2,
								               5, 
								               -180, 
								               BitmapFactory.decodeResource(getResources(), R.drawable.default_small_wheel_menu_special_treats),
								               BitmapFactory.decodeResource(getResources(), R.drawable.default_small_wheel_menu_special_treats_pressed),
								               false,
								               getButtonText(getResources().getString(R.string.wheel_special_day_treats))));
		
		//Button from 6 hour
		lstDynamicItems.add(new WheelMenuItem("btnEverydayTreats", 
								                this.getWidth()/2-var.getButtonWidth()/2, 
								                var.getWheelMenuDiameter()+3*(var.getWheelForkHeight()/4)-var.getButtonHeight()-7, 
								                5, 
								                -270, 
								                BitmapFactory.decodeResource(getResources(), R.drawable.default_small_wheel_menu_everyday_treats),
								                BitmapFactory.decodeResource(getResources(), R.drawable.default_small_wheel_menu_everyday_treats_pressed),
								                false,
								                getButtonText(getResources().getString(R.string.wheel_treats))));

	}

	private List<WheelTextProperties> getButtonText(String longText)
	{
		List<WheelTextProperties> lstLinesOfText = new ArrayList<WheelTextProperties>();
		
		String[] buttonEverydayTreatsText = longText.split(" ");
		int lineLength=0;
		int lineHeight=0;
		int wordLength=0;
		int wordHeight=0;
		String finalLineText="";
		
		if (buttonEverydayTreatsText.length==1)
		{
			 var.getTextPaint().getTextBounds(buttonEverydayTreatsText[0],0,buttonEverydayTreatsText[0].length(),var.getTextRectangle());
			 lstLinesOfText.add(new WheelTextProperties(buttonEverydayTreatsText[0], var.getTextRectangle().width(), var.getTextRectangle().height() ));
		}
		else
		{
		
		int positionOnString=0;
		for (String word : buttonEverydayTreatsText)  
			{  
			   positionOnString++;
			   var.getTextPaint().getTextBounds(word,0,word.length(),var.getTextRectangle());
			   wordLength = var.getTextRectangle().width();
			   wordHeight = var.getTextRectangle().height();
			   
			   var.getTextPaint().getTextBounds(finalLineText,0,finalLineText.length(),var.getTextRectangle());
			   lineLength = var.getTextRectangle().width();
			   lineHeight = var.getTextRectangle().height();
			   
			   if (lineLength + wordLength <= var.getMaxTextWidth())
			   {
				   finalLineText+=" "+word;   
				   if (positionOnString == buttonEverydayTreatsText.length)
				   {
					   lstLinesOfText.add(new WheelTextProperties(finalLineText.trim(), wordLength, wordHeight));
				   }
			   }
			   else
			   {
				   if (wordLength >= var.getMaxTextWidth())
				   {
					   lstLinesOfText.add(new WheelTextProperties(word.trim(), wordLength, wordHeight));
					   finalLineText = "";
				   }
				   else
				   {
					   lstLinesOfText.add(new WheelTextProperties(finalLineText.trim(), lineLength, lineHeight));
					   lstLinesOfText.add(new WheelTextProperties(word.trim(), wordLength, lineHeight));
					   finalLineText = "";
				   }
			   }
			   
			}
		}
		
		return lstLinesOfText;
	}
	
	
	 @SuppressWarnings("unchecked")
	@Override
	 public boolean onTouchEvent(MotionEvent event) {
		 
		 //Check if touched point in on a valid range (the plate)  (x-center_x)^2 + (y - center_y)^2 < radius^2
			     if (event.getAction() == MotionEvent.ACTION_DOWN)
			     {
			    	 if (Math.pow((event.getX()-var.getWheelMenuCenterX()),2) + Math.pow((event.getY()-var.getWheelMenuCenterY()),2) <= Math.pow(var.getWheelMenuRadius(), 2))
					 {
			    		 
				    	 var.setTouchedTime(SystemClock.elapsedRealtime());
				    	 var.setForkButtonPressed(false);
				    	 
				    	 for (WheelMenuItem button : lstDynamicItems)
			 		     {
			    			 if ((button.getX()>=var.getWheelForkValidX()-var.getButtonWidth()/4 &&  
										button.getX()<=var.getWheelForkValidX()+var.getButtonWidth()/4 &&
										button.getY()>=var.getWheelForkValidY()-var.getButtonHeight()/4 &&
										button.getY()<=var.getWheelForkValidY()+var.getButtonHeight()/4
										)
										&&
										(event.getX() >= button.getX() 
												 && event.getX() <= button.getX()+var.getButtonWidth() 
												 && event.getY() >= button.getY() 
												 && event.getY() <= button.getY()+var.getButtonHeight()
											 )
											 
			    				 )
									{
										button.setTouched(true);
										updateDrawings();
										var.setButtonPressed(true);
										var.setForkButtonPressed(true);
										OnButtonClick(button);
										break;
									}
			 		     }
					 }
			     }
			     else 
			     {
						 if (event.getAction() == MotionEvent.ACTION_UP)
						 {
							 
							 if (!var.isForkButtonPressed())
							 {
							   var.setOldRotationAngle(0);
							   CheckIfButtonWasPressed(event.getX(), event.getY());
							 
							 for (WheelMenuItem button : lstDynamicItems)
							 {
								 if (button.isTouched())
								 {
									 //rotate clockwise
									 if (button.getX()<var.getWheelForkValidX())
									 {
										 
										 while (button.getX()<var.getWheelForkValidX())
										 {
											 //Make sure it snaps in correct final position and add slowdown effect
											 if (button.getX()>= var.getWheelForkValidX()- 3*(var.getButtonWidth()/4) && button.getY()<=var.getWheelForkValidY()+var.getButtonHeight())
											 {
												 var.setWheelRotationSpeed(1);
											 }
											 
											 var.setWheelRotationAngle(var.getWheelRotationAngle()+ -var.getWheelRotationSpeed());
											 
											 button.setX(getButtonX((int) (button.getAngle()-var.getWheelRotationAngle())));
											 button.setY(getButtonY((int) (button.getAngle()-var.getWheelRotationAngle())));
											 
											 for (WheelMenuItem but : lstDynamicItems)
											 {
												 if (but.getName() != button.getName())
												 {
													 but.setX(getButtonX((int) (but.getAngle()-var.getWheelRotationAngle())));
													 but.setY(getButtonY((int) (but.getAngle()-var.getWheelRotationAngle())));		
												 }
											 }
											 
											 updateDrawings();
										 }
									 }
									 //rotate counterclockwise
									 else
									 {
										 while (button.getX()>=var.getWheelForkValidX())
										 {
											//Make sure it smaps in correct final position and add slowdown effect
											 if (button.getX()- 3*(var.getButtonWidth()/4) <= var.getWheelForkValidX()&& button.getY()<=var.getWheelForkValidY()+var.getButtonHeight())
											 {
												 var.setWheelRotationSpeed(1);
											 }
											 var.setWheelRotationAngle(var.getWheelRotationAngle()+ var.getWheelRotationSpeed());
											
											 button.setX(getButtonX((int) (button.getAngle()-var.getWheelRotationAngle())));
											 button.setY(getButtonY((int) (button.getAngle()-var.getWheelRotationAngle())));
											
											 for (WheelMenuItem but : lstDynamicItems)
											 {
												 if (but.getName() != button.getName())
												 {
													 but.setX(getButtonX((int) (but.getAngle()-var.getWheelRotationAngle())));
													 but.setY(getButtonY((int) (but.getAngle()-var.getWheelRotationAngle())));													 
												 }
											 }
											 updateDrawings();
										 }
									 }
									 //OnButtonClick(button);
									 button.setTouched(false);
									 updateDrawings();
								 }
							 }
							 
							 //if not button was pressed I need to rotate the menu so the the closest button will be under the fork
							 if (!var.isButtonPressed())
							 {
								 ArrayList<WheelMenuItem> lstCurrentButtons = new ArrayList<WheelMenuItem>();
								 
								 for (WheelMenuItem button : lstDynamicItems)
								 {
									 lstCurrentButtons.add(new WheelMenuItem(button.getName(), (int)button.getX(), (int) button.getY()));
								 }
								 
								 for (WheelMenuItem button : lstCurrentButtons)
								 {
									 if (button.getX() > var.getWheelForkValidX())
									{
										button.setX( (int) (button.getX()-var.getWheelForkValidX()));
									}
									else
									{
										button.setX( (int) (var.getWheelForkValidX()-button.getX()));
									}
									 button.setY((int) (button.getY()-var.getWheelForkValidY()));
								 }
								 Collections.sort(lstCurrentButtons, WheelMenuItem.SortByTotal);
								 
								 WheelMenuItem closestButton = lstCurrentButtons.get(0);
								 lstCurrentButtons.clear();
								 lstCurrentButtons=null;
								 
								 for (WheelMenuItem button : lstDynamicItems)
								 {
									 if (button.getName()==closestButton.getName())
									 {
										//rotate clockwise
										 if (button.getX()<var.getWheelForkValidX())
										 {
											 while (button.getX()<var.getWheelForkValidX())
											 {
												 //Make sure it snaps in correct final position and add slowdown effect
												 if (button.getX()>= var.getWheelForkValidX()- 3*(var.getButtonWidth()/4) && button.getY()<=var.getWheelForkValidY()+var.getButtonHeight())
												 {
													 var.setWheelRotationSpeed(1);
												 }
												 
												 var.setWheelRotationAngle(var.getWheelRotationAngle()+ -var.getWheelRotationSpeed());
												 
												 button.setX(getButtonX((int) (button.getAngle()-var.getWheelRotationAngle())));
												 button.setY(getButtonY((int) (button.getAngle()-var.getWheelRotationAngle())));
												 
												 for (WheelMenuItem but : lstDynamicItems)
												 {
													 if (but.getName() != button.getName())
													 {
														 but.setX(getButtonX((int) (but.getAngle()-var.getWheelRotationAngle())));
														 but.setY(getButtonY((int) (but.getAngle()-var.getWheelRotationAngle())));		
													 }
												 }
												 
												 updateDrawings();
											 }
										 }
										 //rotate counterclockwise
										 else
										 {
											 while (button.getX()>=var.getWheelForkValidX())
											 {
												//Make sure it snaps in correct final position and add slowdown effect
												 if (button.getX()- 3*(var.getButtonWidth()/4) <= var.getWheelForkValidX()&& button.getY()<=var.getWheelForkValidY()+var.getButtonHeight())
												 {
													 var.setWheelRotationSpeed(1);
												 }
												 var.setWheelRotationAngle(var.getWheelRotationAngle()+ var.getWheelRotationSpeed());
												
												 button.setX(getButtonX((int) (button.getAngle()-var.getWheelRotationAngle())));
												 button.setY(getButtonY((int) (button.getAngle()-var.getWheelRotationAngle())));
												
												 for (WheelMenuItem but : lstDynamicItems)
												 {
													 if (but.getName() != button.getName())
													 {
														 but.setX(getButtonX((int) (but.getAngle()-var.getWheelRotationAngle())));
														 but.setY(getButtonY((int) (but.getAngle()-var.getWheelRotationAngle())));													 
													 }
												 }
												 updateDrawings();
											 }
										 }
									 }
								 }
								 closestButton=null;
							 }
							 var.setWheelRotationSpeed(15);
							 }
						 }
						 else 
						 {
							 if (event.getAction() == MotionEvent.ACTION_MOVE)
							 {
								 
								 if (Math.pow((event.getX()-var.getWheelMenuCenterX()),2) + Math.pow((event.getY()-var.getWheelMenuCenterY()),2) <= Math.pow(var.getWheelMenuRadius(), 2))
								 {
									 //Only make the move if more then 1 px is between old X.Y and new X.Y coordinates.
									 if (!var.isForkButtonPressed() && (Math.abs(event.getX() - var.getOldTouchedX())>1 || Math.abs(event.getY() - var.getOldTouchedY())>1))
									 {
									  var.setCurrentRotationAngle((int) Math.toDegrees( Math.atan2((float) (event.getY() - var.getWheelMenuCenterY()), (float) (event.getX() - var.getWheelMenuCenterX()))));
									 
									 if (var.getCurrentRotationAngle()<0)
									 {
										 var.setCurrentRotationAngle(Math.abs(var.getCurrentRotationAngle()));
									 }
									 else
									 {
										var.setCurrentRotationAngle(360 - var.getCurrentRotationAngle());
									 }
									 
									 if (var.getOldRotationAngle()>0)
									 {
										 var.setSnapAngle(0);
										 if (var.getCurrentRotationAngle()>=var.getOldRotationAngle())
										 {
												//If button is close to fork, make it snap
												 for (WheelMenuItem button : lstDynamicItems)
												 {
													if (button.getX() >= var.getWheelForkValidX() && button.getX()<= var.getWheelForkValidX()+ var.getButtonWidth()/2+var.getButtonWidth() && button.getY()<=var.getWheelForkValidY()+var.getButtonHeight())
													{
														var.setSnapAngle((int) Math.abs(Math.atan2(var.getWheelForkValidY()-button.getY(), var.getWheelForkValidX()-button.getX())));
													}
												 }
												 
												 if (var.getWheelRotationAngle()+(var.getCurrentRotationAngle()-var.getOldRotationAngle())>360)
												 {
													 var.setWheelRotationAngle(360 - (var.getWheelRotationAngle()+(var.getCurrentRotationAngle()-var.getOldRotationAngle()))+var.getSnapAngle());
												 }
												 else 
												 {
													 var.setWheelRotationAngle(var.getWheelRotationAngle()+(var.getCurrentRotationAngle()-var.getOldRotationAngle())+var.getSnapAngle());
												 }
											 }
											 else
											 {
												 //If button is close to fork, make it snap
												 for (WheelMenuItem button : lstDynamicItems)
												 {
													if (button.getX() >= var.getWheelForkValidX() - var.getButtonWidth() && button.getX() <= var.getWheelForkValidX() && button.getY()<=var.getWheelForkValidY()+var.getButtonHeight())
													{
														var.setSnapAngle((int) Math.abs(Math.atan2(button.getY()-var.getWheelForkValidY(), button.getX()-var.getWheelForkValidX())));
													}
												 }
												 
												 var.setWheelRotationAngle(var.getWheelRotationAngle()+(var.getCurrentRotationAngle()-var.getOldRotationAngle())-var.getSnapAngle());
											 }
										 }
										 
										SetButtonsStatusAndPosition(event.getX(), event.getY());
										
										var.setOldRotationAngle(var.getCurrentRotationAngle());
										 
										updateDrawings();
									}
								 }
							 }
						  }
					 }
//				 }
//				 else 
//				 {
//					 var.setOldRotationAngle(0);
//				 }	
		 	 
		 return true;
	 }

	    private void CheckIfButtonWasPressed(double newX, double newY)
	    {
	    	if (SystemClock.elapsedRealtime()-var.getTouchedTime() <=1000)
	    	{
	    		for (WheelMenuItem button : lstDynamicItems)
	 		     {
			    	  if (newX >= button.getX() 
							 && newX <= button.getX()+var.getButtonWidth() 
							 && newY >= button.getY() 
							 && newY <= button.getY()+var.getButtonHeight()
						 )
						 {
								button.setTouched(true);
								for (WheelMenuItem but : lstDynamicItems)
					 		    {
									if (but.getName() != button.getName())
									{
										but.setTouched(false);
									}
					 		    }
								break;
						}
	 		     }
	    	}
	    	else
	    	{
	    		for (WheelMenuItem button : lstDynamicItems)
	 		     {
	    			 if (button.getX()>=var.getWheelForkValidX()-var.getButtonWidth()/4 &&  
								button.getX()<=var.getWheelForkValidX()+var.getButtonWidth()/4 &&
								button.getY()>=var.getWheelForkValidY()-var.getButtonHeight()/4 &&
								button.getY()<=var.getWheelForkValidY()+var.getButtonHeight()/4
								)
							{
								button.setTouched(true);
								for (WheelMenuItem but : lstDynamicItems)
					 		    {
									if (but.getName() != button.getName())
									{
										but.setTouched(false);
									}
					 		    }
								break;
							}
	 		     }
			}
	    }
	    
		private void SetButtonsStatusAndPosition(double newX, double newY)
		 {
			if (var.getOldRotationAngle() != var.getCurrentRotationAngle())
			{
				if (var.getOldRotationAngle()>0)
				 {
					var.setButtonPressed(false);
					for (WheelMenuItem button : lstDynamicItems)
					{
						 button.setX(getButtonX((int) (button.getAngle()-var.getWheelRotationAngle())));
						 button.setY(getButtonY((int) (button.getAngle()-var.getWheelRotationAngle())));

						 if (button.getX()>=var.getWheelForkValidX()-var.getButtonWidth()/4 &&  
									button.getX()<=var.getWheelForkValidX()+var.getButtonWidth()/4 &&
									button.getY()>=var.getWheelForkValidY()-var.getButtonHeight()/4 &&
									button.getY()<=var.getWheelForkValidY()+var.getButtonHeight()/4)
								{
									//button.setTouched(true);
									var.setButtonPressed(true);
								}
								else
								{
									button.setTouched(false);
									
									if (newX >= button.getX() 
									 && newX <= button.getX()+var.getButtonWidth() 
									 && newY >= button.getY() 
									 && newY <= button.getY()+var.getButtonHeight())
						 			{
										button.setTouched(true);
						 			}
							 }
								
						 
						 
					}
				 }
			}
		 }
		 
		private float getButtonX(int angle)
		{
			return (float) ((var.wheelMenuCenterX + Math.cos(angle*Math.PI / 180F) * (var.getWheelMenuDiameter()-var.getButtonMargin()-var.getButtonWidth())/2) - var.getButtonWidth()/2);
		}
		
		private float getButtonY(int angle)
		{
			return (float) ((var.wheelMenuCenterY + Math.sin(angle*Math.PI / 180F) * (var.getWheelMenuDiameter()-var.getButtonMargin()-var.getButtonHeight())/2) - var.getButtonHeight()/2);
		}
		
		private void OnButtonClick(WheelMenuItem button)
		{
			for (WheelMenuItem but : lstDynamicItems)
			{
				but.setTouched(false);
			}
			var.setButtonPressed(false);
			SessionValues.getInstance().setWheelMenuPressedButton(button.getName());
			
			if(button.getName() == "btnPromotions")
			{
//				Intent intent = new Intent();
//				intent.setClass( getContext(), ApiOutletsActivity.class );						
//				intent.putExtra( Constants.TAG_MI, menuID);
//				intent.putExtra( Constants.TAG_REQUEST, request);
//				intent.putExtra( Constants.TAG_MODULE, Constants.TYPE_OUTLETMENU);							
//				getContext().startActivity(intent);
			}
			else if(button.getName() == "btnHappenings")
			{
				//TODO add action on button						
			}
			else if(button.getName() == "btnEverydayTreats")
			{
				//TODO add action on button						
			}
			else if(button.getName() == "btnSpecialTreats")
			{
				//TODO add action on button						
			}

		 }
		 
		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
		}
            
		//Draw everything
		@Override
		public void onDraw(Canvas canvas) {
			
			//Because SurfaceView is not transparent, I can's see the background thru it. I just
			//draw a color that looks alike the background, for optimization purpose	
			canvas.drawColor(Color.argb(255, 255, 255, 255));
			
			//Draw the plate
		     var.getRotationMatrix().setRotate((float) -var.getWheelRotationAngle(), var.getWheelMenuRadius(), var.getWheelMenuRadius());
		     Bitmap rbpm = Bitmap.createBitmap(lstStaticItems.get(R.drawable.default_small_wheel_menu), 0, 0, var.getWheelMenuDiameter(), var.getWheelMenuDiameter(), var.getRotationMatrix(), true);

		     canvas.drawBitmap(rbpm, this.getWidth()/2-var.getWheelMenuRadius() + (var.getWheelMenuDiameter()- rbpm.getWidth())/2, (3*var.getWheelForkHeight()/4)+(var.getWheelMenuDiameter() - rbpm.getHeight())/2, null);
			 canvas.drawBitmap(lstStaticItems.get(R.drawable.default_small_center_logo), var.getLogoX(), var.getLogoY(),null);
		     
			
			//Draw the buttons
			for (WheelMenuItem button : lstDynamicItems) {
				
				//Draw the button's icon
				canvas.drawBitmap(lstStaticItems.get(R.drawable.default_small_wheel_menu_background), button.getX(),button.getY(), null);
				
				if (!button.isTouched())
				{
				   canvas.drawBitmap(button.getBitmapResource(), button.getX() + var.getButtonWidth()/2-var.getButtonCircleImageWidth()/2, button.getY(), null);
				}
				else 
				{
					canvas.drawBitmap(button.getBitmapTouchedResource(), button.getX() + var.getButtonWidth()/2-var.getButtonCircleImageWidth()/2, button.getY(), null);
				}
				
				//Draw the text
				int lineno=1;
				for (WheelTextProperties textProperties : button.getLstText())
				{
					canvas.drawText(textProperties.getText(), 
							        button.getX() + var.getButtonWidth()/2 - textProperties.getTextWidth()/2, 
							        button.getY()+var.getButtonCircleImageHeight()+textProperties.getTextHeight()*(lineno), var.getTextPaint());
					lineno++;
				}
			}
			
			//Draw the fork on top
			canvas.drawBitmap(lstStaticItems.get(R.drawable.default_small_wheel_fork),this.getWidth()/2- var.getWheelForkWidth()/2 ,0, null);
		}
		 
		    public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {
		    }

		    public void surfaceCreated(SurfaceHolder holder) {
		    	  //I prepare the cache in here, because now I know the height and width of the surface and I can use it in order to find element coordinates
		    	  prepareCache();
		    	 
		    	  SessionValues sessionValues = SessionValues.getInstance();
		    	
		    	  if (sessionValues != null)
		    	  {
			    	  //Set buttons position and rotation
			    	  if (sessionValues.getWheelMenuPressedButton().length()>0)
			    	  {
			    		if(sessionValues.getWheelMenuPressedButton() == "btnPromotions")
			  			{
			    			//do nothing, menu in default position
			  			}
			  			else if(sessionValues.getWheelMenuPressedButton() == "btnHappenings")
			  			{
			  				var.setCurrentRotationAngle(90);
			  				for (WheelMenuItem button : lstDynamicItems)
			  				{
			  					button.setAngle(button.getAngle()-90);
			  					button.setX(getButtonX((int) (button.getAngle()-var.getWheelRotationAngle())));
								button.setY(getButtonY((int) (button.getAngle()-var.getWheelRotationAngle())));
			  				}
			  				
			  			}
			  			else if(sessionValues.getWheelMenuPressedButton() == "btnEverydayTreats")
			  			{
			  				var.setCurrentRotationAngle(180);
			  				for (WheelMenuItem button : lstDynamicItems)
			  				{
			  					button.setAngle(button.getAngle()-180);
			  					button.setX(getButtonX((int) (button.getAngle()-var.getWheelRotationAngle())));
								button.setY(getButtonY((int) (button.getAngle()-var.getWheelRotationAngle())));
			  				}
			  			}
			  			else if(sessionValues.getWheelMenuPressedButton() == "btnSpecialTreats")
			  			{
			  				var.setCurrentRotationAngle(-90);
			  				for (WheelMenuItem button : lstDynamicItems)
			  				{
			  					button.setAngle(button.getAngle()+90);
			  					button.setX(getButtonX((int) (button.getAngle()-var.getWheelRotationAngle())));
								button.setY(getButtonY((int) (button.getAngle()-var.getWheelRotationAngle())));
			  				}
			  			}
			    	  }
		    	  }
		    	  
		    	  //Draw the screen when surface is initially shown
		    	  updateDrawings();
		    }
		    
		    public void surfaceDestroyed(SurfaceHolder holder) {
			    lstDynamicItems.clear();
			    lstStaticItems.clear();
			    var.setWheelRotationAngle(0);
			    System.gc();
		    }
		    
		    //A separate running thread is not necessary required because all the drawing will take place only 
		    //on user interaction.
		    public void updateDrawings() {
		        Canvas canvas = null;
		        try {
		            canvas = getHolder().lockCanvas(null);
		            synchronized (getHolder()) {
		                this.onDraw(canvas);
		            }
		        }
		        finally {
		            if (canvas != null) {
		                getHolder().unlockCanvasAndPost(canvas);
		            }
		        }
		    }   
}
