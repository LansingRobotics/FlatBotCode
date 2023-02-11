package frc.robot.Commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class SwitchDrive extends CommandBase {
  static double rightTurnValue;
  static double leftTurnValue;

  public SwitchDrive() {
    addRequirements(Robot.driveTrain);
  }

  @Override
  public void initialize() {
    Robot.driveTrain.arcadeDrive(RobotMap.NOTHING, RobotMap.NOTHING);
  }

  @Override
  public void execute() {
    switch (Robot.gui.getDrivingType()) {
      case RobotMap.TANK_DRIVE_DRIVING:
        singleTankDriveExecute();
        break;
      case RobotMap.VIDEO_GAME_DRIVING:
        singleVideoGameDriveExecute();
        break;
      default:
        singleTankDriveExecute();
        break;
    }
    SmartDashboard.putString("Drive Mode", Robot.gui.sayCurrentCommand());
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    Robot.driveTrain.arcadeDrive(RobotMap.NOTHING, RobotMap.NOTHING);
  }

  private static void singleTankDriveExecute() {
    double leftStickY = Robot.controllerOne.getDriverRawAxis(RobotMap.LEFT_STICK_Y);
    double rightStickY = Robot.controllerOne.getDriverRawAxis(RobotMap.RIGHT_STICK_Y);

    Robot.driveTrain.arcadeDrive(leftStickY, rightStickY);
  }

  private static void singleVideoGameDriveExecute() {
    double turnValue = Robot.controllerOne.getDriverRawAxis(RobotMap.LEFT_STICK_X);

    if (turnValue >= RobotMap.NOTHING) {
      rightTurnValue = turnValue;
    } else {
      rightTurnValue = RobotMap.NOTHING;
    }
    if (turnValue < RobotMap.NOTHING) {
      leftTurnValue = -turnValue;
    } else {
      leftTurnValue = RobotMap.NOTHING;
    }

    Robot.driveTrain.arcadeDrive(-(Robot.controllerOne.getDifferenceInTriggers() - leftTurnValue),
        -(Robot.controllerOne.getDifferenceInTriggers() - rightTurnValue));

  }
}
