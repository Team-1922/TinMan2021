// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class LimelightDistance extends CommandBase {
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-shooter");
  NetworkTableEntry ty = table.getEntry("ty");
  private double distance;
  private double targetY;
  private double lightAngle = 18;
  private double lightHeight = 25;
  private double targetHeight = 93.5;
  private NetworkTableEntry distanceEntry;
  private NetworkTableEntry velocityDistance;

  private double shortRange;
  private double medRange;
  private double bigRange;
  private double maxRange;
  private double rangeValue;

  /** Creates a new LimelightDistance. */
  public LimelightDistance() {


   
   
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    NetworkTable coolTable = NetworkTableInstance.getDefault().getTable("OzRam");
   distanceEntry = coolTable.getEntry("limeDistance");

   velocityDistance = coolTable.getEntry("limeVelocityDistance");
  targetY= ty.getDouble(0.0);
  
  shortRange = coolTable.getEntry("velocityLimeShort").getDouble(1901);
   medRange = coolTable.getEntry("velocityLimeMed").getDouble(1902);
   bigRange = coolTable.getEntry("velocityLimeBig").getDouble(1903);
   maxRange = coolTable.getEntry("velocityLimeMax").getDouble(1904);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
//d = (h2-h1) / tan(a1+a2) a2=ty   h1, a1, h2 physical
distance = (targetHeight - lightHeight) / Math.tan(Math.toRadians(lightAngle + targetY));
distanceEntry.setNumber(distance);


if(distance < 35) {
  rangeValue = shortRange; 
} else if(distance < 70) {
  rangeValue = medRange;
} else if(distance < 105) {
  rangeValue = bigRange;
} else {
  rangeValue = maxRange;
}
  velocityDistance.setNumber(rangeValue);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
