

think(X):-
	move(X).
	
%check(X),
%hunt(X).	
	
%me(R):-
%	jpl_call('prologTest.AgentPrologDebug',me,[],R),
%	jpl_call('prologTest.AgentPrologDebug',test,[],T),
%	isme(R).

%him(R):-
%	jpl_call('prologTest.AgentPrologDebug',him,[],R),
%	isme(R),
%	jpl_call('prologTest.AgentPrologDebug',test,[],T).
	
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
	jpl_call('prolog.PrologCallsThink',hunt,[X],R),
	jpl_is_true(R).