/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autocommands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;


public class Turn extends CommandBase {
  private DriveTrain m_driveTrain;
 private double m_startAngle;
private double m_angle;
private double m_turnSpeed;
private String m_angleTableEntry;
  /**
   * Creates a new Turn.
   */
  public Turn(DriveTrain driveTrain, String angle) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_driveTrain = driveTrain;
    addRequirements(m_driveTrain);
    m_angleTableEntry = angle;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_startAngle = m_driveTrain.getAngle();
    NetworkTable table = NetworkTableInstance.getDefault().getTable("OzRam");
m_turnSpeed = table.getEntry("turnspeed").getDouble(.40);
 m_angle = table.getEntry(m_angleTableEntry).getDouble(25);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_angle < 0){
      m_driveTrain.drive(-m_turnSpeed, m_turnSpeed, false);
    }
    else {
      m_driveTrain.drive(m_turnSpeed, -m_turnSpeed, false);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_driveTrain.drive(0, 0, false);
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double currentAngle = m_driveTrain.getAngle();
    if (m_angle < 0) {
      if (m_angle >= currentAngle - m_startAngle) {
       return true;
      }
     
    }

    else {
     if (m_angle <= currentAngle - m_startAngle){
       return true;
      }
    }
   
    return false;
  }
}
