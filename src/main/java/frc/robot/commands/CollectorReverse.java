// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.Collector;



public class CollectorReverse extends CommandBase {
private Collector m_subsystem;
private CollectorDown m_collectorDown;

  /** Creates a new CollectorReverse. */
  public CollectorReverse(Collector subsystem, CollectorDown collectorDown) {
      m_subsystem = subsystem;
      m_collectorDown = collectorDown;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

if (m_subsystem.getCollectorSpeed() > 0) {
    m_collectorDown.reverse();
}
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {


  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
//CommandScheduler.getInstance().schedule(new CollectorDown(m_subsystem, m_speed));
if (m_subsystem.UpStatus() == false) {
  m_collectorDown.reverse();
}

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
