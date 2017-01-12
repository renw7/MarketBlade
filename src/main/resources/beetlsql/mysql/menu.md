list
===
select 
	m.*,d.name as dic_status 
from blade_menu m 
	left join (select num,name from blade_dict where code=902) d on m.status=d.num