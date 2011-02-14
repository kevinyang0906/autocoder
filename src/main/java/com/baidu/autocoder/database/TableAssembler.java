package com.baidu.autocoder.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.autocoder.Configuration;

/**
 * 数据库表信息组装器.
 * TODO 此类代码有待优化
 * 
 * @author GuoLin
 *
 */
public class TableAssembler {
	
	private static final Logger logger = LoggerFactory.getLogger(TableAssembler.class);

	/** 数据库连接. */
	private Connection conn = null;

	/**
	 * 获取指定表名的数据库表信息对象.
	 * @param tableName 表名
	 * @return 数据库表信息对象，如果发生任何异常则返回null
	 */
	public Table getTable(String tableName) {
		return getTable(tableName, null);
	}
	
	/**
	 * 获取指定表名的数据库表信息对象.
	 * @param tableName 表名
	 * @param entityName 实体名称
	 * @return 数据库表信息对象，如果发生任何异常则返回null
	 */
	public Table getTable(String tableName, String entityName) {
		try {
			openConnection();
			
			List<Field> fields = getFieldList(tableName);
			Field primaryKey = null;
			
			// 将主键从字段列表中独立出来
			for (Iterator<Field> iter = fields.iterator(); iter.hasNext(); ) {
				Field field = iter.next();
				if (field.isPrimary()) {
					primaryKey = field;
					iter.remove();
					break;
				}
			}
			
			if (entityName == null) {
				return new Table(tableName, primaryKey, fields);
			} else {
				return new Table(tableName, entityName, primaryKey, fields);
			}
		} catch(SQLException ex) {
			logger.error("", ex);
			return null;
		} finally {
			closeConnection();
		}
	}

	/**
	 * 打开数据库连接.
	 * @throws SQLException 如果连接建立失败
	 */
	private void openConnection() throws SQLException {
		Configuration config = Configuration.getConfiguration();
		
		// 实例化JDBC驱动
		try {
			Class.forName(config.getDatabaseDriver());
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException(ex);
		}

		// 建立连接及关闭连接
		conn = DriverManager.getConnection(config.getDatabaseUrl(), config.getDatabaseUsername(), config.getDatabasePassword());
	}
	
	/**
	 * 销毁数据库连接.
	 */
	private void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException ex) {
				logger.error("", ex);
			}
		}
	}

	/**
	 * 获取指定表的字段列表.
	 * @param tableName 表名
	 * @return 字段列表
	 * @throws SQLException 如果发生任何可能的SQL异常
	 */
	private List<Field> getFieldList(String tableName) throws SQLException {
		// 获取Metadata
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from " + tableName.toLowerCase());
		ResultSetMetaData metadata = rs.getMetaData();
		int columnCount = metadata.getColumnCount();
		
		// 获取主键名称
		Set<String> primaryKeysName = getPrimaryKeysName(tableName);
		
		// 获取所有字段
		List<Field> fields = new ArrayList<Field>(columnCount);
		for (int i = 1; i <= columnCount; i++) {
			String name = metadata.getColumnName(i).toLowerCase();
			String type = metadata.getColumnTypeName(i);
			int length = metadata.getColumnDisplaySize(i);
			int nullable = metadata.isNullable(i);
			Field f = new Field(name, type, length, primaryKeysName.contains(name), nullable == ResultSetMetaData.columnNullable);
			fields.add(f);
		}
		
		return fields;
	}
	
	/**
	 * 获取指定表的主键名称.
	 * @param tableName 表名
	 * @return 主键名称
	 * @throws SQLException 如果发生任何可能的SQL异常
	 */
	private Set<String> getPrimaryKeysName(String tableName) throws SQLException {
		Set<String> primaryKeys = new HashSet<String>();
		
		DatabaseMetaData dbMetadata = conn.getMetaData();
		ResultSet rs = dbMetadata.getPrimaryKeys(null, null, tableName);
		while (rs.next()) {
			primaryKeys.add(rs.getString("COLUMN_NAME").toLowerCase());
		}
		return primaryKeys;
	}

}
