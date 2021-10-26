// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberSubsystem;

public class ClimberMove extends CommandBase {
  private ClimberSubsystem m_climber;
  private double m_topSpeed;
  private double m_bottomSpeed;
  /** Creates a new ClimberMove. */
  public ClimberMove(ClimberSubsystem climber, double topSpeed, double bottomSpeed) { 
    // Use addRequirements() here to declare subsystem dependencies.
m_topSpeed = topSpeed;
m_bottomSpeed = bottomSpeed;
m_climber = climber;

addRequirements(m_climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_climber.drive(m_topSpeed, m_bottomSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_climber.drive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
