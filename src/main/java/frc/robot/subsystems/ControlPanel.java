/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ControlPanel extends SubsystemBase {
  private WPI_VictorSPX wheelSpin;
  private WPI_VictorSPX snowBlower;
  private DutyCycleEncoder sbEncoder;

  /**
   * Initializes the motors and sensors for the control panel subsystem.
   */
  public ControlPanel() {
    wheelSpin = new WPI_VictorSPX(11);
    snowBlower = new WPI_VictorSPX(12);
    wheelSpin.setNeutralMode(NeutralMode.Brake);
    snowBlower.setNeutralMode(NeutralMode.Brake);
    sbEncoder = new DutyCycleEncoder(6);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("absolute", sbEncoder.get());
  }

  /**
   * Spins the color wheel motor at the desired speed.
   * @param speed the speed of the color wheel motor
   */
  public void wheelSpin(double speed) {
    wheelSpin.set(ControlMode.PercentOutput, speed);
  }

  public double sbEncoderValue() {
    return sbEncoder.get();
  }

  public void snowBlower(double speed) {
    snowBlower.set(ControlMode.PercentOutput, speed);
  }

}
