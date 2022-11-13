%%%-------------------------------------------------------------------
%%% @author jasht
%%% @copyright (C) 2022, <COMPANY>
%%% @doc
%%%
%%% @end
%%% Created : 06. Oct 2022 3:53 PM
%%%-------------------------------------------------------------------
% Lab 01

-module(lab01).
-author("jasht").

%% API
-export([isSame/2, mealType/1, myMult/2, mySum/1, mySeries/3]).

isSame(X, Y) ->   X =:= Y.


mealType(1) -> "breakfast";
mealType(2) -> "lunch";
mealType(3) -> "dinner";
mealType(4) -> "appetizer";
mealType(5) -> "dessert";
mealType(_) -> "no_match".

myMult(M, N) when (N < 0) ->
(-1) * myMult(M, N * -1);

myMult(M, N) when ((N =:= 0) or (M =:= 0)) -> 0;

myMult(M, N) when (N > 0) -> 
M + myMult(M, N-1).

mySum(1)-> 1;
mySum(N)-> N + mySum(N-1).

mySeries(X,Y,2) -> X*Y;
mySeries(X, Y, Z) -> mySeries(X*Y,Y,Z-1).


