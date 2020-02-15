/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Elevator;

public class ClimbCommand extends PIDCommand {
  
  Elevator elevator;
  private static double heightVal = 0;
  private DoubleSupplier powerSupplier;
  
  /**
   * Creates a new ClimbCommand.
   */
  public ClimbCommand(DoubleSupplier powerSupplier, Elevator elevator) {
    super(
        Constants.Elevator.PID,
        () -> (elevator.getDistance()),
        () -> (getTargetHeight()),
        output -> {
        elevator.motorControl(output);
        },
        elevator
    );
    this.powerSupplier = powerSupplier;
    this.elevator = elevator;
    heightVal = elevator.getDistance();
    addRequirements(elevator);
  }


  public static double getTargetHeight() {
    return heightVal;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    heightVal = elevator.getDistance();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    heightVal = Constants.Climber.clamp(
        heightVal + (Constants.Climber.SPEED * powerSupplier.getAsDouble()) / 50
      );
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
