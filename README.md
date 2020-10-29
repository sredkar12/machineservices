For simplicity I have used a static list of devices which forms the existing devices . In actual scenario, I would have used a hibernate and JPA to connect to a say MySQL db . I would add the JPA dependency in spring boot .

I have created  the project using the spring boot initializer with Web dependency as a starting point.

The @Pattern validation validates for both a new device ( all 3 fields ) and also individual fields when a search is done by say - serialnumber or machinecode.


I have written a Customized error handler for a customized error to be returned .
In case of new device or even for individual fields ( in cases of search), for invalid format  for any of the 3 fields , it throws a binding invalid error which is grabbed from the MethodArgumentNotValidException and then with some added logic for type of field , is converted to the required format .

Different error messages are written into the message field depending upon if the field is not found or invalid format according to the given requirements

The entire code is completely tested and in working condition .


Following are the URI’S

/devices - The Create new device creates a new device and returns a Response entity alongwith a location in the header .

/allDevices.  – gets all devices

/deviceBymachinecode/{machinecode}.  – gets a single device searched by machine code .
Returns the device if success or returns the error in the format specified in the requirements.

/deviceByserialnumber/{serialnumber} -   – gets a single device searched by serial number .
Returns the device if success or returns the error in the format specified in the requirements .


