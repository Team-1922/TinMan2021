/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */

public final class Constants {

    // Collector end is forward //

    // talon placement
    // 5 0
    // 6 1
    // 7 2
    // 8 3
    // 9 4

    /*
     * public static final int frontRight = 0; public static final int frontLeft =
     * 5; public static final int rearRight = 1; public static final int rearLeft =
     * 6; // shooter public static final int shooterRight = 2; public static final
     * int shooterLeft = 7; // collector public static final int pickUp = 3; public
     * static final int pickUpTransfer = 8; // public static final int linearLoaderT
     * = 4; // public static final int linearLoaderB = 9; // linear public static
     * final int transferFront = 4; public static final int transferRear = 9;
     */

    public static final int collector = 5;

    public static final int rearRight = 1;
    public static final int frontRight = 2;
    public static final int rearLeft = 6;
    public static final int frontLeft = 7;

    public static final int linearTransfer = 8;

    public static final int leftIndexer = 9;
    public static final int rightIndexer = 4;

    public static final int shooterRight = 11;
    public static final int shooterLeft = 10;

    // Climber
    public static final int topLeft = 13;
    public static final int topRight = 14;
    public static final int bottomLeft = 15;
    public static final int bottomRight = 16;

    // pneumatics
    // public static final int collectorRight = 0;
    public static final int lifter = 1;
    public static final int hood = 2;
    public static final int collectorP = 3;
    public static final int compressor = 12;
    

public static final  double encoderInchConversion = 2048/19.24;
}
