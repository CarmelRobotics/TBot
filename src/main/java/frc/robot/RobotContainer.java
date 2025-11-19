// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.cannon.Cannon;
import frc.robot.subsystems.drive.Drive;

public class RobotContainer {
  private final CommandXboxController m_controller = new CommandXboxController(0);

  private final Drive m_drive = new Drive();
  private final Cannon m_cannon = new Cannon();

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {
    /*
     * The y-axis on the left joystick controls the forwards and backwards movement.
     * The x-axis on the left joystick controls the rotation of the robot.
     */
    m_drive.setDefaultCommand(Commands.runOnce(() ->
      m_drive.tankDrive(
        m_controller.getLeftX() - m_controller.getLeftY(),
        m_controller.getLeftX() + m_controller.getLeftY()
    ), m_drive));

    /*
     * The left trigger will release the air whilst held, firing the cannon, unless the safety is enabled.
     * The right trigger will toggle the safety unless the cannon is firing.
     */
    m_controller.leftTrigger().whileTrue(m_cannon.fire().unless(() -> m_cannon.safetyEnabled()));
    m_controller.rightTrigger().onTrue(m_cannon.setSafety().unless(() -> m_cannon.firing()));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
