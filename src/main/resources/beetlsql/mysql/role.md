list
===
select r.*,d.simpleName deptname,(select name from blade_role where id=r.pId) pname from blade_role r left join blade_dept d on r.deptId=d.id

diy
===
select 0 as "id", 0 as "pId",'顶级' as "name",'true' as "open" from  dual 
union
select ID as "id", pId as "pId",name as "name",(case when (pId=0 or pId is null) then 'true' else 'false' end) as "open" from  blade_role