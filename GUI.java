package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

public class GUI {
  private static SendableChooser<Integer> drivingType = new SendableChooser<>();
  private static SendableChooser<Integer> autonomousCommand = new SendableChooser<>();
  private static SendableChooser<Double> drivingMotorPower = new SendableChooser<>();

  public void startGui() {
    SmartDashboard.putData("Driving Type", drivingType);
    drivingType.setDefaultOption("Tank Drive", RobotMap.TANK_DRIVE_DRIVING);
    drivingType.addOption("Video Game Driving", RobotMap.VIDEO_GAME_DRIVING);

    SmartDashboard.putData("Driving Power", drivingMotorPower);
    drivingMotorPower.setDefaultOption("100%", 1.0);
    drivingMotorPower.addOption("90%", 0.9);
    drivingMotorPower.addOption("80%", 0.8);
    drivingMotorPower.addOption("70%", 0.7);
    drivingMotorPower.addOption("60%", 0.6);
    drivingMotorPower.addOption("50%", 0.5);
    drivingMotorPower.addOption("40%", 0.4);
    drivingMotorPower.addOption("30%", 0.3);
    drivingMotorPower.addOption("20%", 0.2);
    drivingMotorPower.addOption("10%", 0.1);
    drivingMotorPower.addOption("Off", 0.0);
  }

  public int getDrivingType() {
    return drivingType.getSelected();
  }

  public double getDrivingPower() {
    return drivingMotorPower.getSelected();
  }

  public int getAutonomousCommand() {

    if (autonomousCommand.getSelected() == null) {
      return 0;
    } else {
      return autonomousCommand.getSelected();
    }
  }

  public String sayCurrentCommand() {
    switch (getDrivingType()) {
      case RobotMap.TANK_DRIVE_DRIVING:
        return "Tank Driving";
      case RobotMap.VIDEO_GAME_DRIVING:
        return "Video Game Driving";
      default:
        return "Nothing";
    }
  }
}
