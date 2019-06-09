package aragorn.uml.editor.gui.button;

import java.awt.geom.Point2D;
import aragorn.math.geometry.Paintable;
import aragorn.math.geometry.Polygon2D;
import aragorn.uml.editor.gui.UmlButton;
import aragorn.uml.editor.gui.UmlCanvas;
import aragorn.uml.editor.gui.mode.SelectMode;

@SuppressWarnings("serial")
public class SelectButton extends UmlButton {

	private static final Paintable ICON = new Polygon2D(new Point2D.Double(189, 0), new Point2D.Double(189, 1020), new Point2D.Double(439, 770),
			new Point2D.Double(603, 1098), new Point2D.Double(747, 1026), new Point2D.Double(594, 720), new Point2D.Double(909, 720));

	public SelectButton(UmlCanvas canvas_area) {
		super(canvas_area, new SelectMode(canvas_area), SelectButton.ICON);
	}
}