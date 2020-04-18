package client;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import client.utils.JFrameUtils;

/**
 * Holds info for the wait staff to see including
 * marking orders as complete, taking cash payments and 
 * fulfilling help/refill requests..
 * 
 * @author Karimshan
 *
 */
public class WaitstaffStartPage extends JPanel {

	private static final long serialVersionUID = -811294553957L;

	public JTable table;
	public DefaultTableModel model;

	public int orderIndex = -1;
	
	public WaitstaffStartPage() {
		super();
		setBounds(0, 0, 1039, 656);
		setLayout(null);
	
		// Create our scroll pane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1039, 552);
		add(scrollPane);
		

		table = createTable();
		table.setRowSelectionAllowed(false);
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 18));
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setRowHeight(26);
		
		table.addMouseListener(new MouseListener() {

			@Override public void mouseClicked(MouseEvent arg0) { }

			@Override public void mouseEntered(MouseEvent arg0) { }

			@Override public void mouseExited(MouseEvent arg0) { }

			@Override public void mousePressed(MouseEvent arg0) { }

			@Override
			public void mouseReleased(MouseEvent arg0) {
				int row = table.getSelectedRow();
				int col = table.getSelectedColumn();
				if(col == 0)
					return;
				if(arg0.isPopupTrigger())
					return;
				if(!(table.getModel().getValueAt(row, col) instanceof String))
					return;
				String val = (String) table.getModel().getValueAt(row, col);
				if(val.equals("X"))
					return;
				else {
					
					// Refill
					if(col == 1) {
						boolean choice = JFrameUtils.confirmDialog("Refill On The Way", 
							"Are you sure you want to let table "+(row + 1)+" know that you're on your way\n"
							+ "with the refill that they requested?"
							+ " This action cannot be undone.");
						if(!choice) {
							return;
						}
						table.getModel().setValueAt("X", row, col);
						Client.session.getPacketEncoder().sendRequestComplete(row, true);
					}
					// Help
					else if(col == 2) {
						boolean choice = JFrameUtils.confirmDialog("Help On The Way", 
							"Are you sure you want to let table "+(row + 1)+" know that you're on your way\n"
							+ "to help them with their request?"
							+ " This action cannot be undone.");
						if(!choice) {
							return;
						}
						table.getModel().setValueAt("X", row, col);
						Client.session.getPacketEncoder().sendRequestComplete(row, false);
					}
					// Confirming table got order
					else if(col == 3) {
						boolean choice = JFrameUtils.confirmDialog("Order Completion Confirmation", 
							"Are you sure you want to mark this order for table "+(row + 1)+" as delivered?"
								+ "\nThis action cannot be undone.");
						if(!choice) {
							return;
						}
						table.getModel().setValueAt("X", row, col);
						Client.session.getPacketEncoder().sendOrderDelivered(row, orderIndex);
						orderIndex = -1;
					}
				}
			}

		});
		
		scrollPane.setViewportView(table);
		
	}
	
	public JTable createTable() {
		
		Object[][] rows = new Object[20][5];

		for(int i = 0; i < 20; i++) {
			rows[i][0] = new Integer(i + 1);
			rows[i][1] = "X";
			rows[i][2] = "X";
			rows[i][3] = "X";
			rows[i][4] = "X";
		}
		
		Object[] cols = new Object[]{
				"Table Number", "Refill", "Help", "Order", "Payment"
		};
		
		JTable t = new JTable(rows, cols) {

			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int column) {
				int col = convertColumnIndexToModel(column);
				if(col == 0) 
					return Integer.class;
				else if(col >= 1)
					return String.class;
				return super.getColumnClass(column);
			}
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		t.setDefaultRenderer(Integer.class, centerRenderer);
		
		t.setDefaultRenderer(String.class, new DefaultTableCellRenderer() {

			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, 
					boolean isSelected, boolean hasFocus, int row, int column) {
				super.setHorizontalAlignment( JLabel.CENTER );
				JLabel c = (JLabel) super.getTableCellRendererComponent(table, value, 
						isSelected, hasFocus, row, column);
				Color darkgreen = new Color(0, 153, 0);
				c.setBackground(c.getText().equals("O") ? darkgreen : Color.RED);
				return c;
			}
		});
		
		return t;
	}
	

}