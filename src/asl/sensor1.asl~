// Agent sample_agent in project homesec

/* Initial beliefs and rules */

/* Initial goals */
!start.

/* Plans*/

+!start : true <- .print("sensor1 started...").
+movement <- .send(central, tell , sensor1movement); 
			 .print("sensor 1 detected movement"); 
			 .abolish(nomovement) .
+nomovement <-  .print("s1 nincs mozgas"); 
				.send(central,tell,sensor1nomovement); 
				.abolish(movement).
+turnonlights <- .printf("lights turned on"); 
				 .abolish(turnofflights).
+turnofflights : not turnonlights <- .print("lights turned off").
+turnofflights : turnonlights <- .print("lights turned off"); 
								 .abolish(turnonlights).
