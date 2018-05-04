package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import translatorIAP.SharedClass;

public class GuiTranslationFail {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public void runGui() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiTranslationFail window = new GuiTranslationFail();
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
	public GuiTranslationFail() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblSorryWeCouldnt = new JLabel("Sorry we couldn't find this word in our database ");
		frame.getContentPane().add(lblSorryWeCouldnt, BorderLayout.CENTER);
		
		JButton btnBack = new JButton("Back");
		frame.getContentPane().add(btnBack, BorderLayout.SOUTH);
		
		
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// display/center the jdialog when the button is pressed
				
				SharedClass.setBackToTranslateButton(true);
				frame.dispose();
				

			}
		});
		
	}
	
	
	
	
}
