package org.springblade.modules.api.mapper;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.Sql;
import org.beetl.sql.core.annotatoin.SqlStatementType;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springblade.modules.platform.model.Notice;

import java.util.List;

public interface NoticeMapper extends BaseMapper<Notice> {

	public Notice findById(@Param("id") Integer id);
	
	@Sql("select * from blade_notice")
	public List<Notice> selectAll();
	
	@Sql(value=" update blade_notice set title = ? where id = ? ", type=SqlStatementType.UPDATE)
	public void updateTitle(String f_vc_bt, int id);
	
}
