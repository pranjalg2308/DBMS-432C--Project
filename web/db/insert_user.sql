create or replace procedure insert_user (user_id varchar2, password varchar2, email varchar2, firstName varchar2, lastName varchar2, gender varchar2, age number, contactNo number, addLine1 varchar2, addLine2 varchar2, city varchar2)
is
begin
    insert into users(user_id, password, email, firstName, lastName, gender, age , contactNo , addLine1, addLine2, city) values(user_id, password, email, firstName, lastName, gender, age , contactNo , addLine1, addLine2, city);
    commit;
exception
    when others then
        raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
END;
/