package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import translatorIAP.SharedClass;

public class GuiTranslate {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	@SuppressWarnings("unused")
	private boolean noWords=false; 
	


	/**
	 * Launch the application.
	 */
	public void runGui() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiTranslate window = new GuiTranslate();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GuiTranslate() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[][][grow]", "[][][][][][][][][]"));
		
		JLabel lbllittleTranslator = new JLabel("**little Translator**");
		frame.getContentPane().add(lbllittleTranslator, "cell 1 0,alignx center");
		
		JLabel lblEnglish = new JLabel("english");
		frame.getContentPane().add(lblEnglish, "cell 0 3");
		
		textField = new JTextField();
		frame.getContentPane().add(textField, "cell 1 3,alignx left");
		textField.setColumns(10);
		
		JLabel lblPleaseTypeIn = new JLabel("Please type in a word");
		frame.getContentPane().add(lblPleaseTypeIn, "cell 2 3");
		
		
		
		JLabel lblSpanish = new JLabel("spanish");
		frame.getContentPane().add(lblSpanish, "cell 0 4");
		
		textField_1 = new JTextField();
		frame.getContentPane().add(textField_1, "cell 1 4,alignx left");
		textField_1.setColumns(10);
		
		JCheckBox chckbxCsv = new JCheckBox("XML");
		frame.getContentPane().add(chckbxCsv, "cell 0 7");
		
		JLabel lblStandardFormatTo = new JLabel("Standard format to send is JSON");
		frame.getContentPane().add(lblStandardFormatTo, "cell 1 7");
		
		JButton btnTranslate = new JButton("translate");
		frame.getContentPane().add(btnTranslate, "cell 1 8");
	
		
	chckbxCsv.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			SharedClass.setXmlButtonClicked(!SharedClass.isXmlButtonClicked());
			
		}
		
	});
	
	
	btnTranslate.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			// display/center the jdialog when the button is pressed
			
			SharedClass.setTranslateButtonClicked(true);
			
			
			if(textField.getText().length()!=0) {
				SharedClass.setTranslateButtonClicked(false);
				SharedClass.setEnglish(textField.getText());
				SharedClass.setSpanish("");
				frame.dispose();
			}
			
			else if(textField_1.getText().length()!=0) {
				SharedClass.setTranslateButtonClicked(false);
				SharedClass.setSpanish(textField_1.getText());
				SharedClass.setEnglish("");
				frame.dispose();
			}
			
			
			else {
				SharedClass.setTranslateButtonClicked(false);
				noWords=true;
				frame.dispose();
				runGui();
				
			}
			
			
			
			
			

		}
	});

}
}
