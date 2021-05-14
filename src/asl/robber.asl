/* Initial beliefs and rules */

/* Initial goals */

!start.

!breaking.

/* Plans */

+!start : true <- .print("robber started...").

+!breaking : trigger_sensor2 <- .print("robber enters the house");
              					.send(sensor2, tell, movement).

+!breaking : trigger_sensor1 <- .print("robber enters the garden");
              					.send(sensor1, tell, movement).

+!caught <- .print("robber got caught by the police"); 
			 robber_caught; 
			.abolish(restart).

+restart <- .print("Simulation restarted"); !breaking.

