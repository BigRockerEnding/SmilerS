package org.jointheleague.stephenh.smilers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class SmilerSPanel extends JPanel implements Runnable {
	private static final int INCH = 72; //points
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new SmilerSPanel());
	}

	@Override
	public void run() {
		JFrame frame = new JFrame("Smiler S");
		frame.add(this);
		frame.setPreferredSize(new Dimension(500, 500));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		this.setBackground(Color.BLACK);
		g2.setStroke(new BasicStroke(5));
		g2.setColor(Color.YELLOW);
		g2.fillOval(0, 0, getWidth(), getHeight());
		g2.setColor(new Color(138, 43, 226));
		g2.drawArc(125, 125, 250, 250, 225, 90);
		g2.fillOval(125, 50, 62, 125);
		g2.fillOval(281, 50, 62, 125);
	}
}
