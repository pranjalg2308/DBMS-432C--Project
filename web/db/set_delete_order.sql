create or replace procedure set_delete_order (id in varchar2)
is
begin
    update orders
    set deleted = 1
    where order_id = id;
    commit;
exception
    when others then
        raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
END;
/