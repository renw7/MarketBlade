list
===
select 
	t.*, d.name as typename, u.name as creatername 
from  blade_notice t 
	left join (select num,name from blade_dict where code='102')d on t.type=d.num
	left join (select * from blade_user where status = 1) u on t.creater = u.id
        		
data
===
select 
	t.*, d.name as typename
	from 
		blade_notice t 
		left join (select num,name from blade_dict where code='102')d on t.type=d.num 
		left join blade_attach a on t.pic=a.ID
where t.id = #{id}
		
update
===
 update blade_notice 
  set
	 @if(!isEmpty(creater)){
		creater = #{creater},
	 @} if(!isEmpty(type)){
		type = #{type},
	 @} if(!isEmpty(pic)){
		pic = #{pic},
	 @} if(!isEmpty(content)){
		content = #{content},
	 @} if(!isEmpty(title)){
		title = #{title},
	 @} if(!isEmpty(createtime)){
		createtime = #{createtime},
	 @} if(!isEmpty(publishtime)){
		publishtime = #{publishtime},
	 @} if(!isEmpty(version)){
		version = #{version}
	 @}
 where id = #{id}

findById
===
select * from blade_notice where id = #{id}


diy
===
select NUM as "num",
	ID as "id",
	PID as "pId",
	NAME as "name",
	(case when (pId=0 or pId is null) then 'true' else 'false' end) "open" 
from  blade_dict
where CODE = '102'