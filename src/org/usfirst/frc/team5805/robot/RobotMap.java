package org.usfirst.frc.team5805.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	public static int FRONTLEFT = 0;
	public static int REARLEFT = 1;
	public static int FRONTRIGHT = 2;
	public static int REARRIGHT = 3;
	
	public static int BREACH_SOLENOID_OPEN = 0;
	public static int BREACH_SOLENOID_CLOSE = 1;
	public static int DUMP_SOLENOID = 2;
	
    public static int RIGHT_TRIGGER = 3;
    public static int RIGHT_X_AXIS = 1;
    public static int LEFT_Y_AXIS = 4; 
    
    public static int TOGGLE_BREACH = 1;
}
