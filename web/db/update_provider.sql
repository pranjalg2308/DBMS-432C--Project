create or replace procedure update_provider (id varchar2, pass varchar2, mail varchar2, cName varchar2, cNo number, aLine1 varchar2, aLine2 varchar2, c varchar2)
is
begin
    update serviceProviders
    set password = pass,
        email = mail,
        companyName = cName,
        contactNo = cNo,
        addLine1 = aLine1,
        addLine2 = aLine2,
        city = c
    where user_id = id;
    commit;
exception
    when others then
        raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
END;
/