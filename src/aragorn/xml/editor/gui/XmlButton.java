package aragorn.xml.editor.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import javax.swing.JToggleButton;
import aragorn.math.geometry.Coordinate2D;
import aragorn.math.geometry.LineSegment2D;
import aragorn.math.geometry.Oval;
import aragorn.math.geometry.Paintable;
import aragorn.math.geometry.Polygon2D;
import aragorn.math.geometry.Polyline2D;

@SuppressWarnings("serial")
abstract class XmlButton extends JToggleButton {

	static class AssociationLine extends XmlButton {

		private static final Paintable ICON = new LineSegment2D(new Point2D.Double(0, 4), new Point2D.Double(8, 4));

		AssociationLine() {
			super("association line", XmlButton.AssociationLine.ICON);
		}

		@Override
		void clickedAction(Point point) { // TODO
		}
	}

	static class Class extends XmlButton {

		private static final Paintable ICON = new Polyline2D(new Point2D.Double(0, 5), new Point2D.Double(0, 1), new Point2D.Double(8, 1), new Point2D.Double(8, 7),
				new Point2D.Double(0, 7), new Point2D.Double(0, 5), new Point2D.Double(8, 5), new Point2D.Double(8, 3), new Point2D.Double(0, 3));

		Class() {
			super("class", XmlButton.Class.ICON);
		}

		@Override
		void clickedAction(Point point) { // TODO
		}
	}

	static class CompositionLine extends XmlButton {

		private static final Paintable ICON = new Polyline2D(new Point2D.Double(10, 5), new Point2D.Double(4, 5), new Point2D.Double(2, 3), new Point2D.Double(0, 5),
				new Point2D.Double(2, 7), new Point2D.Double(4, 5));

		CompositionLine() {
			super("composition line", XmlButton.CompositionLine.ICON);
		}

		@Override
		void clickedAction(Point point) { // TODO
		}
	}

	static class GeneralizationLine extends XmlButton {

		private static final Paintable ICON = new Polyline2D(new Point2D.Double(8, 4), new Point2D.Double(2, 4), new Point2D.Double(2, 2), new Point2D.Double(0, 4),
				new Point2D.Double(2, 6), new Point2D.Double(2, 4));

		GeneralizationLine() {
			super("generalization line", XmlButton.GeneralizationLine.ICON);
		}

		@Override
		void clickedAction(Point point) { // TODO
		}
	}

	static class Select extends XmlButton {

		private static final Paintable ICON = new Polygon2D(new Point2D.Double(189, 0), new Point2D.Double(189, 1020), new Point2D.Double(439, 770),
				new Point2D.Double(603, 1098), new Point2D.Double(747, 1026), new Point2D.Double(594, 720), new Point2D.Double(909, 720));

		Select() {
			super("select", XmlButton.Select.ICON);
		}

		@Override
		void clickedAction(Point point) { // TODO
			System.out.println(point);
		}
	}

	static class UseCase extends XmlButton {

		private static final Paintable ICON = new Oval(new Point2D.Double(4, 4), 8, 6);

		UseCase() {
			super("use case", XmlButton.UseCase.ICON);
		}

		@Override
		void clickedAction(Point point) { // TODO
		}
	}

	private static String encode(Color color) {
		return Integer.toHexString(color.getRGB()).substring(2);
	}

	private Color tool_tip_text_color = Color.DARK_GRAY;

	private String function_name;

	private Paintable icon;

	protected XmlButton(String function_name, Paintable icon) {
		this.function_name = function_name;
		this.icon = icon;

		setSize(50);
		setToolTipText(this.function_name);
	}

	abstract void clickedAction(Point point);

	private Coordinate2D getFitCoordinate(int margin) {
		Dimension button_size = getSize();
		double unit = Math.min((button_size.width - 2.0 * margin) / icon.getBounds().getWidth(), (button_size.getHeight() - 2.0 * margin) / icon.getBounds().getHeight());
		double ox = button_size.getWidth() / 2.0 - unit * (icon.getBounds().getWidth() / 2.0 + icon.getBounds().getX());
		double oy = button_size.getHeight() / 2.0 - unit * (icon.getBounds().getHeight() / 2.0 + icon.getBounds().getY());
		return new Coordinate2D(new Point2D.Double(ox, oy), unit, -unit);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(isSelected() ? Color.BLACK : Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(isSelected() ? Color.WHITE : Color.BLACK);
		icon.draw(g, getFitCoordinate(Math.min(getWidth(), getHeight()) / 10));
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
}