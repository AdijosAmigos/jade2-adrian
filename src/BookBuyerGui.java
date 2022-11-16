package jadelab2;

import jade.core.AID;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class BookBuyerGui extends JFrame {

	private BookBuyerAgent myAgent;

	private JTextField titleField;

	BookBuyerGui(BookBuyerAgent agent) {
		super(agent.getLocalName());

		myAgent = agent;

		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridLayout(2, 2));

		jPanel.add(new JLabel(" Title:"));
		titleField = new JTextField(15);
		jPanel.add(titleField);

		getContentPane().add(jPanel, BorderLayout.CENTER);

		JButton addButton = new JButton("Search");
		addButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					String title = titleField.getText().trim();
					myAgent.lookForTitle(title);
					titleField.setText("");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(BookBuyerGui.this, "Invalid values. " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		} );
		jPanel = new JPanel();
		jPanel.add(addButton);
		getContentPane().add(jPanel, BorderLayout.SOUTH);

		addWindowListener(new	WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				myAgent.doDelete();
			}
		} );

		setResizable(false);
	}

	public void display() {
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)screenSize.getWidth() / 2;
		int centerY = (int)screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 3, centerY - getHeight() / 3);
		setVisible(true);
	}
}
