package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class DoubleControllers extends SubsystemBase {
  private Joystick driverController = new Joystick(RobotMap.DRIVER_CONTROLLER1);

  public double getDriverRawAxis(int axis) {
    return driverController.getRawAxis(axis);
  }

  public double getDifferenceInTriggers() {
    return (getDriverRawAxis(RobotMap.RIGHT_TRIGGER) - getDriverRawAxis(RobotMap.LEFT_TRIGGER));
  }

  public int isButtonPressed(int buttonID) {
    if (driverController.getRawButton(buttonID) == true) {
      return 1;
    } else {
      return 0;
    }
  }

  public boolean getIsButtonPressed(int buttonID) {
    return driverController.getRawButton(buttonID);
  }

  public double getDifferenceInBumpers() {
    return (isButtonPressed(RobotMap.RIGHT_BUMPER) - isButtonPressed(RobotMap.LEFT_BUMPER));
  }

}
