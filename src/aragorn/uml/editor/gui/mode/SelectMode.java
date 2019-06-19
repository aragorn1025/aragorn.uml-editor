package aragorn.uml.editor.gui.mode;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import aragorn.uml.editor.gui.UmlCanvas;
import aragorn.uml.editor.gui.UmlMode;
import aragorn.uml.editor.object.UmlDraggedBox;
import aragorn.util.MathVector2D;

public class SelectMode extends UmlMode {

	public SelectMode(UmlCanvas parent) {
		super(parent, "select");
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		clearSelectedUmlBasicObjects();
		for (int i = 0; i < getUmlObjectsNumber(); i++) {
			if (getUmlBasicObject(i).isSurround(event.getPoint())) {
				addSelectedUmlBasicObjects(getUmlBasicObject(i));
			}
		}
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		if (getMousePressedPoint() == null)
			throw new InternalError("Unknown error.");
		if (getStartingObject() != null) {
			Point2D.Double starting_point = new Point2D.Double(getMousePressedPoint().getX(), getMousePressedPoint().getY());
			Point2D.Double ending_point = new Point2D.Double(event.getPoint().getX(), event.getPoint().getY());
			Point2D.Double new_location = MathVector2D.add(getMousePressedObjectInitialLocation(), new MathVector2D(starting_point, ending_point));
			getStartingObject().setLocation(new_location.getX(), new_location.getY());
		} else {
			setDraggedBoxCurrentPoint(event.getPoint());
		}
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent event) {
		super.mousePressed(event);
		setDraggedBoxPressedPoint(event.getPoint());
		setDraggedBoxCurrentPoint(event.getPoint());
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		setDraggedBoxPressedPoint(null);
		setDraggedBoxCurrentPoint(null);
		if (getSelectedUmlObjectsNumber() == 1 && getSelectedUmlBasicObject(0).isSurround(event.getPoint()))
			return;
		Point mouse_released_point = event.getPoint();
		if (mouse_released_point.equals(getMousePressedPoint())) {
			resetPressedReleased();
			return;
		}
		clearSelectedUmlBasicObjects();
		UmlDraggedBox dragged_box = new UmlDraggedBox(getMousePressedPoint(), event.getPoint());
		for (int i = 0; i < getUmlObjectsNumber(); i++) {
			if (getUmlBasicObject(i).isSurroundedBy(dragged_box)) {
				addSelectedUmlBasicObjects(getUmlBasicObject(i));
			}
		}
		resetPressedReleased();
		repaint();
	}
}