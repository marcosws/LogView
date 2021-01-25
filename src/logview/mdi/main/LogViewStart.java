package logview.mdi.main;

public class LogViewStart {
	
	public static void main(String[] args) {
		
        java.awt.EventQueue.invokeLater(() -> {
        	LogViewMain logViewMain = new LogViewMain();
        	logViewMain.start();
        });
		
	}

}
