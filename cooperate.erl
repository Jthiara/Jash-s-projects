%%%-------------------------------------------------------------------
%%% @author Jasharn Thiara, Benjamin Lee
%%% @copyright (C) 2022, <COMPANY>
%%% @doc
%%%
%%% @end
%%% Created : 27. Oct 2022 4:02 PM
%%%-------------------------------------------------------------------
-module(cooperate).
-author("Jasharn Thiara, Benjamin Lee").

%% API
-compile(export_all).

producer(Pid, 0) -> ending;
producer(Pid, N) ->
    Message = rand:uniform(10),
    Pid ! Message,
    io:format("process ~p producing ~p~n", [self(), Message]),
    producer(Pid, N - 1).

consumer() ->
    timer:sleep(10),
    receive
        Message -> 
            io:format("process ~p consuming ~p~n", [self(), Message]),
            consumer()
    after 40 ->
        io:format("ending consumer process ~n"),
        ending
    end.

go() ->
    spawn(cooperate, producer, [spawn(cooperate, consumer, []), 7]).

map_filter(L, P, F) ->
    L2 = lists:filter(P, L),
    map_filter(L, L2, P, F).
map_filter([], L2, P, F) -> lists:map(F, L2);
map_filter(L1, [], P, F) -> L1;
map_filter([H1 | L1], [H2 | L2], P, F) when (H1 /= H2) ->
    [H1 | map_filter(L1, [H2 | L2], P, F)];
map_filter([H1 | L1], [H2 | L2], P, F) ->
    [F(H1) | map_filter(L1, L2, P, F)].





% Helper function to call our tail recursive method.
not_tail(N) -> not_tail(N-1, N).
not_tail(0, Acc) -> Acc;
not_tail(N, Acc) -> not_tail(N - 1, Acc * N).
