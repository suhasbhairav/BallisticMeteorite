package com.ballistic.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import com.ballistic.execution.ProcessExecution;
import com.ballistic.logging.LoggerMessage;
import com.ballistic.text.ProgrammingArea;

public class Listeners implements ActionListener{

	private static String content = null;
	private static File filename = null;
	
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
				filename = saveProgramFile.getSelectedFile();				
				saveContentsToFile(filename.getPath());
				break;
				
			case "OPEN":
				JFileChooser openProgramFile = new JFileChooser();
				openProgramFile.showOpenDialog(null);
				filename = openProgramFile.getSelectedFile();
				boolean bOpen = loadContentsFromFile(filename.getPath());				
				if(bOpen){
					ProgrammingArea.setProgramContent(content);
				}else{
					LoggerMessage.printLog(Listeners.class.getName(), "Error in loading contents of the file");
				}
				break;
			
			case "RUN":
				saveContentsToFile(filename.getPath());
				try{
					ProcessExecution.executeFile(filename.getPath());
				}catch(Exception e1){
					LoggerMessage.printLog(Listeners.class.getName(), e1.getMessage());
				}
				
				break;
		}
	}
	
	public void saveContentsToFile(String filename){
		try(BufferedWriter fileSave = new BufferedWriter(new FileWriter(filename))){			
			fileSave.write(ProgrammingArea.getProgramContent());
			
		}catch(Exception e){
			LoggerMessage.printLog(Listeners.class.getName(), e.getMessage());
		}
	}
	
	public boolean loadContentsFromFile(String filename){
		try(BufferedReader reader = new BufferedReader(new FileReader(filename)))
		{
			String line = null;
			
			ArrayList<String> arrayList = new ArrayList<String>();
			ProgrammingArea.clearProgramContent();
			while((line = reader.readLine())!= null){				
				arrayList.add(line);				
			}			
			for(int i=0;i<arrayList.size();i++){				
				ProgrammingArea.setProgramContent(arrayList.get(i));				
			}			
		}catch(Exception e){
			LoggerMessage.printLog(Listeners.class.getName(), e.getMessage());
			return false;
		}
		return true;
	}
	
	
}
