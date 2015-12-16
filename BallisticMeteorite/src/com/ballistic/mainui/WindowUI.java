package com.ballistic.mainui;

import java.awt.Dimension;

import javax.swing.JFrame;

public class WindowUI {
	
	private static JFrame jframeWindow;
	private static String windowUIName = "BallisticMeteorite";
	private static int WIDTH = 400;
	private static int HEIGHT = 400;
	public WindowUI(){
		
	}
	
	
	private static void createAndShowWindowUI(){
		jframeWindow = new JFrame();		
		jframeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jframeWindow.setTitle(windowUIName);
		
		
		jframeWindow.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		jframeWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		jframeWindow.pack();
		jframeWindow.setVisible(true);
		
	}
	
	
	public static void main(String[] args){
		
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				createAndShowWindowUI();
			}
		});
		
		
	}

}
