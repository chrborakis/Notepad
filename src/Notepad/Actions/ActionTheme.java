package Notepad.Actions;

import Notepad.Notepad;
import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Xrhstos Mporakhs TP4815
 */
public class ActionTheme {
    public ActionTheme( Notepad notepad){
        this.notepad = notepad;
        switchMode(notepad.getFrame(),notepad.getTools(3));
    }
    
    /**
     * Checks if there's action on the themeButton
     * if true, then changes to Dark mode
     * if dark mode is displayed, switches to day mode
     * and changes the text of the button based on the action
     * 
     * @param frame
     * @param themeButton 
     */
    
    public void switchMode( JFrame frame, JButton themeButton){
        themeButton.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                if(themeButton.getText().trim().equals("Dark Mode")){
                    try {
                        UIManager.setLookAndFeel( new FlatDarkLaf());
                        themeButton.setText("Day Mode");
                        notepad.getTextArea().setForeground(Color.white);
                        notepad.setForeground(Color.white);
                    } catch( Exception ex ) {
                        System.err.println( "Failed to initialize Dark Mode");}
                }else{
                    try {
                        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                        themeButton.setText("Dark Mode");
                        notepad.getTextArea().setForeground(Color.black);
                        notepad.setForeground(Color.black);
                    } catch( Exception ex ) {
                        System.err.println( "Failed to initialize Day Mode");}
                }
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });
    }
    private Notepad notepad;
}