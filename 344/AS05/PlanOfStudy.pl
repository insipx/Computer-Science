% UofS Computer Science Major Plan of Study
% -----------------------------------------
%

% Computer Science Courses
% ------------------------

course(major,required,cmps134).
course(major,required,cmps144).
course(major,required,cmps240).
course(major,required,cmps250).
course(major,required,cmps260).
course(major,required,cmps340).
course(major,required,cmps350).
course(major,required,cmps352).
course(major,required,cmps344).
course(major,required,cmps374).
course(major,required,cmps490).

course(major,elective,elective1).
course(major,elective,elective2).
course(major,elective,elective3).

course(major,option,cmps341).
course(major,option,cmps354).
course(major,option,cmps355).
course(major,option,cmps356).
course(major,option,cmps358).
course(major,option,cmps360).
course(major,option,cmps362).
course(major,option,cmps364).
course(major,option,cmps370).
course(major,option,cmps372).
course(major,option,cmps376).
course(major,option,cmps384).
course(major,option,cmps393).
course(major,option,cmps440).
course(major,option,cmps481).

%%%% Math Cognates
course(cognate,required,math114).
course(cognate,required,math142).
course(cognate,required,math221).
course(cognate,required,math310).

%% Science Cognates
course(cognate,required,phys140).
course(cognate,required,phys141).
course(cognate,required,bio141).
course(cognate,required,bio142).
course(cognate,required,chem112).
course(cognate,required,chem113).

course(cognate,required,phys140L).
course(cognate,required,phys141L).
course(cognate,required,bio141L).
course(cognate,required,bio142L).
course(cognate,required,chem112L).
course(cognate,required,chem113L).



% "Public" Rules
% --------------

earnedList(Area,elective,L) :- list(Area,elective,R),
                               length(R,N),
                               earnedList(Area,option,E),
                               firstN(E,N,L),
                               !.

earnedList(Area,Category,L) :- findall(Course, earned(Area,Category,Course), L). 

remainingList(Area,elective,L) :- list(Area,elective,R),
                                  earnedList(Area,elective,E),
                                  length(E,N), !,
                                  removeN(R,N,L).

remainingList(Area,Category,L) :- list(Area,Category,R),
                                  earnedList(Area,Category,E),
                                  removeSubList(R, E, L).

% "Private" Rules
% ---------------

earned(Area,Category,Course) :- passed(Course), course(Area,Category,Course).

list(Area,Category,L) :- findall(Course, course(Area,Category,Course), L).

% "Private Helper" Rules
% ----------------------

removeSubList(Full, Sub, Result) :-
  select(E, Full, F),
  select(E, Sub, S),
  !, removeSubList(F, S, Result).
  
removeSubList(Full, _, Full).

removeN( L     , 0 , L ) .  % Trimming zero elements from a list yields the original, unchanged list
removeN( [H|T] , N , R ) :- % Otherwise,
  N > 0 ,                   % - assuming N is greater than zero
  N1 is N-1 ,               % - we decrement N
  removeN( T , N1 , R )     % - and recurse down, discarding the head of the list.
  .                         % That's about all there is too it.
  
firstN(List, N, Prefix) :-
  length(List, Len),
  (   Len =< N
  ->  Prefix = List
  ;   length(Prefix, N),
      append(Prefix, _, List)
  ).

% Rules for explicitly printing
% -----------------------------
printEarnedList(Area,Category) :- earnedList(Area,Category,L), print(L).

printRemainingList(Area,Category):- remainingList(Area,Category, L), print(L).

print([]).
print([H|T]) :- nl, write(H), print(T).

printList(Area,Category) :- list(Area,Category,L), print(L).
