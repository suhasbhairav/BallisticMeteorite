package com.ballistic.mainui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.ballistic.logging.LoggerMessage;
import com.ballistic.text.OutputConsole;
import com.ballistic.text.ProgrammingArea;
import com.ballistic.topmenu.TopLevelMenu;

public class WindowUI {
	
	private static JFrame jframeWindow = null;
	private static String windowUIName = "BallisticMeteorite";
	private static JPanel programmingAreaPanel = null;
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
			
			
			jframeWindow.setMinimumSize(new Dimension(WIDTH, HEIGHT));
			jframeWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
			
			container = jframeWindow.getContentPane();
			container.setLayout(new BorderLayout());
			
			TopLevelMenu topLevelMenu = new TopLevelMenu();
			container.add(topLevelMenu.addMenuBar(), BorderLayout.NORTH);
			
			ProgrammingArea programmingArea = new ProgrammingArea();
			container.add(programmingArea.createProgramPanel(), BorderLayout.CENTER);

			OutputConsole outputConsole = new OutputConsole();
			container.add(outputConsole.createOutputPanel(), BorderLayout.SOUTH);
			
			jframeWindow.pack();
			jframeWindow.setVisible(true);		
		}
		catch(Exception e){
			LoggerMessage.printLog(WindowUI.class.getName(), e.getMessage());
		}
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
