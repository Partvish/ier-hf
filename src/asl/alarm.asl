/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("alarm system started...").

+alarmon <- .print("Alarm turned on!");
			.print("Alarm tells central to notify the police!"); 
			.send(central, achieve, notifyPolice); 
			.abolish(alarmoff).
			
+alarmoff : not alarmon <- .print("No alarm").

+alarmoff : alarmon <- .print("Alarm turned off!");
					   .abolish(alarmon).
