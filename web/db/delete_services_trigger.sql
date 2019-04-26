CREATE OR REPLACE TRIGGER delete_services
BEFORE DELETE ON services
FOR EACH ROW
DECLARE
BEGIN    
    delete from orders
    where service_id = :old.service_id;
END;
/