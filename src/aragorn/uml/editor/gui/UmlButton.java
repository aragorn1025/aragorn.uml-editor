package aragorn.uml.editor.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import javax.swing.JToggleButton;
import aragorn.math.geometry.Coordinate2D;
import aragorn.math.geometry.Paintable;
import aragorn.math.geometry.Polygon2D;
import aragorn.uml.editor.objects.UmlAssociationLine;
import aragorn.uml.editor.objects.UmlClass;
import aragorn.uml.editor.objects.UmlCompositionLine;
import aragorn.uml.editor.objects.UmlGeneralizationLine;
import aragorn.uml.editor.objects.UmlUseCase;

@SuppressWarnings("serial")
class UmlButton extends JToggleButton implements ActionListener {

	static class AssociationLine extends UmlButton {

		AssociationLine(CanvasArea canvas_area) {
			super(canvas_area, new CanvasMouseAdapter.AssociationLine(canvas_area), UmlAssociationLine.NAME, UmlAssociationLine.BUTTON_ICON);
		}
	}

	static class Class extends UmlButton {

		Class(CanvasArea canvas_area) {
			super(canvas_area, new CanvasMouseAdapter.Class(canvas_area), UmlClass.NAME, UmlClass.BUTTON_ICON);
		}
	}

	static class CompositionLine extends UmlButton {

		CompositionLine(CanvasArea canvas_area) {
			super(canvas_area, new CanvasMouseAdapter.CompositionLine(canvas_area), UmlCompositionLine.NAME, UmlCompositionLine.BUTTON_ICON);
		}
	}

	static class GeneralizationLine extends UmlButton {

		GeneralizationLine(CanvasArea canvas_area) {
			super(canvas_area, new CanvasMouseAdapter.GeneralizationLine(canvas_area), UmlGeneralizationLine.NAME, UmlGeneralizationLine.BUTTON_ICON);
		}
	}

	static class Select extends UmlButton {

		private static final Paintable ICON = new Polygon2D(new Point2D.Double(189, 0), new Point2D.Double(189, 1020), new Point2D.Double(439, 770),
				new Point2D.Double(603, 1098), new Point2D.Double(747, 1026), new Point2D.Double(594, 720), new Point2D.Double(909, 720));

		Select(CanvasArea canvas_area) {
			super(canvas_area, new CanvasMouseAdapter.Select(canvas_area), "select", UmlButton.Select.ICON);
		}
	}

	static class UseCase extends UmlButton {

		UseCase(CanvasArea canvas_area) {
			super(canvas_area, new CanvasMouseAdapter.UseCase(canvas_area), UmlUseCase.NAME, UmlUseCase.BUTTON_ICON);
		}
	}

	private static String encode(Color color) {
		return Integer.toHexString(color.getRGB()).substring(2);
	}

	private Color tool_tip_text_color = Color.DARK_GRAY;

	private String function_name;

	private Paintable icon;

	private CanvasArea canvas_area;

	private CanvasMouseAdapter mouse_adapter;

	protected UmlButton(CanvasArea canvas_area, CanvasMouseAdapter mouse_adapter, String function_name, Paintable icon) {
		this.canvas_area = canvas_area;
		this.mouse_adapter = mouse_adapter;
		this.function_name = function_name;
		this.icon = icon;

		setSize(50);
		setToolTipText(this.function_name);
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		canvas_area.setCanvasMouseAdapter(mouse_adapter);
	}

	private Coordinate2D getFitCoordinate(int margin) {
		Dimension button_size = getSize();
		double unit = Math.min((button_size.width - 2.0 * margin) / icon.getBounds().getWidth(), (button_size.getHeight() - 2.0 * margin) / icon.getBounds().getHeight());
		double ox = button_size.getWidth() / 2.0 - unit * (icon.getBounds().getWidth() / 2.0 + icon.getBounds().getX());
		double oy = button_size.getHeight() / 2.0 - unit * (icon.getBounds().getHeight() / 2.0 + icon.getBounds().getY());
		return new Coordinate2D(new Point2D.Double(ox, oy), unit, -unit);
	}

	@Override
	protected final void paintComponent(Graphics g) {
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
	public final void setToolTipText(String text) {
		super.setToolTipText("<html><p><font color=\"#" + encode(tool_tip_text_color) + "\" size=\"4\" face=\"SansSerif\">" + text + "</font></p></html>");
	}
}