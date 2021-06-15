// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.LinearTransfer;
import frc.robot.subsystems.Shooter;


public class BetterIndexer extends CommandBase {
  private Indexer m_indexer;
  private Shooter m_shooter;
  private LinearTransfer m_transfer;
  private long m_startingTime;
  private long m_currentTime;
  private long m_totalTime;

  /** Creates a new BetterIndexer. */
  public BetterIndexer(Indexer indexer, LinearTransfer transfer, Shooter shooter) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_indexer = indexer;
    m_transfer = transfer;
    m_shooter = shooter;
    addRequirements(m_indexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
m_startingTime = 0;
m_currentTime = 0;
m_totalTime = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
double shootSpeed = m_shooter.getShooterRPM();
double transferSpeed = m_transfer.getTransferSpeed();


if (shootSpeed > 0 ) {
  m_indexer.drive(0.5);
  if (m_totalTime == 0) {
    m_startingTime = System.currentTimeMillis();
  }
  m_currentTime = System.currentTimeMillis();
  m_totalTime = m_currentTime - m_startingTime + 1;

} else {
  if (Math.abs(transferSpeed) > 0) {
    m_indexer.drive(-0.5);
  } else {
    m_indexer.drive(0);
    
  }
    m_totalTime = 0;
    m_startingTime = 0;
}


  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
m_startingTime = 0;
m_currentTime = 0;
m_totalTime = 0;

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  public long getTotalTime() {
    return m_totalTime;
  }
}
