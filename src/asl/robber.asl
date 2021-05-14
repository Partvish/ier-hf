/* Initial beliefs and rules */
trigger_sensor1.
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
			 .send(sensor1, tell, reset);
			 .send(sensor2, tell, reset);
			 robber_done. 

+restart <- .abolish(caught); .abolish(won); .print("Simulation restarted"); !breaking.

+!handleNext: caught <- !caught; .abolish(restart).

+!handleNext: not caught & trigger_sensor1 <- .abolish(trigger_sensor1); +trigger_sensor2; !breaking.

+!handleNext: not caught & trigger_sensor2 <- .abolish(trigger_sensor2); +won.

+won <- .print("robber got away with your valuables"); .abolish(restart); robber_done.

