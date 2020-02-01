package com.washkk.serial;

import com.fazecast.jSerialComm.SerialPort;


import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;
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

    public static boolean openIt(SerialPort thisPort, boolean connect, byte [] data)  {
        boolean isOpen = thisPort.isOpen();
        boolean b;

        if (thisPort.openPort()) {
            System.out.println("Yeyyy the port is open");

            int baudRate = thisPort.getBaudRate();

            System.out.println("Baudrate is: " + baudRate );

            System.out.println("Parity is: " + thisPort.getParity() );

            OutputStream out = thisPort.getOutputStream();
            InputStream in = thisPort.getInputStream();

            /*
            try {
                for (int j = 0; j<20 ; j++) {
                    try {
                        byte [] valuesRead = new byte[] {};
                        valuesRead = in.readAllBytes();
                        System.out.println(valuesRead);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }catch (Exception e) {
               e.printStackTrace();
            }

            try {
                out.write(data);
            } catch (Exception e) {
                e.printStackTrace();
            }

             */

            /* For Nonblocking mode
            thisPort.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING, 0,0);

            try {
                while (true)
                {
                    while (thisPort.bytesAvailable() == 0)
                        Thread.sleep(20);

                    byte[] readBuffer = new byte[thisPort.bytesAvailable()];
                    int numRead = thisPort.readBytes(readBuffer, readBuffer.length);
                    System.out.println("Read " + numRead + " bytes.");

                }
            } catch (Exception e) { e.printStackTrace(); }

             */

            thisPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
           // InputStream input = thisPort.getInputStream();
            try
            {
                for (int j = 0; j < 1000; ++j) {
                    //char x = (char)in.read();
                    System.out.print((char)in.read());
                   /*
                     int y = (int) x;
                     System.out.println("y =  " + y);
                    */
                }
                in.close();
            } catch (Exception e) { e.printStackTrace(); }


            //thisPort.writeBytes(data, data.length);

            int flowControlSettings = thisPort.getFlowControlSettings();

            System.out.println("Flow control settings: " + flowControlSettings );

            System.out.println("Port description is: " + thisPort.getPortDescription() );

            System.out.printf("Read buffer size is: %d%n", thisPort.getDeviceReadBufferSize());

            b = thisPort.closePort();
            if (b)
                System.out.println("Port succesfully closed");
                    else  System.out.println("Port not closed");

        } else System.out.println(" the port is NOT open");


        return isOpen;
    }
}
