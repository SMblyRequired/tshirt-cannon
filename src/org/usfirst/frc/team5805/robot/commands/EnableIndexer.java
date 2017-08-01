package org.usfirst.frc.team5805.robot.commands;

import org.usfirst.frc.team5805.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class EnableIndexer extends Command {
	public EnableIndexer() {
		requires(Robot.cannon);
	}
	
	@Override
	protected void initialize() {
		Robot.cannon.enableIndexer();
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
}
