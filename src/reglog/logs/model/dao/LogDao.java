package reglog.logs.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import reglog.logs.model.connection.ConnectionFactory;
import reglog.logs.model.entity.LogFilter;

public class LogDao {

	private Connection connection;
	
	private String sqlSelect
			= "SELECT ID_LOG, TEXT_LOG, EXEC_DATETIME, NAME_STATUS, NAME_CLASSNAME, NAME_WORKSTATION, NAME_USER "
			+ "FROM TB_LOG, TB_USER, TB_WORKSTATION, TB_CLASSNAME, TB_STATUS "
			+ "WHERE TB_LOG.ID_USER = TB_USER.ID_USER "
			+ "AND TB_LOG.ID_WORKSTATION = TB_WORKSTATION.ID_WORKSTATION "
			+ "AND TB_LOG.ID_CLASSNAME = TB_CLASSNAME.ID_CLASSNAME "
			+ "AND TB_LOG.ID_STATUS = TB_STATUS.ID_STATUS ";
	
	public void delete() { 
		
		this.connection = new ConnectionFactory().getConnection();
		String sql = "TRUNCATE TABLE TB_LOG";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);	
            preparedStatement.execute();
            preparedStatement.close();	
            this.connection.close();
        }
        catch(SQLException e){
        	e.printStackTrace();
        }
        
	}
	public List<LogFilter> select(){
		
		this.connection = new ConnectionFactory().getConnection();
		try{
			List<LogFilter> logs = new ArrayList<LogFilter>();
			PreparedStatement preparedStatement = this.connection.prepareStatement(sqlSelect + "ORDER BY EXEC_DATETIME");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
			                    
				LogFilter log = new LogFilter();  
				log.setIdLog(resultSet.getInt("ID_LOG"));
				log.setLogText(resultSet.getString("TEXT_LOG"));
				log.setDateTime(resultSet.getTimestamp("EXEC_DATETIME"));
				log.setNameUser(resultSet.getString("NAME_USER"));
				log.setNameWorkstation(resultSet.getString("NAME_WORKSTATION"));
				log.setNameClassName(resultSet.getString("NAME_CLASSNAME"));
				log.setNameStatus(resultSet.getString("NAME_STATUS"));
				logs.add(log);
		                    
			}
			this.connection.close();
			return logs;
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public List<LogFilter> select(boolean failed, boolean passed, boolean title, boolean information, String dateTimeStart, String dateTimeEnd){
		
		String sqlPartial = "";
		String sqlCompl = "AND  NAME_STATUS IN (";
		int cont = 0;
		if(failed) { 
			cont++;
			sqlPartial += "'FAILED'";
		}
		if(passed) { 
			cont++;
			sqlPartial += ((cont > 1) ? "," : "") + "'PASSED'";
		}
		if(title) { 
			cont++;
			sqlPartial += ((cont > 1) ? "," : "") + "'TITLE'";
		}
		if(information) {
			cont++;
			sqlPartial += ((cont > 1) ? "," : "") + "'INFORMATION'";
		}
		if(cont > 0) sqlPartial = sqlCompl + sqlPartial + ") ";
		if(dateTimeStart != "" && dateTimeEnd != "") {
			sqlPartial += "AND EXEC_DATETIME BETWEEN '" + dateTimeStart + "' AND '" + dateTimeEnd + "' ";
		}
		
		sqlPartial += "ORDER BY EXEC_DATETIME";
		
		
		this.connection = new ConnectionFactory().getConnection();
		try{
			List<LogFilter> logs = new ArrayList<LogFilter>();
			PreparedStatement preparedStatement = this.connection.prepareStatement(sqlSelect + sqlPartial);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
			                    
				LogFilter log = new LogFilter();  
				log.setIdLog(resultSet.getInt("ID_LOG"));
				log.setLogText(resultSet.getString("TEXT_LOG"));
				log.setDateTime(resultSet.getTimestamp("EXEC_DATETIME"));
				log.setNameUser(resultSet.getString("NAME_USER"));
				log.setNameWorkstation(resultSet.getString("NAME_WORKSTATION"));
				log.setNameClassName(resultSet.getString("NAME_CLASSNAME"));
				log.setNameStatus(resultSet.getString("NAME_STATUS"));
				logs.add(log);
		                    
			}
			this.connection.close();
			return logs;
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}

}
