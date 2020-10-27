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
import java.util.regex.Pattern;

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
        if(!Pattern.matches("^[0-9]{2}-[0-9]{4}$", serialnumber))
            throw new DeviceException("Serial number invalid");
        Device device = deviceService.retrieveDevicebySerialNum(serialnumber);
        if ( device == null )
            throw new DeviceException("Serial number not found");

        return device;
    }

    @GetMapping("/devicebyMachineCode/{machinecode}")
    public Device getDevicebyMachineCode(@PathVariable String machinecode) {
        if(!Pattern.matches("^[0-9]{7}-[0-9]{5}$", machinecode))
            throw new DeviceException("Machine code invalid");

        Device device = deviceService.retrieveDevicebyMacineCode(machinecode);
        if ( device == null )
            throw new DeviceException("Machine code not found");
        return device;
    }



    @PostMapping("/devices")
    public ResponseEntity<Void> addNewDevice(
           @Valid @RequestBody Device newDevice) {


        Device device = deviceService.addDevice( newDevice);

        if (device == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{serialnum}").buildAndExpand(device.getSerialnumber()).toUri();

        return ResponseEntity.created(location).build();
    }




}

