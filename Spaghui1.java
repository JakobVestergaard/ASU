import java.awt.EventQueue;
//import java.io.*;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.border.CompoundBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Spaghui1 implements TableModelListener {

	static boolean newStuff = false;

	private JFrame Beertab;
	private JTable Beertab_table;
	private DefaultTableModel Model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		newStuff = false;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Spaghui1 window = new Spaghui1();
					window.Beertab.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Spaghui1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// Initialize jframe
		Beertab = new JFrame("Beertab");
		Beertab.setMinimumSize(new Dimension(1000, 500));

		// Jscrollpane
		JScrollPane scrollPane = new JScrollPane();
		Beertab.getContentPane().add(scrollPane, BorderLayout.CENTER);
		Model = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			// Restricts typing in the counting part of the table
			public boolean isCellEditable(int row, int column) {
				if (row == 0) {
					return true;
				} else if (column == 0) {
					return true;
				} else {
					return false;
				}
			};
		};

		Beertab_table = new JTable();
		Beertab_table.setName("Beertab");

		Beertab_table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				int row = Beertab_table.rowAtPoint(e.getPoint());
				int col = Beertab_table.columnAtPoint(e.getPoint());

				if (e.getButton() == MouseEvent.BUTTON1 && row != 0 && col != 0) {
					Object count = Beertab_table.getValueAt(row, col);

					// If the cell contains nothing
					if (count == null) {
						int intcount = 0;
						Beertab_table.setValueAt((Object) intcount, row, col);
					}

					// Count up if cell contains something
					else {
						int intcount = (int) count;
						intcount++;
						Beertab_table.setValueAt((Object) intcount, row, col);
					}
				}

				else if (e.getButton() == MouseEvent.BUTTON3 && row != 0 && col != 0) {
					Object count = Beertab_table.getValueAt(row, col);

					// If the cell contains nothing ignore it
					if (count == null) {
						int intcount = 0;
						Beertab_table.setValueAt((Object) intcount, row, col);
					}

					// subtract if cell contains something
					else {
						int intcount = (int) count;
						if (intcount > 0) {
							intcount--;
							Beertab_table.setValueAt((Object) intcount, row, col);
						}
					}
				}
			}
		});

		// Inserts dummy inputs
		Model.addColumn("First");
		Model.addColumn("Second");
		Model.addRow(new Object[] { "Kunde \\ Drikkevare", "Drikkevare" });
		Model.addRow(new Object[] { "Kunde" });

		Model.addTableModelListener(this);

		Beertab_table.setModel(Model);
		Beertab_table.setColumnSelectionAllowed(true);
		Beertab_table.setCellSelectionEnabled(true);
		scrollPane.setColumnHeaderView(Beertab_table);
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		if (!newStuff) {
			newStuff = true;
			// TODO Auto-generated method stub
			int row = e.getFirstRow();
			int column = e.getColumn();
			// TableModel model = (TableModel) e.getSource();
			// String columnName = model.getColumnName(column);
			// Object data = model.getValueAt(row, column);

			// If a column is edited, add a new blank one
			if (row == 0) {
				Model.addColumn("");
			}

			// If a row is edited, add a new blank one
			else if (column == 0) {
				Model.addRow(new Object[] { "" });
			}
		} else {
			newStuff = false;
		}
	}

}
