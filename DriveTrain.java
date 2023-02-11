package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.Commands.SwitchDrive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class DriveTrain extends SubsystemBase {

  public void initDefaultCommand() {
    setDefaultCommand(new SwitchDrive());
  }

  private static final WPI_TalonSRX  leftRearDrive = new WPI_TalonSRX(RobotMap.LEFT_REAR_DRIVE);
  private static final WPI_TalonSRX  rightFrontDrive = new WPI_TalonSRX(RobotMap.RIGHT_FRONT_DRIVE);
  private static final WPI_TalonSRX leftFrontDrive = new WPI_TalonSRX(RobotMap.LEFT_FRONT_DRIVE);
  private static final WPI_TalonSRX rightRearDrive = new WPI_TalonSRX(RobotMap.RIGHT_REAR_DRIVE);
  private static double batteryVoltage;

  public void arcadeDrive(double leftSpeed, double rightSpeed) {
    leftRearDrive.set(ControlMode.PercentOutput, leftSpeed * Robot.gui.getDrivingPower());
    leftFrontDrive.set(ControlMode.PercentOutput, leftSpeed * Robot.gui.getDrivingPower());
    rightRearDrive.set(ControlMode.PercentOutput, -rightSpeed * Robot.gui.getDrivingPower());
    rightFrontDrive.set(ControlMode.PercentOutput, -rightSpeed * Robot.gui.getDrivingPower());
  }

  public double getBatteryVoltage() {
    batteryVoltage = RobotController.getBatteryVoltage();
    return batteryVoltage;
  }

  public void brakeMotors() {
    rightFrontDrive.setNeutralMode(NeutralMode.Brake);
    rightRearDrive.setNeutralMode(NeutralMode.Brake);
    leftFrontDrive.setNeutralMode(NeutralMode.Brake);
    leftRearDrive.setNeutralMode(NeutralMode.Brake);
  }

  public void coastMotors() {
    leftRearDrive.setNeutralMode(NeutralMode.Coast);
    leftFrontDrive.setNeutralMode(NeutralMode.Coast);
    rightRearDrive.setNeutralMode(NeutralMode.Coast);
    rightFrontDrive.setNeutralMode(NeutralMode.Coast);
  }

  public void haltMotors() {
    leftRearDrive.set(ControlMode.PercentOutput, 0 * Robot.gui.getDrivingPower(), DemandType.ArbitraryFeedForward, 0);
    leftFrontDrive.set(ControlMode.PercentOutput, 0 * Robot.gui.getDrivingPower(), DemandType.ArbitraryFeedForward, 0);
    rightRearDrive.set(ControlMode.PercentOutput, 0 * Robot.gui.getDrivingPower(), DemandType.ArbitraryFeedForward, 0);
    rightFrontDrive.set(ControlMode.PercentOutput, 0 * Robot.gui.getDrivingPower(), DemandType.ArbitraryFeedForward, 0);
  }

  public boolean doWeHavePower() {
    if (batteryVoltage <= 10) {
      return false;
    } else {
      return true;
    }
  }

}