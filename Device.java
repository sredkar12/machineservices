package com.in28minutes.springboot.machineservices.service;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

public class Device {

    Integer id;
    @Pattern(regexp=("^[0-9]{2}-[0-9]{4}$"))
    private String serialnumber;


    @Pattern(regexp=("^[0-9]{7}-[0-9]{5}$"))
    private String machinecode;


    @Pattern(regexp=("^[0-9]{1}-[0-9]{8}$"))
    private String devicename;

    // Needed by Caused by: com.fasterxml.jackson.databind.JsonMappingException:
    // Can not construct instance of com.in28minutes.springboot.model.Course:
    // no suitable constructor found, can not deserialize from Object value
    // (missing default constructor or creator, or perhaps need to add/enable
    // type information?)
    public Device() {

    }

    public Device( Integer id, String serialnumber, String machinecode, String devicename) {
        super();
        this.id = id;
        this.serialnumber = serialnumber;
        this.machinecode = machinecode;
        this.devicename = devicename;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Objects.equals(serialnumber, device.serialnumber) &&
                Objects.equals(machinecode, device.machinecode) &&
                Objects.equals(devicename, device.devicename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialnumber, machinecode, devicename);
    }

    public String getMachinecode() {
        return machinecode;
    }

    public void setMachinecode(String machinecode) {
        this.machinecode = machinecode;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public void setSerialnumber(String sn) {
        this.serialnumber = sn;
    }
    public String getSerialnumber() {
        return serialnumber;
    }








}
