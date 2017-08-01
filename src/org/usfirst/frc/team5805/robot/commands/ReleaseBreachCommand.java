package org.usfirst.frc.team5805.robot.commands;

import org.usfirst.frc.team5805.robot.Robot;
import org.usfirst.frc.team5805.robot.subsystems.Cannon.BreachState;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class ReleaseBreachCommand extends TimedCommand {
	public ReleaseBreachCommand() {
		super(0.5);
		requires(Robot.cannon);
	}
	
	@Override
	protected void initialize() {
		Robot.cannon.releaseBreach();
	}
	
	@Override
	protected boolean isFinished() {
		return super.isFinished() || Robot.cannon.getBreachState() == BreachState.RELEASED;
	}
}
