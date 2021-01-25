package logview.mdi.utils;

import java.awt.Dimension;

import javax.swing.JLabel;

public class StatusBar extends JLabel{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public StatusBar() {
        super();
        super.setPreferredSize(new Dimension(100, 18));
        
        this.textoMensagem("LogView");
    }
    
    private void textoMensagem(String msg){
        this.setText(" " + msg);
    }

}
