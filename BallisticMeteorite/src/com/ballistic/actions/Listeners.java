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
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import com.ballistic.execution.ProcessExecution;
import com.ballistic.logging.LoggerMessage;
import com.ballistic.mainui.WindowUI;
import com.ballistic.text.NewProjectTab;
import com.ballistic.windows.About;

public class Listeners implements ActionListener{

	private static String content = null;
	private static File filename = null;
	private JComponent focusOwnerComponent = null;
	private static  String stringContent = "";
	private static int noOfTabs = 0;
	public Listeners(){
	
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {		
		if(e!=null){
			String action = e.getActionCommand();
			
			switch(action.toUpperCase()){
				case "NEW":					
					filename = null;
					WindowUI.addNewProgrammingTab("");
					WindowUI.getTabbedPane().setTabComponentAt(WindowUI.getTabbedPane().getTabCount()-1, null);
					WindowUI.initTabComponent(WindowUI.getTabbedPane().getTabCount()-1);
					break;
				case "SAVE":
					if(WindowUI.getTabbedPane().getTabCount() > 0){
						if(WindowUI.getTabName(WindowUI.getTabbedPane().getSelectedIndex()).endsWith(".py") == false){
							JFileChooser saveProgramFile = new JFileChooser();
							saveProgramFile.showSaveDialog(null);											
							if(saveProgramFile.getSelectedFile()!=null){
									
									filename = saveProgramFile.getSelectedFile();				
									saveContentsToFile(filename.getPath());
								}
						}
						else{				
							String tabName = WindowUI.getTabName(WindowUI.getTabbedPane().getSelectedIndex());
							File tempFile = new File(tabName);
							filename = tempFile;
							saveContentsToFile(filename.getPath());
						}
					}
					break;
					
				case "OPEN":
					JFileChooser openProgramFile = new JFileChooser();
					openProgramFile.showOpenDialog(null);
					if(openProgramFile.getSelectedFile()!=null){
						filename = openProgramFile.getSelectedFile();
						stringContent = "";
						boolean bOpen = loadContentsFromFile(filename.getPath());				
						if(bOpen){
							NewProjectTab.openProgrammingProject(stringContent, filename.getPath());
						}else{
							LoggerMessage.printLog(Listeners.class.getName(), "Error in loading contents of the file");
						}
					}
				
					break;
				
				case "RUN":
					if(WindowUI.getTabbedPane().getTabCount() > 0){
						//if(filename == null){
						if(WindowUI.getTabName(WindowUI.getTabbedPane().getSelectedIndex()).endsWith(".py") == false){
								
						File tempFile = null;
							
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
							}catch(Exception e1){
								e1.printStackTrace();
							
							}
						}else{
							
							String tabName = WindowUI.getTabName(WindowUI.getTabbedPane().getSelectedIndex());
							File tempFile = new File(tabName);
							filename = tempFile;
							saveContentsToFile(filename.getPath());	
							try{
								ProcessExecution.executeFile(filename.getPath());
							}catch(Exception e1){
								LoggerMessage.printLog(Listeners.class.getName(), e1.getMessage());
							}
							
						}
						
					}
					break;
				
				case "QUIT":
					if(filename!=null){
						saveContentsToFile(filename.getPath());
					}
					WindowUI.closeProgram();					
					break;
				
				case "SELECT ALL":
						if(WindowUI.getTabbedPane().getTabCount() > 0){
							NewProjectTab.selectAllProgramContent();
						}
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
			if(filename!=null){
				updateTabName();
			}
			
			fileSave.write(NewProjectTab.getProgramContent());
			
		}catch(Exception e){
			LoggerMessage.printLog(Listeners.class.getName(), e.getMessage());
		}
	}
	
	public boolean loadContentsFromFile(String filename){
		try(BufferedReader reader = new BufferedReader(new FileReader(filename)))
		{
			String line = null;
			
			ArrayList<String> arrayList = new ArrayList<String>();
			
			while((line = reader.readLine())!= null){				
				arrayList.add(line);				
			}
			
			for(int i=0;i<arrayList.size();i++){				
				stringContent = stringContent + arrayList.get(i);
				stringContent = stringContent + "\n";
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

	protected int incrementTabCount(){
		return noOfTabs++;
	}
	
	protected void updateTabName(){
		SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run(){
	        	if(Listeners.filename!=null){
	        	WindowUI.getTabbedPane().setTitleAt(WindowUI.getTabbedPane().getSelectedIndex(), Listeners.filename.getPath());
	        	WindowUI.getTabbedPane().setToolTipTextAt(WindowUI.getTabbedPane().getSelectedIndex(), Listeners.filename.getPath());
	        	}
			
	       }
	    });
		
	}
	
}
