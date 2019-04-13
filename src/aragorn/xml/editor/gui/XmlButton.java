package aragorn.xml.editor.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;
import aragorn.math.geometry.Coordinate2D;
import aragorn.math.geometry.LineSegment2D;
import aragorn.math.geometry.Oval;
import aragorn.math.geometry.Paintable;
import aragorn.math.geometry.Polyline2D;

@SuppressWarnings("serial")
abstract class XmlButton extends JButton {

	static class AssociationLine extends XmlButton {

		private static final LineSegment2D icon = new LineSegment2D(new Point2D.Double(0, 4), new Point2D.Double(8, 4));

		AssociationLine() {
			super("association line");
		}

		@Override
		protected Paintable getIconn() {
			return XmlButton.AssociationLine.icon;
		}
	}

	static class Class extends XmlButton {

		private static final Polyline2D icon = new Polyline2D(new Point2D.Double(0, 5), new Point2D.Double(0, 1), new Point2D.Double(8, 1), new Point2D.Double(8, 7),
				new Point2D.Double(0, 7), new Point2D.Double(0, 5), new Point2D.Double(8, 5), new Point2D.Double(8, 3), new Point2D.Double(0, 3));

		Class() {
			super("class");
		}

		@Override
		protected Paintable getIconn() {
			return XmlButton.Class.icon;
		}
	}

	static class CompositionLine extends XmlButton {

		private static final Polyline2D icon = new Polyline2D(new Point2D.Double(8, 4), new Point2D.Double(4, 4), new Point2D.Double(2, 2), new Point2D.Double(0, 4),
				new Point2D.Double(2, 6), new Point2D.Double(4, 4));

		CompositionLine() {
			super("composition line");
		}

		@Override
		protected Paintable getIconn() {
			return XmlButton.CompositionLine.icon;
		}
	}

	static class GeneralizationLine extends XmlButton {

		private static final Polyline2D icon = new Polyline2D(new Point2D.Double(8, 4), new Point2D.Double(2, 4), new Point2D.Double(2, 2), new Point2D.Double(0, 4),
				new Point2D.Double(2, 6), new Point2D.Double(2, 4));

		GeneralizationLine() {
			super("generalization line");
		}

		@Override
		protected Paintable getIconn() {
			return XmlButton.GeneralizationLine.icon;
		}
	}

	static class Select extends XmlButton {

		private static final Polyline2D icon = new Polyline2D(new Point2D.Double(189, 0), new Point2D.Double(189, 1020), new Point2D.Double(439, 770),
				new Point2D.Double(603, 1098), new Point2D.Double(747, 1026), new Point2D.Double(594, 720), new Point2D.Double(909, 720), new Point2D.Double(189, 0));

		Select() {
			super("select");
		}

		@Override
		protected Paintable getIconn() {
			return XmlButton.Select.icon;
		}
	}

	static class UseCase extends XmlButton {

		private static final Oval icon = new Oval(new Point2D.Double(4, 4), 8, 6);

		UseCase() {
			super("use case");
		}

		@Override
		protected Paintable getIconn() {
			return XmlButton.UseCase.icon;
		}
	}

	private static String encode(Color color) {
		return Integer.toHexString(color.getRGB()).substring(2);
	}

	private Color tool_tip_text_color = Color.DARK_GRAY;

	protected XmlButton(String function_name) {
		setSize(50);
		setToolTipText(function_name);
		setToolTipTextColor(Color.DARK_GRAY);
	}

	public Coordinate2D getFitCoordinate(int margin) {
		Dimension button_size = getSize();
		double unit = Math.min((button_size.width - 2.0 * margin) / getIconn().getBounds().getWidth(),
				(button_size.getHeight() - 2.0 * margin) / getIconn().getBounds().getHeight());
		double ox = button_size.getWidth() / 2.0 - unit * (getIconn().getBounds().getWidth() / 2.0 + getIconn().getBounds().getX());
		double oy = button_size.getHeight() / 2.0 - unit * (getIconn().getBounds().getHeight() / 2.0 + getIconn().getBounds().getY());
		return new Coordinate2D(new Point2D.Double(ox, oy), unit, -unit);
	}

	protected Rectangle2D.Double getIconBounds() {
		double length = Math.max(getIconn().getBounds().getWidth(), getIconn().getBounds().getHeight());
		return new Rectangle2D.Double(0, 0, length, length);
	}

	protected abstract Paintable getIconn();

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		getIconn().draw(g, getFitCoordinate(Math.min(getWidth(), getHeight()) / 10));
	}

	private void setSize(int length) {
		Dimension size = new Dimension(length, length);
		setMinimumSize(size);
		setPreferredSize(size);
	}

	@Override
	public void setToolTipText(String text) {
		super.setToolTipText("<html><p><font color=\"#" + encode(tool_tip_text_color) + "\" size=\"4\" face=\"SansSerif\">" + text + "</font></p></html>");
	}

	public void setToolTipTextColor(Color color) {
		this.tool_tip_text_color = color;
	}
}