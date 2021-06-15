/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * Collector Subsystem
 */
public class Collector extends SubsystemBase {
  private WPI_TalonSRX pickUp = new WPI_TalonSRX(Constants.collector);
  private Solenoid CollectorSolenoid;
  private double CollectorSpeed;

  public Collector() {
    super();
    CollectorSolenoid = new Solenoid(Constants.collectorP);
  }

  public double getCollectorSpeed() {

  return CollectorSpeed;
  }
  

  public void drive(double speed) {
    pickUp.set(speed);
    CollectorSpeed = speed;
  }
/* Just testing -- Switching true and false here below.
If that doesn't work, put it back and delete this line after.*/ 
  public void CollectorUp() {
     CollectorSolenoid.set(false);
  }

  public void CollectorDown() {
     CollectorSolenoid.set(true);
  }

  public void ToggleCollector() {
     CollectorSolenoid.set(! CollectorSolenoid.get());
  }

  public boolean UpStatus() {
if (CollectorSolenoid.get() == false) {
  return true;
}
else {
  return false;
}

  }
}
