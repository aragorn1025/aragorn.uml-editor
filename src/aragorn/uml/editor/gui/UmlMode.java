package aragorn.uml.editor.gui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import javax.swing.event.MouseInputAdapter;
import aragorn.uml.editor.object.UmlBasicObject;
import aragorn.uml.editor.object.UmlPort;

public class UmlMode extends MouseInputAdapter {

	private UmlCanvas parent;

	private UmlBasicObject mouse_pressed_object = null;

	private Point2D.Double mouse_pressed_object_initial_location = null;

	private Point mouse_pressed_point = null;

	private Point mouse_released_point = null;

	private UmlBasicObject mouse_released_object = null;

	protected UmlMode(UmlCanvas parent) {
		this.parent = parent;
	}

	protected void defaultMousePressed(MouseEvent event) {
		resetPressedReleased();
		mouse_pressed_point = event.getPoint();
		for (int i = getParent().getUmlObjectsNumber() - 1; i >= 0; i--) {
			if (getParent().getUmlBasicObject(i).isSurround(mouse_pressed_point)) {
				mouse_pressed_object = getParent().getUmlBasicObject(i);
				mouse_pressed_object_initial_location = mouse_pressed_object.getLocation();
				return;
			}
		}
	}

	protected void defaultMouseReleased(MouseEvent event) {
		if (mouse_pressed_point == null || mouse_pressed_object == null) {
			resetPressedReleased();
			return;
		}
		mouse_released_point = event.getPoint();
		mouse_released_object = null;
		for (int i = getParent().getUmlObjectsNumber() - 1; i >= 0; i--) {
			if (getParent().getUmlBasicObject(i).isSurround(mouse_released_point)) {
				mouse_released_object = getParent().getUmlBasicObject(i);
				return;
			}
		}
		resetPressedReleased();
	}

	protected UmlPort getEndingConnectionPort() {
		return getEndingObject().getCorrespondingConnectPort(mouse_released_point);
	}

	protected UmlBasicObject getEndingObject() {
		return mouse_released_object;
	}

	protected Point2D.Double getMousePressedObjectInitialLocation() {
		return mouse_pressed_object_initial_location;
	}

	protected Point getMousePressedPoint() {
		return mouse_pressed_point;
	}

	protected UmlCanvas getParent() {
		return parent;
	}

	protected UmlPort getStartingConnectionPort() {
		return getStartingObject().getCorrespondingConnectPort(mouse_pressed_point);
	}

	protected UmlBasicObject getStartingObject() {
		return mouse_pressed_object;
	}

	protected boolean isUmlConnectLineShouldBeSet() {
		if (mouse_pressed_point == null || mouse_released_point == null)
			return false;
		if (mouse_pressed_object == null && mouse_released_object == null)
			return false;
		return (!getStartingObject().equals(getEndingObject()) || !getStartingConnectionPort().equals(getEndingConnectionPort()));
	}

	protected void resetPressedReleased() {
		mouse_pressed_point = null;
		mouse_released_point = null;
		mouse_pressed_object_initial_location = null;
		mouse_pressed_object = null;
		mouse_released_object = null;
	}
}