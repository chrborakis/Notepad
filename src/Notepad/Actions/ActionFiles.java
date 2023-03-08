package Notepad.Actions;

import Notepad.Notepad;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ActionFiles {
    public ActionFiles( Notepad notepad){
        this.notepad = notepad;
        fileListeners(notepad.getMenuItem(0),//New File Menu Item
                notepad.getMenuItem(1),     //Open File Menu Item
                notepad.getMenuItem(2),      //SaveFile Menu Item
                notepad.getMenuItem(3));            //Info Menu item
    }
    
    public void fileListeners( JMenuItem newFileItem,JMenuItem openFileItem,JMenuItem saveFileItem,JMenuItem infoItem){
        newFileItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newFile();
            }}
        );
        openFileItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }});
        saveFileItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    saveFile();
                }catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }});
        infoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInfo();
            }});
    }
    
    public void newFile(){
        notepad.getTextArea().setText(null);
        notepad.getTextArea().requestFocus();
    }

    /**
     * Get the user to choose a text file from his File Explorer
     * and display its content on the text area of the notepad
     */
    public void openFile(){
        JFileChooser FileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        FileChooser.setFileFilter(filter);
        FileChooser.showOpenDialog(FileChooser);
        File newFile = FileChooser.getSelectedFile();
        if(newFile == null){
            return;
        }
        FileReader myFile = null;
        BufferedReader buff = null;
        try{
            if(!newFile.getName().endsWith(".txt")){
                JOptionPane.showMessageDialog(null,"Please select a text file","Error",JOptionPane.ERROR_MESSAGE);
                openFile();
            }
            myFile = new FileReader(newFile);
            buff = new BufferedReader(myFile);

            while(true){
                String line = buff.readLine();
                if (line == null){
                    break;
                }
                notepad.getTextArea().append(line + "\n");
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                buff.close();
                myFile.close();
                JOptionPane.showMessageDialog(null,"File opened Succefully","Information",JOptionPane.INFORMATION_MESSAGE);
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    /**
     * Save the current content of the text area 
     * to a new text file or rewrite an existing one
     * @throws IOException 
     */
    public void saveFile() throws IOException{
        JFileChooser FileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
        FileChooser.setFileFilter(filter);
        FileChooser.showSaveDialog(FileChooser);
        File newFile = FileChooser.getSelectedFile();
        if(newFile == null){
            return;
        }
        FileWriter myFile = null;
        BufferedWriter buff = null;
        try {
            myFile = new FileWriter(newFile);
            buff = new BufferedWriter(myFile);
            buff.write(notepad.getTextArea().getText());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                buff.flush();
                buff.close();
                myFile.close();
                JOptionPane.showMessageDialog(null,"File saved Succefully","Information",JOptionPane.INFORMATION_MESSAGE);
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    /**
     * Open a message dialog
     * displaying my Name and Number
     */
    public void showInfo(){
        String msg = "This Notepad is created by\nChristos Borakis TP4815";
        JOptionPane.showMessageDialog(null,msg,"Credits",JOptionPane.INFORMATION_MESSAGE);
    }
    
    private Notepad notepad;
    private JFileChooser FileChooser;
}