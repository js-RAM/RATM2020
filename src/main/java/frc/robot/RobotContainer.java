/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.AutoLeftDumpCommandGroup;
import frc.robot.commands.AutoLeftShootCommandGroup;
import frc.robot.commands.AutoMidDumpCommand;
import frc.robot.commands.AutoMidShootCommand;
import frc.robot.commands.AutoRightShootCommandGroup;
import frc.robot.commands.AutoStraightCommandGroup;
import frc.robot.commands.AutoStraightDumpCommandGroup;
import frc.robot.commands.AutoStraightShootCommandGroup;
import frc.robot.commands.ControlPanelBagCommand;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.commands.DefaultIntakeCommand;
import frc.robot.commands.ElevatorCommand;
import frc.robot.commands.PidShootCommandGroup;
import frc.robot.commands.SetRpmShootCommandGroup;
import frc.robot.commands.SnowBlowerCommand;
import frc.robot.commands.VisionCommandGroup;
import frc.robot.commands.WinchCommand;
import frc.robot.subsystems.ControlPanel;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.PidShooter;
import frc.robot.subsystems.Winch;
import frc.robot.triggers.AnalogTrigger;
import frc.robot.triggers.TriggerButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  int leftHoriz = 0;
  int leftVert = 1;
  int rightHoriz = 4;
  int rightVert = 5;

  private DriveTrain driveTrain;
  private Intake intake;
  private PidShooter pidShooter;
  private Elevator elevator;
  private Winch winch;
  private ControlPanel controlPanel;

  private AutoStraightCommandGroup autoStraightCommandGroup;
  private AutoRightShootCommandGroup autoRightShootCommandGroup;
  private AutoStraightShootCommandGroup autoStraightShootCommandGroup;
  private AutoStraightDumpCommandGroup autoStraightDumpCommandGroup;
  private AutoMidShootCommand autoMidShootCommand;
  private AutoMidDumpCommand autoMidDumpCommand;
  private AutoLeftShootCommandGroup autoLeftShootCommandGroup;
  private AutoLeftDumpCommandGroup autoLeftDumpCommandGroup;
  private VisionCommandGroup visionCommandGroup;
  private SetRpmShootCommandGroup midShootCommand;
  private SetRpmShootCommandGroup lowShootCommand;
  private PidShootCommandGroup highShootCommand;
  private WinchCommand winchCommand;
  private SnowBlowerCommand snowBlowerCommand;
  private ControlPanelBagCommand controlPanelBagCommand;
  //private DriveDistanceCommand tDriveDistanceCommand;
  //private DriveTurnCommand tDriveTurnCommand;

  XboxController driverController;
  JoystickButton driverButtonA;
  JoystickButton driverButtonX;
  JoystickButton driverRightBumper;
  JoystickButton driverLeftBumper;
  POVButton driverPovDown;
  POVButton driverPovUp;
  Trigger driverLeftTrigger;
  Trigger driverRightTrigger;
  
  XboxController manipulatorController;
  JoystickButton maniButtonY;
  JoystickButton maniButtonA;
  JoystickButton maniButtonX;
  JoystickButton maniButtonB;
  JoystickButton maniLeftBumper;
  JoystickButton maniRightBumper;
  POVButton maniPovUp;
  POVButton maniPovDown;
  POVButton maniPovRight;
  Trigger maniLeftTrigger;
  Trigger maniRightTrigger;
  AnalogTrigger analogTrigger;

  ShuffleboardTab parameterTab;
  NetworkTableEntry highSpeed;
  NetworkTableEntry midSpeed;
  NetworkTableEntry lowerMidSpeed;
  NetworkTableEntry lowSpeed;
  SendableChooser<Command> autoChooser;

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Initalize subsystems
    driveTrain = new DriveTrain();
    intake = new Intake();
    pidShooter = new PidShooter();
    elevator = new Elevator(); 
    winch = new Winch();
    controlPanel = new ControlPanel();

    // Initalize commands
    autoStraightCommandGroup = new AutoStraightCommandGroup(driveTrain);
    autoRightShootCommandGroup = new AutoRightShootCommandGroup(driveTrain, intake, 
                                                                pidShooter);
    autoStraightShootCommandGroup = new AutoStraightShootCommandGroup(driveTrain, intake, 
                                                                      pidShooter);
    autoStraightDumpCommandGroup =  new AutoStraightDumpCommandGroup(driveTrain, intake,
                                                                     pidShooter);
    autoMidShootCommand = new AutoMidShootCommand(driveTrain, intake, pidShooter);
    autoMidDumpCommand = new AutoMidDumpCommand(driveTrain, intake, pidShooter);
    autoLeftShootCommandGroup = new AutoLeftShootCommandGroup(driveTrain, intake, 
                                                              pidShooter);
    autoLeftDumpCommandGroup = new AutoLeftDumpCommandGroup(driveTrain, intake, 
                                                            pidShooter);
    visionCommandGroup = new VisionCommandGroup(driveTrain);
    highShootCommand = new PidShootCommandGroup(driveTrain, intake, pidShooter);
    midShootCommand = new SetRpmShootCommandGroup(3500, intake, 
                                                        pidShooter);
    lowShootCommand = new SetRpmShootCommandGroup(1000, intake, 
                                                        pidShooter);
    highShootCommand = new PidShootCommandGroup(driveTrain, intake, pidShooter);
    winchCommand = new WinchCommand(winch);
    snowBlowerCommand = new SnowBlowerCommand(0.5, controlPanel);
    controlPanelBagCommand = new ControlPanelBagCommand(0.5, controlPanel);
    //tDriveDistanceCommand = new DriveDistanceCommand(50, true, driveTrain);
    //tDriveTurnCommand = new DriveTurnCommand(-90, driveTrain);
    
    // Initialize Driver Controller and Buttons
    driverController = new XboxController(0);

    driverButtonA = new JoystickButton(driverController, Button.kA.value);
    driverButtonX = new JoystickButton(driverController, Button.kX.value);
    driverPovDown = new POVButton(driverController, 180);
    driverPovUp = new POVButton(driverController, 0);
    driverRightBumper = new JoystickButton(driverController, Button.kBumperRight.value);
    driverLeftBumper = new JoystickButton(driverController, Button.kBumperLeft.value);
    driverLeftTrigger = new TriggerButton(driverController, Hand.kLeft);
    driverRightTrigger = new TriggerButton(driverController, Hand.kRight);
    
    

    // Initialize Manipulator Controller and Buttons
    manipulatorController = new XboxController(1);

    maniButtonA = new JoystickButton(manipulatorController, Button.kA.value);
    maniButtonX = new JoystickButton(manipulatorController, Button.kX.value);
    maniButtonY = new JoystickButton(manipulatorController, Button.kY.value);
    maniButtonB = new JoystickButton(manipulatorController, Button.kB.value);
    maniLeftBumper = new JoystickButton(manipulatorController, Button.kBumperLeft.value);
    maniRightBumper = new JoystickButton(manipulatorController, Button.kBumperRight.value);
    maniPovUp = new POVButton(manipulatorController, 0);
    maniPovDown = new POVButton(manipulatorController, 180);
    maniPovRight = new POVButton(manipulatorController, 90);
    
    // Set the default drive command to split-stick arcade drive
    driveTrain.setDefaultCommand(new DefaultDriveCommand(
        () -> driverController.getY(Hand.kLeft),
        () -> driverController.getY(Hand.kRight), 
        () -> driverController.getX(Hand.kRight), 
        () -> driverController.getXButtonReleased(), 
        () -> driverController.getAButtonReleased(),
        () -> driverController.getBButtonReleased(),
        () -> driverController.getYButtonReleased(),
        () -> Math.abs(driverController.getTriggerAxis(Hand.kLeft)) > 0.2,
        () -> Math.abs(driverController.getTriggerAxis(Hand.kRight)) > 0.2,
        driveTrain));

    intake.setDefaultCommand(new DefaultIntakeCommand(
        () -> manipulatorController.getY(Hand.kLeft),
        intake));
    elevator.setDefaultCommand(new ElevatorCommand(
        () -> manipulatorController.getY(Hand.kRight),
        elevator));
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //driverPovUp.whenHeld(tDriveDistanceCommand.raceWith(new IntakeCommand(0.8, 0.5, intake)));
    //driverPovDown.whenHeld(tDriveTurnCommand);
    driverRightBumper.whenHeld(visionCommandGroup);
    driverButtonX.whenReleased(new InstantCommand(() -> driveTrain.toggleOnLight(), driveTrain));
    maniButtonA.whenHeld(lowShootCommand);
    maniButtonX.whenHeld(new InstantCommand(() -> pidShooter.shootPower(1), pidShooter))
      .whenReleased(new InstantCommand(() -> pidShooter.stopShooting()));
    maniButtonB.whenHeld(midShootCommand);
    maniButtonY.whenHeld(highShootCommand)  
        .whenReleased(new InstantCommand(
            () -> pidShooter.stopShooting(), pidShooter));
    maniPovUp.whenHeld(new InstantCommand(
        () -> winch.winchControl(-1), winch))
        .whenReleased(new InstantCommand(
            () -> winch.winchControl(0)
        ));
    maniLeftBumper.whenHeld(visionCommandGroup);
    maniRightBumper.whenHeld(winchCommand);     
    maniPovDown.whenPressed(snowBlowerCommand);
    maniPovRight.whenPressed(controlPanelBagCommand);             
    
    //The Autonomous chooser
    autoChooser = new SendableChooser<Command>();
    autoChooser.setDefaultOption("Auto Right Shoot", autoRightShootCommandGroup);
    autoChooser.addOption("Auto Straight", autoStraightCommandGroup);
    autoChooser.addOption("Auto Straight Shoot", autoStraightShootCommandGroup);
    autoChooser.addOption("Auto Straight Dump", autoStraightDumpCommandGroup);
    autoChooser.addOption("Auto Middle Shoot", autoMidShootCommand);
    autoChooser.addOption("Auto Middle Dump", autoMidDumpCommand);
    autoChooser.addOption("Auto Left Shoot", autoLeftShootCommandGroup);
    autoChooser.addOption("Auto Left Dump", autoLeftDumpCommandGroup);
    Shuffleboard.getTab("Driver Tab")
      .add("Auto Chooser", autoChooser)
      .withWidget(BuiltInWidgets.kComboBoxChooser);
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autoChooser.getSelected();
  }
}
