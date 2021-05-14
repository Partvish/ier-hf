// Agent sample_agent in project homesec

/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans*/

+!start : true <- .print("sensor2 started...").
+movement <- .send(central, tell , sensor2movement); 
			 .print("sensor 2 detected movement"); 
			 .abolish(movement).
+nomovement <-  .print(" s2 no movement detected");
				.send(central, tell , sensor2nomovement); 
				.abolish(movement).
