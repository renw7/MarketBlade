sourceList
===
select * from blade_parameter

list
===
select p.*,e.name STATUSNAME from blade_parameter p left join (select num,name from blade_dict where code=902) e on p.status=e.num 