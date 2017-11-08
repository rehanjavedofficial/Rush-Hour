/**
 * 
 */
package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/** 
 * @version 7/3/2017
 */
public class MainFrame extends JFrame {

	// Attributes.
	private static final long serialVersionUID = 8804703186899321071L;
	private JPanel contentPane;
	private JLabel[][] cells;
	private JLabel letsPlay, rLabel, colsLabel, vLabel;
	private JComboBox<String> vehicle, row, column;
	private JButton execute;
	private GameObject[] vehicles;
	private JLabel lblThisRowAnd;
	private int moves = 0;

	public MainFrame(boolean random, GameObject[] gameObjects) {
		
		this.vehicles = gameObjects;
		cells = new JLabel[6][6];
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(656, 450);
		setResizable(false);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Components();
	
		if(random){
			setRandomSelection();
		}else{
			
			Cell[] c;
			for(int i = 0; i < gameObjects.length; i++){
				
				c = gameObjects[i].getCellLocations();
				for(int j = 0; j < c.length; j++){
					
					if(c[j].isHead())
						this.cells[c[j].getX()][c[j].getY()].setText((i+1)+" (H)");
					else
						this.cells[c[j].getX()][c[j].getY()].setText((i+1)+"");
					this.cells[c[j].getX()][c[j].getY()].setBackground(gameObjects[i].getColor());
				}
				
			}
			
		}
		
		execute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				moves++;
				if(moves > 10){
					JOptionPane.showMessageDialog(null, "You Lose after 10 moves!\nGame Ends");
					System.exit(0);
				}
				
				int vindex = vehicle.getSelectedIndex();
				int row = MainFrame.this.row.getSelectedIndex();
				int cols = MainFrame.this.column.getSelectedIndex();
				
				if(vehicles[vindex].getOrientation() == Orientation.HORIZONTAL){
					
					if(row == vehicles[vindex].getCellLocations()[0].getX()){
						int difference = vehicles[vindex].getCellLocations()[0].getY() - cols;
						Cell[] newLocation = vehicles[vindex].getCellLocations();
						JLabel[][] labels = new JLabel[6][6];
						for(int i = 0; i < labels.length; i++)
							for(int j = 0; j < labels[0].length; j++)
								labels[i][j] = cells[i][j];
						for(int i = 0; i < newLocation.length; i++){
							labels[newLocation[i].getX()][newLocation[i].getY()].setText("");
							labels[newLocation[i].getX()][newLocation[i].getY()].setBackground(new Color(204, 204, 204));
						}
						for(int i = 0; i < newLocation.length; i++)
							newLocation[i].setY(newLocation[i].getY() - difference);
						if(validLocationIndexes(labels, newLocation, vindex)){
							return;
						}
					}
					
				}else{
					
					if(cols == vehicles[vindex].getCellLocations()[0].getY()){
						int difference = vehicles[vindex].getCellLocations()[0].getX() - row;
						Cell[] newLocation = vehicles[vindex].getCellLocations();
						JLabel[][] labels = new JLabel[6][6];
						for(int i = 0; i < labels.length; i++)
							for(int j = 0; j < labels[0].length; j++)
								labels[i][j] = cells[i][j];
						for(int i = 0; i < newLocation.length; i++){
							labels[newLocation[i].getX()][newLocation[i].getY()].setText("");
							labels[newLocation[i].getX()][newLocation[i].getY()].setBackground(new Color(204, 204, 204));
						}
						for(int i = 0; i < newLocation.length; i++)
							newLocation[i].setX(newLocation[i].getX() - difference);
						if(validLocationIndexes(labels, newLocation, vindex)){
							return;
						}
					}
					
				}
				
				JOptionPane.showMessageDialog(null, "Wrong Move..");
				
			}

		});
		
	}
	
	private boolean validLocationIndexes(JLabel[][] labels, Cell[] newLocation, int vindex) {
	
		boolean value = true;
		Cell c;
		for(int i = 0; i < newLocation.length; i++){
			
			c = newLocation[i];
			if(!(c.getX() >= 0 && c.getX() < 6 && c.getY() >= 0 && c.getY() < 6
					&& (this.cells[c.getX()][c.getY()].getBackground().equals(new Color(204, 204, 204))
							|| this.cells[c.getX()][c.getY()].getBackground().equals(Color.RED)))){
				value = false;
				break;
			}
			
		}
		
		if(value){
			for(int j = 0; j < vehicles[vindex].size(); j++){
				Cell cc = newLocation[j];
				if(j == 0)
					labels[cc.getX()][cc.getY()].setText((vindex+1)+" (H)");
				else
					labels[cc.getX()][cc.getY()].setText((vindex+1)+"");
				labels[cc.getX()][cc.getY()].setBackground(vehicles[vindex].getColor());
			}
			this.cells = labels;
			if(this.cells[2][5].getBackground().equals(Color.red) && !this.cells[2][5].getText().equals("Exit")){
				
				JOptionPane.showMessageDialog(null, "Congratulations!, You Win!\nGame Ends");
				System.exit(0);
				
			}
		}
		
		return value;
	
	}


	/**
	 * Initializing the components.
	 */
	private void Components() {
		
		letsPlay = new JLabel("Let's Play");
		letsPlay.setHorizontalAlignment(SwingConstants.CENTER);
		letsPlay.setBounds(6, 6, 644, 16);
		contentPane.add(letsPlay);
		
		vLabel = new JLabel("Vehicle:");
		vLabel.setBounds(6, 47, 61, 16);
		contentPane.add(vLabel);
		
		vehicle = new JComboBox<>();
		vehicle.setBounds(79, 43, 83, 27);
		contentPane.add(vehicle);
		
		rLabel = new JLabel("Row:");
		rLabel.setBounds(174, 47, 44, 16);
		contentPane.add(rLabel);
		
		row = new JComboBox<>();
		row.setBounds(230, 43, 83, 27);
		contentPane.add(row);
		
		colsLabel = new JLabel("Column:");
		colsLabel.setBounds(335, 47, 61, 16);
		contentPane.add(colsLabel);
		
		column = new JComboBox<>();
		column.setBounds(408, 43, 83, 27);
		contentPane.add(column);
		
		execute = new JButton("Execute");
		execute.setForeground(Color.BLACK);
		execute.setBackground(Color.WHITE);
		execute.setBounds(503, 42, 117, 29);
		contentPane.add(execute);
		
		lblThisRowAnd = new JLabel("This row and column represents the head of vehicle");
		lblThisRowAnd.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		lblThisRowAnd.setBounds(200, 26, 252, 16);
		contentPane.add(lblThisRowAnd);
		
		int x = 174, y = 97;
		for(int i = 0; i < cells.length; i++){
		
			for(int j = 0; j < cells[i].length; j++){
				
				cells[i][j] = new JLabel("");
				cells[i][j].setOpaque(true);
				cells[i][j].setForeground(Color.white);
				cells[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				cells[i][j].setBackground(new Color(204, 204, 204));
				cells[i][j].setBounds(x+(j*50), y, 50, 50);
				x += 1;
				contentPane.add(cells[i][j]);
			}
			x = 174;
			y += 51;
		}
		
		cells[2][5].setText("Exit");
		cells[2][5].setBackground(Color.red);
		
		for(int i = 1; i <= 6; i++){
			vehicle.addItem(String.valueOf(i));
			row.addItem(String.valueOf(i));
			column.addItem(String.valueOf(i));
		}
		
	}


	/**
	 * Randomly place vehicles on map.
	 */
	private void setRandomSelection() {
		
		Random random = new Random();
		vehicles = new GameObject[6];
		
		Cell[] cc = new Cell[2];
		cc[0] = new Cell(2,1);
		cc[1] = new Cell(2,0);
		Orientation or = Orientation.HORIZONTAL;
		vehicles[0] = new GameObject(cc, Color.red, or); 
		
		for(int j = 0; j < vehicles[0].size(); j++){
			
			if(j == 0)
				cells[cc[j].getX()][cc[j].getY()].setText("1 (H)");
			else
				cells[cc[j].getX()][cc[j].getY()].setText("1");
			cells[cc[j].getX()][cc[j].getY()].setBackground(Color.red);
		}
		
		for(int i = 1; i < 6; i++){
			
			int x,y,z;
			
			while(true){
				
				x = random.nextInt(6);
				y = random.nextInt(6);
				z = random.nextInt(4);
				
				vehicles[i] = getGameObject(x,y,z,i);
				if(vehicles[i] == null)
					continue;
				else{
					Cell[] cs = vehicles[i].getCellLocations();
					for(int j = 0; j < vehicles[i].size(); j++){
						
						if(j == 0)
							cells[cs[j].getX()][cs[j].getY()].setText((i+1)+" (H)");
						else
							cells[cs[j].getX()][cs[j].getY()].setText((i+1)+"");
						cells[cs[j].getX()][cs[j].getY()].setBackground(vehicles[i].getColor());
					}
					break;
				}
				
			}
			
			
			
		}
		
	}
	
	private GameObject getGameObject(int x, int y, int z, int index){
		
		int size  = index < 4 ? 2 : 3;
		Cell[] cells = new Cell[size];
		boolean done = false;
		Orientation or = Orientation.HORIZONTAL;
		switch(z){
		
		case 0:
			if(x-size >= 0){
				for(int i = 0; i < size; i++)
					cells[i] = new Cell(x--, y);
				done = true;
			    or = Orientation.VERTICAL;
			}
			break;
		case 1:
			if(y+size < 6){
				for(int i = 0; i < size; i++)
					cells[i] = new Cell(x, y++);
				done = true;
				or = Orientation.HORIZONTAL;
			}
			break;
		case 2:
			if(x+size < 6){
				for(int i = 0; i < size; i++)
					cells[i] = new Cell(x++, y);
				done = true;
				or = Orientation.VERTICAL;
			}
			break;
		case 3:
			if(y-size >= 0){
				for(int i = 0; i < size; i++)
					cells[i] = new Cell(x, y--);
				done = true;
				or = Orientation.HORIZONTAL;
			}
			break;
		
		}
		
		if(!done)
			return null;
		
		for(int i = 0; i < cells.length; i++){
			if(!this.cells[cells[i].getX()][cells[i].getY()].getBackground().equals(new Color(204, 204, 204)))
				return null;
		}
		
		
		return new GameObject(cells, size == 3 ? new Color(0,112,0) : Color.BLUE, or);
		
	}
	 
}
