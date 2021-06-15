// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autogroups;

import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.LifterCommand;
import frc.robot.commands.LifterUp;
import frc.robot.commands.ShootVelocity;
import frc.robot.commands.TransferCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShootAuto extends ParallelCommandGroup {
  /** Creates a new ShootAuto. */
  public ShootAuto(ShootVelocity shooter, TransferCommand transfer, LifterUp lifterUp) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
    shooter,
    transfer,
    lifterUp
    );
  }
}
