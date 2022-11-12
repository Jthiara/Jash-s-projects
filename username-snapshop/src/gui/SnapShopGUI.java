/**
 * TCSS 305
 *  
 *  Class represents GUI for a application that loads an image and has filter buttons to alter an image and also buttons 
 *  that allow you to open, save, and close. 
 */

package gui;

import filters.AbstractFilter;
import filters.EdgeDetectFilter;
import filters.EdgeHighlightFilter;
import filters.FlipHorizontalFilter;
import filters.FlipVerticalFilter;
import filters.GrayscaleFilter;
import filters.SharpenFilter;
import filters.SoftenFilter;
import image.PixelImage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Class represents GUI in the SnapShop program.
 * 
 * @author jthiara
 * @version Fall 2021
 */
public class SnapShopGUI extends JFrame {
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 25L;
    
    /**
     * ImageIcon field.
     */
    private final ImageIcon myIcon;
    
    /**
     * label field to display image.
     */
    private final JLabel myLabel;
    
    /**
     * panel field to hold filter buttons.
     */
    private final JPanel myFilterPanel;
    
    /**
     * panel field to hold filter buttons.
     */
    private final JPanel myImagePanel;
    
    /**
     * panel field to hold filter buttons.
     */
    private final JPanel myBottomPanel;
    
    /**
     * Array List field to hold filter buttons.
     */
    private ArrayList<JButton> myFilterButtonsList;
    
    /**
     * Array List field to hold bottoms buttons.
     */
    private ArrayList<JButton> myBottomButtonsList;
    
    /**
     * pixel image field.
     */
    private PixelImage myImage;
    
    /**
     *JFileChooser field.
     */
    private JFileChooser myChooser;
    
    /**
     * String to hold path address.
     */
    private String myCurrentPath; 
    
    /**
     * Constructor (initializes fields). 
     */
    public SnapShopGUI() { 
        super("TCSS 305 - Assignment 5"); 
        myIcon = new ImageIcon("\\Users\\jasht\\eclipse_workspace\\username-snapshop\\icons\\smile.jpg");
        myLabel = new JLabel();
        myFilterPanel = new JPanel(new FlowLayout());
        myImagePanel = new JPanel();
        myBottomPanel = new JPanel(new FlowLayout());
        myFilterButtonsList = new ArrayList<JButton>();
        myBottomButtonsList = new ArrayList<JButton>();
        myCurrentPath = "."; 
        myChooser = new JFileChooser(myCurrentPath);
    }
    
    /**
     * method starts GUI.
     */
    public void start() {
        //javax.swing.JOptionPane.showMessageDialog(null, "SnapShop placeholder");
        buttonManager(this);
        setUpFrame();
    }
    
    /**
     * Method sets up the frame for GUI application.
     */
    public void setUpFrame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setIconImage(myIcon.getImage());
        myImagePanel.add(myLabel);
        add(myImagePanel, BorderLayout.CENTER);
        add(myFilterPanel, BorderLayout.NORTH);
        add(myBottomPanel, BorderLayout.SOUTH); 
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        
    }
    
    /**
     * Method is a manager to implement buttons in the GUI (used to prevent complication).
     * 
     * @param theFrame
     */
    public void buttonManager(final JFrame theFrame) {
        implementFilterButtons(new JButton("Edge Detect"), new EdgeDetectFilter()); //calls method to add filter functionality
        implementFilterButtons(new JButton("Edge Highlight"), new EdgeHighlightFilter());
        implementFilterButtons(new JButton("Flip Horizontal"), new FlipHorizontalFilter());
        implementFilterButtons(new JButton("Flip Vertical"), new FlipVerticalFilter());
        implementFilterButtons(new JButton("Grayscale"), new GrayscaleFilter());
        implementFilterButtons(new JButton("Sharpen"), new SharpenFilter());
        implementFilterButtons(new JButton("Soften"), new SoftenFilter());
        
        implementBottomButtons();
    }
    
    /**
     * Method implements bottom row buttons (save, open, and close).
     */
    public void implementBottomButtons() {
        final JButton open = new JButton("Open..."); 
        open.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(final ActionEvent theEvent) { // after image is loaded, GUI is resized.
                myImagePanel.setVisible(true);
                chooseFile(); //call to method that utilizes file chooser. 
                resizeFrame();
            }
        });
        
        final JButton save = new JButton("Save As...");
        save.addActionListener(new ActionListener() {
            
            @Override 
            public void actionPerformed(final ActionEvent theEvent) { 
                saveFile(); // call to method that utilizes file chooser to save our image.
            }
        });
        
        final JButton close = new JButton("Close Image");
        close.addActionListener(new ActionListener() {
            
            @Override 
            public void actionPerformed(final ActionEvent theEvent) { // after image is closed, frame is rezised.
                myImagePanel.setVisible(false); 
                resizeFrame();
                toggleAllButtons(false);
            }
        });
        myBottomPanel.add(open);
        myBottomPanel.add(save);
        myBottomPanel.add(close);
        
        myBottomButtonsList.add(save); //array list utilized as a helper for toggle button method.
        myBottomButtonsList.add(close);
        save.setEnabled(false);
        close.setEnabled(false);
    }
    
    /**
     * Method takes in a button and a filter and creates action listeners, so when the button is clicked
     * then the filter will be used on the image. 
     * 
     * @param theButton
     * @param theFilter
     */
    public void implementFilterButtons(final JButton theButton, final AbstractFilter theFilter) { 
        theButton.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(final ActionEvent theEvent) {
                theFilter.filter(myImage); // applies filter.
                myLabel.repaint();
                resizeFrame(); //resize image. 
            }
        });
        myFilterButtonsList.add(theButton); //array list utilized as helper for toggle buttons method.
        theButton.setEnabled(false); // all filter buttons are not enabled until image is opened. 
        myFilterPanel.add(theButton);
    }
    
    /**
     * method is used to resize frame after a image is added or taken out. 
     */
    public void resizeFrame() {
        setMinimumSize(new Dimension(0, 0));
        pack();
        setMinimumSize(getSize());
        setLocationRelativeTo(null); //center GUI. 
    }
    
    /**
     * Method is used to avoid manually toggling each button.
     * 
     * @param theStatus
     */
    public void toggleAllButtons(final boolean theStatus) {
        for (JButton button: myFilterButtonsList) { //toggles filter buttons on or off.
            button.setEnabled(theStatus);
        }
        
        for (JButton button: myBottomButtonsList) { //toggles bottom buttons on or off.
            button.setEnabled(theStatus);
        }
    }
    
    /**
     * Method creates functionality for open button, so that it opens a file directory.
     */
    public void chooseFile() {
        final int result = myChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            final File chosenFile = myChooser.getSelectedFile(); 
            try {
                myImage = PixelImage.load(chosenFile);
                myLabel.setIcon(new ImageIcon(myImage));
                toggleAllButtons(true); // once image is selected all buttons will be enabled. 
                myCurrentPath = chosenFile.getAbsolutePath(); //creates new path.
            } catch (final IOException e) { 
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "The selected file did not contain an image!");
            }
        }
    }
    
    /**
     * Method is used to create functionality to save button. 
     */
    public void saveFile() {
        final int result = myChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            final File fileToSave = myChooser.getSelectedFile();
            try {
                myImage.save(fileToSave); // saves file. 
            } catch (final IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "The selected file did not contain an image to save!");  
            }
        }
    }
    
}