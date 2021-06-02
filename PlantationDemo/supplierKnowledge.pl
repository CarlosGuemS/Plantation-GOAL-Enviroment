% Declaration of the order/3 predicate for orders that aren't processed
% order(Id, Agente, Objeto, Cantidad)
:- dynamic order/4.
:- dynamic tempOrder/4.

% pending /2 predicate for an order pending assignation
% pending(transport, orderID)
:-dynamic pending/2.

% assign /2 predicate for assigned order
% assign(transport, orderID)
:-dynamic assign/2.

% Predicate availableTransports/1 for available Transports
:-dynamic availableTransport/1.

% Predicate here/1 for available Transports
:-dynamic here/1.

% Predicate sentOrder/1 for already sent orders
:-dynamic sentOrder/1.

%-------------------
% Predicate kill/0 to end the masd
:- dynamic kill/0.