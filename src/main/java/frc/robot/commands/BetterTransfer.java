// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.LinearTransfer;
import frc.robot.subsystems.Shooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class BetterTransfer extends ParallelCommandGroup {
  /** Creates a new BetterTransfer. */

  private Shooter m_shooter;

  public BetterTransfer(Indexer indexer, LinearTransfer transfer, double speed) {
/*
  NetworkTable table = NetworkTableInstance.getDefault().getTable("OzRam");
  double shooterSpeed = table.getEntry("DisplayShootingVelocity"); // fix this so it works //

    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    if (shooterSpeed > 0) {

      addCommands(
      new IndexerReverse(indexer),
      new TransferCommand(transfer, speed)
    );

    }
    */
  }
}
