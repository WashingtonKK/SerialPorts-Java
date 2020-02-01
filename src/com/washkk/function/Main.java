package com.washkk.function;

import com.fazecast.jSerialComm.SerialPort;
import com.washkk.serial.Serial;

public class Main {
    public static void main (String args []) {
       Serial myPort = new Serial();
       boolean connect;
       byte [] data = new byte [] {1,2,3,4,5,6,7,8,95};
       connect = myPort.displayPorts();

       SerialPort myPort1 = Serial.connect(connect);

        boolean b = Serial.openIt(myPort1, connect, data);

        if (b)  System.out.println("THE PORT OPENED SUCCESSFULLY");

    }
}
