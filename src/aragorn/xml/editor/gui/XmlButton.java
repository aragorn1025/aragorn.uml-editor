package aragorn.xml.editor.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import javax.swing.JToggleButton;
import aragorn.math.geometry.Coordinate2D;
import aragorn.math.geometry.Paintable;
import aragorn.math.geometry.Polygon2D;
import aragorn.xml.editor.objects.UmlAssociationLine;
import aragorn.xml.editor.objects.UmlClass;
import aragorn.xml.editor.objects.UmlCompositionLine;
import aragorn.xml.editor.objects.UmlGeneralizationLine;
import aragorn.xml.editor.objects.UmlObject;
import aragorn.xml.editor.objects.UmlUseCase;

@SuppressWarnings("serial")
abstract class XmlButton extends JToggleButton {

	static class AssociationLine extends XmlButton {

		AssociationLine() {
			super(UmlAssociationLine.NAME, UmlAssociationLine.BUTTON_ICON);
		}

		@Override
		UmlObject draggedAction(Point point) {
			return super.draggedAction(point); // TODO
		}

		@Override
		UmlObject pressedAction(Point point) {
			return super.pressedAction(point); // TODO
		}

		@Override
		UmlObject releasedAction(Point point) {
			return super.releasedAction(point); // TODO
		}
	}

	static class Class extends XmlButton {

		Class() {
			super(UmlClass.NAME, UmlClass.BUTTON_ICON);
		}

		@Override
		UmlObject clickedAction(Point point) {
			System.out.printf("Create an UML object, class, at (%.0f, %.0f).%n", point.getX(), point.getY());
			return super.clickedAction(point); // TODO
		}
	}

	static class CompositionLine extends XmlButton {

		CompositionLine() {
			super(UmlCompositionLine.NAME, UmlCompositionLine.BUTTON_ICON);
		}

		@Override
		UmlObject draggedAction(Point point) {
			return super.draggedAction(point); // TODO
		}

		@Override
		UmlObject pressedAction(Point point) {
			return super.pressedAction(point); // TODO
		}

		@Override
		UmlObject releasedAction(Point point) {
			return super.releasedAction(point); // TODO
		}
	}

	static class GeneralizationLine extends XmlButton {

		GeneralizationLine() {
			super(UmlGeneralizationLine.NAME, UmlGeneralizationLine.BUTTON_ICON);
		}

		@Override
		UmlObject draggedAction(Point point) {
			return super.draggedAction(point); // TODO
		}

		@Override
		UmlObject pressedAction(Point point) {
			return super.pressedAction(point); // TODO
		}

		@Override
		UmlObject releasedAction(Point point) {
			return super.releasedAction(point); // TODO
		}
	}

	static class Select extends XmlButton {

		private static final Paintable ICON = new Polygon2D(new Point2D.Double(189, 0), new Point2D.Double(189, 1020), new Point2D.Double(439, 770),
				new Point2D.Double(603, 1098), new Point2D.Double(747, 1026), new Point2D.Double(594, 720), new Point2D.Double(909, 720));

		Select() {
			super("select", XmlButton.Select.ICON);
		}

		@Override
		UmlObject clickedAction(Point point) {
			return super.clickedAction(point); // TODO
		}

		@Override
		UmlObject draggedAction(Point point) {
			return super.draggedAction(point); // TODO
		}

		@Override
		UmlObject pressedAction(Point point) {
			return super.pressedAction(point); // TODO
		}

		@Override
		UmlObject releasedAction(Point point) {
			return super.releasedAction(point); // TODO
		}
	}

	static class UseCase extends XmlButton {

		UseCase() {
			super(UmlUseCase.NAME, UmlUseCase.BUTTON_ICON);
		}

		@Override
		UmlObject clickedAction(Point point) {
			System.out.printf("Create an UML basic object, use case, at (%.0f, %.0f).%n", point.getX(), point.getY());
			return super.clickedAction(point); // TODO
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

	UmlObject clickedAction(Point point) {
		return null;
	}

	UmlObject draggedAction(Point point) {
		return null;
	}

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

	UmlObject pressedAction(Point point) {
		return null;
	}

	UmlObject releasedAction(Point point) {
		return null;
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