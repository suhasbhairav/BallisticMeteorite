package com.ballistic.mainui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.ballistic.logging.LoggerMessage;
import com.ballistic.looks.Windows;
import com.ballistic.text.ButtonTabComponent;
import com.ballistic.text.NewProjectTab;
import com.ballistic.text.OutputConsole;
import com.ballistic.topmenu.IconToolbar;
import com.ballistic.topmenu.TopLevelMenu;

public class WindowUI {
	
	private static JFrame jframeWindow = null;
	private static String windowUIName = "Ballistic Meteorite";
	private static JPanel programmingAreaPanel = null;
	private static JTabbedPane tabbedPane = null;
	private static int indexOfTabs = 0;
	private static Container container = null;
	private static int WIDTH = 400;
	private static int HEIGHT = 400;
	
	public WindowUI(){
		
	}
	
	
	private static void createAndShowWindowUI(){
		try{		
			jframeWindow = new JFrame();		
			jframeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			jframeWindow.setTitle(windowUIName);
			jframeWindow.setBackground(Windows.mainScreenColor());
			
			
			jframeWindow.setMinimumSize(new Dimension(WIDTH, HEIGHT));
			jframeWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
			
			container = jframeWindow.getContentPane();
			container.setLayout(new BorderLayout());
			
			TopLevelMenu topLevelMenu = new TopLevelMenu();			
			tabbedPane = new JTabbedPane();
			tabbedPane.setBackground(Windows.tabColor());
			
			tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
			addNewProgrammingTab("");
			
			tabbedPane.setTabComponentAt(0, null);
			initTabComponent(0);
			OutputConsole outputConsole = new OutputConsole();
			container.add(outputConsole.createOutputPanel(), BorderLayout.SOUTH);
			
			IconToolbar iconToolBar = new IconToolbar();			
			container.add(iconToolBar, BorderLayout.PAGE_START);
			
			jframeWindow.setJMenuBar(topLevelMenu.addMenuBar());
			jframeWindow.pack();
			jframeWindow.setVisible(true);		
		}
		catch(Exception e){
			LoggerMessage.printLog(WindowUI.class.getName(), e.getMessage());
		}
	}
	
	public static void closeProgram(){
		jframeWindow.dispatchEvent(new WindowEvent(jframeWindow, WindowEvent.WINDOW_CLOSING));
	}
	
	public static int incrementIndexOfTabs(){
		indexOfTabs = indexOfTabs + 1;
		return indexOfTabs;
	}
	
	public void addToContainer(JTabbedPane pane){
		container.add(pane, BorderLayout.CENTER);
		
	}
	
	 public static void initTabComponent(int indexOfTabs) {
	        tabbedPane.setTabComponentAt(indexOfTabs,
	                 new ButtonTabComponent(tabbedPane));
	 }   
	 
	 
	 public static int getIndexOfTabs(){
		 return indexOfTabs;
	 }
	 
	 
	public static void addNewProgrammingTab(String text){
		try{
			NewProjectTab newProject = new NewProjectTab();
			tabbedPane.addTab("Project "+incrementIndexOfTabs(), null, 
					newProject.createNewProgrammingPanel(text), text);
			
			container.add(tabbedPane, BorderLayout.CENTER);
		}catch(Exception e){
			LoggerMessage.printLog(WindowUI.class.getName(), e.getMessage());
		}
		
	}
	
	public static void addNewProgrammingTab(String text, String title){
		try{
			NewProjectTab newProject = new NewProjectTab();
			tabbedPane.addTab(title, null, 
					newProject.createNewProgrammingPanel(text), text);
			
			container.add(tabbedPane, BorderLayout.CENTER);
		}catch(Exception e){
			LoggerMessage.printLog(WindowUI.class.getName(), e.getMessage());
		}
		
	}
	
	public static String getTabName(int index){		
		return tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());		
	}
	
	public static void setTabName(int index, String title){
		tabbedPane.setTitleAt(index, title);
	}
	
	public static JTabbedPane getTabbedPane(){
		return tabbedPane;
	}
	
	
	public static void main(String[] args){
		
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				LoggerMessage.printLog(WindowUI.class.getName(),"Successful creation of thread");
				createAndShowWindowUI();
			}
		});
		
		
	}

}
