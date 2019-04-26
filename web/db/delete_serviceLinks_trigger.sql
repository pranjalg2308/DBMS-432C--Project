CREATE OR REPLACE TRIGGER delete_serviceLinks
  BEFORE DELETE ON serviceLinks
  FOR EACH ROW
DECLARE
BEGIN    
    delete from services
    where service_id = :old.service_id;
END;
/