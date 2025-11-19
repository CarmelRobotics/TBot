package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {
    private final VictorSP m_leftMotorLeader = new VictorSP(DriveConstants.kLeftMotorLeaderChannel);
    private final VictorSP m_leftMotorFollower = new VictorSP(DriveConstants.kLeftMotorFollowerChannel);
    private final VictorSP m_rightMotorLeader = new VictorSP(DriveConstants.kRightMotorLeaderChannel);
    private final VictorSP m_rightMotorFollower = new VictorSP(DriveConstants.kRightMotorFollowerChannel);

    private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotorLeader, m_rightMotorLeader);

    public Drive() {
        //Configure the motors

        m_leftMotorLeader.setInverted(true);
        m_rightMotorLeader.setInverted(true);

        m_leftMotorLeader.addFollower(m_leftMotorFollower);
        m_rightMotorLeader.addFollower(m_rightMotorFollower);
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        m_drive.tankDrive(leftSpeed, rightSpeed);
    }
}
