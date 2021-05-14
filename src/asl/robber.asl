/* Initial beliefs and rules */

/* Initial goals */

!start.

!breaking.

/* Plans */

+!start : true <- .print("robber started...").

+!breaking <- .print("im gonna steal"); 
			  .send(sensor2, tell, movement).

+!caught <- .print("robber caught"); 
			 robber_caught; 
			 .abolish(restart).

+restart <- .print("Simulation restarted"); !breaking.

