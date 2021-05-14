// Agent sample_agent in project homesec

/* Initial beliefs and rules */
trigger_sensor2.
/* Initial goals */
!start.
!breaking.
/* Plans */


+!start : true <- .print("robber started...").

+!breaking : trigger_sensor2 <- .print("im gonna commit burglary");
			  .send(sensor2, tell, movement).

			  
+!breaking : trigger_sensor1 <- .print("im gonna tresspass"); 
			  .send(sensor1, tell, movement).


+!caught <- .print("robber caught"); 
			 robber_caught; .abolish(trigger_sensor1);
			.abolish(trigger_sensor2);.abolish(restart).

+restart <- .print("Simulation restarted"); !breaking.


