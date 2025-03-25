
CREATE OR REPLACE VIEW depenseTicketCustomer AS 
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
    
CREATE OR REPLACE VIEW depenseLeadCustomer AS 
    SELECT 
    d.idDepense , 
    d.libelle , 
    d.date_depense , 
    d.amount , 
    d.idTicket , 
    d.idLead , 
    t.customer_id 
    FROM depenseTicketLead as d
    LEFT JOIN trigger_lead as t ON d.idLead = t.lead_id  ; 

DROP VIEW depenseTicketCustomer ; 
DROP VIEW depenseLeadCustomer ;  