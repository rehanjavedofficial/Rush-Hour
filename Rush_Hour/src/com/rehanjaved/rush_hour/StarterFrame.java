/**
 * 
 */
package game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

/** 
 * @version 7/3/2017
 */
public class StarterFrame extends JFrame {

	private static final long serialVersionUID = -4597285777020176431L;
	private JPanel contentPane;
	private JTable table;
	private JLabel welcome, userLabel, locationTitle, cell1, cell2, cell3;
	private JComboBox<String> row1, row2, row3, cols1, cols2, cols3;
	private JButton details, submit, random;
	private JLabel rowTitle;
	private JScrollPane pane;
	private ArrayList<String[]> dataArray;
	private int index = 1;
	private GameObject[] go;
	private boolean[][] bools;
	private JLabel colsTtile;

	
	public StarterFrame() {
		setTitle("Game");
		
		dataArray = new ArrayList<String[]>();
		go = new GameObject[6];
		bools = new boolean[6][6];
		bools[2][5] = true;
		bools[2][0] = true;
		bools[2][1] = true;
		Cell[] ce = new Cell[2];
		ce[0] = new Cell(2,1);
		ce[0].setHead(true);
		ce[1] = new Cell(2,0);
		dataArray.add(new String[]{"Vehicle","(2,1)","(2,0)",""});
		go[0] = new GameObject(ce, Color.red, Orientation.HORIZONTAL);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(697, 350);
		setLocationRelativeTo(null);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Components();
		UpdateTable();
		Listeners();
		
	}


	/**
	 * To update table.
	 */
	private void UpdateTable() {
	
		try{
			contentPane.remove(pane);
		}catch(Exception e){}
		
		table = new JTable(dataArray.toArray(new String[][]{}), new String[]{"Type","Cell 1","Cell 2","Cell 3"});
		pane = new JScrollPane(table);
		table.setBounds(343, 142, 325, 161);
		pane.setBounds(343, 142, 325, 161);
		contentPane.add(pane);
	
	}


	/**
	 * Adding all listeners
	 */
	private void Listeners() {
		
		random.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				new MainFrame(true, null).setVisible(true);
				StarterFrame.this.dispose();
			
			}
		});
		
		details.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				Cell[] c;
				if(index < 3){
					c = new Cell[3];
				}else{
					c = new Cell[2];
				}
				c[0] = new Cell(row1.getSelectedIndex(), cols1.getSelectedIndex());
				c[1] = new Cell(row2.getSelectedIndex(), cols2.getSelectedIndex());
				
				if(c.length == 3)
					c[2] = new Cell(row3.getSelectedIndex(), cols3.getSelectedIndex());
				
				if(index > 2 && index < 6){
					
					if(!((c[0].getX() == c[1].getX() && Math.abs(c[0].getY() - c[1].getY()) == 1) || 
							(c[0].getY() == c[1].getY() && Math.abs(c[0].getX() - c[1].getX()) == 1))){
						JOptionPane.showMessageDialog(null, "It is not a valid position for vehicle. Try different.");
						return;
					}
					
				}else{
					
					int row = c[0].getX();
					int col = c[0].getY();
					if(!((row == c[1].getX() && row == c[2].getX() && (Math.abs(c[0].getY() - c[1].getY()) == 1 
							&& Math.abs(c[1].getY() - c[2].getY()) == 1)) || 
							(col == c[1].getY() && col == c[2].getY() && (Math.abs(c[0].getX() - c[1].getX()) == 1 
							&& Math.abs(c[1].getX() - c[2].getX()) == 1)))){
						JOptionPane.showMessageDialog(null, "It is not a valid position for vehicle. Try different.");
						return;
					}
					
				}
				
				for(int i = 0; i < c.length; i++)
					if(bools[c[i].getX()][c[i].getY()]){
						JOptionPane.showMessageDialog(null, "It is not a valid position. Try different.");
						return;
					}
				for(int i = 0; i < c.length; i++)
					bools[c[i].getX()][c[i].getY()] = true;
				
				Orientation orientation = Orientation.HORIZONTAL;
				
				if(c[0].getY() == c[1].getY())
					orientation = Orientation.VERTICAL;
				
				c[0].setHead(true);
				go[index] = new GameObject(c, index < 3 ? new Color(0,112,0) : Color.BLUE, orientation);
				dataArray.add(new String[]{index < 3 ? "Truck" : "Vehicle", "("+(c[0].getX()+1)+","+(c[0].getY()+1)+")",
						"("+(c[1].getX()+1)+","+(c[1].getY()+1)+")", index < 3 ? "("+(c[2].getX()+1)+","+(c[2].getY()+1)+")" : ""});
				
				updateStatus();
				
			}

		});
		
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				new MainFrame(false, go).setVisible(true);
				StarterFrame.this.dispose();
				
			}
		});
		
	}

	/**
	 * Updating current status.
	 */
	private void updateStatus() {
		
		index++;
		
		if(index > 5){
			details.setVisible(false);
			submit.setVisible(true);
		}
		
		if(index < 3)
			locationTitle.setText("Location for Truck 2");
		else if(index < 6){
			locationTitle.setText("Location for Vehicle "+(index-1));
			cell3.setVisible(false);
			row3.setVisible(false);
			cols3.setVisible(false);
		}else
			locationTitle.setText("All vehicles add, press submit to start game.");
		
		UpdateTable();
		
	}

	/**
	 * Initializing components and adding
	 * them to frame.
	 */
	private void Components() {
		
		welcome = new JLabel("Welcome, Please select initialization option");
		welcome.setHorizontalAlignment(SwingConstants.CENTER);
		welcome.setBounds(6, 6, 646, 16);
		contentPane.add(welcome);
		
		random = new JButton("Random Selection");
		random.setBackground(Color.WHITE);
		random.setBounds(6, 41, 164, 29);
		contentPane.add(random);
		
		userLabel = new JLabel("User Selection");
		userLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userLabel.setBounds(6, 80, 646, 16);
		contentPane.add(userLabel);
		
		locationTitle = new JLabel("Location for Truck 1");
		locationTitle.setBounds(16, 113, 675, 16);
		contentPane.add(locationTitle);
		
		cell1 = new JLabel("Cell 1(Head)");
		cell1.setBounds(16, 165, 97, 16);
		contentPane.add(cell1);
		
		row1 = new JComboBox<>();
		row1.setBounds(125, 161, 97, 27);
		contentPane.add(row1);
		
		cols1 = new JComboBox<>();
		cols1.setBounds(234, 161, 97, 27);
		contentPane.add(cols1);
		
		rowTitle = new JLabel("Row");
		rowTitle.setHorizontalAlignment(SwingConstants.CENTER);
		rowTitle.setBounds(125, 141, 97, 16);
		contentPane.add(rowTitle);
		
		colsTtile = new JLabel("Column");
		colsTtile.setHorizontalAlignment(SwingConstants.CENTER);
		colsTtile.setBounds(234, 141, 97, 16);
		contentPane.add(colsTtile);
		
		cell2 = new JLabel("Cell 2");
		cell2.setBounds(16, 193, 97, 16);
		contentPane.add(cell2);
		
		row2 = new JComboBox<>();
		row2.setBounds(125, 189, 97, 27);
		contentPane.add(row2);
		
		cols2 = new JComboBox<>();
		cols2.setBounds(234, 189, 97, 27);
		contentPane.add(cols2);
		
		cell3 = new JLabel("Cell 3");
		cell3.setBounds(16, 221, 97, 16);
		contentPane.add(cell3);
		
		row3 = new JComboBox<>();
		row3.setBounds(125, 217, 97, 27);
		contentPane.add(row3);
		
		cols3 = new JComboBox<>();
		cols3.setBounds(234, 217, 97, 27);
		contentPane.add(cols3);
		
		for(int i = 1; i < 7; i++){
			row1.addItem(String.valueOf(i));
			row2.addItem(String.valueOf(i));
			row3.addItem(String.valueOf(i));
			cols1.addItem(String.valueOf(i));
			cols2.addItem(String.valueOf(i));
			cols3.addItem(String.valueOf(i));
		}
		
		details = new JButton("Add Details");
		details.setBackground(Color.WHITE);
		details.setBounds(6, 249, 117, 29);
		contentPane.add(details);
		
		submit = new JButton("Submit User Selection");
		submit.setBounds(6, 274, 325, 29);
		submit.setVisible(false);
		contentPane.add(submit);
		
	}
	
}
