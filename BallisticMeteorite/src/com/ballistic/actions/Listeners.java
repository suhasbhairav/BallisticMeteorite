package com.ballistic.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFileChooser;

import com.ballistic.execution.ProcessExecution;
import com.ballistic.logging.LoggerMessage;
import com.ballistic.mainui.WindowUI;
import com.ballistic.text.OutputConsole;
import com.ballistic.text.ProgrammingArea;
import com.ballistic.windows.About;

public class Listeners implements ActionListener{

	private static String content = null;
	private static File filename = null;
	private JComponent focusOwnerComponent = null;
	
	public Listeners(){
	
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {		
		if(e!=null){
			String action = e.getActionCommand();
			
			switch(action.toUpperCase()){
				case "NEW":
					if(filename!=null){
						saveContentsToFile(filename.getPath());
					}
					filename = null;
					ProgrammingArea.clearProgramContent();
					OutputConsole.clearProgramOutput();
					break;
				case "SAVE":
					if(filename==null){
						JFileChooser saveProgramFile = new JFileChooser();
						saveProgramFile.showSaveDialog(null);
						if(saveProgramFile.getSelectedFile()!=null){
							filename = saveProgramFile.getSelectedFile();				
							//saveContentsToFile(filename.getPath());
						}
					}								
					saveContentsToFile(filename.getPath());
					
					//resetFilename();
					break;
					
				case "OPEN":
					JFileChooser openProgramFile = new JFileChooser();
					openProgramFile.showOpenDialog(null);
					if(openProgramFile.getSelectedFile()!=null){
						filename = openProgramFile.getSelectedFile();
						boolean bOpen = loadContentsFromFile(filename.getPath());				
						if(bOpen){
							ProgrammingArea.setProgramContent(content);
						}else{
							LoggerMessage.printLog(Listeners.class.getName(), "Error in loading contents of the file");
						}
					}
					//resetFilename();
					break;
				
				case "RUN":					
					if(filename == null){
						try{
						JFileChooser saveNewProgramFile = new JFileChooser();
						saveNewProgramFile.showSaveDialog(null);											
						if(saveNewProgramFile.getSelectedFile()!=null){								
								filename = saveNewProgramFile.getSelectedFile();							
								saveContentsToFile(filename.getPath());
								try{
									ProcessExecution.executeFile(filename.getPath());
								}catch(Exception e1){
									LoggerMessage.printLog(Listeners.class.getName(), e1.getMessage());
								}
							
							}
						}catch(Exception e2){
							System.out.println(e2.getLocalizedMessage());
						}
					}else{
						try{
							saveContentsToFile(filename.getPath());
							ProcessExecution.executeFile(filename.getPath());
						}catch(Exception e1){
							LoggerMessage.printLog(Listeners.class.getName(), e1.getMessage());
						}
					}
					
					
					break;
				
				case "QUIT":
					if(filename!=null){
						saveContentsToFile(filename.getPath());
					}
					WindowUI.closeProgram();					
					break;
				
				case "ABOUT":
					try{
					About about = new About();
					}catch(Exception eAbout){
						LoggerMessage.printLog(Listeners.class.getName(), eAbout.getMessage());
					}
					break;
			}
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
	
	
	public void resetFilename(){
		filename = null;
	}

	
}
