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
import frc.robot.subsystems.LeftShooter;
import frc.robot.subsystems.RightShooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoStraightDumpCommandGroup extends SequentialCommandGroup {
  /**
   * Creates a new AutoStraightDumpCommandGroup.
   */
  public AutoStraightDumpCommandGroup(DriveTrain driveTrain, Intake intake, 
                                      LeftShooter leftShooter, RightShooter rightShooter) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
        new DriveDistanceCommand(-86, driveTrain),
        new TestHighShootCommandGroup(0.6, 0.1, intake, leftShooter, rightShooter).withTimeout(5)
    );
  }
}
