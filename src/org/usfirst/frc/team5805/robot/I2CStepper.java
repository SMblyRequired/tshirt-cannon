package org.usfirst.frc.team5805.robot;

import edu.wpi.first.wpilibj.I2C;

public class I2CStepper {
    private I2C arduinoConn;
    
    public I2CStepper() {
        arduinoConn = new I2C(I2C.Port.kOnboard, 8);
    }
    
    private void sendCommand(char command) {
        arduinoConn.transaction(new byte[]{ (byte) command }, 1, null, 0);
    }
    
    public void indexerForward() {
        System.out.println("I2C Forward");
        sendCommand('a');
    }
    
    public void indexerBackwards() {
        System.out.println("I2C Backwards");
        sendCommand('b');
    }

    public void enable() {
        sendCommand('e');
    }
    
    public void disable() {
        sendCommand('d');
    }
}