// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class LimePickupBall extends CommandBase {
  private DriveTrain m_driveTrain;
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  private double PGain;
  private double DGain;
  private double error;
  private double startingError;
  private double time;
  private double startingTime;
  private double changeError;
  private double changeTime;
  private double response;
  private double speedMod;
  private double speed;
  /** Creates a new LimePickupBall. */
  public LimePickupBall(DriveTrain driveTrain) {
m_driveTrain = driveTrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("OzRam");
    PGain = table.getEntry("BallLimelightPGain").getDouble(.25);
    DGain = table.getEntry("BallLimelightDGain").getDouble(.25);
    speed = table.getEntry("limeGetBallSpeed").getDouble(.75);

    startingTime = System.currentTimeMillis();
    startingError = tx.getDouble(0.0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

error = tx.getDouble(0.0);
time = System.currentTimeMillis();

response = PGain * error;

changeTime = time - startingTime;
changeError = error - startingError;

speedMod = changeError / changeTime * DGain;
response = response + speedMod;

m_driveTrain.drive(speed+response, speed-response, false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (ty.getDouble(0.0) < -20) {
      return true;
    }
    return false;
  }
}
