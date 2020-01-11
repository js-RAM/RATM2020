package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain; 
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public class DefaultDriveCommand extends CommandBase {
    
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrain driveTrain;

  private DoubleSupplier leftY;
  private DoubleSupplier rightY;
  private DoubleSupplier rightX;

  private Supplier<Boolean> isDriveToggled;

  boolean driveState;

  /**
   * Creates a new default drive command.
   *
   * @param leftY The leftY joystick of driver gamepad
   * @param rightY The RightY joystick of driver gamepad
   * @param rightX rightX joystick of driver gamepad
   * 
   * @param isDriveToggled Whether the toggle button has been released (basically pressed)
   * 
   * @param driveTrain The driveTrain subsystem.
   */
  public DefaultDriveCommand(DoubleSupplier leftY, DoubleSupplier rightY, DoubleSupplier rightX, 
      Supplier<Boolean> isDriveToggled, DriveTrain driveTrain) {

    this.driveTrain = driveTrain;

    this.leftY = leftY;
    this.rightY = rightY;
    this.rightX = rightX;

    this.isDriveToggled = isDriveToggled;
    this.driveState = false;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (isDriveToggled.get()) {
      driveState = !driveState;
    }

    if (driveState) {
      driveTrain.tankDrive(leftY.getAsDouble(), rightY.getAsDouble());
    } else {
      driveTrain.arcadeDrive(leftY.getAsDouble(), rightX.getAsDouble());
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.tankDrive(0, 0);
  }

  
}