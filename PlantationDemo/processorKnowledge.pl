% Predicate unprocessedCrops/1 number of unprocessed crops
:- dynamic unprocessedCrops/1.

% Predicate processedCrops/1 number of processed crops
:- dynamic processedCrops/1.
nextprocessedCrops(Y) :- processedCrops(X), Y is X+1.

% Rule indicating if it has achieved to process all the cops
noPendingCrops :- unprocessedCrops(0).

%-------------------
% Predicate kill/0 to end the masd
:- dynamic kill/0.