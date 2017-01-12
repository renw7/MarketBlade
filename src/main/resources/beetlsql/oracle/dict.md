list
===
select d.*,(select name from blade_dict  where id=d.pId) pname from blade_dict d 

diy
===
select 0 as "id", 0 as "pId",'顶级' as "name",'true' as "open" from  dual 
union
select ID as "id", pId as "pId",name as "name",'false' as "open" from  blade_dict