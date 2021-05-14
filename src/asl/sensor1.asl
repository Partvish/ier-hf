/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans*/

+!start : true <- .print("sensor1 started...").

+movement <- .print("sensor 1 tells central : sensor1 movement!");
			 .send(central, tell , sensor1movement);
			 .abolish(nomovement);
			 .abolish(reset).
			 
+nomovement <- .print("sensor 1 tells central : sensor1 no movement!"); 
			   .send(central, tell, sensor1nomovement); 
			   .abolish(movement).
				
+turnonlights <- .printf("lights turned on"); 
				 .abolish(turnofflights).
				 
+turnofflights : not turnonlights <- .print("lights turned off").

+turnofflights : turnonlights <- .print("lights turned off"); 
								 .abolish(turnonlights).

+reset <- .abolish(movement); .abolish(nomovement); .abolish(turnonlights); .abolish(turnofflights).
