package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private final PWMSparkMax RightMotor1 = new PWMSparkMax(1);
  private final PWMSparkMax RightMotor2 = new PWMSparkMax(2);

  private final PWMSparkMax LeftMotor1 = new PWMSparkMax(3);
  private final PWMSparkMax LeftMotor2 = new PWMSparkMax(4);

  private final DifferentialDrive robotdrive = new DifferentialDrive(LeftMotor1::set, RightMotor1::set ); 

  private final XboxController controller = new XboxController(1);
  private final RobotContainer m_robotContainer;

  public Robot() {
    m_robotContainer = new RobotContainer();

    LeftMotor1.addFollower(LeftMotor2);
    RightMotor1.addFollower(RightMotor2);
    RightMotor1.setInverted(true);
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      CommandScheduler.getInstance().schedule(m_autonomousCommand);
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void autonomousExit() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {

    robotdrive.tankDrive(controller.getLeftY() * -1, controller.getRightY() * -1);
  }

  @Override
  public void teleopExit() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void testExit() {}
}
