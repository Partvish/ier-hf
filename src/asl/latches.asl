/* Initial beliefs and rules */

/* Initial goals */

!start.

/* Plans */

+!start : true <- .print("latches started...").

+closeall <- .print("all window,doors locked"); 
			 .abolish(open).
			 
+open : not closeall <- .print("all window,doors open").

+open : closeall<-  .print("all window,doors open"); 
 					.abolish(closeall).
