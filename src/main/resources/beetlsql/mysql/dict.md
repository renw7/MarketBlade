list
===
select d.*,(select name from tfw_dict  where id=d.pId) PNAME from tfw_dict d 

diy
===
select 0 as "id", 0 as "pId",'top' as "name" from  dual 
union
select ID as "id", pId as "pId",name as "name" from  TFW_DICT 