/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autocommands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.Constants;

public class DriveForward extends CommandBase {
 private DriveTrain m_driveTrain;
 private double m_distance;
 private double m_speed;
 private double m_encoderStartValue;
 private String m_leg;

 /**
   * Creates a new DriveForward.
   */
  public DriveForward(DriveTrain driveTrain, String leg) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_driveTrain = driveTrain;
    addRequirements(m_driveTrain);
    m_leg = leg;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_encoderStartValue = m_driveTrain.getLeftEncoder();
 
    NetworkTable table = NetworkTableInstance.getDefault().getTable("OzRam");
    m_speed = table.getEntry("drivespeed").getDouble(.25);
    m_distance = table.getEntry(m_leg).getDouble(25) * Constants.encoderInchConversion;
  
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_driveTrain.drive(m_speed, m_speed, false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.drive(0, 0, false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double leftEncoder = m_driveTrain.getLeftEncoder();
    if (m_distance <= leftEncoder - m_encoderStartValue){
      return true;
    }
    return false;
  }
}
