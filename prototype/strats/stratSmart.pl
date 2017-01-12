think(X):-
	view(X).
%	move(X),
%	hunt(X).


%think(X):-
%	PasEnVue(X),
%	observe(X),
%	move(X).
		
%think(X):-
%	ennemi(X),
%	move(X),
%	tir(X).
	
move(X):-
	jpl_call('prolog.CallsSmart',move,[X],R),
	jpl_is_true(R).
	
view(X):-
	jpl_call('prolog.CallsSmart',view,[X],R),
	jpl_is_true(R).
	
hunt(X):-
	jpl_call('prolog.CallsSmart',shoot,[X],R),
	jpl_is_true(R).