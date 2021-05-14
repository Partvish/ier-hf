// Agent sample_agent in project homesec

/* Initial beliefs and rules */

/* Initial goals */
!start.

/* Plans*/

+!start : true <- .print("sensor1 started...").
+movement <- .send(central, tell , sensor1movement); .print("sensor 1 detected movement"); -nomovement .
+nomovement <- .print("s1 nincs mozgas"); .send(central,tell,sensor1nomovement); -movement.
+turnonlights <- .printf("lights turned on"); -turnofflights.
+turnofflights : not turnonlights <- .print("lights turned off").
+turnofflights : turnonlights <- .print("lights turned off"); -turnonlights.
