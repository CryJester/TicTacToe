package component;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;

import event.ButtonEventHandler;

public class Board extends JFrame{

	private static final long serialVersionUID = -1438872763722100162L;
	static final String size[] = {"3", "5"};
    static Board frame;
    static ButtonEventHandler handler;    
    static int boardSize = 3;
    static Vector<JButton> buttonList = new Vector<JButton>();
    static JLabel result = new JLabel("-");
    GridLayout experimentLayout = new GridLayout(0,3);    
    JButton applyButton = new JButton("Change Board Size");
    JComboBox<Object> sizeComboBox;    
    
    public static boolean isX = true;        
    
    public Board(String name) {
        super(name);
        setResizable(false);
    }
    
	public void initSize() {
        sizeComboBox = new JComboBox<Object>(size);
    }
	
	public static void check() {
		if (boardSize == 3) {
			//Check column
			for(int i=0; i<boardSize; i++) {
				if (buttonList.get(i).getText().equals(buttonList.get(i+boardSize).getText()) &&
						buttonList.get(i+boardSize).getText().equals(buttonList.get(i+boardSize*2).getText()) &&
						buttonList.get(i).getText() != "-") {					
					result.setText(buttonList.get(i).getText() + " is the winner");
				}
			}		
			
			//Check row
			for(int i=0; i<boardSize; i++) {
				if (buttonList.get(boardSize*i).getText().equals(buttonList.get(boardSize*i+1).getText()) &&
						buttonList.get(boardSize*i+1).getText().equals(buttonList.get(boardSize*i+2).getText()) &&
						buttonList.get(boardSize*i).getText() != "-") {					
					result.setText(buttonList.get(boardSize*i).getText() + " is the winner");
				}
			}
			
			//Check diagonal
			if (buttonList.get(0).getText().equals(buttonList.get(0+boardSize+1).getText()) &&
					buttonList.get(0+boardSize+1).getText().equals(buttonList.get(0+boardSize*2+2).getText()) &&
					buttonList.get(0).getText() != "-") {				
				result.setText(buttonList.get(0).getText() + " is the winner");
			}
			
			//Check anti-diagonal
			if (buttonList.get(boardSize-1).getText().equals(buttonList.get(boardSize*2-2).getText()) &&
					buttonList.get(boardSize*2-2).getText().equals(buttonList.get(boardSize*3-3).getText()) &&
					buttonList.get(boardSize-1).getText() != "-") {				
				result.setText(buttonList.get(boardSize-1).getText() + " is the winner");
			}			
		} else {
			//Check column
			for(int i=0; i<boardSize; i++) {
				if (buttonList.get(i).getText().equals(buttonList.get(i+boardSize).getText()) &&
						buttonList.get(i+boardSize).getText().equals(buttonList.get(i+boardSize*2).getText()) &&
						buttonList.get(i+boardSize*2).getText().equals(buttonList.get(i+boardSize*3).getText()) &&
						buttonList.get(i+boardSize*3).getText().equals(buttonList.get(i+boardSize*4).getText()) &&
						buttonList.get(i).getText() != "-") {				
					result.setText(buttonList.get(i).getText() + " is the winner");
				}
			}	
			
			//Check row
			for(int i=0; i<boardSize; i++) {
				if (buttonList.get(boardSize*i).getText().equals(buttonList.get(boardSize*i+1).getText()) &&
						buttonList.get(boardSize*i+1).getText().equals(buttonList.get(boardSize*i+2).getText()) &&
						buttonList.get(boardSize*i+2).getText().equals(buttonList.get(boardSize*i+3).getText()) &&
						buttonList.get(boardSize*i+3).getText().equals(buttonList.get(boardSize*i+4).getText()) &&						
						buttonList.get(boardSize*i).getText() != "-") {					
					result.setText(buttonList.get(boardSize*i).getText() + " is the winner");
				}
			}
			
			//Check diagonal			
			if (buttonList.get(0).getText().equals(buttonList.get(0+boardSize+1).getText()) &&
					buttonList.get(0+boardSize+1).getText().equals(buttonList.get(0+boardSize*2+2).getText()) &&
					buttonList.get(0+boardSize*2+2).getText().equals(buttonList.get(0+boardSize*3+3).getText()) &&
					buttonList.get(0+boardSize*3+3).getText().equals(buttonList.get(0+boardSize*4+4).getText()) &&					
					buttonList.get(0).getText() != "-") {				
				result.setText(buttonList.get(0).getText() + " is the winner");
			}
			
			//Check anti-diagonal
			if (buttonList.get(boardSize-1).getText().equals(buttonList.get(boardSize*2-2).getText()) &&
					buttonList.get(boardSize*2-2).getText().equals(buttonList.get(boardSize*3-3).getText()) &&
					buttonList.get(boardSize*3-3).getText().equals(buttonList.get(boardSize*4-4).getText()) &&
					buttonList.get(boardSize*4-4).getText().equals(buttonList.get(boardSize*5-5).getText()) &&					
					buttonList.get(boardSize-1).getText() != "-") {				
				result.setText(buttonList.get(boardSize-1).getText() + " is the winner");
			}			
		}
	}
	
    public void addComponentsToPane(final Container pane) {
    	initSize();
        final JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(experimentLayout);
        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(2,2));
        
        //Set up components preferred size
        compsToExperiment.setSize(new Dimension(90, 90));
        
        //Add buttons to Grid Layout
    	for(int i=1; i<=9; i++) {
    		buttonList.add(new JButton("-"));
            compsToExperiment.add(buttonList.lastElement());                		
    	}     
        handler = new ButtonEventHandler(buttonList);
        for (JButton buttons : buttonList) {
            buttons.addActionListener(handler);
        }    	
        
        //Add footer 
        controls.add(new Label("Board Size:"));
        controls.add(result);
        controls.add(sizeComboBox);        
        controls.add(applyButton);

        //Process the change board size button press
        applyButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	String size = (String)sizeComboBox.getSelectedItem();
        		isX = true;            	
            	buttonList.clear();        
            	result.setText("-");
            	
            	if(size == "3") {
            		boardSize = 3;   
                	experimentLayout = new GridLayout(0,3);
                	compsToExperiment.setLayout(experimentLayout);
                	compsToExperiment.removeAll();
                	compsToExperiment.setSize(new Dimension(90, 90));

            	} else {
            		boardSize = 5;     
                	experimentLayout = new GridLayout(0,5);
                	compsToExperiment.setLayout(experimentLayout);
                	compsToExperiment.removeAll();
                	compsToExperiment.setSize(new Dimension(150, 150));
            	}
            	
            	for(int i=1; i<=Math.pow(boardSize, 2); i++) {
            		buttonList.add(new JButton("-"));
                    compsToExperiment.add(buttonList.lastElement());   
                    handler = new ButtonEventHandler(buttonList);
                    for (JButton buttons : buttonList) {
                        buttons.addActionListener(handler);
                    }                         
                }                	
            	
            	frame.pack();
            }
        });
        
        pane.add(compsToExperiment, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(controls, BorderLayout.SOUTH);
    }        
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
     */
    public static void createAndShowGUI() {
        //Create and set up the window.
    	frame = new Board("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
