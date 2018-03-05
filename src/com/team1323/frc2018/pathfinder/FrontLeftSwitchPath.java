package com.team1323.frc2018.pathfinder;

import com.team1323.frc2018.Constants;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Waypoint;

public class FrontLeftSwitchPath extends PathfinderPath{
	
	public FrontLeftSwitchPath(){
		super.points = new Waypoint[]{
			new Waypoint(Constants.ROBOT_HALF_LENGTH, Constants.kAutoStartingCorner.y() + Constants.ROBOT_HALF_WIDTH, Pathfinder.d2r(-50)),
			new Waypoint(Constants.kLeftSwitchCloseCorner.x() - Constants.ROBOT_HALF_LENGTH - 1.0, Constants.kLeftSwitchCloseCorner.y() + Constants.ROBOT_HALF_WIDTH + 1.0, Pathfinder.d2r(0))
		};
		super.maxAccel = 5.0;
		super.defaultSpeed = 5.0;
	}
	
}
