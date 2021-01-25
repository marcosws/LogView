package logview.mdi.utils;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class GridView {
	
	protected JTable table;
	protected DefaultTableModel model;
	protected JScrollPane jScrollPane;
	
	public JTable getTable() {
		return table;
	}
		    
	public DefaultTableModel getModel() {
		return model;
	}
	public JScrollPane getjScrollPane() {
		return jScrollPane;
	}
	public GridView(){
		        
		model = new DefaultTableModel();
		table = new JTable(model) {	          
			private static final long serialVersionUID = 1L;
			@Override // Deixa o JTable como somente leitura (Read Only)
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
			
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
			{
				Component c = super.prepareRenderer(renderer, row, column);

				if (!isRowSelected(row))
				{
					int modelRow = convertRowIndexToModel(row);
					String type = this.getModel().getValueAt(modelRow, 1).toString();
					if ("TITLE".equals(type)) c.setForeground(Color.BLUE);
					if ("INFORMATION".equals(type)) c.setForeground(Color.BLACK);
					if ("PASSED".equals(type)) c.setForeground(new Color(26, 134, 58));
					if ("FAILED".equals(type)) c.setForeground(Color.RED);
				}
				
				
			/*	if(this.getModel().getValueAt(row, column).toString().equals("4")) {
					c.setForeground(Color.BLUE);
				}
				else if(this.getModel().getValueAt(row, column).toString().equals("1")) {
					c.setForeground(Color.RED);
				}
				else if(this.getModel().getValueAt(row, column).toString().equals("2")) {
					c.setForeground(Color.GREEN);
				}
				else if(this.getModel().getValueAt(row, column).toString().equals("3")){
					c.setForeground(Color.BLACK);
				}
                    */
				return c;
			}
			
		};
		jScrollPane = new JScrollPane(table);
	}
	public void clearGrid(){
		int linhas = model.getRowCount();
		for(int i = linhas - 1; i >= 0; i--){
			model.removeRow(i); 
		}
	}
}
