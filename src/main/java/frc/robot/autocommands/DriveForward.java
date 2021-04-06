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
 private double m_rightEncoderStartValue;
 private String m_leg;
 private double m_pGain;
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
    m_rightEncoderStartValue = m_driveTrain.getRightEncoder();
 
    NetworkTable table = NetworkTableInstance.getDefault().getTable("OzRam");
    m_speed = table.getEntry("drivespeed").getDouble(.25);
    m_distance = table.getEntry(m_leg).getDouble(25) * Constants.encoderInchConversion;
    m_pGain = table.getEntry("PGain").getDouble(.0005);
  
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  double newEncoderValue = m_driveTrain.getLeftEncoder();
  double newRightEncoderValue = m_driveTrain.getRightEncoder();
  double encoderError = (newRightEncoderValue - newEncoderValue) - (m_rightEncoderStartValue - m_encoderStartValue) ;

  NetworkTable table = NetworkTableInstance.getDefault().getTable("OzRam");
  NetworkTableEntry errorEntry = table.getEntry("encoderError");
                errorEntry.setNumber(encoderError);      

  double speedAdjustment = m_pGain * encoderError ; 
  m_driveTrain.drive((m_speed + speedAdjustment), m_speed - speedAdjustment, false);

  
    

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
