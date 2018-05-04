package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;
import translatorIAP.SharedClass;

public class GuiTranslationPossible {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public void runGui() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiTranslationPossible window = new GuiTranslationPossible();
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
	public GuiTranslationPossible() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[][][][grow][grow]", "[][][grow][][][grow]"));
		
		JLabel lblYourWord = new JLabel("your Word: ");
		frame.getContentPane().add(lblYourWord, "cell 1 2");
		
		JLabel lblNewLabel = new JLabel(SharedClass.getWord());
		frame.getContentPane().add(lblNewLabel, "cell 3 2");
		
		JLabel lblTranslation = new JLabel("Translation");
		frame.getContentPane().add(lblTranslation, "cell 1 3");
		
		JLabel lblTranslation_1 = new JLabel(SharedClass.getTranslation());
		frame.getContentPane().add(lblTranslation_1, "cell 3 3");
		
		JLabel lblAnotherTranslation = new JLabel("Another translation?");
		frame.getContentPane().add(lblAnotherTranslation, "cell 1 5");
		
		JButton btnBack = new JButton("Back");
		frame.getContentPane().add(btnBack, "cell 3 5");
	
	
	
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// display/center the jdialog when the button is pressed
				
				SharedClass.setBackToTranslateButton(true);
				frame.dispose();
				

			}
		});
	}
}
