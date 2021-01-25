package logview.mdi.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import logview.mdi.child.LogViewChild;
import logview.mdi.utils.SizeWindow;
import logview.mdi.utils.StatusBar;

public class LogViewMain extends JFrame implements ActionListener {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JDesktopPane jDesktopPane;
    private JMenuBar menuLogView;
    private JMenu jmLog;
    private JMenuItem jmiTestLogs;
    private JMenuItem jmiExit;
    private StatusBar statusBar;
   
	
	public LogViewMain() {
		
		this.statusBar = new StatusBar();
        this.menuLogView = new JMenuBar();
        this.jDesktopPane = new JDesktopPane();
        this.jmLog = new JMenu("Log");
        this.jmiTestLogs = new JMenuItem("Test Logs");
        this.jmiExit = new JMenuItem("Exit");

        jmLog.add(jmiTestLogs);
        jmLog.addSeparator();
        jmLog.add(jmiExit);
        menuLogView.add(jmLog);
        this.setJMenuBar(menuLogView);
	}
	
	public void start() {
		
		jmiTestLogs.addActionListener(this);
		jmiExit.addActionListener(this);
		this.setTitle("LogView");
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(statusBar, java.awt.BorderLayout.SOUTH);
        this.setMinimumSize(new Dimension(800, 500));
        this.add(jDesktopPane, BorderLayout.CENTER);
        this.pack();
        this.setVisible(true);
        Dimension dimension = this.getSize();
        SizeWindow.setHeight(dimension.getHeight());
        SizeWindow.setWidth(dimension.getWidth()); 
		
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource().equals(jmiTestLogs)) {
			LogViewChild viewChild = new LogViewChild();
			viewChild.start();
			jDesktopPane.add(viewChild.getFrame());
		}
		else if(event.getSource().equals(jmiExit)) {
			System.exit(0);
		}
	
	}
	
	
}
