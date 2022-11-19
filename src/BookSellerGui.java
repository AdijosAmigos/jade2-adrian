package jadelab2;

import jade.core.AID;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class BookSellerGui extends JFrame {

	private BookSellerAgent myAgent;

	private JTextField titleField, priceField, shippingPriceField;

	BookSellerGui(BookSellerAgent agent) {
		super(agent.getLocalName());

		myAgent = agent;

		JPanel jPanel = new JPanel();
		jPanel.setLayout(new GridLayout(3, 2));

		jPanel.add(new JLabel(" Title:"));
		titleField = new JTextField(15);
		jPanel.add(titleField);

		jPanel.add(new JLabel(" Price:"));
		priceField = new JTextField(15);
		jPanel.add(priceField);

		jPanel.add(new JLabel(" Shipping:"));
		shippingPriceField = new JTextField(15);
		jPanel.add(shippingPriceField);

		getContentPane().add(jPanel, BorderLayout.CENTER);

		JButton addButton = new JButton("Add");
		addButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					String title = titleField.getText().trim();
					String price = priceField.getText().trim();
					String shippingPrice = shippingPriceField.getText().trim();

					myAgent.updateCatalogue(title, Integer.parseInt(price)+Integer.parseInt(shippingPrice));

					titleField.setText("");
					priceField.setText("");
					shippingPriceField.setText("");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(BookSellerGui.this, "Invalid values. " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		setVisible(true);
	}
}
