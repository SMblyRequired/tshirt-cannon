package org.usfirst.frc.team5805.robot.commands;

import org.usfirst.frc.team5805.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DisableIndexer extends Command {
	public DisableIndexer() {
		requires(Robot.cannon);
	}
	
	@Override
	protected void initialize() {
		Robot.cannon.disableIndexer();
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
}
