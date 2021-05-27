/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * shhoot shoot bang bagn
 */
public class Shooter extends SubsystemBase {
    private WPI_TalonFX shooterLeft = new WPI_TalonFX(Constants.shooterLeft);
    private WPI_TalonFX shooterRight = new WPI_TalonFX(Constants.shooterRight);
    
    private Solenoid hoodSolenoid;
    // private WPI_TalonFX shooterLeft = new WPI_TalonFX(Constants.shooterLeft);
    // private WPI_TalonFX shooterRight = new WPI_TalonFX(Constants.shooterRight);

    public Shooter() {
        super();
        shooterLeft.configFactoryDefault();
        shooterRight.configFactoryDefault();
        shooterLeft.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);
        shooterLeft.config_kF(0, .058, 30);
		shooterLeft.config_kP(0, 0.05115, 30);
		shooterLeft.config_kI(0, 0, 30);
		shooterLeft.config_kD(0, 0, 30);
        shooterRight.set(ControlMode.Follower, shooterLeft.getDeviceID());
        shooterLeft.setInverted(true);
        shooterRight.setInverted(InvertType.OpposeMaster);
        hoodSolenoid = new Solenoid(Constants.hood);
    }

    public void shoot(double speed) {
        shooterLeft.set(speed);
        // shooterRight.set(speed);

    }

    public void setVelocity(double speed) {
        shooterLeft.set(ControlMode.Velocity, speed);
    }

    public void hoodUp() {
        hoodSolenoid.set(true);
    }

    public void hoodDown() {
        hoodSolenoid.set(false);

    }
    public void toggleHood(){
        hoodSolenoid.set(!hoodSolenoid.get());
    }

    public double getShooterRPM()
    {
            return shooterLeft.getSelectedSensorVelocity(0) * 600 / 2048;
    }

    @Override
    public void periodic() {
        // SmartDashboard.putNumber("DisplayShootingVelocity", shooterLeft.getSelectedSensorVelocity(0) * 600 / 2048);
        SmartDashboard.putNumber("DisplayShootingVelocity", getShooterRPM());
        SmartDashboard.putNumber("VelocityError", shooterLeft.getClosedLoopError(0) * 600 / 2048);
    }
}
