/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.DrivePid;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.NetworkTables;

public class CustomDriveDistanceCommand extends PIDCommand {
  private DriveTrain driveTrain;
  private Intake intake;
  private boolean ballIntake;
  
  /**
   * Drives forward a certain distance.
   * 
   * @param driveTrain The DriveTrain subsystem used by this command
   */

  public CustomDriveDistanceCommand(boolean ballIntake, 
      DriveTrain driveTrain, Intake intake, NetworkTables networkTables) {
    // Use addRequirements() here to declare subsystem dependencies.
    super(
        new PIDController(DrivePid.P_DISTANCE, DrivePid.I_DISTANCE, DrivePid.D_DISTANCE),
        //input
        driveTrain::leftEncoderValue,
        //setpoint
        networkTables::getValue,
        //output
        output -> {
          if (ballIntake) {
            if (output > 0.5) {
              driveTrain.tankDrive(-0.5, -0.5);
            } 
            if (output < -0.5) {
              driveTrain.tankDrive(0.5, 0.5);
            }
            if (Math.abs(output) < 0.5) {
              driveTrain.tankDrive(-output, -output);
            }
          } else {
            driveTrain.tankDrive(-output, -output);
          }
        },
        driveTrain
    );
    this.driveTrain = driveTrain;
    this.intake = intake;
    this.ballIntake = ballIntake;
    getController().setTolerance(0.5);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    driveTrain.leftEncoderReset(); 
    super.initialize();
  }

  @Override
  public void execute() {
    if (ballIntake) {
      intake.intakeBar(0.7);
      intake.conveyorControl(0.5);
    } else {
      intake.intakeBar(0);
      intake.conveyorControl(0);
    }
    super.execute();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.tankDrive(0, 0);
    intake.intakeBar(0);
    intake.conveyorControl(0);;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (getController().atSetpoint()) {
      return true;
    } else {
      return false;
    }
    
  }
}
