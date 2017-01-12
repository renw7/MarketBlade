list
===
select * from blade_user 

mysqllist
===
select u.*,
	d.name as SEXNAME,
	e.name as STATUSNAME,
	dept.simpleName as DEPTNAME,
	(select GROUP_CONCAT(NAME) from blade_role where  INSTR(CONCAT(",",u.ROLEID,","),CONCAT(",",ID,","))>0) ROLENAME
from blade_user u 
	left join (select num,name from blade_dict where code=101) d on u.sex=d.num 
	left join (select num,name from blade_dict where code=901) e on u.status=e.num 
	left join (select id,simpleName from blade_dept) dept on u.deptId=dept.id