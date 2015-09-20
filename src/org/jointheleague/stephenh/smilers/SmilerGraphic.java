package org.jointheleague.stephenh.smilers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class SmilerGraphic {
	private static final int INCH = 72; //points
	private static final int UNIT = INCH / 8;
	
	public void drawSelf(Graphics2D g2) {
		g2.setStroke(new BasicStroke(5));
		g2.setColor(Color.GRAY);
		g2.fillOval(0, 0, INCH, INCH);
		g2.setColor(Color.RED);
		g2.drawArc(UNIT, UNIT, 6 * UNIT, 6 * UNIT, 225, 90);
		g2.setColor(new Color(138, 43, 226));
		g2.fillOval(2 * UNIT, UNIT, UNIT, 2 * UNIT);
		g2.setColor(Color.BLUE);
		g2.fillOval(5 * UNIT, UNIT, UNIT, 2 * UNIT);
	}
}
