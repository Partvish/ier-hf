/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("central started...").

+sensor1movement <- .print("central tells latches to close all!");
					.send(latches, tell , closeall);
					.print("central tells sensor1 to turn on the lights!");
					.send(sensor1,tell,turnonlights).

+sensor2movement <- .print("central tells alarm to turn the alarm on!");
					.send(alarm, tell , alarmon).

+!notifyPolice <- .print("central notifies the police!"); 
				  .wait(2000); 
				  .send(robber, achieve, caught);
				  .send(alarm, tell, alarmoff); 
				  .abolish(sensor1movement); 
				  .abolish(sensor2movement).

+sensor1nomovement : sensor1movement <- .send(latches, tell, open); 
										.send(sensor1, tell, tornofflights); 
										.print("you can turn down the lights"); 
										.abolish(sensor1movement).
										
+sensor1nomovement : not sensor1movement <- .print("no movement detectod from sensor 1").

+sensor2nomovement <- .send(alarm, tell, alarmoff); 
					  .print("you can turn the alarm off"); 
					  .abolish(sensor2movement).
						 
