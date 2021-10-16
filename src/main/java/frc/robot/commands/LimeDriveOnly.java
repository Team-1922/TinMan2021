// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class LimeDriveOnly extends CommandBase {
  private DriveTrain m_driveTrain;
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry thor = table.getEntry("thor");
  private double speed;
  private double m_pGain;
  private double m_encoderStartValue;
  private double m_rightEncoderStartValue;


  
  /** Creates a new LimeDriveOnly. */
  public LimeDriveOnly(DriveTrain driveTrain) {
    m_driveTrain = driveTrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("OzRam");
    speed = table.getEntry("limeGetBallSpeed").getDouble(.75);

    m_pGain = table.getEntry("PGain").getDouble(.0005);
    m_encoderStartValue = m_driveTrain.getLeftEncoder();
    m_rightEncoderStartValue = m_driveTrain.getRightEncoder();
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
 SmartDashboard.putString("Command", "LimeDriveOnly");
  
    double speedAdjustment = m_pGain * encoderError ; 
    //m_driveTrain.drive((speed + speedAdjustment), speed - speedAdjustment, false);
    m_driveTrain.drive(speed, speed, false);
  

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.drive(0, 0, false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double teaWhy = thor.getDouble(0.0);
    if (teaWhy > 60) {
      return true;
    }

    return false;
  }
}
