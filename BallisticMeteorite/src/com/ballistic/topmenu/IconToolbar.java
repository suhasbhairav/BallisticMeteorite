package com.ballistic.topmenu;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.text.DefaultEditorKit;

import com.ballistic.actions.Listeners;
import com.ballistic.actions.TextTransferHandler;

@SuppressWarnings("serial")
public class IconToolbar  extends JPanel{
	
	private  JToolBar toolBar = null;
	private static TextTransferHandler textHandler = new TextTransferHandler();
	
	public IconToolbar(){
		super(new BorderLayout());
		//setMaximumSize(new Dimension(30,100));
		//setPreferredSize(new Dimension(30,100));
		try{		
			toolBar = new JToolBar();
			
			addIcons(toolBar);
			toolBar.addSeparator();
			add(toolBar, BorderLayout.PAGE_START);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void addIcons(JToolBar toolBar){
		JButton button = null;
		button = makeIconButton("new", "New",
				"Start a new project", "New" );
		toolBar.add(button);
		
		button = makeIconButton("open", "Open",
				"Open an existing project", "Open" );
		toolBar.add(button);
		

		button = makeIconButton("save", "Save",
				"Save project", "Save" );
		toolBar.add(button);
		
		button = makeIconButton("cut", "Cut",
				"Cut", "Cut" );
		toolBar.add(button);
		
		button = makeIconButton("copy", "Copy",
				"Copy", "Copy" );
		toolBar.add(button);
		
		button = makeIconButton("paste", "Paste",
				"Paste", "Paste" );
		toolBar.add(button);
		
		button = makeIconButton("run", "Run",
				"Run the current project", "Run" );
		toolBar.add(button);
		
	}
	
	protected JButton makeIconButton(String image,
			String actionCommand,
			String toolTipText,
			String altText){
		
		String imageLocation = "/images/"+ 
								image + ".png";
		URL imageURL = getClass().getResource(imageLocation);
		
		JButton button = new JButton();
		
		if(actionCommand.toUpperCase().equals("NEW") 
				|| actionCommand.toUpperCase().equals("OPEN")
				|| actionCommand.toUpperCase().equals("RUN") 
				|| actionCommand.toUpperCase().equals("SAVE")){
			
			button = new JButton();
			button.setActionCommand(actionCommand);
			button.setToolTipText(toolTipText);			
			button.addActionListener(new Listeners());
		}else{
			switch(actionCommand.toUpperCase()){
			case "CUT":
				button = new JButton(new DefaultEditorKit.CutAction());
				button.setText("");
				button.setToolTipText(toolTipText);				
				break;
			case "COPY":
				button = new JButton(new DefaultEditorKit.CopyAction());
				button.setText("");
				button.setToolTipText(toolTipText);
				break;
			case "PASTE":
				button = new JButton(new DefaultEditorKit.PasteAction());
				button.setText("");
				button.setToolTipText(toolTipText);
				break;
			}
		
			
		}
		if(imageURL!=null){
			
			Image imageFile = null;
			Image resizedImage = null;
			try {
				imageFile = ImageIO.read(getClass().getResource(imageLocation));
				resizedImage = imageFile.getScaledInstance(25, 25, 25);
				ImageIcon img = new ImageIcon(resizedImage);
				
				button.setIcon(img);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else{
			//button.setText(altText);
			  System.err.println("Resource not found: "
                      + imageLocation);
		}
		
		button.setVisible(true);
		//button.setSize(new Dimension(10,10));
		return button;
		
		
	}
	
	
}
