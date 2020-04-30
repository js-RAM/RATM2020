/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class NetworkTables extends SubsystemBase{
  ShuffleboardTab customTab;
  NetworkTableEntry value1, value2, value3, value4, value5, value6;
  double value;

  /**
   * Creates a new NetworkTables.
   */
  public NetworkTables() {
    customTab = Shuffleboard.getTab("Custom Auto");
    value1 = customTab.add("Value 1", 0).getEntry();
    value2 = customTab.add("Value 2", 0).getEntry();
    value3 = customTab.add("Value 3", 0).getEntry();
    value4 = customTab.add("Value 4", 0).getEntry();
    value5 = customTab.add("Value 5", 0).getEntry();
    value6 = customTab.add("Value 6", 0).getEntry();
  }

  public void setValue(NetworkTableEntry entry) {
    this.value = entry.getDouble(0);
  }

  public void setValue1() {
    setValue(value1);
  }
  
  public void setValue2() {
    setValue(value2);
  }

  public void setValue3() {
    setValue(value3);
  }

  public void setValue4() {
    setValue(value4);
  }

  public void setValue5() {
    setValue(value5);
  }

  public void setValue6() {
    setValue(value6);
  }

  public double getValue() {
    return this.value;
  }
}
