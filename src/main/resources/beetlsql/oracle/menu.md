list
===
select 
	m.*,d.name as DIC_STATUS 
from blade_menu m 
	left join (select num,name from blade_dict where code=902) d on m.status=d.num