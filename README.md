Task:
Please define and implement a rest API to match the following problem....
We expect this task to take 60-90 minutes... Code does not need to run but needs to be readable and maintainable. Please submit answer by email ​gregory.lawson@globalpay.com​, ​jason.derlein@e-hps.com gabriel.braslavsky@globalpay.com
Problem Definition:
1. We need an API to manage devices in our system.
The API needs to be able to create, update and find these devices by serial number and machine code. The minimum fields required for the device or as follows:
- Serial number
- Machine code
- Device name
2. We need the API to make sure the managed devices have serial numbers that match a certain format for entry.
- 12​-​1222
- 3455670​-​22222 - 1​-​00022221
These are all valid entries for serial numbers. If a serial number is not in that format then
An error message with error code ER003, message and resourceKey should be sent in the API
3. Ensure that the machine codes are not blank. If the machine code is blank , An error message ER001 should be presented along with the resourcekey and the message
4. If the serial number or the machine code is not found in the persistent store (e.g. SQL Server) an error code with ER002 and/or ER004 should be presented along with the resourcekey and the message
Json error messages:
[ {
}, {
}, {
   "resourceKey": "machine.code.invalid",
"errorCode": "ER001",
"message": "The machine code is incorrect. Check the Machine code you provided and try again."
"resourceKey": "machine.code.not.found",
"errorCode": "ER002",
"message": "The machine code does not match our records."
"resourceKey": "serial.number.invalid",
"errorCode": "ER003",
"message": "The serial number entered can include a - z, A - Z, 0 - 9 and hyphen. Please correct your
entry." },
{
"resourceKey": "serial.number.not.found",
"errorCode": "ER004",
"message": "The serial number does not match our records."
} ]
