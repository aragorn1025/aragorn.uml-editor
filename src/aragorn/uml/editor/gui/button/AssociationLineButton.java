package aragorn.uml.editor.gui.button;

import java.awt.geom.Point2D;
import aragorn.math.geometry.LineSegment2D;
import aragorn.math.geometry.Paintable;
import aragorn.uml.editor.gui.UmlButton;
import aragorn.uml.editor.gui.UmlCanvas;
import aragorn.uml.editor.gui.mode.AssociationLineMode;

@SuppressWarnings("serial")
public class AssociationLineButton extends UmlButton {

	private static final Paintable ICON = new LineSegment2D(new Point2D.Double(0, 5), new Point2D.Double(10, 5));

	public AssociationLineButton(UmlCanvas canvas_area) {
		super(canvas_area, new AssociationLineMode(canvas_area), AssociationLineButton.ICON);
	}
}