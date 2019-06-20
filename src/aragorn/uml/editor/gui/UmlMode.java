package aragorn.uml.editor.gui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import javax.swing.event.MouseInputAdapter;
import aragorn.uml.editor.object.UmlBasicObject;
import aragorn.uml.editor.object.UmlPort;
import aragorn.util.MathVector2D;

public class UmlMode extends MouseInputAdapter {

	private UmlCanvas parent;

	private String name;

	private Point mouse_pressed_point = null;

	private Point mouse_released_point = null;

	private UmlBasicObject starting_object = null;

	private UmlBasicObject ending_object = null;

	private MathVector2D starting_object_vector = null;

	protected UmlMode(UmlCanvas parent, String name) {
		this.parent = parent;
		this.name = name;
	}

	protected UmlBasicObject getEndingObject() {
		return ending_object;
	}

	protected UmlPort getEndingPort() {
		return getEndingObject().getCorrespondingPort(mouse_released_point);
	}

	protected Point getMousePressedPoint() {
		return mouse_pressed_point;
	}

	String getName() {
		return name;
	}

	protected UmlCanvas getParent() {
		return parent;
	}

	protected UmlBasicObject getStartingObject() {
		return starting_object;
	}

	protected MathVector2D getStartingObjectVector() {
		return starting_object_vector;
	}

	protected UmlPort getStartingPort() {
		return getStartingObject().getCorrespondingPort(mouse_pressed_point);
	}

	protected boolean isUmlConnectLineShouldBeSet() {
		if (mouse_pressed_point == null || mouse_released_point == null)
			return false;
		if (starting_object == null || ending_object == null)
			return false;
		return !getStartingPort().equals(getEndingPort());
	}

	@Override
	public void mousePressed(MouseEvent event) {
		resetPressed();
		resetReleased();
		mouse_pressed_point = event.getPoint();
		for (int i = parent.getUmlObjectsNumber() - 1; i >= 0; i--) {
			if (parent.getUmlBasicObject(i).isSurround(mouse_pressed_point)) {
				starting_object = parent.getUmlBasicObject(i);
				setStartingObjectVector();
				return;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		resetReleased();
		mouse_released_point = event.getPoint();
		for (int i = parent.getUmlObjectsNumber() - 1; i >= 0; i--) {
			if (parent.getUmlBasicObject(i).isSurround(mouse_released_point)) {
				ending_object = parent.getUmlBasicObject(i);
				return;
			}
		}
	}

	private void resetPressed() {
		mouse_released_point = null;
		ending_object = null;
	}

	private void resetReleased() {
		mouse_pressed_point = null;
		starting_object = null;
		starting_object_vector = null;
	}

	private void setStartingObjectVector() {
		if (mouse_pressed_point == null || starting_object == null) {
			starting_object_vector = null;
		} else {
			Point2D.Double p = new Point2D.Double(mouse_pressed_point.getX(), mouse_pressed_point.getY());
			starting_object_vector = new MathVector2D(starting_object.getLocation(), p);
		}
	}
}