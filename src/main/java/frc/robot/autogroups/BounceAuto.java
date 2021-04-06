// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autogroups;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;
import frc.robot.autocommands.DriveForward;
import frc.robot.autocommands.Turn;

public class BounceAuto extends SequentialCommandGroup {
  /** Creates a new BounceAuto. */
  public BounceAuto(DriveTrain driveTrain) {
    
    addCommands(new DriveForward(driveTrain, "c_firstLeg"), 
    new Turn(driveTrain, "c_firstturn"),
      new DriveForward(driveTrain, "c_secondLeg"),
      new Turn(driveTrain, "c_secondturn"),
      new DriveForward(driveTrain, "c_thirdLeg"),
      new Turn(driveTrain, "c_thirdturn"),
      new DriveForward(driveTrain, "c_fourthLeg"),
      new Turn(driveTrain, "c_fourthturn"),
      new DriveForward(driveTrain, "c_fifthLeg"), 
      new Turn(driveTrain, "c_fifthturn"),
      new DriveForward(driveTrain, "c_sixthLeg"),
      new Turn(driveTrain, "c_sixthturn"),
      new DriveForward(driveTrain, "c_seventhLeg"),
      new Turn(driveTrain, "c_seventhturn"),
      new DriveForward(driveTrain, "c_eighthLeg"),
      new Turn(driveTrain, "c_eighthturn"),
      new DriveForward(driveTrain, "c_ninthLeg"), 
      new Turn(driveTrain, "c_ninthturn"),
      new DriveForward(driveTrain, "c_tenthLeg"));
  }



}