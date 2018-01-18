package com.team1323.frc2018.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;
import com.ctre.phoenix.sensors.PigeonIMU.PigeonState;
import com.team254.lib.util.math.Rotation2d;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Pigeon {
	private static Pigeon instance = null;
	public static Pigeon getInstance(){
		if(instance == null){
			instance = new Pigeon();
		}
		return instance;
	}
	
	private PigeonIMU pigeon;
	PigeonIMU.FusionStatus fusionStatus = new PigeonIMU.FusionStatus();
    
	public Pigeon(){
		try{
			pigeon = new PigeonIMU(new TalonSRX(0));
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public boolean isGood(){
		return (pigeon.getState() == PigeonState.Ready) ? true : false;
	}
	
	public Rotation2d getAngle(){
		return Rotation2d.fromDegrees(-pigeon.getFusedHeading(fusionStatus));
	}
	
	public void setAngle(double angle){
		pigeon.setFusedHeading(-angle, 10);
		pigeon.setYaw(-angle, 10);
	}
	
	public void outputToSmartDashboard(){
		SmartDashboard.putNumber("Heading Angle", getAngle().getDegrees());
		SmartDashboard.putBoolean("Pigeon Good", isGood());
		SmartDashboard.putNumber("Pigeon Temp", pigeon.getTemp());
		SmartDashboard.putNumber("Pigeon Compass", pigeon.getAbsoluteCompassHeading());
	}
}