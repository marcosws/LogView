package logview.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import logview.common.Common;
import logview.common.TemplateHtml;
import logview.mdi.child.LogViewChild;
import reglog.logs.model.dao.LogDao;
import reglog.logs.model.entity.LogFilter;


public class LogViewController {
	
	LogViewChild logViewChild;
	
	public void select(Object view) {
		
		logViewChild = (LogViewChild)view;
		
		boolean check1 = logViewChild.getJcbFailed().isSelected();
		boolean check2 = logViewChild.getJcbPassed().isSelected();
		boolean check3 = logViewChild.getJcbTitle().isSelected();
		boolean check4 = logViewChild.getJcbInformation().isSelected();
		
		String dateTimeStart = "";
		String dateTimeEnd = "";
		if(logViewChild.getJcbDateTimeFilter().isSelected()) {
			dateTimeStart = Common.formatDate(logViewChild.getJspDateStart().getValue().toString()) + " " + Common.formatTime(logViewChild.getJspTimeStart().getValue().toString());
			dateTimeEnd = Common.formatDate(logViewChild.getJspDateEnd().getValue().toString()) + " " + Common.formatTime(logViewChild.getJspTimeEnd().getValue().toString());
			dateTimeStart = Common.formatDateTime(dateTimeStart);
			dateTimeEnd = Common.formatDateTime(dateTimeEnd);
		}
		
		if(((check1 == check2) && (check1 == check3) && (check1 == check4))){
			
			LogDao logDao = new LogDao();
			List<LogFilter> logs = new ArrayList<LogFilter>();
			
			if(dateTimeStart != "" && dateTimeEnd != "") {
			
			logs = logDao.select(logViewChild.getJcbFailed().isSelected(), 
					logViewChild.getJcbPassed().isSelected(), 
					logViewChild.getJcbTitle().isSelected(), 
					logViewChild.getJcbInformation().isSelected(),
					dateTimeStart,
					dateTimeEnd);
			}
			else {
				logs = logDao.select();
			}
			
			
			logViewChild.getGridView().clearGrid();
			logs.forEach((l) ->{
				logViewChild.getGridView().getModel().addRow(new Object[] {
						l.getIdLog(),
						l.getNameStatus(),
						l.getLogText(),
						l.getNameUser(),
						l.getNameClassName(),
						l.getNameWorkstation(),
						new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(l.getDateTime())
				});
			});
		}
		else {
			
			LogDao logDao = new LogDao();
			List<LogFilter> logs = new ArrayList<LogFilter>();
			logs = logDao.select(logViewChild.getJcbFailed().isSelected(), 
					logViewChild.getJcbPassed().isSelected(), 
					logViewChild.getJcbTitle().isSelected(), 
					logViewChild.getJcbInformation().isSelected(),
					dateTimeStart,
					dateTimeEnd);
			logViewChild.getGridView().clearGrid();
			logs.forEach((l) ->{
				logViewChild.getGridView().getModel().addRow(new Object[] {
						l.getIdLog(),
						l.getNameStatus(),
						l.getLogText(),
						l.getNameUser(),
						l.getNameClassName(),
						l.getNameWorkstation(),
						new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(l.getDateTime())
				});
			});  
			
		}
	}
	
	public void delete() {
		
		LogDao logDao = new LogDao();
		logDao.delete();
		
	}
	
	public void report(Object view) {
		
		logViewChild = (LogViewChild)view;
		
		
		int rowCount = logViewChild.getGridView().getTable().getModel().getRowCount();
		String rowsLog = "";
		for(int i = 0;i < rowCount;i++) {
			String log = logViewChild.getGridView().getTable().getModel().getValueAt(i, 2).toString();
			String status = logViewChild.getGridView().getTable().getModel().getValueAt(i, 1).toString();
			String rowDateTime = logViewChild.getGridView().getTable().getModel().getValueAt(i, 6).toString();
			if(status.equals("FAILED")) {
				rowsLog += TemplateHtml.FAILED_LOG.replace("{LOG}", log + " - " + rowDateTime) +  "\r\n";
			}
			else if(status.equals("PASSED")) {
				rowsLog += TemplateHtml.PASSED_LOG.replace("{LOG}", log+ " - " + rowDateTime) + "\r\n";
			}
			else if(status.equals("TITLE")) {
				rowsLog += TemplateHtml.TITLE_LOG.replace("{LOG}", log+ " - " + rowDateTime) + "\r\n";
			}
			else {
				rowsLog += TemplateHtml.INFO_LOG.replace("{LOG}", log+ " - " + rowDateTime) + "\r\n";
			}
		}
		
		String contentHtml = TemplateHtml.TEMPLATE_HTML.replace("{LINE}", rowsLog);
		
        try {
            Files.write(Paths.get(logViewChild.getPathReport()), contentHtml.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
		
	}

}
