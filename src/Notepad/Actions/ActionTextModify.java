package Notepad.Actions;

import Notepad.Notepad;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Font;
import java.awt.font.TextAttribute;

/**
 *
 * @author Xrhstos Mporakhs TP48815
 */
public class ActionTextModify {
    public ActionTextModify( Notepad notepad){
        this.notepad = notepad;
        counters();                           //Counters for total of chars/rows
        changeFont( notepad.getFontSizeCombo(), notepad.getFontFamilyCombo());//ActionListeners to change Font
        modifyText( notepad.getTools(0), notepad.getTools(1), notepad.getTools(2));//ActionListeners to modify text
        changeColor(notepad.getColorCombo(), notepad.getColorsMap());//ActionListener to change foreground
    }
    
    /**
     * Counts the total of characters and the numbers of rows
     * and change the text of the label displayed on the bottom of the Notepad
     */
    public void counters(){
        notepad.getTextArea().getDocument().addDocumentListener(new DocumentListener()
        {
            public void changedUpdate(DocumentEvent arg0) {
                changeCount();
            }
            public void insertUpdate(DocumentEvent arg0) {
                changeCount();
            }
            public void removeUpdate(DocumentEvent arg0) {
                changeCount();
            }
            private void changeCount(){
                notepad.getCharsCount().setText(String.valueOf(notepad.getTextArea().getText().length()));
                String textAreaText = notepad.getTextArea().getText();
                String[] splited = textAreaText.split("\n");
                notepad.getRowsCount().setText(String.valueOf(splited.length));
            }
        });
    }
    
    /**
     * Set the font size and font family
     * based on the user choose by selecting the value
     * from each combo box
     * @param fontSizeCombo
     * @param fontFamilyCombo 
     */
    public void changeFont( JComboBox fontSizeCombo, JComboBox fontFamilyCombo){
        fontSizeCombo.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                fontSize = (int) fontSizeCombo.getSelectedItem();
                notepad.getTextArea().setFont(new Font(fontFamily,fontStyle, fontSize));
            }
        });
        fontFamilyCombo.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                String selectedFamily = (String) fontFamilyCombo.getSelectedItem();
                notepad.getTextArea().setFont(new Font(selectedFamily,fontStyle, fontSize));
            }
        });
    }
    
    /**
     * Get the bold,italic,underline buttons from the Notepad
     * if one of those is pressed
     * make the text bold or italic or underline
     * and if text is already with this style, remove it
     * @param bold
     * @param italic
     * @param underline 
     */
    public void modifyText( JButton bold, JButton italic, JButton underline){
        bold.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                if(!isBold){
                    notepad.getTextArea().setFont(new Font(fontFamily, Font.BOLD, fontSize));
                    isBold=true;
                }else{
                    notepad.getTextArea().setFont(new Font(fontFamily, Font.PLAIN, fontSize));
                    isBold=false;
                }
            }
        });
        italic.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                if(!isItalic){
                    notepad.getTextArea().setFont(new Font(fontFamily, Font.ITALIC, fontSize));
                    isItalic=true;
                }else{
                    notepad.getTextArea().setFont(new Font(fontFamily, Font.PLAIN, fontSize));
                    isItalic=false;
                }
            }
        });
        underline.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                Font font = notepad.getTextArea().getFont();
                Map attrs = font.getAttributes();
                if(!isUnderline){
                    attrs.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                    notepad.getTextArea().setFont(font.deriveFont(attrs));
                    isUnderline=true;
                }else{
                    notepad.getTextArea().setFont(new Font(fontFamily, Font.PLAIN, fontSize));
                    isUnderline=false;
                } 
            }
        });
    }
    
    /**
     * if a color from the color combo box is selected
     * get its value (Color) by the key (String)
     * and change the foreground of the text area with the Color
     * @param colorCombo
     * @param colorMap 
     */
    public void changeColor( JComboBox colorCombo, Map<String,Color>colorMap){
        colorCombo.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                String selectedColor = (String) colorCombo.getSelectedItem();
                notepad.getTextArea().setForeground(colorMap.get(selectedColor));
            }
        });
    }

    private Notepad notepad;
    private String fontFamily = "Arial";
    private int fontStyle = Font.PLAIN;
    private int fontSize = 14;
    private Boolean isBold=false, isItalic=false, isUnderline=false;
}
