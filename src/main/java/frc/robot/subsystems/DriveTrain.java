/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/**
 * manages the speed of the wheels
 */
public class DriveTrain extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private WPI_TalonFX frontLeft = new WPI_TalonFX(Constants.frontLeft);
  private WPI_TalonFX rearLeft = new WPI_TalonFX(Constants.rearLeft);
  private WPI_TalonFX frontRight = new WPI_TalonFX(Constants.frontRight);
  private WPI_TalonFX rearRight = new WPI_TalonFX(Constants.rearRight);
  private AHRS ahrs = new AHRS(SPI.Port.kMXP);
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");
  private double x = tx.getDouble(0.0);
  private double y = ty.getDouble(0.0);
  private double area = ta.getDouble(0.0);
  private boolean isFlipped = false;

  public DriveTrain() {
    super();
    rearLeft.set(ControlMode.Follower, frontLeft.getDeviceID());
    rearRight.set(ControlMode.Follower, frontRight.getDeviceID());
    frontLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    frontRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    frontRight.setInverted(TalonFXInvertType.Clockwise);
    rearRight.setInverted(InvertType.FollowMaster);
    frontLeft.setInverted(TalonFXInvertType.CounterClockwise);
    rearLeft.setInverted(InvertType.FollowMaster);
  }

  public void drive(double left, double right, boolean flipped) {
    if (flipped) {
      frontRight.set(-left);
      frontLeft.set(-right*1.2);
    
    } else {
      //Multiply left by 1.2 if autonomous, works well because drifitng
      frontLeft.set(left*1.2);
      frontRight.set(right);
  
    }
      SmartDashboard.putNumber("Left", left);
      SmartDashboard.putNumber("Right",right);
      SmartDashboard.putBoolean("Flipped", flipped); 
  }
 
  @Override
  public void periodic() {
    double angle = ahrs.getAngle();
    SmartDashboard.putNumber("Angle", angle);
    double encoderLeft = frontLeft.getSelectedSensorPosition();
    SmartDashboard.putNumber("leftEncoder", encoderLeft);
    double encoderRight = frontRight.getSelectedSensorPosition();
    SmartDashboard.putNumber("rightEncoder", encoderRight);

  }

  public double getAngle() {
   double angle = ahrs.getAngle();
    SmartDashboard.putNumber("Angle", angle);
    return angle;

    
  }

  public void refreshLimelight() {
    x = tx.getDouble(0.0);
    y = ty.getDouble(0.0);
    area = ta.getDouble(0.0);

    // post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);
  }

  public void setFlip(boolean f){
    isFlipped = f;
  }
  public boolean getFLip(){
    return isFlipped;
  }
  public void toggleFlip(){
    isFlipped = !isFlipped;
  }

  public double getLeftEncoder(){
    double encoderLeft = frontLeft.getSelectedSensorPosition();
    SmartDashboard.putNumber("leftEncoder", encoderLeft);

    return encoderLeft;
  }
  public double getRightEncoder(){
double encoderRight = frontRight.getSelectedSensorPosition();
SmartDashboard.putNumber("rightEncoder", encoderRight);
    return encoderRight;
  }

}
