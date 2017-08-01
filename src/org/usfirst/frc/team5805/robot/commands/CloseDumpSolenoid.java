package org.usfirst.frc.team5805.robot.commands;

import org.usfirst.frc.team5805.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;

public class CloseDumpSolenoid extends Command {
	
	public CloseDumpSolenoid() {
		requires(Robot.cannon);
	}
	
	@Override
	protected void initialize() {
		Robot.cannon.closeDumpSolenoid();
		Robot.oi.dStick.setRumble(RumbleType.kLeftRumble, -2);
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
