package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import net.miginfocom.swing.MigLayout;

public class GuiPleaseWait {

	private JFrame frame;
	private JLabel lblPleaseWait;
	private JFrame framePointer;

	/**
	 * Launch the application.
	 */
	public void runGui() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiPleaseWait window = new GuiPleaseWait();
					window.frame.setVisible(true);
					framePointer= window.frame;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GuiPleaseWait() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 250, 142);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[][][][][][][]", "[][][][]"));
		
		lblPleaseWait = new JLabel("Please Wait");
		frame.getContentPane().add(lblPleaseWait, "cell 6 3");
		
		
	}
	
	public void stopGui() {
		frame.removeAll();
		frame.dispose();
		framePointer.dispose();
	}
}
