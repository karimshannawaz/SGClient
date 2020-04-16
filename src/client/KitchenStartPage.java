
package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import client.games.GamePanel;
import client.games.GuessTheNumber;
import client.games.RockPaperScissors;
import client.order.MenuPanel;
import client.order.PayPanel;
import client.rewards.RewardsPanel;
import client.utils.JFrameUtils;
import javazoom.jl.player.Player;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JList;



public class KitchenStartPage extends JPanel implements TableModelListener{

	private static final long serialVersionUID = -8112480994553957L;
	
	public static String currentScreen = "";
	public JTable table;

	public KitchenStartPage(ClientFrame frame) {
		super();
		setBounds(0, 0, 1039, 656);
		setLayout(null);
		
		JButton btnCallWait = new JButton("Call Waitstaff");
		btnCallWait.setFont(new Font("Tahoma", Font.PLAIN, 32));
		btnCallWait.setBounds(0, 0, 336, 328);
		add(btnCallWait);
		btnCallWait.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callWaitstaff();
			}
		});
		
		JButton btnCallMang = new JButton("Call Manager");
		btnCallMang.setFont(new Font("Tahoma", Font.PLAIN, 32));
		btnCallMang.setBounds(0, 328, 336, 328);
		add(btnCallMang);
		btnCallMang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callManager();
			}
		});
		
		
		JLabel lblTableNum = new JLabel("Table Number");
		lblTableNum.setHorizontalAlignment(SwingConstants.CENTER);
		lblTableNum.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblTableNum.setBounds(336, 0, 163, 63);
		add(lblTableNum);
		
		JLabel lblOrder = new JLabel("Order");
		lblOrder.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblOrder.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrder.setBounds(499, 0, 377, 63);
		add(lblOrder);
		
		JLabel lblOrdStatus = new JLabel("Status");
		lblOrdStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrdStatus.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblOrdStatus.setBounds(876, 0, 163, 63);
		add(lblOrdStatus);
		
		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 20));
		table.setBounds(336, 68, 703, 588);
		add(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"Table Number", "Order", "Status"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 413841898716691914L;
			Class[] columnTypes = new Class[] {
				Integer.class, List.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(163);
		table.getColumnModel().getColumn(1).setPreferredWidth(377);
		table.getColumnModel().getColumn(2).setPreferredWidth(163);
		
		table.getModel().addTableModelListener(new TableModelListener(){
			
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				int row = e.getFirstRow();
			    int column = e.getColumn();
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
		//format to update, need to understand how the packages are sent etc to continue - desere
		//when something is sent about the order, set the first section to the table ID
		//then read in the order to be stored into the list
		// the bool will always be set as false to say the order is not ready
		// then once it is marked as true, will be sent back to the waiter to notify order is ready
		//row in table will then be removed
		//similar to the waitstaff option
		List<String> temp = new ArrayList();
		temp.add("Juicy Lucy");
		temp.add("Coke");
		DefaultTableModel update = (DefaultTableModel) table.getModel();
		update.addRow(new Object[] {new Integer(5),temp,new Boolean(false)});
		
	}
	
	public void addToTable() {
		
	}
	
	public void callWaitstaff() {
		
	}
	
	public void callManager() {
		
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		int row = e.getFirstRow();
	    int column = e.getColumn();
	    if (column == 3) {
	        TableModel model = (TableModel) e.getSource();
	        String columnName = model.getColumnName(column);
	        Boolean checked1 = (Boolean) model.getValueAt(row, column);
	        if (checked1) {
	        	//send information to the waiter that table order is done
	        	
	        } 
	    }
	}
}