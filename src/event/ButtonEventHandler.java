package event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;

import component.Board;

public class ButtonEventHandler implements ActionListener {
    private Vector<JButton> buttonList;
    	
    public ButtonEventHandler(Vector<JButton> buttonList) {

       this.buttonList = buttonList;
   }

   public void actionPerformed(ActionEvent event) {
	   JButton button = buttonList.get(buttonList.indexOf(event.getSource()));
	   if (button.getText() == "-") {
		   if(Board.isX) {
			   button.setText("X");	
			   Board.isX = false;
		   } else {
			   button.setText("O");			 
			   Board.isX = true;			   
		   }
		   Board.check();		   
	   }
    }
}
