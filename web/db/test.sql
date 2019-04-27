-- create table t1 
-- (
--     user_id varchar(20),
--     password varchar(20) not null,
--     constraint u_pk primary key (user_id)  
-- );

-- create or replace procedure test1 (usr in varchar, pass in varchar)
-- is
-- begin
--     insert into t1 values(usr, pass);
--     commit;
-- exception
--     when others then
--         raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
-- END;
-- /

-- insert into categories values('E01', 'Entertainment', 4000.00);
-- insert into categories values('E02', 'Electronics Repair', 2000.00);
-- insert into categories values('C01', 'Comfort Therapy', 3000.00);
-- insert into categories values('R01', 'Renovation', 1500.00);

insert into serviceProviders values('Apex Repairs', 'qwerty', 'apex@gmail.com', 'Apex ltd.', 1234567891, 2, 'E02', 'asdfg cd', 'sa', 'Anand');
-- insert into serviceProviders values('Ap Repairs', 'qwerty', 'ap@gmail.com', 'Ap ltd.', 1234561891, 5, 'C01', 'asd cd', 's23a', 'Allahabad');

insert into services values('TT1', 'qwerty', 'sasgfsfg', 2000, 1, 100);
insert into services values('TT2', 'qwerty', 'sassfg', 3000, 1, 150);

insert into serviceLinks values('Apex Repairs', 'TT1');
insert into serviceLinks values('Apex Repairs', 'TT2');

-- insert into users values('falconis', 'qwerty', 'panivishal17@gmail.com', 'Vishal', 'Pani', 'M', 20, 7990826365, 'wert', 'rtyu', 'Anand');
-- insert into users values('misraw', 'qwerty123', 'tipra2614@gmail.com', 'Arpit', 'Mishra', 'M', 20, 8765987659, 'wer34t', 'rtfsdyu', 'Allahabad');
commit;