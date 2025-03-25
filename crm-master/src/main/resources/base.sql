CREATE TABLE budget(
    idBudget INT PRIMARY KEY AUTO_INCREMENT,
    libelle VARCHAR(255) NOT NULL,
    date_budget DATETIME NOT NULL,
    amount DECIMAL(15,2) NOT NULL,
    idCustomer INT(10) UNSIGNED NOT NULL, 
    CONSTRAINT fk_budget_customer FOREIGN KEY (idCustomer) REFERENCES customer(customer_id)
) ENGINE=InnoDB;

CREATE TABLE depenseTicketLead (
    idDepense INT PRIMARY KEY AUTO_INCREMENT,
    libelle VARCHAR(255) NOT NULL,
    date_depense DATETIME NOT NULL,
    amount DECIMAL(15,2) NOT NULL,
    idTicket INT(10) UNSIGNED,
    idLead INT(10) UNSIGNED,
    CONSTRAINT fk_ticket FOREIGN KEY (idTicket) REFERENCES trigger_ticket(ticket_id) ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT fk_lead FOREIGN KEY (idLead) REFERENCES trigger_lead(lead_id) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE taux_alert( 
    idTaux INT PRIMARY KEY AUTO_INCREMENT,
    libelle VARCHAR(255) NOT NULL,
    taux DECIMAL(15,2) NOT NULL
) ; 

CREATE TABLE CustomerExpense (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customerEmail VARCHAR(255) NOT NULL,
    subjectOrName VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    expense DECIMAL(10, 2) NOT NULL
);


INSERT INTO taux_alert (libelle, taux) VALUES 
('Alerte critique', 80.00) ; 


INSERT INTO depenseTicketLead (libelle, date_depense, amount, idTicket, idLead)
VALUES
('Dépense liée au ticket A', '2025-03-23 10:00:00', 10000.00, 1, null ) ; 

