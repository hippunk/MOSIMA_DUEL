%a fish is free or caught
%a fisherman is fishing, bredouille or victorious

%initial state
fish(maurice).
free(maurice).

fisherman(tom).
fishing(tom).

victorious(X) :- 
	fisherman(X),
	fish(Y),
	caught(X,Y).
	
caught(X,Y) :-
	fisherman(X),
	fishing(X),
	fish(Y),
	free(Y),
	jpl_call('prolog.PrologCalls',hooked,[X,Y],R),
	jpl_is_true(R).
	
	


	