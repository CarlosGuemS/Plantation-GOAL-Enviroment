% Predicate inventory/2 indicating the number of supplies (seeds, fertilizer or pesticides)
:- dynamic inventory/2.

% Predicate field/1 indicating the ids of the usable fields
:- dynamic field/1.

% Predicate indicating a field is in use
:- dynamic fieldInUse/1.

% Rule indicating a field NOT in use
fieldUnused(X) :- field(X), not(fieldInUse(X)).

% Predicate sickPlant/1 for plants in the given field being sick
:- dynamic sickPlant/1.

% Predicate weakSoil/1 for the soil in a given field requiring fertilizer
:- dynamic weakSoil/1.

% Predicate readyToHarvest/1 for indicating a field is ready to harvest
:- dynamic readyToHarvest/1.



%------------------------
% Predicate pendingSeeds/1 indicating we are waiting for an supplies it currently has to arrive
% (we do this to avoid spamming the supplier)
:- dynamic waitingOrder/1.

% Predicate pending/0 indicating we are in the middle of a transaction
:- dynamic pending/0.

% Response response/1 indicating we have a (positive) response
:- dynamic response/1.

% Predicate processor indicating the next processor to use
:- dynamic processor/1.
processorAvailable :- processor(_).

%-------------------
% Declaration of the order/3 predicate for orders that aren't processed
% order(Id, Agente, Objeto, Cantidad)
:- dynamic order/4.

% Predicates for id generation
:-dynamic id/1.
nextID(Y) :- id(X), Y is X+1.

% pending /2 predicate for an order pending assignation
% pending(transport, order)
:-dynamic pending/2.

% assign /2 predicate for assigned order
% assign(transport, order)
:-dynamic assign/2.

% Predicate availableTransports/1 for available Transports
:-dynamic availableTransport/1.

% Predicate here/1 for available Transports
:-dynamic here/1.

% Rule indicating an order is ready to be sent
readyToSend(O) :- assign(T, O), here(T). 

% Predicate sentOrder/1 for already sent orders
:- dynamic sentOrder/1.

%-------------------
% Predicate kill/0 to end the masd
:- dynamic kill/0.

:- dynamic dummy/0.
