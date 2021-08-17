// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;



public class LimelightBallFind extends CommandBase {
  private DriveTrain m_driveTrain;
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  private double PGain = .07;
  private double minSpeed = .25;
  private double error;
  private double acceptableError = 1.5;
  private double response;
  private double targetTimerStart;
  private double targetTimerTotal = 10;

  private double DGain;
  private double startingTime;
  private double totalTime;
  private double speedMod;
  private double changeTime;
  private double startingError; 
  private double currentError;
  private double changeError;
  /** Creates a new LimelightShooter. */
  public LimelightBallFind(DriveTrain driveTrain) {
    m_driveTrain = driveTrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("OzRam");
    PGain = table.getEntry("BallLimelightPGain").getDouble(.25);
    DGain = table.getEntry("BallLimelightDGain").getDouble(.25);
    minSpeed = table.getEntry("BallLimelightMinSpeed").getDouble(.25);
    acceptableError = table.getEntry("BallLimelightGoodError").getDouble(.25);
    targetTimerTotal = table.getEntry("BallLimelightTargetTime").getDouble(10);
    targetTimerStart = 0;
    startingTime = System.currentTimeMillis();
    startingError = tx.getDouble(0.0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    error = tx.getDouble(0.0);
    response = PGain * error;
  /*  if(Math.abs(response) < minSpeed) {
      response = minSpeed * Math.signum(response);
    }
    */

    totalTime = System.currentTimeMillis();
    changeTime = totalTime - startingTime;
    startingTime = totalTime;
    
    currentError = tx.getDouble(0.0);
    changeError = startingError - currentError;
    startingError = currentError;
    speedMod = changeError / changeTime * DGain;

    response = response + speedMod + minSpeed * Math.signum(response);

    if(error >= -acceptableError && error <= acceptableError){
      targetTimerStart = targetTimerStart + 1;
      response = response * .5;
    } else {
      targetTimerStart = 0;
    }

    m_driveTrain.drive(response, -response, false);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (error <= -acceptableError && error >= acceptableError) {
      return false;
    }
  
    if(targetTimerStart == targetTimerTotal) {
      return true;
    }
    return false;
  }

}
