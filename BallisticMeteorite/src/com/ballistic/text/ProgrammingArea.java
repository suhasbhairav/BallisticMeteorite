package com.ballistic.text;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

	
public class ProgrammingArea{

	public ProgrammingArea(){
				
		
	}
	
	public JPanel createProgramPanel(){
		JPanel programmingPanel = new JPanel();
		programmingPanel.setBorder(new TitledBorder(new EtchedBorder(), "Write your program here!"));
		
		JTextArea programDisplay = new JTextArea(30,110);
		programDisplay.setEditable(true);
		
		JScrollPane scrollPane = new JScrollPane(programDisplay);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		programmingPanel.add(scrollPane);
		return programmingPanel;
	}
	
	
}
