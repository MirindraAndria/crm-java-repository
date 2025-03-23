CREATE OR REPLACE VIEW depenseTicketLeadCustomer AS 
    SELECT 
    d.idDepense , 
    d.libelle , 
    d.date_depense , 
    d.amount , 
    d.idTicket , 
    d.idLead , 
    t.customer_id 
    FROM depenseTicketLead as d
    LEFT JOIN trigger_ticket as t ON d.idTicket = t.ticket_id  ; 
   

   