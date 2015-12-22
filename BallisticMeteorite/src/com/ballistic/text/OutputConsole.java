package com.ballistic.text;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.ballistic.looks.Windows;

public class OutputConsole {

	private static JTextArea outputDisplay = null;
	public OutputConsole(){
			
	}
	
	public JPanel createOutputPanel(){
		JPanel consolePanel = new JPanel();
		consolePanel.setBorder(new TitledBorder(new EtchedBorder(), "Output"));
		consolePanel.setBackground(Windows.mainScreenColor());
		
		outputDisplay = new JTextArea(9,110);
		outputDisplay.setEditable(true);
		
		JScrollPane scrollPane = new JScrollPane(outputDisplay);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		consolePanel.add(scrollPane);
		return consolePanel;
		
	}
	
	public static void setProgramOutput(String content){
		System.out.println("Out1:"+content);
		outputDisplay.append(content);
		outputDisplay.append("\n");
	}
	
	public static void setProgramOutput(char content){
		System.out.println("Out:");
		outputDisplay.append(Character.toString(content));
		outputDisplay.append("\n");
	}
	
	public static void clearProgramOutput(){
		outputDisplay.setText("");
	}
}
