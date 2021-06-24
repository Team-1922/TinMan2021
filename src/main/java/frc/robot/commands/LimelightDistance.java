// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class LimelightDistance extends CommandBase {
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-shooter");
  NetworkTableEntry thor = table.getEntry("thor");
  private double distance;
  private double targetWidth = thor.getDouble(0-320);
  /** Creates a new LimelightDistance. */
  public LimelightDistance() {

  
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // thor = -.52*distance(in)+156(in) honestly idk how to do simple graphing, works for now
    // distance = (thor-156)/-.52 hypothetically
distance = (targetWidth-156)/-.52;
SmartDashboard.putNumber("limelightDistance", distance);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
