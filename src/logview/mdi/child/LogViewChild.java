package logview.mdi.child;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import logview.controller.LogViewController;
import logview.mdi.utils.GridView;
import logview.mdi.utils.SizeWindow;

public class LogViewChild implements ActionListener{
	
	protected JInternalFrame frame;
	public JPanel panel;
	private GridView gridView;
	private JButton btnFilterLogs;
	private JButton btnDeleteLogs;
	private JButton btnReport;
	private JButton btnExit;
	private JCheckBox jcbTitle;
	private JCheckBox jcbPassed;
	private JCheckBox jcbFailed;
	private JCheckBox jcbInformation;
	private JCheckBox jcbDateTimeFilter;
    private JSpinner jspDateStart;
    private JSpinner jspTimeStart;
    private JSpinner jspDateEnd;
    private JSpinner jspTimeEnd;
    private String pathReport;
    
	public String getPathReport() {
		return pathReport;
	}

	public void setPathReport(String pathReport) {
		this.pathReport = pathReport;
	}

	public JSpinner getJspDateStart() {
		return jspDateStart;
	}

	public JSpinner getJspTimeStart() {
		return jspTimeStart;
	}

	public JSpinner getJspDateEnd() {
		return jspDateEnd;
	}

	public JSpinner getJspTimeEnd() {
		return jspTimeEnd;
	}

	public JCheckBox getJcbDateTimeFilter() {
		return jcbDateTimeFilter;
	}

	public JCheckBox getJcbTitle() {
		return jcbTitle;
	}

	public JCheckBox getJcbPassed() {
		return jcbPassed;
	}

	public JCheckBox getJcbFailed() {
		return jcbFailed;
	}

	public JCheckBox getJcbInformation() {
		return jcbInformation;
	}

	public JInternalFrame getFrame() {
		return frame;
	}
	
	public GridView getGridView() {
		return gridView;
	}

	public LogViewChild() {
		
        panel = new JPanel();
        panel.setLayout(null);
        frame = new JInternalFrame("Test Logs", true, true, true, true);
        
        JLabel titulo = new JLabel("Test Logs");
        titulo.setFont(new Font("Arial", Font.BOLD,14));
        titulo.setBounds(20, 20, 500, 20);
        
        jcbTitle = new JCheckBox("Title");
        jcbTitle.setBounds(20, 50, 80, 20);
        jcbTitle.setSelected(true);
        jcbPassed = new JCheckBox("Passed");
        jcbPassed.setBounds(100, 50, 80, 20);
        jcbPassed.setSelected(true);
        jcbFailed = new JCheckBox("Failed");
        jcbFailed.setBounds(180, 50, 80, 20);
        jcbFailed.setSelected(true);
        jcbInformation = new JCheckBox("Information");
        jcbInformation.setBounds(260, 50, 100, 20);
        jcbInformation.setSelected(true);
        
        btnFilterLogs = new JButton("Filter Logs");
        btnFilterLogs.setBounds(20, 80, 120, 20);
        
        btnDeleteLogs = new JButton("Delete Logs");
        btnDeleteLogs.setBounds(140, 80, 120, 20);
        
        btnReport = new JButton("Report");
        btnReport.setBounds(260, 80, 120, 20);
        
        btnExit = new JButton("Exit");
        btnExit.setBounds(380, 80, 120, 20);
        
        
        jcbDateTimeFilter = new JCheckBox("Date Time Filter");
        jcbDateTimeFilter.setBounds(10, 16, 120, 20);
        
        JLabel lbDateTimeStart = new JLabel("Date Time Start:");
        lbDateTimeStart.setBounds(140, 16, 100, 20);
        
        JLabel lbDateTimeEnd = new JLabel("Date Time End:");
        lbDateTimeEnd.setBounds(140, 40, 100, 20);
        

        
        
        Date date = new Date();
        SpinnerDateModel spinnerDateModel1 = new SpinnerDateModel(date, null, null, Calendar.DATE);
        jspDateStart = new JSpinner(spinnerDateModel1);
        JSpinner.DateEditor de1 = new JSpinner.DateEditor(jspDateStart, "dd/MM/yyyy");
        jspDateStart.setEditor(de1);
        jspDateStart.setBounds(260, 16, 90, 20);
        jspDateStart.setEnabled(false);
        
        SpinnerDateModel spinnerDateModel2 = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        jspTimeStart = new JSpinner(spinnerDateModel2);
        JSpinner.DateEditor de2 = new JSpinner.DateEditor(jspTimeStart, "HH:mm:ss");
        jspTimeStart.setEditor(de2);
        jspTimeStart.setBounds(360, 16, 80, 20);
        jspTimeStart.setEnabled(false);
        
        SpinnerDateModel spinnerDateModel3 = new SpinnerDateModel(date, null, null, Calendar.DATE);
        jspDateEnd = new JSpinner(spinnerDateModel3);
        JSpinner.DateEditor de3 = new JSpinner.DateEditor(jspDateEnd, "dd/MM/yyyy");
        jspDateEnd.setEditor(de3);
        jspDateEnd.setBounds(260, 40, 90, 20);
        jspDateEnd.setEnabled(false);
        
        SpinnerDateModel spinnerDateModel4 = new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        jspTimeEnd = new JSpinner(spinnerDateModel4);
        JSpinner.DateEditor de4 = new JSpinner.DateEditor(jspTimeEnd, "HH:mm:ss");
        jspTimeEnd.setEditor(de4);
        jspTimeEnd.setBounds(360, 40, 80, 20);
        jspTimeEnd.setEnabled(false);
        
        
        JPanel filter = new JPanel();
        filter.setLayout(null);
        filter.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Date Time Filter"));
        filter.setBounds(510, 30, 480, 72);
        
        filter.add(jcbDateTimeFilter);
        filter.add(lbDateTimeStart);
        filter.add(jspDateStart);
        filter.add(jspTimeStart);
        filter.add(lbDateTimeEnd);
        filter.add(jspDateEnd);
        filter.add(jspTimeEnd);
        
        
        gridView = new GridView();
        
        gridView.getModel().addColumn("Id");
        gridView.getModel().addColumn("Status");
        gridView.getModel().addColumn("Log");
        gridView.getModel().addColumn("User");
        gridView.getModel().addColumn("Class Name");
        gridView.getModel().addColumn("Workstation");
        gridView.getModel().addColumn("Date Time");
        
        gridView.getTable().getColumnModel().getColumn(0).setMaxWidth(40);
        gridView.getTable().getColumnModel().getColumn(0).setPreferredWidth(40);
        gridView.getTable().getColumnModel().getColumn(1).setMaxWidth(100);
        gridView.getTable().getColumnModel().getColumn(1).setPreferredWidth(100);
        gridView.getTable().getColumnModel().getColumn(2).setPreferredWidth(800);
        gridView.getTable().getColumnModel().getColumn(3).setPreferredWidth(80);
        gridView.getTable().getColumnModel().getColumn(4).setPreferredWidth(80);
        gridView.getTable().getColumnModel().getColumn(5).setPreferredWidth(80);
        gridView.getTable().getColumnModel().getColumn(6).setPreferredWidth(80);
        gridView.getTable().setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
       
        gridView.getjScrollPane().setBounds(0, 120, (int)SizeWindow.getWidth() - 10, (int)SizeWindow.getHeight() - 180);
        
        panel.add(titulo);
        panel.add(jcbTitle);
        panel.add(jcbInformation);
        panel.add(jcbPassed);
        panel.add(jcbFailed);
        panel.add(btnFilterLogs);
        panel.add(btnDeleteLogs);
        panel.add(btnReport);
        panel.add(btnExit);
        panel.add(filter);
        panel.add(gridView.getjScrollPane());
        
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setBounds(0, 0, (int)SizeWindow.getWidth(), (int)SizeWindow.getHeight());
        frame.setVisible(true);
		
	}
	public void start() {
		
		btnFilterLogs.addActionListener(this);
		btnDeleteLogs.addActionListener(this);
		btnReport.addActionListener(this);
		btnExit.addActionListener(this);
		jcbDateTimeFilter.addActionListener(this);
		LogViewController logViewController = new LogViewController();
		logViewController.select(this);
	}
	



	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource().equals(btnFilterLogs)) {
			LogViewController logViewController = new LogViewController();
			logViewController.select(this);
		}
		else if(event.getSource().equals(btnDeleteLogs)) {
			LogViewController logViewController = new LogViewController();
			logViewController.delete();
			logViewController.select(this);
		}
		else if(event.getSource().equals(btnReport)) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			fileChooser.setSelectedFile(new File("report"));
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("*.html", "html"));
			fileChooser.setAcceptAllFileFilterUsed(false);
			int result = fileChooser.showSaveDialog(this.getFrame());
			if (result == JFileChooser.APPROVE_OPTION) {
			    File selectedFile = fileChooser.getSelectedFile();
			    this.setPathReport(selectedFile.getAbsolutePath() + ".html");
			    LogViewController logViewController = new LogViewController();
			    logViewController.report(this);
			}
		}
		else if(event.getSource().equals(btnExit)) {
			 this.getFrame().dispose();
		}
		else if(event.getSource().equals(jcbDateTimeFilter)) {
			if(jcbDateTimeFilter.isSelected()) {
			    jspDateStart.setEnabled(true);
			    jspTimeStart.setEnabled(true);
			    jspDateEnd.setEnabled(true);
			    jspTimeEnd.setEnabled(true);
			}
			else {
			    jspDateStart.setEnabled(false);
			    jspTimeStart.setEnabled(false);
			    jspDateEnd.setEnabled(false);
			    jspTimeEnd.setEnabled(false);
			}
		}
	}


}
