package com.ballistic.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Listeners implements ActionListener{

	public Listeners(){
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String action = e.getActionCommand();
		switch(action.toUpperCase()){
		case "SAVE":
			
			break;
		}
	}
	
	
}
