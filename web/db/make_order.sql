create or replace procedure make_order (order_id in varchar2, user_id in varchar2, service_id in varchar2, date_of_order number, start_time number, duration number, completed number, deleted number)
is
begin
    insert into orders(order_id, user_id, service_id, date_of_order, start_time, duration, completed, deleted) values(order_id, user_id, service_id, date_of_order, start_time, duration, completed, deleted);
    commit;
exception
    when others then
        raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
END;
/