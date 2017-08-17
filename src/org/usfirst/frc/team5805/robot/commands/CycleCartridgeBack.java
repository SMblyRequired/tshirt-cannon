package org.usfirst.frc.team5805.robot.commands;

import org.usfirst.frc.team5805.robot.Robot;
import org.usfirst.frc.team5805.robot.subsystems.Cannon.BreachState;

import edu.wpi.first.wpilibj.command.Command;

public class CycleCartridgeBack extends Command {
	public CycleCartridgeBack() {
		super(1);
		requires(Robot.cannon);
	}
	
	@Override
	protected void initialize() {
		if (Robot.cannon.getBreachState() == BreachState.ENGAGED) {	// Let's not break anything...
			System.err.println("ERROR: Could not cycle forward. Breach still engaged.");
			return;
		}
		
		Robot.cannon.enableIndexer();
		Robot.cannon.indexerBack();
	}
	
	@Override
    public synchronized void cancel() {
        Robot.cannon.stopIndexer();
    }
	 
	@Override
	protected boolean isFinished() {
		return !Robot.cannon.indexerMoving();
	}
}
