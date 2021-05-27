/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;


public class ShootVelocity extends CommandBase {
  private final Shooter m_Subsystem;
  private double m_shootingVelocity;
  private String m_target; 
  /**
   * Creates a new SetVelocity.
   */
  public ShootVelocity(Shooter subsystem, String target) {
    m_Subsystem = subsystem;
    m_target = target; 
    addRequirements(m_Subsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("OzRam");
    m_shootingVelocity = table.getEntry(m_target).getDouble(2585);
    m_Subsystem.hoodUp();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_Subsystem.setVelocity((m_shootingVelocity * 2048 / 600));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_Subsystem.setVelocity(0);
    m_Subsystem.hoodDown();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
