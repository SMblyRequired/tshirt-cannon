package org.usfirst.frc.team5805.robot.commands;

import org.usfirst.frc.team5805.robot.OI;
import org.usfirst.frc.team5805.robot.Robot;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;

public class OpenDumpSolenoid extends Command {
	public OpenDumpSolenoid() {
		requires(Robot.cannon);
	}
	
	@Override
	protected void initialize() {
		Robot.cannon.openDumpSolenoid();
		OI.dStick.setRumble(RumbleType.kLeftRumble, 200);
		System.out.println("Firing");
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}

}
