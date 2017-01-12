list
===
select d.*,(select simpleName from blade_dept  where id=d.pId) pname from blade_dept d 

diy
===
select 0 as "id", 0 as "pId",'顶级' as "name",'true' as "open"
union
select ID as "id", pId as "pId",simplename as "name",'false' as "open" from  blade_dept