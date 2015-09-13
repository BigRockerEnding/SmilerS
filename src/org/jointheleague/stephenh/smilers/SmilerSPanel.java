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
	private static final Dimension US_LETTER = new Dimension((int)(8.5 * INCH), 11 * INCH);
	private SmilerGraphic smiler = new SmilerGraphic();
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new SmilerSPanel());
	}

	@Override
	public void run() {
		JFrame frame = new JFrame("Smiler S");
		frame.add(this);
		this.setPreferredSize(US_LETTER);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		this.setBackground(Color.WHITE);
		for (int i = INCH / 2; i < getWidth(); i += INCH / 2) {
			g2.drawLine(i, 0, i, getHeight());
		}
		for (int i = INCH / 2; i < getHeight(); i += INCH / 2) {
			g2.drawLine(0, i, getWidth(), i);
		}
		g2.translate(INCH, 0);
		g2.rotate(Math.PI / 2);
		smiler.drawSelf(g2);
	}
}
