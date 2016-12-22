

gentil(player).

mechant(enemy).



victoire() :- 
	mort(mechant),
	vivant(gentil).

vivant(X) :- not(mort(X)).

mort(X) :-
	blesse(X),
	tirer(Y,X).
	
blesse(X) :-
	tirer(Y,X).
	
tirer(X,Y) :-
	jpl_call('env.jme.Environment',shoot,[X,Y],R),
	jpl_is_true(R).
	
	



