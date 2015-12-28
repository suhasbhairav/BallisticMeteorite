package com.ballistic.windows;


import java.awt.Dimension;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

public class About {

	private JFrame aboutFrame = null;
	private String sName = "Ballistic Meteorite";
	private String sVersion = "1.0";
	
	public About(){
		
		aboutFrame = new JFrame();
		aboutFrame.setTitle("Ballistic Meteorite");
		aboutFrame.setSize(new Dimension(200,200));
		aboutFrame.setMinimumSize(new Dimension(200,200));
		aboutFrame.setMaximumSize(new Dimension(200,200));
		aboutFrame.setLocationRelativeTo(null);
		
		SpringLayout layout = new SpringLayout();		
		Label labelName = new Label();
		labelName.setText(sName + "-"+sVersion);
		labelName.setVisible(true);
		
		layout.putConstraint(SpringLayout.SOUTH,labelName, 20, SpringLayout.SOUTH, aboutFrame);
		
		aboutFrame.setLayout(layout);		
		
		aboutFrame.add(labelName);
		aboutFrame.pack();
		aboutFrame.setVisible(true);
	}
	
	
}
