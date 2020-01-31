package com.washkk.function;

import com.fazecast.jSerialComm.SerialPort;
import com.washkk.serial.Serial;

public class Main {
    public static void main (String args []) {
       Serial myPort = new Serial();
       boolean connect;
       connect = myPort.displayPorts();

       SerialPort myPort1 = Serial.connect(connect);

        boolean b = Serial.openIt(myPort1);

        if (b)  System.out.println("THE PORT OPENED SUCCESSFULLY");

    }
}
