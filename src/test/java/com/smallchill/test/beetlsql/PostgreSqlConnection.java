
package com.smallchill.test.beetlsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.beetl.sql.core.ConnectionSource;

/**
 * @author suxinjie
 *
 */
public class PostgreSqlConnection implements ConnectionSource {
	
	private Connection _getConn(){
		String driver = "org.postgresql.Driver";
        String dbName = "blade";
        String userName = "postgres";
        String password = "1qaz@WSX";
        String url = "jdbc:postgresql://localhost:5432/" + dbName;
        Connection conn = null;
        try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userName, password);
			conn.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	@Override
	public Connection getMaster() {
		return _getConn();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Connection getConn(String sqlId, boolean isUpdate, String sql, List paras) {
		return _getConn();
	}

	

	@Override
	public boolean isTransaction() {
		
		return false;
	}

	@Override
	public Connection getSlave() {
		return this.getMaster();
	}

	@Override
	public void forceBegin(boolean isMaster) {
		
		
	}

	@Override
	public void forceEnd() {
		
		
	}

	

}
