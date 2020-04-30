/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.modules;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * Add your docs here.
 */
public class CustomSendableChooser extends SendableChooser<Command> {

  /**
   *Creates a new table entry class. 
   */
  public CustomSendableChooser(Command driveCommand, Command turnCommand, 
        Command shootCommand, Command intakeDriveCommand) {
    setDefaultOption("Drive Distance", driveCommand);
    addOption("Turn", turnCommand);
    addOption("Pick Up Balls", intakeDriveCommand);
    addOption("Shoot", shootCommand);
  }
}
