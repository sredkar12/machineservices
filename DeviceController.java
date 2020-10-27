package com.in28minutes.springboot.machineservices.rest;


import com.in28minutes.springboot.machineservices.rest.exception.DeviceException;
import com.in28minutes.springboot.machineservices.service.Device;
import com.in28minutes.springboot.machineservices.service.DeviceDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class DeviceController {

    @Autowired
    private DeviceDaoService deviceService;

    @GetMapping("/allDevices")
    public List<Device> retrieveDevices() {
        return deviceService.retrieveDevices();
    }

    @GetMapping("/devicebySerialNum/{serialnumber}")
    public Device getDevicebySernum(@PathVariable String serialnumber) {
        Device device = deviceService.retrieveDevicebySerialNum(serialnumber);
        if ( device == null )
            throw new DeviceException("Serial number not found");

        return device;
    }

    @GetMapping("/devicebyMachineCode/{machinecode}")
    public Device getDevicebyMachineCode(@PathVariable String machinecode) {
        String errcode =  validateMachinecode(machinecode);
        if (errcode != null)
        {
            throw new DeviceException(errcode);
        }
        Device device = deviceService.retrieveDevicebyMacineCode(machinecode);
        if ( device == null )
            throw new DeviceException("Machine code not found");
        return device;
    }



    @PostMapping("/devices")
    public ResponseEntity<Void> addNewDevice(
           @Valid @RequestBody Device newDevice) {

            String errcode = validate(newDevice);
        if (errcode != null)
        {
            System.out.println("throwing exception adddevice");
            throw new DeviceException(errcode);
        }
        System.out.println("adding device");
        Device device = deviceService.addDevice( newDevice);

        if (device == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{serialnum}").buildAndExpand(device.getSerialnumber()).toUri();

        return ResponseEntity.created(location).build();
    }

    private String validate(Device newDevice) {
        System.out.println("Validating");


        if ( newDevice.getSerialnumber().isEmpty() || newDevice.getSerialnumber().length() != 7) {
            System.out.println("Validating error " + newDevice.getSerialnumber().length());
            return ("Serial number invalid");
        }
        if ( newDevice.getMachinecode().isEmpty() || newDevice.getMachinecode().length() != 13) {
            System.out.println("Validating error " + newDevice.getMachinecode().length());
            return ("Machine code invalid");
        }
        else
            return null;
    }

    private String validateMachinecode(String machinecode) {

        if ( machinecode.isEmpty() || machinecode.length() != 13 )
            return ("Machine code invalid");
        else
            return null;

    }



}
