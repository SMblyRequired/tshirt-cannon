package org.usfirst.frc.team5805.robot.subsystems;

import org.usfirst.frc.team5805.robot.RobotMap;
import org.usfirst.frc.team5805.robot.commands.MoveWithJoystick;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

	public VictorSP frontLeft, frontRight, rearLeft, rearRight, intakeRight;
	RobotDrive myDrive;
	
	public DriveTrain () {
		super("Drive Train");

		frontLeft = new VictorSP(RobotMap.FRONTLEFT); // PBot Encoder
		frontRight = new VictorSP(RobotMap.FRONTRIGHT); // Encoder //
		rearLeft = new VictorSP(RobotMap.REARLEFT); // Encoder //
		rearRight = new VictorSP(RobotMap.REARRIGHT);
		
		myDrive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
	}
	
	public void resetEncPos() {
		frontRight.setPosition(0.0);
		rearLeft.setPosition(0.0);
	}
	
	public void arcadeDrive(double forward, double turn) {
		myDrive.arcadeDrive(-forward, -turn);
	}
	
	public void driveDriveTrain(double lStickY, double rStickY){
			myDrive.arcadeDrive(-rStickY, lStickY);
	}  
	 
	public void setTurn(double turn) {
		myDrive.arcadeDrive(0, turn);
	}
	
	public void setCurve(double curPIDval, double turn) {
		myDrive.arcadeDrive(curPIDval, turn);
	}
	public void stop() {
	//	myDrive.tankDrive(0, 0);
		myDrive.arcadeDrive(0,0);
	}
	
	@Override
	protected void initDefaultCommand() {
        setDefaultCommand(new MoveWithJoystick());
	}

	public void tankDrive(double left, double right) {
		// TODO Auto-generated method stub
		myDrive.tankDrive(left, right);
	}
}
