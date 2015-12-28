package com.ballistic.actions;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.TransferHandler;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;
import javax.swing.text.Position;

import com.ballistic.logging.LoggerMessage;
import com.ballistic.topmenu.TopLevelMenu;

public class TextTransferHandler extends TransferHandler{

	//We need this while performing move
	Position p0 = null;
	Position p1 = null;
	
	public boolean importData(TransferHandler.TransferSupport support){
		if(!canImport(support)){
			return false;
		}		
		
		String data;
		try{
			data = (String)support.getTransferable().getTransferData(DataFlavor.stringFlavor);
		}catch(UnsupportedFlavorException | IOException e){
			return false;
		}
		
		JTextArea textArea = (JTextArea)support.getComponent();
		textArea.replaceSelection(data);
		return true;
	}
	
	/**
     * Bundle up the data for export.
     */
    protected Transferable createTransferable(JComponent c) {
        JTextArea source = (JTextArea)c;
        int start = source.getSelectionStart();
        int end = source.getSelectionEnd();
        Document doc = source.getDocument();
        if (start == end) {
            return null;
        }
        try {
            p0 = doc.createPosition(start);
            p1 = doc.createPosition(end);
        } catch (BadLocationException e) {
           // System.out.println(
            //        "Can't create position - unable to remove text from source.");
        	LoggerMessage.printLog(TextTransferHandler.class.getName(), 
        			"Can't create position - unable to remove text from source.");
        }
        String data = source.getSelectedText();
        return new StringSelection(data);
    }

    /**
     * These text fields handle both copy and move actions.
     */
    public int getSourceActions(JComponent c) {
        return COPY_OR_MOVE;
    }
    
    /**
     * When the export is complete, remove the old text if the action
     * was a move.
     */
    protected void exportDone(JComponent c, Transferable data, int action) {
        if (action != MOVE) {
            return;
        }
        
        if ((p0 != null) && (p1 != null) &&
            (p0.getOffset() != p1.getOffset())) {
            try {
                JTextComponent tc = (JTextComponent)c;
                tc.getDocument().remove(p0.getOffset(), 
                        p1.getOffset() - p0.getOffset());
            } catch (BadLocationException e) {
                //System.out.println("Can't remove text from source.");
            	LoggerMessage.printLog(TextTransferHandler.class.getName(), "Can't remove text from source.");
            }
        }
    }
    
    /**
     * We only support importing strings.
     */
    public boolean canImport(TransferHandler.TransferSupport support) {
        // we only import Strings
        if (!support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return false;
        }
        return true;
    }
	
}
