package com.washkk.serial;

import com.fazecast.jSerialComm.SerialPort;


import java.util.Scanner;

import static com.fazecast.jSerialComm.SerialPort.getCommPort;

public class Serial {

    static String portDescriptor = "";

    public boolean displayPorts () {
        SerialPort[] Ports = new SerialPort[]{};
        Ports = SerialPort.getCommPorts();
        int len = Ports.length;
        String portName = "";
        boolean connect = false;

        for (int i = 0; i < len; i++) {
            System.out.println(Ports[i].getDescriptivePortName());

            System.out.println("Type the name of your port as it appears");

        Scanner sc = new Scanner (System.in);
        if(sc.hasNext()) portName = sc.nextLine();
        else portName = "";
        sc.close();

        if (Ports[i].getDescriptivePortName().equalsIgnoreCase(portName)){
            portDescriptor = Ports[i].getSystemPortName();
            connect = true; }
                else connect = false;
        }

        return connect;
    }

    public static SerialPort connect(boolean connect) {
        System.out.println(portDescriptor);
        return SerialPort.getCommPort(portDescriptor);
    }

    public static boolean openIt(SerialPort thisPort) {
        boolean isOpen = thisPort.isOpen();
        boolean b;

        if (thisPort.openPort()) {
            System.out.println("Yeyyy the port is open");
            b = thisPort.closePort();
            if (b)
                System.out.println("Port succesfully closed");
                    else  System.out.println("Port not closed");

        } else System.out.println(" the port is NOT open");


        return isOpen;
    }
}
