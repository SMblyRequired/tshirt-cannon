package org.usfirst.frc.team5805.robot.subsystems;

import org.usfirst.frc.team5805.robot.RobotMap;
import org.usfirst.frc.team5805.robot.StepperMotor;
import org.usfirst.frc.team5805.robot.StepperMotor.AlreadyMovingException;
import org.usfirst.frc.team5805.robot.StepperMotor.NotEnabledException;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Cannon extends Subsystem {
	private DoubleSolenoid breachSolenoid;
	private DigitalOutput dumpSolenoid;
	private StepperMotor indexerMotor;
	
	public enum BreachState {
		ENGAGED,
		RELEASED
	}
	
	public Cannon() {
		breachSolenoid = new DoubleSolenoid(RobotMap.BREACH_SOLENOID_CLOSE, RobotMap.BREACH_SOLENOID_OPEN);
		dumpSolenoid = new DigitalOutput(0);
		
		indexerMotor = new StepperMotor(800, 2, 3, 4);		
		this.closeDumpSolenoid();
	}
	
	@Override
	protected void initDefaultCommand() {
		// We have no default command for our cannon subsystem
	}
	
	/**
	 * Disengage the breach. Required for indexer movement.
	 */
	public void releaseBreach() {
		System.out.println("Releasing breach");
		breachSolenoid.set(Value.kReverse);
	}
	
	/**
	 * Engage the breach. Required for firing.
	 */
	public void engageBreach() {
		System.out.println("Engaging breach");
		breachSolenoid.set(Value.kForward);
	}
	
	public BreachState getBreachState() {
		return breachSolenoid.get() == Value.kForward ? BreachState.ENGAGED : BreachState.RELEASED;
	}
	
	/**
	 * Open solenoid that dumps air into the cartridge and fires the cannon.
	 */
	public void openDumpSolenoid() {
		dumpSolenoid.set(false);
	}
	
	/**
	 * Open solenoid that dumps air into the cartridge and fires the cannon.
	 */
	public void closeDumpSolenoid() {
		dumpSolenoid.set(true);
	}
	
	/**
	 * Move indexer forward, retrieving a new, filled cartridge, ready to be fired.
	 */
	public void indexerForward() {
		try {
			indexerMotor.setDirection(StepperMotor.Direction.CW);
			indexerMotor.rotateDeg(120);
		} catch (AlreadyMovingException e) {
			System.err.println("Error: Cannot move indexer forward. Already moving.");
		} catch (NotEnabledException e) {
		    System.err.println("Error: Cannot move indexer forward. Not enabled.");
		}
	}
	
	/**
	 * Move indexer backwards, retrieving an empty cartridge.
	 */
	public void indexerBack() {
		try {
			indexerMotor.setDirection(StepperMotor.Direction.CCW);
			indexerMotor.rotateDeg(120);
		} catch (Exception e) {
			System.err.println("Error: Cannot move indexer backwards. Error: " + e.getMessage());
		}
	}
	
	/**
	 * Stop/interrupt indexer movement
	 */
	public void stopIndexer() {
	    indexerMotor.interrupt();
	}
	
	/**
	 * Enable the indexer. This is required for moving the indexer.
	 */
	public void enableIndexer() {
		indexerMotor.enable();
	}
	
	/**
	 * Disable the indexer. This is required to freely move the indexer, by hand.
	 */
	public void disableIndexer() {
		indexerMotor.disable();
	}
	
	/**
	 * Returns if the motor is enabled, or not.
	 * @return true if enabled, false if disabled
	 */
	public boolean indexerEnabled() {
		return indexerMotor.isEnabled();
	}

	/**
	 * Determines if the indexer is moving
	 * @return true if moving, false if not
	 */
	public boolean indexerMoving() {
		return indexerMotor.isStepping();
	}
}
