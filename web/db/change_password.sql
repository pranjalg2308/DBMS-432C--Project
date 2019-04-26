create or replace procedure change_password (id varchar2, pass varchar2)
is
begin
    update users
    set password = pass
    where user_id = id;
    commit;
exception
    when others then
        raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
END;
/