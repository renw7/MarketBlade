sourceList
===
select * from blade_operation_log

list
===
select l.*,
	u.NAME USERNAME,
	(CASE WHEN l.SUCCEED=1 THEN '成功' else '失败' end) SUCCEEDNAME 
from 
	blade_operation_log l 
	LEFT JOIN blade_user u on l.USERID=u.ID