package com.ballistic.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;

import com.ballistic.logging.LoggerMessage;
import com.ballistic.text.ProgrammingArea;

public class Listeners implements ActionListener{

	public Listeners(){
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String action = e.getActionCommand();
		switch(action.toUpperCase()){
			case "SAVE":
				JFileChooser saveProgramFile = new JFileChooser();
				saveProgramFile.showSaveDialog(null);
				File filename = saveProgramFile.getSelectedFile();
				saveContentsToFile(filename.getPath());
				break;		
			case "OPEN":
				JFileChooser openProgramFile = new JFileChooser();
				openProgramFile.showOpenDialog(null);
				break;
		}
	}
	
	public void saveContentsToFile(String filename){
		try{
			@SuppressWarnings("resource")
			BufferedWriter fileSave = new BufferedWriter(new FileWriter(filename));
			fileSave.write(ProgrammingArea.getProgramContent());
		}catch(Exception e){
			LoggerMessage.printLog(Listeners.class.getName(), e.getMessage());
		}
	}
	
	
}
