package org.usfirst.frc.team5805.robot;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.MotorSafety;

public class StepperMotor {
	int pulsePin, dirPin, enablePin;
	DigitalOutput pulseOut, dirOut, enableOut;
	
	double stepDelay;
	int stepsPerRev;
	
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
	 */
	public void step(int steps) {
		enable();
		
		long lastPulse = System.nanoTime();
		while (steps > 0) {
			long curTime = System.nanoTime();
			long microDiff = (curTime - lastPulse) / 1000;
			if (microDiff >= stepDelay) {
				lastPulse = curTime;
				steps--;
				pulseOut.set(true);
			} else {
				pulseOut.set(false);
			}
		}
	}
	
	/**
	 * Moves motor a number of rotations
	 * @param rotations Number of rotations
	 */
	public void rotate(double rotations) {
		step((int)(rotations * stepsPerRev) / 2);
	}
	
	/**
	 * Sets speed of motor in RPMs
	 * Sets stepDelay to a value in microseconds
	 * @param rpms Rotations per minute
	 */
	public void setSpeed(int rpms) {
		stepDelay = 60 * 1000 * 1000 / stepsPerRev / (rpms / 2);
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
	
	public boolean isEnabled() {
		return enableOut.get();
	}
}
