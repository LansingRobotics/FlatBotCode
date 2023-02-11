package frc.robot;

import java.text.DecimalFormat;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.Subsystems.DoubleControllers;
import frc.robot.Subsystems.DriveTrain;
import frc.robot.Subsystems.GUI;

public class Robot extends TimedRobot {
  public static DriveTrain driveTrain = new DriveTrain();
  public static final DecimalFormat twoDecimalPlaceFormat = new DecimalFormat("###.##");
  public static final DecimalFormat wholeNumberFormat = new DecimalFormat("###");
  public static GUI gui = new GUI();
  public static DoubleControllers controllerOne = new DoubleControllers();
  private long rawStartTime;
  private double timeLeft;


  @Override
  public void robotInit() {
    gui.startGui();
  }

  @Override
  public void robotPeriodic() {

    SmartDashboard.putString
    ("Battery Voltage", twoDecimalPlaceFormat.format(driveTrain.getBatteryVoltage()) + " Volts");

    SmartDashboard.putBoolean("Power Avaliability", driveTrain.doWeHavePower());

    CommandScheduler.getInstance().run();

  }


  @Override
  public void autonomousInit() {
    gui.startGui();
  }


  @Override
  public void autonomousPeriodic() {
    CommandScheduler.getInstance().run();
  }
  

  @Override
  public void teleopInit() {
    gui.startGui();
    rawStartTime = System.currentTimeMillis();
  }


  @Override
  public void teleopPeriodic() {
    timeLeft = (136000 - (System.currentTimeMillis() - rawStartTime)) / 1000;
    SmartDashboard.putNumber("Time Left", timeLeft);
    CommandScheduler.getInstance().run();
  }


  @Override
  public void testInit() {
    gui.startGui();
  }


  @Override
  public void testPeriodic() {
    CommandScheduler.getInstance().run();
  }

}
