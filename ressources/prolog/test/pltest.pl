

think():-
	me(X),
	him(Y),
	view(X,Y,Z,W),
	move(Z),
	check(Y,Z,W),
	hunt(Y).
	
test(player).
	
isme(X):-
	test(X).
	
me(R):-
	jpl_call('prologTest.AgentPrologDebug',me,[],R),
	jpl_call('prologTest.AgentPrologDebug',test,[],T),
	isme(R).

him(R):-
	jpl_call('prologTest.AgentPrologDebug',him,[],R),
	isme(R),
	jpl_call('prologTest.AgentPrologDebug',test,[],T).
	
	
%check(Y,Z,W):-
%	Y \= W,
%	jpl_call...
	
%move(Z):-
%	jpl_call...
	
%view(X,Y,Z,W):-
%	jpl_call... []
	
%hunt(Y):-
%	jpl_call...