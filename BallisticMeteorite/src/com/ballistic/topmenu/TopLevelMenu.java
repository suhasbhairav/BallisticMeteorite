package com.ballistic.topmenu;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.TransferHandler;
import javax.swing.text.DefaultEditorKit;

import com.ballistic.actions.Listeners;
import com.ballistic.logging.LoggerMessage;
import com.ballistic.looks.FontType;

public class TopLevelMenu {

	static JMenuBar menuBar= null;
	
	static JMenu fileMenu = null;
	static JMenu editMenu = null;
	static JMenu executeMenu = null;
	static JMenu helpMenu = null;
	
	static JMenuItem newProject = null;
	static JMenuItem openProject = null;
	static JMenuItem saveProject = null;
	static JMenuItem quitProgram = null;

	static JMenuItem cutItem = null;
	static JMenuItem copyItem = null;
	static JMenuItem pasteItem = null;

	
	static JMenuItem runProject = null;
	
	static JMenuItem about = null;
	
	public TopLevelMenu(){
		try{
			menuBar = new JMenuBar();
			
			
			fileMenu = new JMenu("File");
			fileMenu.setFont(FontType.setTextFontForTopMenu());
			
			editMenu = new JMenu("Edit");
			editMenu.setFont(FontType.setTextFontForTopMenu());
			
			executeMenu = new JMenu("Execute");
			executeMenu.setFont(FontType.setTextFontForTopMenu());
			
			helpMenu = new JMenu("Help");
			helpMenu.setFont(FontType.setTextFontForTopMenu());
			
			newProject = new JMenuItem("New Project");			
			newProject.setActionCommand("New");
			newProject.addActionListener(new Listeners());
			newProject.setFont(FontType.setTextFontForTopMenu());
			
			openProject = new JMenuItem("Open Project");
			openProject.setActionCommand("Open");
			openProject.addActionListener(new Listeners());
			openProject.setFont(FontType.setTextFontForTopMenu());
			
			saveProject = new JMenuItem("Save Project");
			saveProject.setActionCommand("Save");
			saveProject.addActionListener(new Listeners());
			saveProject.setFont(FontType.setTextFontForTopMenu());
			
			quitProgram = new JMenuItem("Quit");
			quitProgram.setActionCommand("Quit");
			quitProgram.addActionListener(new Listeners());
			quitProgram.setFont(FontType.setTextFontForTopMenu());
			
			
			cutItem = new JMenuItem(new DefaultEditorKit.CutAction());
			cutItem.setText("Cut");			
			cutItem.setMnemonic(KeyEvent.VK_T);
			cutItem.setFont(FontType.setTextFontForTopMenu());
			
			
			
			copyItem = new JMenuItem(new DefaultEditorKit.CopyAction());
			copyItem.setText("Copy");		
			copyItem.setMnemonic(KeyEvent.VK_C);
			copyItem.setFont(FontType.setTextFontForTopMenu());
			
			
			pasteItem = new JMenuItem(new DefaultEditorKit.PasteAction());
			pasteItem.setText("Paste");			
			pasteItem.setMnemonic(KeyEvent.VK_P);
			pasteItem.setFont(FontType.setTextFontForTopMenu());
			
			runProject = new JMenuItem("Run");
			runProject.setActionCommand("Run");
			runProject.addActionListener(new Listeners());
			runProject.setFont(FontType.setTextFontForTopMenu());
			
			about = new JMenuItem("About BallisticMeteorite");
			about.setActionCommand("About");
			about.addActionListener(new Listeners());
			about.setFont(FontType.setTextFontForTopMenu());
			
		}
		catch(Exception e){
			LoggerMessage.printLog(TopLevelMenu.class.getName(), e.getMessage());
		}
	}
	
	
	public JMenuBar addMenuBar(){
		try{
			menuBar.add(appendFileMenu());
			menuBar.add(appendEditMenu());
			menuBar.add(appendExecuteMenu());
			menuBar.add(appendAboutMenu());
		}catch(Exception e){
			LoggerMessage.printLog(TopLevelMenu.class.getName(), e.getMessage());
		}
		return menuBar;
	}
	
	public JMenu appendFileMenu(){
		try{
			fileMenu.add(newProject);
			fileMenu.add(openProject);
			fileMenu.add(saveProject);
			fileMenu.add(quitProgram);
		}catch(Exception e){
			LoggerMessage.printLog(TopLevelMenu.class.getName(), e.getMessage());
		}
		return fileMenu;
	}
	
	public JMenu appendEditMenu(){
		try{
			editMenu.add(cutItem);
			editMenu.add(copyItem);
			editMenu.add(pasteItem);
			
		}catch(Exception e){
			LoggerMessage.printLog(TopLevelMenu.class.getName(), e.getMessage());
		}
		return editMenu;
	}
	
	public JMenu appendExecuteMenu(){
		try{
			executeMenu.add(runProject);
		}catch(Exception e){
			LoggerMessage.printLog(TopLevelMenu.class.getName(), e.getMessage());
		}
		return executeMenu;
	}
	
	public JMenu appendAboutMenu(){
		try{
			helpMenu.add(about);
		}catch(Exception e){
			LoggerMessage.printLog(TopLevelMenu.class.getName(), e.getMessage());
		}
		return helpMenu;
	}
	

	
}
