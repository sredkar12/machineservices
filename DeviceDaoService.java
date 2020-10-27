package com.in28minutes.springboot.machineservices.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;



@Component
public class DeviceDaoService {

    private static List<Device> devices = new ArrayList<>();
    static int deviceCount = 3;
    static {
        //Initialize Data
        Device device1 = new Device( 1,"12-1322", "3415670​-​22222","1-00022221");
        Device device2 = new Device( 2,"12-1242", "3425670-22222","1-00032221");
        Device device3 = new Device( 3,"12-1272", "3435670-22222","1-00042221");

        devices.add(device1);
        devices.add(device2);
        devices.add(device3);

    }

    public List<Device> retrieveDevices() {

        return devices;
    }
    public Device retrieveDevicebySerialNum( String sernum ){

        for (Device device: devices )
        {
            if (device.getSerialnumber().equals(sernum))
            return device;
        }

        return null;
    }
    public Device retrieveDevicebyMacineCode( String mc ){

        for (Device device: devices )
        {
            if (device.getMachinecode().equals(mc))
                return device;
        }

        return null;
    }

    public Device addDevice( Device newDevice) {
        if ( newDevice.getId() == null) {
            newDevice.setId(++deviceCount);
        }
        devices.add(newDevice);
        return newDevice;
    }
}
