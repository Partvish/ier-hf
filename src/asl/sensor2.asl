/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans*/

+!start : true <- .print("sensor2 started...").

+movement <- .print("sensor 2 tells central : sensor2 movement!");
			 .send(central, tell , sensor2movement);
			 .abolish(nomovement);
			 .abolish(reset).
			 
			 
+nomovement <- .print("sensor 2 tells central : sensor2 no movement!");
			   .send(central, tell, sensor2nomovement); 
			   .abolish(movement).
			   
+reset <- .abolish(movement); .abolish(nomovement).
