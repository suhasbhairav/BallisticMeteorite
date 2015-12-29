package com.ballistic.text;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.ballistic.actions.TextTransferHandler;
import com.ballistic.looks.FontType;
import com.ballistic.looks.Windows;

	
public class ProgrammingArea{

	private static JTextArea programDisplay = null;
	private static TextTransferHandler textHandler = new TextTransferHandler();
	
	public ProgrammingArea(){
				
		
	}
	
	public JPanel createProgramPanel(){
		JPanel programmingPanel = new JPanel();		
		TitledBorder titledBorder = new TitledBorder(new EtchedBorder(), "Write your program here!");
		titledBorder.setTitleFont(FontType.setTextFontForTopMenu());
		programmingPanel.setBorder(titledBorder);
		programmingPanel.setBackground(Windows.mainScreenColor());
		
		
		programDisplay = new JTextArea(24,140);
		programDisplay.setFont(FontType.setTextFont());
		programDisplay.setEditable(true);
		programDisplay.setTransferHandler(textHandler);
		programDisplay.setDragEnabled(true);
		JScrollPane scrollPane = new JScrollPane(programDisplay);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		programmingPanel.add(scrollPane);
		return programmingPanel;
	}
	
	public static String getProgramContent(){
	
		String programContent = "";
		if(programDisplay.getText()!= null){			
			programContent = programDisplay.getText();
		}
		return programContent;
	}
	
	public static void setProgramContent(String content){		
		programDisplay.append(content);
		programDisplay.append("\n");
		
	}
	public static void clearProgramContent(){
		programDisplay.setText("");
		
	}
	
	
}
