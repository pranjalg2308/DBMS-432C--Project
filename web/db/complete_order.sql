-- Work in progress
create or replace procedure make_order (id in varchar2, r number)
is
    avg_rating
begin
    update orders 
    set completed = 1,
        rating = r
    where order_id = id;


    commit;
exception
    when others then
        raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
END;
/