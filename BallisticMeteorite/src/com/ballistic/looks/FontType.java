package com.ballistic.looks;

import java.awt.Font;

public class FontType {
	
	
	public FontType(){
		
	}
	
	public static Font setTextFont(){
		Font font = new Font("INCONSOLATA", Font.BOLD, 16);
		return font;
	}
	
	public static Font setTextFontForTopMenu(){
		Font font = new Font("TREBUCHET MS", Font.BOLD, 15);
		return font;
	}

}
