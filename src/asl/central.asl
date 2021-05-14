/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("central started...").

+sensor1movement <- .send(latches, tell , closeall); .send(sensor1,tell,turnonlights); .print("turn lights on").

+sensor2movement <- .send(alarm, tell , alarmon); .print("set alarm on").

+!notifyPolice <- .print("rendorseg ertesitve"); .wait(2000); .send(alarm, tell, alarmoff); .send(robber, achieve, caught); .abolish(sensor1movement); .abolish(sensor2movement).

+sensor1nomovement : sensor1movement <- .send(latches, tell, open); .send(sensor1, tell, tornofflights); .print("you can turn down the lights"); .abolish(sensor1movement).
+sensor1nomovement : not sensor1movement <- .print("no movement detectod from sensor 1").

+sensor2nomovement <- .send(alarm, tell, alarmoff); .print("you can turn the alarm off"); .abolish(sensor2movement).
						 
