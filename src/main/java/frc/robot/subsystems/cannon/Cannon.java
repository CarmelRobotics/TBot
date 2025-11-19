package frc.robot.subsystems.cannon;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

public class Cannon {
    private final Compressor m_compressor = new Compressor(CannonConstants.kModuleCAN, PneumaticsModuleType.CTREPCM);
    private final Solenoid m_relay = new Solenoid(CannonConstants.kModuleCAN, PneumaticsModuleType.CTREPCM, CannonConstants.kRelayChannel);

    private boolean m_safetyEnabled = true;

    public Cannon() {
        m_compressor.enableDigital();
    }

    public boolean firing() {
        return m_relay.get();
    }

    public boolean safetyEnabled() {
        return m_safetyEnabled;
    }

    public Command setSafety() {
        return Commands.runOnce(() -> {
            m_safetyEnabled = !m_safetyEnabled;
            System.out.println("The safety has been " + (m_safetyEnabled
                ? "enabled! The cannon will not fire!"
                : "disabled! The cannon will fire!"
            ));
        });
    }

    public Command fire() {
        return Commands.runEnd(() -> m_relay.set(true), () -> m_relay.set(false));
    }
}
