
package client;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import client.order.MItem;
import client.order.Menu;
import client.order.OrderQueue;

/**
 * Holds the information for the kitchen start page
 * CLIENT SIDED
 * 
 * @author Desere and Shan
 *
 */
public class KitchenStartPage extends JPanel {

	private static final long serialVersionUID = -8112480994553957L;

	public static String currentScreen = "";
	public JTable table;

	public KitchenStartPage(ClientFrame frame) {
		super();
		setBounds(0, 0, 1039, 656);
		setLayout(null);

		// Create our scroll pane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1039, 522);
		add(scrollPane);

		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 20));
		table.setRowHeight(40);
		
		scrollPane.setViewportView(table);

		/**
		 * Sets the model for this table.
		 */
		table.setModel(new DefaultTableModel(
				new Object[][] {

				},
				new String[] {
					"Table", "Order", "Mark Complete"
				}
				) {
			
			private static final long serialVersionUID = 1378760572757475870L;

			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
					Integer.class, String.class, Boolean.class
			};

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		// Sets the width of the columns.
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(400);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);

		// Centers the text in the columns.
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.setDefaultRenderer(String.class, centerRenderer);
		table.setDefaultRenderer(Integer.class, centerRenderer);
		
		table.addMouseListener(new MouseListener() {
			@Override public void mouseClicked(MouseEvent arg0) { }
			@Override public void mouseEntered(MouseEvent arg0) { }
			@Override public void mouseExited(MouseEvent arg0) { }
			@Override public void mousePressed(MouseEvent arg0) { }

			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(table.getSelectedColumn() == 1)
				System.out.println("Order details for table: "+(int) table.getModel().getValueAt(table.getSelectedRow(), 0));
			}


		});

		// Adds a listener to see when the table has been changed (Clicked)
		table.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				int row = e.getFirstRow();
				int column = e.getColumn();
				if(column == 1) {
					viewOrderDetails(row);
					System.out.println("Order details for table: "+table.getModel().getValueAt(row, 0));
				}
				if (column == 2) {
					TableModel model = (TableModel) e.getSource();
					DefaultTableModel tab = (DefaultTableModel)table.getModel();
					Boolean checked1 = (Boolean) model.getValueAt(row, column);
					if (checked1) {
						//send information to the waiter that table order is done with table number
						int tableNum = (int) table.getValueAt(row, 0);
						System.out.println("Table order done for table "+tableNum);
						tab.removeRow(row);
					} 
				}

			}
		});

		String temp = "View order details";

		DefaultTableModel update = (DefaultTableModel) table.getModel();
		update.addRow(new Object[] {new Integer(5),temp,new Boolean(false)});
		
	}
	
	public void callWaitstaff() {
		//implement a section 
	}
	
	public void callManager() {
		//implement a section

	}

	/**
	 * Displays the order details for this order.
	 * 
	 * @param row
	 */
	public void viewOrderDetails(int row) {

	}

	public void addToTable() {

	}

	public String getOrderToString(int tableIndex) {
		StringBuilder s = new StringBuilder();
		//s.append("Order:\n\n");

		for(MItem i : OrderQueue.orders.get(tableIndex).items) {
			s.append("x"+i.qty+" "+i.name+"\n");

			// Current Menu Item
			MItem prev = Menu.getItem(i.name);
			String[] oldIngTok = prev.ingredients.split(",");

			// Order Menu Item
			String[] newIngTok = i.ingredients.split(",");

			for(int index = 0; index < newIngTok.length; index++) {
				String[] oldIng = oldIngTok[index].split(":");
				String[] newIng = newIngTok[index].split(":");
				// Substituted ingredient
				if(!oldIng[0].equals(newIng[0])) {
					s.append("    - "+oldIng[0]+" sub for "+newIng[0]+"\n");
				}
				if(!oldIng[1].equals(newIng[1])) {
					s.append("    - x"+newIng[1]+" "+newIng[0]+"\n");
				}
			}
			if(!(i.specialReqs.equalsIgnoreCase("none")) 
					&& !i.specialReqs.equals("") && !i.specialReqs.equals(null)) {
				s.append("    - "+i.specialReqs+"\n");
			}
		}
		return s.toString();
	}

}