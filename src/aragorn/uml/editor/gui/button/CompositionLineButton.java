package aragorn.uml.editor.gui.button;

import java.awt.geom.Point2D;
import aragorn.math.geometry.Paintable;
import aragorn.math.geometry.Polyline2D;
import aragorn.uml.editor.gui.UmlButton;
import aragorn.uml.editor.gui.UmlCanvas;
import aragorn.uml.editor.gui.mode.CompositionLineMode;

@SuppressWarnings("serial")
public class CompositionLineButton extends UmlButton {

	private static final Paintable ICON = new Polyline2D(new Point2D.Double(10, 5), new Point2D.Double(4, 5), new Point2D.Double(2, 3), new Point2D.Double(0, 5),
			new Point2D.Double(2, 7), new Point2D.Double(4, 5));

	public CompositionLineButton(UmlCanvas canvas_area) {
		super(canvas_area, new CompositionLineMode(canvas_area), CompositionLineButton.ICON);
	}
}