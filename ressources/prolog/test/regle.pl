


agent(player).
agent(enemy).


victoire(X,Y) :-
	agent(X),
	agent(Y), 
	X \= Y,
	
	mort(Y),
	vivant(X).



vivant(X) :- not(mort(X)).

mort(X) :-
	
	agent(X),
	agent(Y),
	X \= Y,
	blesse(X),
	tirer(Y,X).
	
blesse(X) :-
	agent(X),
	agent(Y),
	X \= Y,
	
	tirer(Y,X).
	
tirer(X,Y) :-
	agent(X),
	agent(Y),
		X \= Y,
	
	jpl_call('prolog.PrologCalls',hooked,[X,Y],R),
	jpl_is_true(R).


%tirer(X,Y).
	



