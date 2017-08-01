package org.usfirst.frc.team5805.robot.subsystems;

import org.usfirst.frc.team5805.robot.RobotMap;
import org.usfirst.frc.team5805.robot.StepperMotor;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
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
		// TODO Auto-generated method stub
		
	}

	public void cycleCartridge() {
		releaseBreach();
		// TODO: Start stepper motor cycle
		engageBreach();
	}
	
	public void releaseBreach() {
		System.out.println("Releasing breach");
		breachSolenoid.set(Value.kReverse);
	}
	
	public void engageBreach() {
		System.out.println("Engaging breach");
		breachSolenoid.set(Value.kForward);
	}
	
	public BreachState getBreachState() {
		return breachSolenoid.get() == Value.kForward ? BreachState.ENGAGED : BreachState.RELEASED;
	}
	
	public void openDumpSolenoid() {
		dumpSolenoid.set(false);
	}
	
	public void closeDumpSolenoid() {
		dumpSolenoid.set(true);
	}
	
	public void indexerForward() {
		// indexerMotor.step(1000);
		indexerMotor.rotate(120);
	}
	
	public void indexerBack() {
		indexerMotor.step(-1000);
	}
	
	public void enableIndexer() {
		indexerMotor.enable();
	}
	
	public void disableIndexer() {
		indexerMotor.disable();
	}
	
	public void fire() throws InterruptedException  {
		dumpSolenoid.set(true);
		Thread.sleep(25);
		dumpSolenoid.set(false);
	}
}
