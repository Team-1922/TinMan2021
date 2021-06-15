/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lifter;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class LifterCommand extends CommandBase {
  private Lifter m_lifter;
  private Shooter m_shooter;
  private BetterIndexer m_bIndexerCommand;
  private boolean m_isToggled;
  public LifterCommand(Lifter lifter, BetterIndexer bIndexerCommand, Shooter shooter) {
    m_lifter = lifter;
    m_bIndexerCommand = bIndexerCommand;
    m_shooter = shooter;
    addRequirements(m_lifter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_isToggled = false;
  }

  @Override
  public void execute() {
  long totalTime = m_bIndexerCommand.getTotalTime();
  if (m_shooter.getShooterRPM() >= 1850 || m_lifter.lifterState()) {//(totalTime >= 2000 || m_lifter.lifterState()) { //that 2000 is arbitrary, test and fix
    m_lifter.lifterToggle();
    m_isToggled = true;
  }

  }

  @Override
  public void end(boolean interrupted) {

  }

  @Override
  public boolean isFinished() {
    return m_isToggled;
  }
}
