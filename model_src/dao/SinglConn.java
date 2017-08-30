package dao;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SinglConn {

	private DataSource ds;
	private static SinglConn INSTANCE = null;

	private SinglConn() throws SQLException, NamingException {
		Context ctx = new InitialContext();
		ds = (DataSource) ctx.lookup("java:comp/env/jdbc/school");		 

	}

	public static synchronized SinglConn getInstance() throws SQLException, NamingException {
		if (INSTANCE == null) {
			INSTANCE = new SinglConn();
			return INSTANCE;
		}
		return INSTANCE;
	}
	
	public Connection getConnection() throws SQLException, NamingException{
		return ds.getConnection();
	}
}
