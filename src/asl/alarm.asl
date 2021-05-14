// Agent sample_agent in project homesec

!start.


+!start : true <- .print("alarm system started...").
+alarmon <- .print("Alarm turned on!"); 
			.send(central,achieve,notifyPolice); 
			.abolish(alarmoff).
+alarmoff : not alarmon<- .print("No alarm").
+alarmoff : alarmon <- .print("Alarm turned off!"); 
					   .abolish(alarmon).
