package aragorn.xml.editor.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;
import aragorn.math.geometry.Coordinate2D;
import aragorn.math.geometry.Polygon2D;
import aragorn.math.geometry.Polyline2D;

@SuppressWarnings("serial")
abstract class XmlButton extends JButton {

	static class Select extends XmlButton {

		private static final Polygon2D icon = new Polygon2D(new Point2D.Double(0, 0), new Point2D.Double(0, 1020), new Point2D.Double(250, 770),
				new Point2D.Double(414, 1098), new Point2D.Double(558, 1026), new Point2D.Double(405, 720), new Point2D.Double(720, 720));

		Select() {
			super("select");
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Polygon2D p = getShiftIcon();
			double d = Math.max(p.getBounds().getWidth(), p.getBounds().getHeight()) * 5 / 4;
			p.draw(g, new Coordinate2D(new Point2D.Double(0, 0), getLength() / d, -getLength() / d));
		}

		private Polygon2D getShiftIcon() {
			Rectangle2D bounds = XmlButton.Select.icon.getBounds();
			double d = Math.abs(bounds.getWidth() - bounds.getHeight());
			double dx = 2 * (bounds.getHeight() - bounds.getWidth() + d) + Math.max(bounds.getWidth(), bounds.getHeight());
			double dy = 2 * (bounds.getWidth() - bounds.getHeight() + d) + Math.max(bounds.getWidth(), bounds.getHeight());
			Polyline2D shift_icon = new Polyline2D();
			for (int i = 0; i < XmlButton.Select.icon.getPointNumber(); i++) {
				Point2D p = XmlButton.Select.icon.getPoint(i);
				shift_icon.addPoint(p.getX() * 8 + dx, p.getY() * 8 + dy);
			}
			return new Polygon2D(shift_icon);
		}
	}

	static class AssociationLine extends XmlButton {

		AssociationLine() {
			super("association line");
		}
	}

	static class GeneralizationLine extends XmlButton {

		GeneralizationLine() {
			super("generalization line");
		}
	}

	static class CompositionLine extends XmlButton {

		CompositionLine() {
			super("composition line");
		}
	}

	static class Class extends XmlButton {

		Class() {
			super("class");
		}
	}

	static class UseCase extends XmlButton {

		UseCase() {
			super("use case");
		}
	}

	private Color tool_tip_text_color = Color.DARK_GRAY;

	protected XmlButton(String function_name) {
		setSize(50);
		setToolTipText(function_name);
		setToolTipTextColor(Color.DARK_GRAY);
	}

	protected void setSize(int length) {
		Dimension size = new Dimension(length, length);
		setMinimumSize(size);
		setPreferredSize(size);
	}

	protected int getLength() {
		return Math.min(getWidth(), getHeight());
	}

	public void setToolTipTextColor(Color color) {
		this.tool_tip_text_color = color;
	}

	private static String encode(Color color) {
		return Integer.toHexString(color.getRGB()).substring(2);
	}

	public void setToolTipText(String text) {
		super.setToolTipText("<html><p><font color=\"#" + encode(tool_tip_text_color) + "\" size=\"4\" face=\"SansSerif\">" + text + "</font></p></html>");
	}
}