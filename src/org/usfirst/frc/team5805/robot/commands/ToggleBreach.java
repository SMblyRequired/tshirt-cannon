package org.usfirst.frc.team5805.robot.commands;

import org.usfirst.frc.team5805.robot.Robot;
import org.usfirst.frc.team5805.robot.subsystems.Cannon.BreachState;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleBreach extends Command {
	public ToggleBreach() {
		requires(Robot.cannon);
	}
	
	@Override
	protected void initialize() {
		if (Robot.cannon.getBreachState() == BreachState.ENGAGED) {
			Robot.cannon.releaseBreach();
			Robot.cannon.disableIndexer();
		} else {
			Robot.cannon.engageBreach();
			Robot.cannon.enableIndexer();
		}
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
}
