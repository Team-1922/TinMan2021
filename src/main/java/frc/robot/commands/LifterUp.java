// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lifter;
import frc.robot.subsystems.Shooter;

public class LifterUp extends CommandBase {
  private Lifter m_lifter;
  private Shooter m_shooter;
  private boolean m_isToggled;
  /** Creates a new LifterUp. */
  public LifterUp(Lifter lifter, Shooter shooter) {
    m_lifter = lifter;
    m_shooter = shooter;
    addRequirements(m_lifter);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_shooter.getShooterRPM() >= 1500) {
          m_lifter.lifterUp();
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_isToggled;
  }
}
