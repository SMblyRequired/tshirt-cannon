package org.usfirst.frc.team5805.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team5805.autos.CycleCartridgeSequence;
import org.usfirst.frc.team5805.autos.FireCannon;
import org.usfirst.frc.team5805.robot.commands.ToggleBreach;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	/**
	 * A simple mapping of buttons to integers (kinda), 
	 * for easily identifying buttons used in 
	 * controlling the robot.
	 */
	public enum XboxButtons {
		NULL,
		A,
		B,
		X,
		Y,
		LeftBumper,
		RightBumper,
		Back,
		Start,
		LeftAnalogButton,
		RightAnalogButton
	}
	
	// The player 1 Xbox controller
	public static XboxController dStick = new XboxController(0);
	
	// Buttons mapped to commands on the player 1 Xbox controller, notice the first argument
	// of each JoystickButton being the dStick defined above.
	Button cycleButton = new JoystickButton(dStick, XboxButtons.LeftBumper.ordinal());
	Button fireButton = new JoystickButton(dStick, XboxButtons.RightBumper.ordinal());
	Button breachToggle = new JoystickButton(dStick, XboxButtons.X.ordinal());
	
	public OI() {
		CycleCartridgeSequence cycleSequence = new CycleCartridgeSequence();
		cycleButton.whenPressed(cycleSequence);
		
		FireCannon fireCannon = new FireCannon();
		fireButton.whenPressed(fireCannon);
		
		breachToggle.whenPressed(new ToggleBreach());
	}
}
