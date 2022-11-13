%%%-------------------------------------------------------------------
%%% @author jasht
%%% @copyright (C) 2022, <COMPANY>
%%% @doc
%%%
%%% @end
%%% Created : 14. Oct 2022 1:08 PM
%%%-------------------------------------------------------------------
-module(lab02).
-author("jasht").

%% API
-compile(export_all).

midCircle({R, X, Y}, {R2, X2, Y2}) when ((R < 0) or (R2 <0)) -> {wrong_radius};
midCircle({R, X, Y}, {R2, X2, Y2}) ->
  {(R+R2)/2, (X+X2)/2, (Y+Y2)/2}.

isOlder(Tuple1 = {dd1, mm1, yy1}, Tuple2 = {dd2, mm2, yy2}) when (Tuple1 == Tuple2) -> false;
isOlder(Tuple1 = {dd1, mm1, yy1}, Tuple2 = {dd2, mm2, yy2}) when (yy1 >= yy2) -> false;
isOlder(Tuple1 = {dd1, mm1, yy1}, Tuple2 = {dd2, mm2, yy2}) when (mm1 >= mm2) -> false;
isOlder(Tuple1 = {dd1, mm1, yy1}, Tuple2 = {dd2, mm2, yy2}) when (dd1 >= dd2) -> false.


modEach([T], N) -> [T rem N];
modEach([H | T], N) -> [H rem N | modEach(T, N)].

removeMods([T], N) when ((T rem N) == 0)-> [];
removeMods([T], N) when ((T rem N) /= 0)-> [T rem N];
removeMods([H | T], N) when ((H rem N) == 0) -> removeMods(T, N);
removeMods([H | T], N) when ((H rem N) /= 0) -> [H | removeMods(T, N)].

calculateSugar([{Food, Grams, Sugar_ratio}]) -> [{Food, Grams, (Grams * Sugar_ratio)}];
calculateSugar([{Food, Grams, Sugar_ratio} | T]) -> [{Food, Grams, (Grams * Sugar_ratio)} | T].

generate(X, Y, Z) when (X >= Y) -> [];
generate(X, Y, Z) when (X < Y) -> [X | generate((X + Z), Y, Z)].

myMax([]) -> {emptyList};
myMax([H | T]) -> myMax(T, H).

myMax([T], X) when (T > X) -> T;
myMax([T], X) when (T =< X) -> X;

myMax([H | T], X) when (H > X) -> myMax(T, H);
myMax([H | T], X) when (X >= H) -> myMax(T, X).

getnth([]) -> {error, no_such_element}.

getnth([H | T], 1) -> H;
getnth([H | T], N) -> getnth(T, N - 1).
