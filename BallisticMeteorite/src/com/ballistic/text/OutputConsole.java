package com.ballistic.text;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.ballistic.looks.FontType;
import com.ballistic.looks.Windows;

public class OutputConsole {

	private static JTextArea outputDisplay = null;
	public OutputConsole(){
			
	}
	
	public JPanel createOutputPanel(){
		JPanel consolePanel = new JPanel();
		TitledBorder titledBorder = new TitledBorder(new EtchedBorder(), "Output");
		titledBorder.setTitleFont(FontType.setTextFontForTopMenu());
		
		consolePanel.setBorder(titledBorder);
		consolePanel.setBackground(Windows.mainScreenColor());
		
		outputDisplay = new JTextArea(6,140);
		outputDisplay.setFont(FontType.setTextFont());
		outputDisplay.setEditable(true);
		
		JScrollPane scrollPane = new JScrollPane(outputDisplay);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		consolePanel.add(scrollPane);
		return consolePanel;
		
	}
	
	public static void setProgramOutput(String content){		
		outputDisplay.append(content);
		outputDisplay.append("\n");
	}	
	
	public static void clearProgramOutput(){
		outputDisplay.setText("");
	}
}
