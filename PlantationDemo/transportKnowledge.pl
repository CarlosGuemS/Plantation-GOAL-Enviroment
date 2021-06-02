% Predicate position/1 for its position
:- dynamic position/1.

% Predicate available/1 for if it's available or not
:- dynamic available/0.

% Predicate inventory/2 for it's current inventory
:- dynamic inventory/2.

% Predicate receiver/1 for the agent that is to receive the current inventory
:- dynamic reciever/1.

% Predicate sender/1 for the agent that is to send the current inventory
:- dynamic sender/1.

% Predicate that indicates if this transporter accepted a transportation
:- dynamic accepted/0.

% Predicate indicating the id of the current order
:- dynamic id/1.

%-------------------
% Predicate kill/0 to end the masd
:- dynamic kill/0.