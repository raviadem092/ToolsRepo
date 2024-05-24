package com.rk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class SelectTest2 {
	
	private static Logger logger = Logger.getLogger(SelectTest2.class);

	static {
		try {
			PropertyConfigurator.configure("src/com/rk/commons/Log4j.properties");
			
			logger.info("com.rk.jdbc.SelectTest: Log4j: setUp ready");
		} catch (Exception e) {
			e.printStackTrace();
			logger.fatal("com.rk.jdbc.SelectTest: Problem while setting up Log4j");
		}
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		logger.debug("com.rk.jdbc.SelectTest: Start of main method");
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			logger.debug("com.rk.jdbc.SelectTest: Class Loaded");
			
			 con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","c##userx","ravikumar");
			 logger.info("com.rk.jdbc.SelectTest: Established Connection with db s/w");
			 if (con !=null) 
			 stmt = con.createStatement();
			logger.debug("com.rk.jdbc.SelectTest: JDBC Statement obj created");
			 
			 if (stmt !=null) 
			 rs = stmt.executeQuery("Select* froM student2");
			logger.debug("com.rk.jdbc.SelectTest: SQL Query is sent to db s/w for execution and ResultSet obj created");
			 
			 if (rs !=null) 
			while (rs.next()!=false) {
				System.out.println(rs.getInt(1)+": " +rs.getString("Sname")+": "+rs.getString("clg_name"));
			}
			logger.debug("com.rk.jdbc.SelectTest: ResultSet obj generated results ");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.error("com.rk.jdbc.SelectTest: Known DB problem"+e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("com.rk.jdbc.SelectTest: Known DB problem"+e.getMessage());
		}
		finally {
			logger.debug("com.rk.jdbc.SelectTest: Closing JDBC objs");
			 try {
				if (rs !=null) 
					 rs.close();
			logger.debug("com.rk.jdbc.SelectTest: ResultSet obj is closed");
			} catch (SQLException e) {
				e.printStackTrace();
			logger.debug("com.rk.jdbc.SelectTest: problem in closing ResultSet obj"+e.getMessage());
			}
			 try {
				if (stmt !=null) 
					 stmt.close();
			logger.debug("com.rk.jdbc.SelectTest: Statement obj is closed");
			} catch (SQLException e) {
				e.printStackTrace();
			logger.debug("com.rk.jdbc.SelectTest: problem in closing Statement obj"+e.getMessage());
			}
			 try {
				if (con !=null) 
					 con.close();		
			logger.debug("com.rk.jdbc.SelectTest: Connection obj is closed");
			} catch (SQLException e) {
				e.printStackTrace();
			logger.debug("com.rk.jdbc.SelectTest: problem in closing Connection obj"+e.getMessage());
			}
		}
		logger.debug("com.rk.jdbc.SelectTest: End of the main method");
	}

}
