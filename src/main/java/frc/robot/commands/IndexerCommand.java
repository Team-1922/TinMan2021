/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Indexer;

public class IndexerCommand extends CommandBase {

  private Indexer m_indexerSubsystem;
  private long m_startingTime;
  private long m_currentTime;
  private long m_totalTime;
  /**
   * Creates a new IndexerCommand.
   */
  public IndexerCommand(Indexer indexerSubsystem) {

    m_indexerSubsystem = indexerSubsystem;
    addRequirements(indexerSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_totalTime = 0;
    m_currentTime = 0;
    m_startingTime = System.currentTimeMillis();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_indexerSubsystem.drive(0.5);
    m_currentTime = System.currentTimeMillis();

    m_totalTime = m_currentTime - m_startingTime;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_totalTime = 0;
    m_currentTime = 0;
    m_startingTime = 0;
    m_indexerSubsystem.drive(0);
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
