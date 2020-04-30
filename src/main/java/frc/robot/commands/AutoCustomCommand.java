/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.NetworkTables;

public class AutoCustomCommand extends CommandBase {
  int step;
  SendableChooser<Command> command1, command2, command3, command4, command5, command6;
  NetworkTables networkTables;
  
  /**
   * Creates a new AutoCustomCommand.
   */
  public AutoCustomCommand(SendableChooser<Command> command1, SendableChooser<Command> command2,
      SendableChooser<Command> command3, SendableChooser<Command> command4, 
      SendableChooser<Command> command5, SendableChooser<Command> command6,
      NetworkTables networkTables) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.command1 = command1;
    this.command2 = command2;
    this.command3 = command3;
    this.command4 = command4;
    this.command5 = command5;
    this.command6 = command6;
    this.networkTables = networkTables;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    step = 1;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    switch (step) {
      default :
        step = 1;
        break;
      case 1 :
        networkTables.setValue1();
        command1.getSelected().schedule(false);
        step++;
        break;
      case 2 :
        if (command1.getSelected().isFinished()) {
          step++;
        }
        break;
      case 3 :
        networkTables.setValue2();
        command2.getSelected().schedule(false);
        step++;
        break;
      case 4 : 
        if (command2.getSelected().isFinished()) {
          step++;
        }
        break;
      case 5 :
        networkTables.setValue3();
        command3.getSelected().schedule(false);
        step++;
        break;
      case 6 : 
        if (command3.getSelected().isFinished()) {
          step++;
        }
        break;
      case 7 :
        networkTables.setValue4();
        command4.getSelected().schedule(false);
        step++;
        break;
      case 8 : 
        if (command4.getSelected().isFinished()) {
          step++;
        }
        break;
      case 9 :
        networkTables.setValue5();
        command5.getSelected().schedule(false);
        step++;
        break;
      case 10 : 
        if (command5.getSelected().isFinished()) {
          step++;
        }
        break;
      case 11 :
        networkTables.setValue6();
        command6.getSelected().schedule(false);
        step++;
        break;
      case 12 : 
        if (command6.getSelected().isFinished()) {
          step++;
        }
        break;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
