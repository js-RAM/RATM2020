/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanel;

public class SnowBlowerCommand extends CommandBase {  
  private ControlPanel snowBlower;
  private double speed;
  private boolean direction;

  /**
   * Controls the snowblower motor that rotates the control panel arm.
   * @param snowBlower the ControlPanel subsystem used.
   */
  public SnowBlowerCommand(double speed, ControlPanel snowBlower) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.snowBlower = snowBlower;
    this.speed = speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (snowBlower.sbEncoderValue() < 0.5) {
      direction = true;
    } else {
      speed = -speed;
      direction = false;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    snowBlower.snowBlower(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    snowBlower.snowBlower(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (direction) {
      if (snowBlower.sbEncoderValue() >= 0.769) {
        return true;
      } else { 
        return false;
      }
    } else {
      if (snowBlower.sbEncoderValue() <= 0.269) {
        return true;
      } else {
        return false;
      }
    }
  }
}