sourceList
===
select * from blade_attach

list
===
select a.*,
	CONCAT('/uploadify/renderFile/',a.ID) ATTACHURL,
	dic.name STATUSNAME,
	u.name CREATERNAME 
from 
	(select ID,CODE,NAME,STATUS,CREATER,CREATETIME from blade_attach) a 
	left join (select * from blade_dict where code=902) dic on a.status=dic.num 
	left join blade_user u on a.creater=u.id