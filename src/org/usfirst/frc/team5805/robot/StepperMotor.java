package org.usfirst.frc.team5805.robot;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.hal.HAL;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class StepperMotor {
    int              pulsePin, dirPin, enablePin;
    DigitalOutput    pulseOut, dirOut, enableOut;

    double           stepDelay;
    int              stepsPerRev;

    volatile boolean moving = false;
    volatile int     stepsLeft;
    
    Direction curDir = Direction.CW;

    public enum Direction {        // Direction motor should move, relative to direction motor is facing
        CW,                        // Clockwise rotation
        CCW                        // Counter-clockwise rotation
    }

    private class StepperMotionThread extends Thread {
        public StepperMotionThread() {
            super("StepperMotionManager");
        }

        @Override
        public void run() {
            System.out.println("StepperMotor: Starting move");
            moving = true;

            long lastPulse = System.nanoTime();                                     // Record the last time a pulse was sent, or the initial time
            while (stepsLeft > 0 && HAL.getSystemActive() && !isInterrupted()) {    // Ensure stepper motor stops when robot is disabled, or when we've reached our end goal
                moving = true;                                                      // Let everyone know we're moving

                long curTime = System.nanoTime();                                   // Record the current time within the loop
                long microDiff = (curTime - lastPulse) / 1000;                      // Calculate how long it's been since we've set lastPulse
                if (microDiff >= stepDelay) {                                       // If the current difference is time is greater than the amount of time we want between pulses...
                    lastPulse = curTime;                                            // Set our lastPulse variable to now
                    stepsLeft--;                                                    // Decrement the amount of steps we've taken
                    pulseOut.set(true);                                             // And finally, send the pulse!
                } else if (microDiff >= 1) {
                    pulseOut.set(false);
                }
            }

            moving = false;                                                         // Let everyone know we're done moving
            System.out.println("StepperMotor: Move complete");
        }
    }

    private StepperMotionThread motionThread;

    public StepperMotor(int _stepsPerRev, int _pulsePin, int _dirPin, int _enablePin) {
        this.pulsePin = _pulsePin;
        this.dirPin = _dirPin;
        this.enablePin = _enablePin;
        this.stepsPerRev = _stepsPerRev;

        pulseOut = new DigitalOutput(this.pulsePin);
        dirOut = new DigitalOutput(this.dirPin);
        enableOut = new DigitalOutput(this.enablePin);

        motionThread = new StepperMotionThread();

        enable();
        setSpeed(30);
        setDirection(Direction.CW);
    }

    /**
     * Moves motor steps amount of steps
     * 
     * @param steps
     *            Amount of steps motor should move
     * @throws Exception
     *             Expect an exception if the motor is not enabled
     */
    public void step(int steps) throws AlreadyMovingException, NotEnabledException {
        if (!isEnabled())
            throw new NotEnabledException();
        if (isStepping())
            throw new AlreadyMovingException();

        stepsLeft = steps;
        motionThread = new StepperMotionThread();
        motionThread.start();
    }

    public class NotEnabledException extends Exception {
        public NotEnabledException() {
            super("StepperMotor not enabled");
        }
    }

    public class AlreadyMovingException extends Exception {
        public AlreadyMovingException() {
            super("StepperMotor already in motion");
        }
    }

    public boolean isStepping() {
        return motionThread.isAlive() || moving;
    }

    /**
     * Moves motor a number of rotations
     * 
     * @param rotations
     *            Number of rotations
     * @throws Exception
     *             Expect an exception if the motor is not enabled
     */
    public void rotate(double rotations) throws AlreadyMovingException, NotEnabledException {
        step((int) (rotations * stepsPerRev));         // This method calculates how many steps are in some amount of rotations
    }

    /**
     * Moves motor an amount of degrees
     * 
     * @param degrees
     * @throws Exception
     *             Expect an exception if the motor is not enabled
     */
    public void rotateDeg(double degrees) throws AlreadyMovingException, NotEnabledException {
        rotate(((degrees) / 360.0));                            // (degrees / 360) is the number of rotations, so we can just call our method above
    }

    /**
     * Sets speed of motor in RPMs Sets stepDelay to a value in microseconds
     * 
     * @param rpms
     *            Rotations per minute
     */
    public void setSpeed(int rpms) {
        stepDelay = 60 * 1000 * 1000 / stepsPerRev / rpms;
        SmartDashboard.putNumber("StepDelay", stepDelay);
    }

    public void setDirection(Direction dir) {
        curDir = dir;
        dirOut.set(curDir == Direction.CCW ? true : false);
    }

    /**
     * Interrupt motor and stop current motion
     */
    public void interrupt() {
        motionThread.interrupt();
    }

    /**
     * Prevents free movement of motor, essentially locking it in place.
     */
    public void enable() {
        enableOut.set(false);
    }

    /**
     * Allows free movement of motor.
     */
    public void disable() {
        enableOut.set(true);
    }

    /**
     * Checks if motor is enabled
     * 
     * @return boolean true if motor is enabled, false otherwise
     */
    public boolean isEnabled() {
        return !enableOut.get();
    }
}
