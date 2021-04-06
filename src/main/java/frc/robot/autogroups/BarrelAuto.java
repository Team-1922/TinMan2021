// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autogroups;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autocommands.DriveForward;
import frc.robot.autocommands.Turn;
import frc.robot.subsystems.DriveTrain;

public class BarrelAuto extends SequentialCommandGroup {
  private DriveTrain m_driveTrain;
  /** Creates a new BarrelAuto. */
  public BarrelAuto(DriveTrain driveTrain) {
    m_driveTrain = driveTrain;
    
    addCommands(new DriveForward(m_driveTrain, "b_firstLeg"), 
    new Turn(m_driveTrain, "b_firstturn"),
      new DriveForward(m_driveTrain, "b_secondLeg"),
      new Turn(m_driveTrain, "b_secondturn"),
      new DriveForward(m_driveTrain, "b_thirdLeg"),
      new Turn(m_driveTrain, "b_thirdturn"),
      new DriveForward(m_driveTrain, "b_fourthLeg"),
      new Turn(m_driveTrain, "b_fourthturn"),
      new DriveForward(m_driveTrain, "b_fifthLeg"), 
      new Turn(m_driveTrain, "b_fifthturn"),
      new DriveForward(m_driveTrain, "b_sixthLeg"),
      new Turn(m_driveTrain, "b_sixthturn"),
      new DriveForward(m_driveTrain, "b_seventhLeg"),
      new Turn(m_driveTrain, "b_seventhturn"),
      new DriveForward(m_driveTrain, "b_eighthLeg"),
      new Turn(m_driveTrain, "b_eighthturn"),
      new DriveForward(m_driveTrain, "b_ninthLeg"), 
      new Turn(m_driveTrain, "b_ninthturn"),
      new DriveForward(m_driveTrain, "b_tenthLeg"));
  }



}
