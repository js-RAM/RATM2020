/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Elevator;

public class ElevatorCommand extends PIDCommand {
  
  private final Elevator subsystem;
  

  /**
   * Creates a new Elevatorommand.
   */
  public ElevatorCommand(double height, Elevator subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    super(frc.robot.Constants.Elevator.PID,
        () -> (subsystem.getDistance()), 
        height,
        output -> {
          subsystem.motorControl(output);
        },
        subsystem
    );
    this.subsystem = subsystem;
    

    addRequirements(subsystem);
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  
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
