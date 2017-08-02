package org.usfirst.frc.team5805.robot;
import edu.wpi.first.wpilibj.DigitalOutput;

public class StepperMotor {
	int pulsePin, dirPin, enablePin;
	DigitalOutput pulseOut, dirOut, enableOut;
	
	double stepDelay;
	int stepsPerRev;
	
	boolean moving = false;
	
	public enum Direction {        // Direction motor should move, relative to direction motor is facing
		CW,                        // Clockwise rotation
		CCW                        // Counter-clockwise rotation
	}
	
	public StepperMotor(int _stepsPerRev, int _pulsePin, int _dirPin, int _enablePin) {
		this.pulsePin = _pulsePin;
		this.dirPin = _dirPin;
		this.enablePin = _enablePin;
		this.stepsPerRev = _stepsPerRev;
		
		pulseOut = new DigitalOutput(this.pulsePin);
		dirOut = new DigitalOutput(this.dirPin);
		enableOut = new DigitalOutput(this.enablePin);
		
		enable();
		setSpeed(120);
	}
	
	/**
	 * Moves motor steps amount of steps
	 * @param steps Amount of steps motor should move
	 * @throws Exception Expect an exception if the motor is not enabled
	 */
	public void step(int steps) throws Exception {
		if (!isEnabled()) throw new Exception("Motor not enabled");
		
		long lastPulse = System.nanoTime();                   // Record the last time a pulse was sent, or the initial time
		while (steps > 0 && isEnabled()) {                    // Ensure stepper motor stops when robot is disabled, or when we've reached our end goal
		    moving = true;                                    // Let everyone know we're moving
			
			long curTime = System.nanoTime();                 // Record the current time within the loop
			long microDiff = (curTime - lastPulse) / 1000;    // Calculate how long it's been since we've set lastPulse
			if (microDiff >= stepDelay) {                     // If the current difference is time is greater than the amount of time we want between pulses...
				lastPulse = curTime;                          // Set our lastPulse variable to now
				steps--;                                      // Decrement the amount of steps we've taken
				pulseOut.set(true);                           // And finally, send the pulse!
			} else {
				pulseOut.set(false);                          // Otherwise, turn off our pulse output
			}
		}
		
		moving = false;                                       // Let everyone know we're done moving
	}
	
	public boolean isStepping() {
		return moving;
	}
	
	/**
	 * Moves motor a number of rotations
	 * @param rotations Number of rotations
	 * @throws Exception Expect an exception if the motor is not enabled
	 */
	public void rotate(double rotations) throws Exception {
		step((int)(rotations * stepsPerRev) / 2);         // This method calculates how many steps are in some amount of rotations
	}
	
	/**
	 * Moves motor an amount of degrees
	 * @param degrees
	 * @throws Exception Expect an exception if the motor is not enabled
	 */
	public void rotateDeg(double degrees) throws Exception {
		rotate(degrees / 360);                            // (degrees / 360) is the number of rotations, so we can just call our method above
	}
	
	/**
	 * Sets speed of motor in RPMs
	 * Sets stepDelay to a value in microseconds
	 * @param rpms Rotations per minute
	 */
	public void setSpeed(int rpms) {
		stepDelay = 60 * 1000 * 1000 / stepsPerRev / (rpms / 2);
	}
	
	public void setDirection(Direction dir) {
		
	}
	
	/**
	 * Prevents free movement of motor, essentially locking it in place.
	 */
	public void enable() {
		enableOut.set(true);
	}
	
	/**
	 * Allows free movement of motor.
	 */
	public void disable() {
		enableOut.set(false);
	}
	
	/**
	 * Checks if motor is enabled
	 * @return boolean true if motor is enabled, false otherwise
	 */
	public boolean isEnabled() {
		return enableOut.get();
	}
}
