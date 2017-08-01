package org.usfirst.frc.team5805.autos;

import org.usfirst.frc.team5805.robot.commands.CloseDumpSolenoid;
import org.usfirst.frc.team5805.robot.commands.OpenDumpSolenoid;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class FireCannon extends CommandGroup {
	public FireCannon() {
		addSequential(new OpenDumpSolenoid());
		addSequential(new TimedCommand(1));
		addSequential(new CloseDumpSolenoid());
	}
}
