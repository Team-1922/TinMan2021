// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;



public class LimelightShooter extends CommandBase {
  private DriveTrain m_driveTrain;
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-shooter");
  NetworkTableEntry tx = table.getEntry("tx");
  private double PGain = .07;
  private double minSpeed = .25;
  private double error;
  private double acceptableError = 1.5;
  /** Creates a new LimelightShooter. */
  public LimelightShooter(DriveTrain driveTrain) {
    m_driveTrain = driveTrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("OzRam");
    PGain = table.getEntry("ShooterLimelightPGain").getDouble(.25);
    minSpeed = table.getEntry("ShooterLimelightMinSpeed").getDouble(.25);
    acceptableError = table.getEntry("ShooterLimelightGoodError").getDouble(.25);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    error = tx.getDouble(0.0);
    double response = PGain * error;
    if(Math.abs(response) < minSpeed) {
      response = minSpeed * Math.signum(response);
    }

    m_driveTrain.drive(response, -response, true);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
if (error >= acceptableError) {
  return false;
}
if (error <= acceptableError * -1) {
  return false;
  }
  return true;
  }

}
