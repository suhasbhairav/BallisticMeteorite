package com.ballistic.text;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class OutputConsole {

	public OutputConsole(){
			
	}
	
	public JPanel createOutputPanel(){
		JPanel consolePanel = new JPanel();
		consolePanel.setBorder(new TitledBorder(new EtchedBorder(), "Output"));
		
		JTextArea outputDisplay = new JTextArea(9,110);
		outputDisplay.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(outputDisplay);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		consolePanel.add(scrollPane);
		return consolePanel;
		
	}
}
