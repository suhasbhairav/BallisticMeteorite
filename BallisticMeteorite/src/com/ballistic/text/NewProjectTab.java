package com.ballistic.text;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;

import com.ballistic.actions.TextTransferHandler;
import com.ballistic.logging.LoggerMessage;
import com.ballistic.looks.FontType;
import com.ballistic.looks.Windows;
import com.ballistic.mainui.WindowUI;

public class NewProjectTab extends JTabbedPane {	

	private JTabbedPane tabbedPane = null;
	private String currentNewProjectName = "";
	ProgrammingArea newProgrammingArea = new ProgrammingArea();
	private static TextTransferHandler textHandler = new TextTransferHandler();
	private static String openProjectContent = "";
	
	public NewProjectTab(){
		
	}
	
	public NewProjectTab(int tabIndex){
		JComponent panel = makeTextAreaPanel();
		tabbedPane = new JTabbedPane();
		
		tabbedPane.addTab("Project "+tabIndex, null, panel, "");
		//tabbedPane.setMnemonicAt(tabIndex,Integer.parseInt("KeyEvent.VK_"+String.valueOf(tabIndex)));	
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}
	
	public JPanel createNewProgrammingPanel(String text){
		JPanel programmingPanel = new JPanel();
		programmingPanel.setLayout(new BorderLayout());
		programmingPanel.setBackground(Windows.mainScreenColor());
		
		JTextArea programDisplay = new JTextArea(24,140);
		programDisplay.setFont(FontType.setTextFont());
		programDisplay.setEditable(true);
		programDisplay.setTransferHandler(textHandler);
		programDisplay.setDragEnabled(true);
		programDisplay.setText(text);
		
		JScrollPane scrollPane = new JScrollPane(programDisplay);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		programmingPanel.add(scrollPane);
		return programmingPanel;
	}
	
	protected JPanel makeTextAreaPanel(){		
		return newProgrammingArea.createProgramPanel();	
	}
	
	
	public static String getProgramContent(){
		String programContent = "";
		try{
		Component[] components =  ((JPanel)WindowUI.getTabbedPane().getComponentAt(WindowUI.getTabbedPane().getSelectedIndex())).getComponents();
		Component[] scrollComponent = ((JScrollPane)components[0]).getComponents();
		Component[] textComponents = ((JViewport)scrollComponent[0]).getComponents();
		if(textComponents[0] instanceof JTextArea){
				JTextArea textArea = ((JTextArea)textComponents[0]);	
				programContent = textArea.getText();
			}
		
		}
		catch(Exception e){
			LoggerMessage.printLog(NewProjectTab.class.getName(), e.getMessage());
		}
		return programContent;
	}
	
	public static void openProgrammingProject(String text, String title){
		WindowUI.addNewProgrammingTab(text, title);
		WindowUI.getTabbedPane().setTabComponentAt(WindowUI.getTabbedPane().getTabCount()-1, null);
		WindowUI.initTabComponent(WindowUI.getTabbedPane().getTabCount()-1);
		
	}
	
	public static void appendProgrammingContent(String text){
		openProjectContent.concat(text);
		openProjectContent.concat("\n");
	}
	
	
	public static String getOpenProjectProgrammingContent(){
		System.out.println(openProjectContent);
		return openProjectContent;
	}
}
