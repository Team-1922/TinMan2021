// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {
  /** Creates a new ClimberSubsystem. */

  /*
TOP ONE EXTENDS
BOTTOM ONE CONTRACTS
  */

  private WPI_TalonSRX topLeft = new WPI_TalonSRX(Constants.topLeft);
  private WPI_TalonSRX topRight = new WPI_TalonSRX(Constants.topRight);
  private WPI_TalonSRX bottomLeft = new WPI_TalonSRX(Constants.bottomLeft);
  private WPI_TalonSRX bottomRight = new WPI_TalonSRX(Constants.bottomRight);

  public ClimberSubsystem() {
bottomRight.set(ControlMode.Follower, bottomLeft.getDeviceID());
topRight.set(ControlMode.Follower, topLeft.getDeviceID());

topRight.setInverted(true);
bottomRight.setInverted(true);
  }

public void drive(double top, double bottom) {
  topLeft.set(top);
  bottomLeft.set(bottom);
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
