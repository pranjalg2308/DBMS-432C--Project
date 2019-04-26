create or replace procedure delete_provider (id in varchar2)
is
begin
    delete from serviceLinks
    where provider_id = id;

    delete from serviceProviders
    where user_id = id;
    commit;
exception
    when others then
        raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
END;
/