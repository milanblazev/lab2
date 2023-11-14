package mk.ukim.finki.wp.lab.services;

import mk.ukim.finki.wp.lab.model.TicketOrder;

public interface TicketOrderService{
    TicketOrder placeOrder(String movieTitle, String clientName, String address, int numberOfTickets);}