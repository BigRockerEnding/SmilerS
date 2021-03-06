package org.jointheleague.stephenh.smilers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.time.LocalTime;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import static java.lang.Math.toRadians;

@SuppressWarnings("serial")
public class SmilerSPanel extends JPanel implements Runnable {
	private static final int INCH = 72; //points
	private static final Dimension US_LETTER = new Dimension((int)(8.5 * INCH), 11 * INCH);
	private SmilerGraphic smiler = new SmilerGraphic();
	private Stroke fatStroke = new BasicStroke(5);
	private BufferedImage back;
	
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
		new Timer(20, e -> repaint()).start();
		frame.pack();
		back = buildBack();
		frame.setVisible(true);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(back, 0, 0, null);
		g2.translate(getWidth() / 2, getHeight() / 2);
		LocalTime now = LocalTime.now();
		int hour = now.getHour();
		int min = now.getMinute();
		int sec = now.getSecond();
		AffineTransform orgin = g2.getTransform();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		drawHour(g2, hour, min);
		g2.setTransform(orgin);
		drawMin(g2, min, sec);
		g2.setTransform(orgin);
		drawSec(g2, sec);
		g2.translate(-INCH / 2, -INCH / 2);
	}

	private BufferedImage buildBack() {
		BufferedImage back = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = back.createGraphics();
		g2.setColor(Color.BLACK);
		g2.translate(back.getWidth() / 2, back.getHeight() / 2);
		AffineTransform orgin = g2.getTransform();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for (int i = 0; i < 60; i++) {
			g2.rotate(toRadians(360 / 60 * i));
			if (i % 5 == 0) {
				g2.setStroke(fatStroke);
				g2.drawLine((int)(3.5 * INCH), 0, (int)(4 * INCH), 0);
			} else {
				g2.setStroke(new BasicStroke(3));
				g2.drawLine((int)(3.5 * INCH), 0, (int)(3.75 * INCH), 0);
			}
			g2.setTransform(orgin);
		}
		return back;
	}
	
	private void drawHour(Graphics2D g2, int hour, int min) {
		double sHour = hour + min / 60.0;
		double angleHand = (360 / 12.0) * sHour;
		g2.rotate(toRadians(angleHand));
		g2.setStroke(fatStroke);
		g2.drawLine(0, 0, 0, (int)(-1.5 * INCH));
		g2.translate(-INCH / 2, -2.5 * INCH);
		smiler.drawSelf(g2);
	}
	
	private void drawMin(Graphics2D g2, int min, int sec) {
		double sMin = min + sec / 60.0;
		double angleHand = (360 / 60.0) * sMin;
		g2.rotate(toRadians(angleHand));
		g2.setStroke(fatStroke);
		g2.setColor(Color.BLACK);
		g2.drawLine(0, 0, 0, (int)(-2 * INCH));
		g2.translate(-INCH / 2, -3 * INCH);
		smiler.drawSelf(g2);
	}
	
	private void drawSec(Graphics2D g2, int sec) {
		double angleHand = (360 / 60.0) * sec;
		g2.rotate(toRadians(angleHand));
		g2.setStroke(fatStroke);
		g2.setColor(Color.RED);
		g2.drawLine(0, 0, 0, (int)(-2.5 * INCH));
		g2.translate(-INCH / 2, -3.5 * INCH);
		smiler.drawSelf(g2);
	}
}
