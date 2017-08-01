package org.usfirst.frc.team5805.autos;

import org.usfirst.frc.team5805.robot.commands.CycleCartridgeFwd;
import org.usfirst.frc.team5805.robot.commands.EngageBreachCommand;
import org.usfirst.frc.team5805.robot.commands.ReleaseBreachCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class CycleCartridgeSequence extends CommandGroup {
	public CycleCartridgeSequence() {
		System.out.println("Activating reload or something like that...");
		
		addSequential(new ReleaseBreachCommand());
		addSequential(new TimedCommand(0.5));
		addSequential(new CycleCartridgeFwd());
		addSequential(new TimedCommand(1.5));
		addSequential(new EngageBreachCommand());
	}
}
