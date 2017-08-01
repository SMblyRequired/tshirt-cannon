package org.usfirst.frc.team5805.robot.commands;

import org.usfirst.frc.team5805.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class CycleCartridgeFwd extends TimedCommand {
	public CycleCartridgeFwd() {
		super(1);
		requires(Robot.cannon);
	}
	
	@Override
	protected void execute() {
		Robot.cannon.indexerForward();
	}
	 
	@Override
	protected boolean isFinished() {
		return super.isFinished();
	}
}
