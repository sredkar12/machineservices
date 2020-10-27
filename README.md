For simplicity I have used a static list of devices which forms the existing devices . In actual scenario, I would have used a hibernate and JPA to connect to a say MySQL db . I would add the JPA deendency in spring boot .

I have created  the project using the spring boot initializer .  

The @Pattern validation somehow does not seem to work on the machinecode and devicename but works on serial number . So I am manually validating those 2 fields. 


Following are the URI’S

/devices - The Create new device creates a new device and returns a Response entity alongwith a location in the header .

/allDevices.  – gets all devices

/deviceBymachinecode/{machinecode}.  – gets a single device searched by machine code .
Returns the device if success or returns the error in the format specified in the task .

/deviceByserialnumber/{serialnumber} -   – gets a single device searched by serial number .
Returns the device if success or returns the error in the format specified in the task .


