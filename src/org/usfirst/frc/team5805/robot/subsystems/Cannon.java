package org.usfirst.frc.team5805.robot.subsystems;

import org.usfirst.frc.team5805.robot.I2CStepper;
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
	private I2CStepper i2cStepper;
	
	public enum BreachState {
		ENGAGED,
		RELEASED
	}
	
	public Cannon() {
		breachSolenoid = new DoubleSolenoid(RobotMap.BREACH_SOLENOID_CLOSE, RobotMap.BREACH_SOLENOID_OPEN);
		dumpSolenoid = new DigitalOutput(0);
		
		i2cStepper = new I2CStepper();
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
	    i2cStepper.indexerForward();
	}
	
	/**
	 * Move indexer backwards, retrieving an empty cartridge.
	 */
	public void indexerBack() {
	    i2cStepper.indexerBackwards();
	}
	
	/**
	 * Stop/interrupt indexer movement
	 */
	public void stopIndexer() {
//	    indexerMotor.interrupt();
	}
	
	/**
	 * Enable the indexer. This is required for moving the indexer.
	 */
	public void enableIndexer() {
	    i2cStepper.enable();
	}
	
	/**
	 * Disable the indexer. This is required to freely move the indexer, by hand.
	 */
	public void disableIndexer() {
	    i2cStepper.disable();
	}
	
	/**
	 * Returns if the motor is enabled, or not.
	 * @return true if enabled, false if disabled
	 */
	public boolean indexerEnabled() {
	    // TODO: Reimplement this within the I2CStepper class
		return true;
	}

	/**
	 * Determines if the indexer is moving
	 * @return true if moving, false if not
	 */
	public boolean indexerMoving() {
	    // TODO: Reimplement this within the I2CStepper class
		return false;
	}
	
	public void manualBreachForwards() {
	    // TODO: Reimplement this within the I2CStepper class
	}
	
	public void manualBreachBackwards() {
        // TODO: Reimplement this within the I2CStepper class
    }
}
