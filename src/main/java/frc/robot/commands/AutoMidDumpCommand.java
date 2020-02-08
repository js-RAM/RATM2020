/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoMidDumpCommand extends SequentialCommandGroup {
  /**
   * Creates a new AutoMidDump.
   */
  public AutoMidDumpCommand(DriveTrain driveTrain, Intake intake, Shooter shooter) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
          
          new DriveTurnCommand(-37.5921834821, driveTrain),
          new DriveDistanceCommand(111.058824503, driveTrain),
          new DriveTurnCommand(37.5921834821, driveTrain),
          new DriveDistanceCommand(34, driveTrain),
          new PidShootCommandGroup(1000, intake, shooter)
          );
  }
}
