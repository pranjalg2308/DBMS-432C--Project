create or replace procedure complete_order (id in varchar2, r number)
is
    avg_rating number(2,1);
    prov_id varchar(20);
begin
    update orders
    set rating = r,
        completed = 1 
    where order_id = id;

    select distinct(provider_id) into prov_id from serviceLinks 
    inner join orders
    on serviceLinks.service_id = orders.service_id
    where orders.order_id = id;

    select avg(orders.rating) into avg_rating from orders 
    inner join serviceLinks
    on serviceLinks.service_id = orders.service_id
    where serviceLinks.provider_id = prov_id;

    update serviceProviders
    set rating = avg_rating
    where user_id = prov_id;
    commit;
exception
    when others then
        raise_application_error(-20001,'An error was encountered - '||SQLCODE||' -ERROR- '||SQLERRM);
END;
/