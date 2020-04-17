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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class WaitstaffStartPage extends JPanel {

	private static final long serialVersionUID = -811294553957L;

	public JTable table;
	public DefaultTableModel model;
	
	public WaitstaffStartPage(ClientFrame frame) {
		super();
		setBounds(0, 0, 1039, 656);
		setLayout(null);
	
		// Create our scroll pane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 1039, 552);
		add(scrollPane);
		
		/*
		JPanel tablePanel = new JPanel();
		tablePanel.setBounds(374, 31, 665, 604);
		mainPanel.add(tablePanel);
		*/
		
		/*
		utilityPanel = new JPanel();
		utilityPanel.setBounds(0, 523, 1039, 133);
		add(utilityPanel);
		utilityPanel.setLayout(null);
		
		
		PayBtn = new JButton("Pay");
		PayBtn.setFont(new Font("Tahoma", Font.PLAIN, 28));
		PayBtn.setBounds(187, 0, 187, 392);
		mainPanel.add(PayBtn);
		OrderBtn = new JButton("Order");
		OrderBtn.setFont(new Font("Tahoma", Font.PLAIN, 28));
		OrderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentScreen = "order";
				showTableOption();
			}
		});
		OrderBtn.setBounds(0, 0, 187, 392);
		mainPanel.add(OrderBtn);
		
		CompBtn = new JButton("Compensate");
		CompBtn.setFont(new Font("Tahoma", Font.PLAIN, 28));
		CompBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentScreen = "compensate";
				showTableOption();
			}
		});
		
		CompBtn.setBounds(0, 394, 374, 262);
		mainPanel.add(CompBtn);
		*/
		
		/*
		JLabel tableLabel = new JLabel("Table Number");
		tableLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tableLabel.setBounds(374, 0, 166, 44);
		mainPanel.add(tableLabel);
		
		JLabel refillLabel = new JLabel("Refill");
		refillLabel.setHorizontalAlignment(SwingConstants.CENTER);
		refillLabel.setBounds(540, 0, 166, 44);
		mainPanel.add(refillLabel);
		
		JLabel helpLabel = new JLabel("Help");
		helpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		helpLabel.setBounds(707, 0, 166, 44);
		mainPanel.add(helpLabel);
		
		JLabel lblNewLabel = new JLabel("Order");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(873, 0, 166, 44);
		mainPanel.add(lblNewLabel);
		
		*/
		
		//for the waiter table, need to find some way that when there is a help or refill request
		//the waiter can tab the icon and update the table 
		//refill has to display the drinks requested

/*
		table = new JTable(dm);
		table.setRowSelectionAllowed(false);
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setRowHeight(26);
		*/
		
		
		/*
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{new Integer(1), null, null, null},
				{new Integer(2), null, null, null},
				{new Integer(3), null, null, null},
				{new Integer(4), null, null, null},
				{new Integer(5), null, null, null},
				{new Integer(6), null, null, null},
				{new Integer(7), null, null, null},
				{new Integer(8), null, null, null},
				{new Integer(9), null, null, null},
				{new Integer(10), null, null, null},
				{new Integer(11), null, null, null},
				{new Integer(12), null, null, null},
				{new Integer(13), null, null, null},
				{new Integer(14), null, null, null},
				{new Integer(15), null, null, null},
				{new Integer(16), null, null, null},
				{new Integer(17), null, null, null},
				{new Integer(18), null, null, null},
				{new Integer(19), null, null, null},
				{new Integer(20), null, null, null},
			},
			new String[] {
				"Table Number", "Refill", "Help", "Order"
			}
		) {
			
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
				Integer.class, Boolean.class, Boolean.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			
		});
		*/
		/*
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(166);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(166);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(166);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(166);
		
		
		// Centers the text in the columns.
//		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
//		table.setDefaultRenderer(Integer.class, centerRenderer);
		
		table.getColumnModel().getColumn(1).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(1).setCellEditor(new ButtonEditor(new JCheckBox()));
		
		scrollPane.setViewportView(table);
		//tablePanel.add(table);
		
		
		List<Object> refill = new ArrayList<Object>(20);
		
		/*
		table.getModel().addTableModelListener(new TableModelListener(){
			
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
				int row = e.getFirstRow();
			    int column = e.getColumn();
			    Object refReq = null;
			    if (column == 1) {
			        TableModel model = (TableModel) e.getSource();
			        Boolean checked1 = (Boolean) model.getValueAt(row, column);
			        if (checked1) {
			        	refReq="Coke, Lemonade";
			        	JFrameUtils.showMessage("Refills", "You have a new refill request: "+refReq+ " "+(row+1));
			        	//read in the customer data about refill
			        	refill.add(row,refReq);
			        } else {
			        	//show the waiter the refill request
			        	//store into object
			        	//refReq = "Coke, Lemonade";
			        	boolean confirm = JFrameUtils.confirmDialog("Table Refill", refill.get(row));
			        	if(!confirm) {
			        		model.setValueAt(Boolean.TRUE, row, column);
			        	}
			        	else
			        	{
			        		refill.add(row,null);
			        	}
			        	
			        }
			    }
			    else if(column == 2)
			    {
			    	TableModel model = (TableModel) e.getSource();
			    	String columnName = model.getColumnName(column);
			    	Boolean checked2= (Boolean) model.getValueAt(row, column);
			    	if (checked2) {
			        	JFrameUtils.showMessage("Help", "You have a new help request: at Table "+(row+1));
			        } else {
			        	//show the waiter the refill request
			        	//store into object
			        	//refReq = "Coke, Lemonade";
			        	boolean confirm = JFrameUtils.confirmDialog("Table Help", "Notifying customer... ");
			        	if(!confirm) {
			        		model.setValueAt(Boolean.TRUE, row, column);
			        	}
			        	else
			        	{
			        		//send to customer that waiter is coming
			        	}
			        	
			        }
			    }
			}
		});
		*/
		
		/*
		PayBtn.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				currentScreen = "pay";
				showTableOption();
			}
		});
		
		
		
		backBtn = new JButton("Back");
		backBtn.setFont(new Font("Haettenschweiler", Font.BOLD, 24));
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				back();
			}
		});
		
		
		backBtn.setBounds(37, 5, 131, 77);
		utilityPanel.add(backBtn);
		
		this.orderPanel = new MenuPanel();
		orderPanel.setVisible(false);
		add(orderPanel);
		
		this.payPanel = new PayPanel();
		payPanel.setVisible(false);
		add(payPanel);
*/
		
		/*
		//create table
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new String[] {
				"Table Number", "Refill", "Help", "Order"
			}, 0
		));

		model = (DefaultTableModel) table.getModel();

		// add inventory list to rowss
		for (int i = 0; i < 20; i++) {
			model.addRow(new Object[] { 
				new Integer(i + 1), "N", "N", "N" 
			});
		}
		
		// Sets the table header and row font, as well as adjusts the row height.
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 18));
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setRowHeight(26);
		*/
		JTable table = createTable();
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
				String val = (String) table.getModel().getValueAt(row, col);
				table.getModel().setValueAt(val.equals("X") ? "O" : "X", row, col);
			}

		});
		
		scrollPane.setViewportView(table);
		
	}
	
	protected static JTable createTable() {
		
		Object[][] rows = new Object[20][4];

		for(int i = 0; i < 20; i++) {
			rows[i][0] = new Integer(i + 1);
			rows[i][1] = "X";
			rows[i][2] = "X";
			rows[i][3] = "X";
		}
		
		Object[] cols = new Object[]{
				"Table Number", "Refill", "Help", "Order"
		};
		
		JTable t = new JTable(rows, cols) {

			private static final long serialVersionUID = 1L;

			@Override
			public Class<?> getColumnClass(int column) {
				int col = convertColumnIndexToModel(column);
				if(col == 0) 
					return Integer.class;
				else if(col == 1 || col == 2 || col == 3)
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
	
	
	
	/*
	protected void showTableOption() {
		String option = (String) JFrameUtils.inputDialog("Table Chooser", "Enter the table number:");
		if(option == null || option.equals("null"))
			return;
		if(option.equals(""))
			return;
		int tableNum;
		try {
			tableNum = Integer.parseInt(option);
		} catch(NumberFormatException e) {
			JFrameUtils.showMessage("Table Chooser", "Invalid table entered, please try again.");
			return;
		}
		if(tableNum < 1 || tableNum > 20) {
			JFrameUtils.showMessage("Table Chooser", "Invalid table entered, please try again.");
			return;
		}
		if(currentScreen == "order")
		{
			openScreen("order");
		}
		else if(currentScreen == "pay")
		{
			openScreen("pay");
		}
		else if(currentScreen == "compensate")
		{
			openScreen("compensate");
		}
	}
*/

	/*
	protected void back() {
		switch(currentScreen) {
			case "order":
				this.orderPanel.setVisible(false);
				this.PayBtn.setVisible(true);
				this.OrderBtn.setVisible(true);
				this.CompBtn.setVisible(true);
				this.mainPanel.setVisible(true);
				this.backBtn.setVisible(false);
				this.utilityPanel.setVisible(false);
				currentScreen = "";
				break;
			case "pay":
				this.payPanel.setVisible(false);
				this.PayBtn.setVisible(true);
				this.OrderBtn.setVisible(true);
				this.CompBtn.setVisible(true);
				this.mainPanel.setVisible(true);
				this.backBtn.setVisible(false);
				this.utilityPanel.setVisible(false);
				currentScreen = "";
				break;
			case "compensate":
				this.PayBtn.setVisible(true);
				this.OrderBtn.setVisible(true);
				this.CompBtn.setVisible(true);
				this.mainPanel.setVisible(true);
				this.backBtn.setVisible(false);
				this.utilityPanel.setVisible(false);
				break;
				
		}
	}
	
public void openScreen(String type) {
	
	this.mainPanel.setVisible(false);
	this.OrderBtn.setVisible(false);
	this.CompBtn.setVisible(false);
	this.PayBtn.setVisible(false);
	this.backBtn.setVisible(true);
	this.utilityPanel.setVisible(true);
	currentScreen = ""+type;
	switch(type) {
		case "order":
			//take table number
			this.orderPanel.setVisible(true);
			this.orderPanel.getMenuItems();
			break;
		case "pay":
			//take table number
			this.payPanel.setVisible(true);
			break;
		case "compensate":
			break;
		}
	}
*/

	/*
@Override
public void tableChanged(TableModelEvent e) {
	// TODO Auto-generated method stub
	int row = e.getFirstRow();
    int column = e.getColumn();
    if (column == 1) {
        TableModel model = (TableModel) e.getSource();
        String columnName = model.getColumnName(column);
        Boolean checked = (Boolean) model.getValueAt(row, column);
        if (checked) {
        	//read in the customer data about refill
            System.out.println(columnName + ": " + true);
        } else {
        	//show the waiter the refill request
        	JFrameUtils.confirmDialog("Table Refill", "Enter the table number:");
            System.out.println(columnName + ": " + false);
        }
    }

}
*/

}