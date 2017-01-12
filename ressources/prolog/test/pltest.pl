think(X):-
	view(X),
	move(X),
	hunt(X).
	
check(X):-
	jpl_call('prolog.PrologCallsThink',check,[X],R),
	jpl_is_true(R).
	
move(X):-
	jpl_call('prolog.PrologCallsThink',move,[X],R),
	jpl_is_true(R).
	
view(X):-
	jpl_call('prolog.PrologCallsThink',view,[X],R),
	jpl_is_true(R).
	
hunt(X):-
	jpl_call('prolog.PrologCallsThink',shoot,[X],R),
	jpl_is_true(R).