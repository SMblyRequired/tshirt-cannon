/*
 * This code is to be used as an introduction for new programmers
 * to team 5805, SMblyRequired. This code retains default comments
 * meant to aid in team members understanding the flow of the code.
 *      (In other words, please do not remove any comments.)
 */

package org.usfirst.frc.team5805.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team5805.autos.CycleCartridgeSequence;
import org.usfirst.frc.team5805.autos.FireCannon;
import org.usfirst.frc.team5805.robot.commands.CycleCartridgeBack;
import org.usfirst.frc.team5805.robot.commands.CycleCartridgeFwd;
import org.usfirst.frc.team5805.robot.commands.DisableIndexer;
import org.usfirst.frc.team5805.robot.commands.EnableIndexer;
import org.usfirst.frc.team5805.robot.commands.EngageBreachCommand;
import org.usfirst.frc.team5805.robot.commands.ReleaseBreachCommand;
import org.usfirst.frc.team5805.robot.subsystems.Cannon;
import org.usfirst.frc.team5805.robot.subsystems.DriveTrain;
import org.usfirst.frc.team5805.robot.subsystems.Cannon.BreachState;

public class Robot extends IterativeRobot {
    public static OI         oi;

    Command                  autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();

    public static Cannon     cannon;                            // Cannon subsystem
    public static DriveTrain driveTrain;

    /**
     * This function is run when the robot is first started up and should be used for any initialization code.
     */
    @Override
    public void robotInit() {
        cannon = new Cannon();
        driveTrain = new DriveTrain();

        oi = new OI();
    }

    /**
     * This function is called once each time the robot enters Disabled mode. You can use it to reset any subsystem information you want to clear when the robot is disabled.
     */
    @Override
    public void disabledInit() {

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select between different autonomous modes using the dashboard. The sendable chooser code works with the Java SmartDashboard. If
     * you prefer the LabVIEW Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box below the Gyro
     *
     * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example) or additional comparisons to the switch structure below with additional
     * strings & commands.
     */
    @Override
    public void autonomousInit() {

    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        Robot.cannon.releaseBreach();

        // Commands for manual operation of subsystems
        SmartDashboard.putData("Indexer Forward", new CycleCartridgeFwd());
        SmartDashboard.putData("Indexer Backward", new CycleCartridgeBack());
        SmartDashboard.putData("Enable Indexer", new EnableIndexer());
        SmartDashboard.putData("Disable Indexer", new DisableIndexer());

        SmartDashboard.putData("Engage Breach", new EngageBreachCommand());
        SmartDashboard.putData("Disengage Breach", new ReleaseBreachCommand());

        SmartDashboard.putData("Fire Cannon", new FireCannon());
        SmartDashboard.putData("Cycle cartridge", new CycleCartridgeSequence());
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();

        SmartDashboard.putBoolean("Breach Engaged",
                Robot.cannon.getBreachState() == BreachState.ENGAGED ? true : false);
        SmartDashboard.putBoolean("Indexer Enabled", Robot.cannon.indexerEnabled());

        if (oi.dStick.getStartButton()) {
            Robot.cannon.manualBreachForwards();
        } else if (oi.dStick.getBackButton()) {
            Robot.cannon.manualBreachBackwards();
        }
        
        // TODO: Get some pressure sensors to retrieve current pressure within system
    }

    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic() {
        LiveWindow.run();
    }
}
