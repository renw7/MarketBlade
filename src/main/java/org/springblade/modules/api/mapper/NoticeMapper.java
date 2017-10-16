package org.springblade.modules.api.mapper;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.Sql;
import org.beetl.sql.core.annotatoin.SqlStatementType;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springblade.modules.platform.model.Notice;

import java.util.List;

/**
 * @author zhuangqian
 */
public interface NoticeMapper extends BaseMapper<Notice> {

	Notice findById(@Param("id") Integer id);
	
	@Sql("select * from blade_notice")
	List<Notice> selectAll();
	
	@Sql(value=" update blade_notice set title = ? where id = ? ", type=SqlStatementType.UPDATE)
	void updateTitle(String title, int id);
	
}
