// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autogroups;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;
import frc.robot.autocommands.DriveForward;
import frc.robot.autocommands.Turn;

public class StartingAuto extends SequentialCommandGroup {
  /** Creates a new StartingAuto. */
  public StartingAuto(DriveTrain driveTrain//, shooter stuff too
  ) {
    // Use addRequirements() here to declare subsystem dependencies.

  addCommands(
    
  new DriveForward(driveTrain, "startLeg1"));

  }

}