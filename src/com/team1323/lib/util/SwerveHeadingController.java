package com.team1323.lib.util;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SwerveHeadingController {
	private double targetHeading;
	private double disabledTimestamp;
	private double lastUpdateTimestamp;
	private final double disableTimeLength = 0.3;
	private SynchronousPIDF headingPID = new SynchronousPIDF(0.006, 0.0, 0.0, 0.0);
	
	public enum State{
		Off, Stabilize, Snap, TemporaryDisable
	}
	private State currentState = State.Off;
	public State getState(){
		return currentState;
	}
	private void setState(State newState){
		currentState = newState;
	}
	
	public SwerveHeadingController(){
		targetHeading = 0;
		lastUpdateTimestamp = Timer.getFPGATimestamp();
	}
	
	public void setStabilizationTarget(double angle){
		targetHeading = angle;
		setState(State.Stabilize);
	}
	
	public void setSnapTarget(double angle){
		targetHeading = angle;
		setState(State.Snap);
	}
	
	public void disable(){
		setState(State.Off);
	}
	
	public void temporarilyDisable(){
		setState(State.TemporaryDisable);
		disabledTimestamp = Timer.getFPGATimestamp();
	}
	
	public double getTargetHeading(){
		return targetHeading;
	}
	
	public double updateRotationCorrection(double heading){
		double correction = 0;
		double now = Timer.getFPGATimestamp();
		double error = heading - targetHeading;
		double dt = now - lastUpdateTimestamp;
		
		switch(currentState){
		case Off:
			
			break;
		case TemporaryDisable:
			targetHeading = heading;
			if(now - disabledTimestamp >= disableTimeLength)
				setState(State.Stabilize);
			break;
		case Stabilize:
			correction = headingPID.calculate(error, dt);
			break;
		case Snap:
			correction = headingPID.calculate(error, dt);
			break;
		}
		
		lastUpdateTimestamp = now;
		return correction;
	}
	
}
