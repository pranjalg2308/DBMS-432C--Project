create or replace procedure insert_provider (user_id varchar2, password varchar2, email varchar2, companyName varchar2, contactNo number, category_id varchar2 addLine1 varchar2, addLine2 varchar2, city varchar2)
is
begin
    insert into serviceProviders(user_id, password, email, companyName, contactNo, category_id, addLine1, addLine2, city) values(user_id, password, email, companyName, contactNo, category_id, addLine1, addLine2, city);
    commit;
exception
    when others then
        raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
END;
/