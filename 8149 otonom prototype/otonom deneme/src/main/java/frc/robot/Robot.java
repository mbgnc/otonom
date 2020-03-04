/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  private static final PWMVictorSPX m_Victor_FR = new PWMVictorSPX(4);
  private static final PWMVictorSPX m_Victor_BR = new PWMVictorSPX(3);
  private static final PWMVictorSPX m_Victor_BL = new PWMVictorSPX(2);
  private static final PWMVictorSPX m_Victor_FL = new PWMVictorSPX(1);

  private Joystick joy1 = new Joystick(0);

  private double startTime;

  @Override
  public void robotInit() {
  }

  @Override
  public void autonomousInit() {
    startTime = Timer.getFPGATimestamp();
  }

  @Override
  public void autonomousPeriodic() {
    double time = Timer.getFPGATimestamp();
    System.out.println(time - startTime);

    if (time - startTime < 3) {
      m_Victor_FL.set(0.6);
      m_Victor_BL.set(0.6);
      m_Victor_FL.set(-0.6);
      m_Victor_BL.set(-0.6);
    } else {
      m_Victor_FL.set(0);
      m_Victor_BL.set(0);
      m_Victor_FR.set(0);
      m_Victor_BR.set(0);
    }
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    double speed = -joy1.getRawAxis(1) * 0.6;
    double turn = joy1.getRawAxis(4) * 0.3;

    double left = speed + turn;
    double right = speed - turn;

    m_Victor_FL.set(left);
    m_Victor_BL.set(left);
    m_Victor_FR.set(-right);
    m_Victor_BR.set(-right);
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}
