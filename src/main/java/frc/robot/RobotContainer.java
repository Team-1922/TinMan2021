/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import javax.lang.model.util.ElementScanner6;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.autogroups.BarrelAuto;
import frc.robot.autogroups.BounceAuto;
import frc.robot.autogroups.SlalomAuto;
import frc.robot.commands.CollectorDown;
import frc.robot.commands.CollectorUp;
import frc.robot.commands.DriveStraight;
import frc.robot.commands.FlipCommand;
import frc.robot.commands.IndexerCommand;
import frc.robot.commands.LifterCommand;
import frc.robot.commands.Limelight;
import frc.robot.commands.Shoot;
import frc.robot.commands.ShootingCommand;
import frc.robot.commands.TankDriveCommand;
import frc.robot.commands.ToggleCompressor;
import frc.robot.commands.ToggleHoodCommand;
import frc.robot.commands.TransferCommand;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.CompressorSubsystem;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Indexer;
import frc.robot.subsystems.Lifter;
import frc.robot.subsystems.LinearTransfer;
import frc.robot.subsystems.Shooter;
import frc.robot.autocommands.DriveForward;
import frc.robot.autocommands.Turn;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
        // The robot's subsystems and commands are defined here...
        private final DriveTrain m_driveTrain = new DriveTrain();
        private final Shooter m_Shooter = new Shooter();
        private final LinearTransfer m_lTransfer = new LinearTransfer();
        private final Collector m_Collector = new Collector();
        private final Indexer m_indexer = new Indexer();
        private final Lifter m_lifter = new Lifter();
        private final CompressorSubsystem m_compressor = new CompressorSubsystem();

        private final Joystick m_joystickLeft = new Joystick(1);
        private final Joystick m_joystickRight = new Joystick(0);

        private final XboxController m_XBoxController = new XboxController(2);

        private final TankDriveCommand m_TankDrive = new TankDriveCommand(m_driveTrain, m_joystickLeft,
                        m_joystickRight);
        private final TransferCommand m_TransferPassive = new TransferCommand(m_lTransfer, 0);
        private final CollectorUp m_CollectorUp = new CollectorUp(m_Collector);
        private final SendableChooser<Command> m_autoChooser = new SendableChooser<Command>();
        // private final CollectorDown m_CollectorDown =
        // private final DriveStraightAuto m_autoCommand = new
        // DriveStraightAuto(m_driveTrain, .2, 2);
        private final SlalomAuto m_slalomAutoCommand = new SlalomAuto(m_driveTrain);
        private final BarrelAuto m_barrelAutoCommand = new BarrelAuto(m_driveTrain);
        private final BounceAuto m_bounceAutoCommand = new BounceAuto(m_driveTrain);
        private final Shoot m_shoot = new Shoot(m_indexer, m_Shooter );
        // private final DefaultAuto m_autoCommand = new DefaultAuto(m_driveTrain);

        
        /**
         * The container for the robot. Contains subsystems, OI devices, and commands.
         */
        public RobotContainer() {
                // Configure the button bindings
                configureButtonBindings();
                m_driveTrain.setDefaultCommand(m_TankDrive);
                m_lTransfer.setDefaultCommand(m_TransferPassive);
                m_Collector.setDefaultCommand(m_CollectorUp);


                initNetworkTable();
                initAutoChooser();

        }

        private void initAutoChooser()
        {

m_autoChooser.setDefaultOption("Slalom", m_slalomAutoCommand);
m_autoChooser.addOption("Barrel", m_barrelAutoCommand);
m_autoChooser.addOption("Bounce", m_bounceAutoCommand);
SmartDashboard.putData("Auto", m_autoChooser);
        }

        /**
         * initialize the NetworkTable with the default values that we expect to have available.
         * 
         **/

        private void initNetworkTable()
        {
                NetworkTable table = NetworkTableInstance.getDefault().getTable("OzRam");
                
                NetworkTableEntry pGainEntry = table.getEntry("PGain");
                pGainEntry.setNumber(.0005);     
                
                NetworkTableEntry stabilizer = table.getEntry("stabilizer");
                stabilizer.setNumber(1);     

                NetworkTableEntry entry = table.getEntry("drivespeed");
                entry.setNumber(.27);      

                NetworkTableEntry turnEntry = table.getEntry("turnspeed");
                turnEntry.setNumber (.46);
                
                NetworkTableEntry firstLeg = table.getEntry("firstLeg");
                firstLeg.setNumber(25);   

                NetworkTableEntry firstturn = table.getEntry("firstturn");
                firstturn.setNumber(-40);   
                
                NetworkTableEntry secondLeg = table.getEntry("secondLeg");
                secondLeg.setNumber(55);   

                NetworkTableEntry secondturn = table.getEntry("secondturn");
                secondturn.setNumber(55);   
                
                NetworkTableEntry thirdLeg = table.getEntry("thirdLeg");
                thirdLeg.setNumber(120);   

                NetworkTableEntry thirdturn = table.getEntry("thirdturn");
                thirdturn.setNumber(48);   

                NetworkTableEntry fourthLeg = table.getEntry("fourthLeg");
                fourthLeg.setNumber(70);   

                NetworkTableEntry fourthturn = table.getEntry("fourthturn");
                fourthturn.setNumber(-89);   

                NetworkTableEntry fifthLeg = table.getEntry("fifthLeg");
                fifthLeg.setNumber(40);   

                NetworkTableEntry fifthturn = table.getEntry("fifthturn");
                fifthturn.setNumber(-80);   

                NetworkTableEntry sixthLeg = table.getEntry("sixthLeg");
                sixthLeg.setNumber(350);   

                NetworkTableEntry sixthturn = table.getEntry("sixthturn");
                sixthturn.setNumber(-87);   

                NetworkTableEntry seventhLeg = table.getEntry("seventhLeg");
                seventhLeg.setNumber(61);   

                NetworkTableEntry seventhturn = table.getEntry("seventhturn");
                seventhturn.setNumber(45);   

                NetworkTableEntry eighthLeg = table.getEntry("eighthLeg");
                eighthLeg.setNumber(120);   

                NetworkTableEntry eighthturn = table.getEntry("eighthturn");
                eighthturn.setNumber(45);   

                NetworkTableEntry ninthLeg = table.getEntry("ninthLeg");
                ninthLeg.setNumber(65);   

                NetworkTableEntry ninthturn = table.getEntry("ninthturn");
                ninthturn.setNumber(-35);   

                NetworkTableEntry tenthLeg = table.getEntry("tenthLeg");
                tenthLeg.setNumber(25);   

                NetworkTableEntry adjustSpeed = table.getEntry("adjustSpeed");
                adjustSpeed.setNumber(.5);   





                NetworkTableEntry b_firstLeg = table.getEntry("b_firstLeg");
                b_firstLeg.setNumber(150);   

                NetworkTableEntry b_firstturn = table.getEntry("b_firstturn");
                b_firstturn.setNumber(88);   
                
                NetworkTableEntry b_secondLeg = table.getEntry("b_secondLeg");
                b_secondLeg.setNumber(54);   

                NetworkTableEntry b_secondturn = table.getEntry("b_secondturn");
                b_secondturn.setNumber(86);   
                
                NetworkTableEntry b_thirdLeg = table.getEntry("b_thirdLeg");
                b_thirdLeg.setNumber(69);   

                NetworkTableEntry b_thirdturn = table.getEntry("b_thirdturn");
                b_thirdturn.setNumber(86);   

                NetworkTableEntry b_fourthLeg = table.getEntry("b_fourthLeg");
                b_fourthLeg.setNumber(54);   

                NetworkTableEntry b_fourthturn = table.getEntry("b_fourthturn");
                b_fourthturn.setNumber(82);   

                NetworkTableEntry b_fifthLeg = table.getEntry("b_fifthLeg");
                b_fifthLeg.setNumber(155);   

                NetworkTableEntry b_fifthturn = table.getEntry("b_fifthturn");
                b_fifthturn.setNumber(-88);   

                NetworkTableEntry b_sixthLeg = table.getEntry("b_sixthLeg");
                b_sixthLeg.setNumber(45);   

                NetworkTableEntry b_sixthturn = table.getEntry("b_sixthturn");
                b_sixthturn.setNumber(-88);   

                NetworkTableEntry b_seventhLeg = table.getEntry("b_seventhLeg");
                b_seventhLeg.setNumber(100);   

                NetworkTableEntry b_seventhturn = table.getEntry("b_seventhturn");
                b_seventhturn.setNumber(-135);   

                NetworkTableEntry b_eighthLeg = table.getEntry("b_eighthLeg");
                b_eighthLeg.setNumber(190);   

                NetworkTableEntry b_eighthturn = table.getEntry("b_eighthturn");
                b_eighthturn.setNumber(-130);   

                NetworkTableEntry b_ninthLeg = table.getEntry("b_ninthLeg");
                b_ninthLeg.setNumber(55);   

                NetworkTableEntry b_ninthturn = table.getEntry("b_ninthturn");
                b_ninthturn.setNumber(-88);   

                NetworkTableEntry b_tenthLeg = table.getEntry("b_tenthLeg");
                b_tenthLeg.setNumber(270);   




                NetworkTableEntry c_firstLeg = table.getEntry("c_firstLeg");
                c_firstLeg.setNumber(50);   

                NetworkTableEntry c_firstturn = table.getEntry("c_firstturn");
                c_firstturn.setNumber(-91);   
                
                NetworkTableEntry c_secondLeg = table.getEntry("c_secondLeg");
                c_secondLeg.setNumber(37);   

                NetworkTableEntry c_secondturn = table.getEntry("c_secondturn");
                c_secondturn.setNumber(161);   

                NetworkTableEntry c_thirdLeg = table.getEntry("c_thirdLeg");
                c_thirdLeg.setNumber(100);  

                NetworkTableEntry c_thirdturn = table.getEntry("c_thirdturn");
                c_thirdturn.setNumber(-75);   

                NetworkTableEntry c_fourthLeg = table.getEntry("c_fourthLeg");
                c_fourthLeg.setNumber(38);   

                NetworkTableEntry c_fourthturn = table.getEntry("c_fourthturn");
                c_fourthturn.setNumber(-83);   

                NetworkTableEntry c_fifthLeg = table.getEntry("c_fifthLeg");
                c_fifthLeg.setNumber(100);   

                NetworkTableEntry c_fifthturn = table.getEntry("c_fifthturn");
                c_fifthturn.setNumber(177);   

                NetworkTableEntry c_sixthLeg = table.getEntry("c_sixthLeg");
                c_sixthLeg.setNumber(100);   

                NetworkTableEntry c_sixthturn = table.getEntry("c_sixthturn");
                c_sixthturn.setNumber(-90);   

                NetworkTableEntry c_seventhLeg = table.getEntry("c_seventhLeg");
                c_seventhLeg.setNumber(73);   

                NetworkTableEntry c_seventhturn = table.getEntry("c_seventhturn");
                c_seventhturn.setNumber(-85);   

                NetworkTableEntry c_eighthLeg = table.getEntry("c_eighthLeg");
                c_eighthLeg.setNumber(95);   

                NetworkTableEntry c_eighthturn = table.getEntry("c_eighthturn");
                c_eighthturn.setNumber(180);   

                NetworkTableEntry c_ninthLeg = table.getEntry("c_ninthLeg");
                c_ninthLeg.setNumber(50);   

                NetworkTableEntry c_ninthturn = table.getEntry("c_ninthturn");
                c_ninthturn.setNumber(-90);   

                NetworkTableEntry c_tenthLeg = table.getEntry("c_tenthLeg");
                c_tenthLeg.setNumber(40);   

        }

      
        private void configureButtonBindings() {
                new JoystickButton(m_joystickLeft, 1)
                                //
                                .whileHeld(new DriveStraight(m_driveTrain, m_joystickLeft, m_joystickRight));

                new JoystickButton(m_joystickRight, 1)
                //
                                .whileHeld(new Limelight(m_driveTrain, m_joystickLeft, m_joystickRight));

                new JoystickButton(m_joystickRight, 4)
                                //
                                .whenPressed(new FlipCommand(m_driveTrain));

                 new JoystickButton(m_joystickRight, 2)
                                //
                                .whenPressed(new ToggleCompressor(m_compressor));



                new JoystickButton(m_XBoxController, 2) // B
                                //
                                .toggleWhenPressed(m_shoot);

                new JoystickButton(m_XBoxController, 3) // X
                                //
                                .toggleWhenPressed(new CollectorDown(m_Collector, .5));

                new JoystickButton(m_XBoxController, 5) // left bumper
                                //
                                .whileHeld(new TransferCommand(m_lTransfer, .9));

                new JoystickButton(m_XBoxController, 6) // right bumper
                                //
                                .whileHeld(new TransferCommand(m_lTransfer, -1.0));

                new JoystickButton(m_XBoxController, 4) // Y
                                //
                                .whenPressed(new ToggleHoodCommand(m_Shooter));

                new JoystickButton(m_XBoxController, 7) // Left side menu button
                                //
                                .toggleWhenPressed(new IndexerCommand(m_indexer));

                new JoystickButton(m_XBoxController, 8) // Right side menu button
                                //
                                .whenPressed(new LifterCommand(m_lifter));

                new JoystickButton(m_XBoxController, 1) // A
                             //
                             .whenPressed(new CollectorUp(m_Collector));



        }

        /**
         * Use this to pass the autonomous command to the main {@link Robot} class.
         *
         * @return the command to run in autonomous
         */
        public Command getAutonomousCommand() {
                // An ExampleCommand will run in autonomous
                Command selected = m_autoChooser.getSelected();
                return selected;
        }

}
