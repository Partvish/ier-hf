/* Initial beliefs and rules */
trigger_sensor2.
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
			 .abolish(trigger_sensor1);
			 .abolish(trigger_sensor2);
			 .abolish(restart);
			 .send(sensor1, tell, reset);
			 .send(sensor2, tell, reset);
			 robber_caught. 

+restart <- .print("Simulation restarted"); !breaking.


