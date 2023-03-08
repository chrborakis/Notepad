package Notepad;
import Notepad.Actions.*;
import java.awt.*;
import javax.swing.*;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.border.Border;
/**
 *
 * @author Xrhstos Mporakhs TP4815
 */
public class Notepad extends JFrame{
    /**
     * Init the Frame  for the Notepad and auto call the actionListener/methods Classes for its components 
     * to isolate the view(notepad) from the functionality
     */
    public Notepad(){
        super("Notepad");
        initComponents();
        
        new ActionFiles(this);      //Call the actionListener
        new ActionTextModify(this); //for each Component
        new ActionTheme(this);
    }

    public void initComponents(){
        this.setPreferredSize(new Dimension(800,700));
//Menu        
        MenuBar = new JMenuBar();          //Menu for file operations(open/save) until line 45
        fileMenu = new JMenu("File Options");
        newFileItem = new JMenuItem("New File");
        openFileItem = new JMenuItem("Open File");
        saveFileItem = new JMenuItem("Save File");
        infoItem = new JMenuItem("Info");
        
        fileMenu.add(newFileItem);                               
        fileMenu.add( openFileItem);
        fileMenu.add( saveFileItem);
        fileMenu.addSeparator();
        fileMenu.add( infoItem);
        MenuBar.add( fileMenu);
        setJMenuBar( MenuBar);
//ToolBar
        toolBar = new JToolBar(); //Toolbar Buttons And ComboBoxes until line 94
        //Panel with Title and font size Combo box
        JPanel fontSizePanel = new JPanel(new BorderLayout());      
        fontSizeCombo = new JComboBox(fontSizeArray);
        fontSizeCombo.setPreferredSize(new Dimension(100,40));
        fontSizePanel.add(new JLabel("Font Size",SwingConstants.CENTER), BorderLayout.NORTH);
        fontSizePanel.add(fontSizeCombo, BorderLayout.SOUTH);
        
//Panel with Title and font family Combobox
        JPanel fontFamilyPanel = new JPanel(new BorderLayout());    
        fontFamilyCombo = new JComboBox();
        for (String fontF : fontFamilyArray) {//add values to each combo box index
            fontFamilyCombo.addItem(fontF);
            fontFamilyCombo.setFont(new Font(fontF, Font.PLAIN, 12));
        }
        
        fontFamilyCombo.setPreferredSize(new Dimension(100,40));
        fontFamilyPanel.add(new JLabel("Font Family",SwingConstants.CENTER), BorderLayout.NORTH);
        fontFamilyPanel.add(fontFamilyCombo, BorderLayout.SOUTH);
        
//Panel with Title and Color combo box
        JPanel colorPanel = new JPanel(new BorderLayout()); 

        colorCombo = new JComboBox();  
        for (String color : colorMap.keySet()) {//add values to each combo box index
            colorCombo.addItem(color);              
        }
        colorCombo.setPreferredSize(new Dimension(100,40));
        colorPanel.add(new JLabel("Font Color",SwingConstants.CENTER), BorderLayout.NORTH);
        colorPanel.add(colorCombo, BorderLayout.SOUTH);
        
//Panel with Title and 3 buttons(bold/italic/underline)
        JPanel modifyPanel = new JPanel(new BorderLayout());    
        modifyPanel.setMaximumSize(new Dimension(110,60));
        boldTool = new JButton("B");
        italicTool = new JButton("I");
        underlineTool = new JButton("U");
        modifyPanel.add(new JLabel("Text Modifiers",SwingConstants.CENTER),BorderLayout.NORTH);
        //Kanw add apeutheias to epistrefomeno JButton me morfopoihmeno mesa stin methodo initModifyButton
        modifyPanel.add(initModifyButton(boldTool),BorderLayout.WEST);
        modifyPanel.add(initModifyButton(italicTool),BorderLayout.CENTER);
        modifyPanel.add(initModifyButton(underlineTool),BorderLayout.EAST);

//Panel with Title and Change Mode Button
        JPanel themePanel = new JPanel(new BorderLayout());
        themeTool = new JButton("Dark Mode");
        themeTool.setPreferredSize(new Dimension(100,40));
        themePanel.add(new JLabel("Change Theme",SwingConstants.CENTER), BorderLayout.NORTH);
        themePanel.add(themeTool, BorderLayout.SOUTH);
        
        toolBar.add(fontSizePanel); //Add Size/Family/Color/Modify/Theme Panels to the ToolBar
        toolBar.addSeparator(new Dimension(50,50));
        toolBar.add(fontFamilyPanel);
        toolBar.addSeparator(new Dimension(50,50));
        toolBar.add(colorPanel);
        toolBar.addSeparator(new Dimension(50,50));
        toolBar.add(modifyPanel);
        toolBar.addSeparator(new Dimension(50,50));
        toolBar.add(themePanel);

//StatusLine        
        statusLine = new JPanel( new BorderLayout());//Init status line to a method below
        statusLine = initStatusLine(statusLine);        
//TextArea        
        textArea = new JTextArea();
        scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setViewportView(textArea);
             
        add(toolBar, BorderLayout.NORTH);           //Modify tools
        add(scroll);                                //Contains TextArea
        add(statusLine, BorderLayout.PAGE_END);  //Count Row/Chars
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }   
    /**
    * Initialize statusLine panel, components
    * Including CountRows and countChars Labels
     * @param status
     * @return 
    */
    public JPanel initStatusLine( JPanel status){
        statusLine.setPreferredSize(new Dimension(this.getWidth(),35));
        JPanel charsPanel = new JPanel();
        JPanel rowsPanel = new JPanel();

        JLabel countCharsLabel = new JLabel("Chars: ");
        countChars = new JLabel("0");
        JLabel countRowsLabel  = new JLabel("Rows: ");
        countRows  = new JLabel("1");
        charsPanel.add(countCharsLabel);
        charsPanel.add(countChars);
        rowsPanel.add(countRowsLabel);
        rowsPanel.add(countRows);
        statusLine.add(charsPanel,BorderLayout.WEST);
        statusLine.add(rowsPanel,BorderLayout.EAST);
        return status;
    }

    /**
     * Modifies the buttons of the toolbar
     * @param toolButton
     * @return JButton
     */
    public JButton initModifyButton( JButton toolButton){
        Border border = BorderFactory.createLineBorder(Color.GRAY, 3);
        toolButton.setPreferredSize(new Dimension(40,40));
        toolButton.setBorder(border);
        return toolButton;
    }

    //Getters to access the components in the Actions Package
    public JFrame getFrame(){return this;}
    public JTextArea getTextArea(){return this.textArea;}
    public JLabel getRowsCount(){return this.countRows;}
    public JLabel getCharsCount(){return this.countChars;}
    public JComboBox getFontSizeCombo(){
        return this.fontSizeCombo;}
    public JComboBox getFontFamilyCombo(){
        return this.fontFamilyCombo;}
    public JComboBox getColorCombo(){
        return this.colorCombo;}
    public Map getColorsMap(){
        return this.colorMap;}
    public JButton getTools( int index){
        JButton[] tools = new JButton[]{boldTool,italicTool,underlineTool,themeTool};
        return tools[index];}
    public JMenuItem getMenuItem( int index){
        JMenuItem[] menuItem = new JMenuItem[]{newFileItem, openFileItem, saveFileItem, infoItem};
        return menuItem[index];}
    
    //TextArea and its ScrollPan
    private JTextArea textArea;
    private JScrollPane scroll;
    //MenuBar/Menu/MenuItem components
    private JMenuBar MenuBar;
    private JMenu fileMenu;
    private JMenuItem newFileItem, openFileItem, saveFileItem, infoItem;
    //ToolBar components and its Buttons
    private JToolBar toolBar;
    private JComboBox fontSizeCombo, fontFamilyCombo, colorCombo;
    private JButton boldTool, italicTool, underlineTool, themeTool;
    private final String[] fontFamilyArray = new String[] { "Arial", "Broadway","Calibri","Cambria","Corinthia","Dejavu Sans","Franklin Gothic", 
        "Futura" ,"Garamond","Helvetica","Rockwell","Tahoma","Times New Roman","Verdana"};
    private final Integer[] fontSizeArray = new Integer[] { 10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,42,44,46,48,50};
    private Map<String, Color> colorMap = new TreeMap<String, Color>(){{
        put("White", Color.white);  
        put("Black", Color.black);
        put("Gray", Color.gray);
        put("Gray Light", Color.darkGray);
        put("Gray Dark", Color.lightGray);
        put("Blue", Color.blue);
        put("Cyan", Color.cyan);
        put("Orange", Color.orange);
        put("Yellow", Color.yellow);
        put("Red", Color.red);      
        put("Green", Color.green);
        put("Pink", Color.pink);
    }};
    //Status Line labels and panel for count rows/chars
    private JPanel statusLine;
    private JLabel countChars, countRows;
}