package aragorn.uml.editor.gui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import javax.swing.event.MouseInputAdapter;
import aragorn.uml.editor.object.UmlBasicObject;
import aragorn.uml.editor.object.UmlLineObject;
import aragorn.uml.editor.object.UmlPortDirection;

public class UmlMode extends MouseInputAdapter {

	private UmlCanvas parent;

	private UmlBasicObject mouse_pressed_object = null;

	private Point2D.Double mouse_pressed_object_initial_location = null;

	private Point mouse_pressed_point = null;

	private Point mouse_released_point = null;

	private UmlBasicObject mouse_released_object = null;

	private String name;

	protected UmlMode(UmlCanvas parent, String name) {
		this.parent = parent;
		this.name = name;
	}

	protected void addSelectedUmlBasicObjects(UmlBasicObject uml_basic_object) {
		parent.addSelectedUmlBasicObjects(uml_basic_object);
	}

	protected void addUmlBasicObject(UmlBasicObject uml_basic_object) {
		parent.addUmlBasicObject(uml_basic_object);
	}

	protected void addUmlLineObject(UmlLineObject uml_line_object) {
		parent.addUmlLineObject(uml_line_object);
	}

	protected void clearSelectedUmlBasicObjects() {
		parent.clearSelectedUmlBasicObjects();
	}

	protected void defaultMousePressed(MouseEvent event) {
		resetPressedReleased();
		mouse_pressed_point = event.getPoint();
		for (int i = parent.getUmlObjectsNumber() - 1; i >= 0; i--) {
			if (parent.getUmlBasicObject(i).isSurround(mouse_pressed_point)) {
				mouse_pressed_object = parent.getUmlBasicObject(i);
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
		for (int i = parent.getUmlObjectsNumber() - 1; i >= 0; i--) {
			if (parent.getUmlBasicObject(i).isSurround(mouse_released_point)) {
				mouse_released_object = parent.getUmlBasicObject(i);
				return;
			}
		}
		resetPressedReleased();
	}

	protected UmlPortDirection getEndingConnectionPort() {
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

	String getName() {
		return name;
	}

	protected UmlBasicObject getSelectedUmlBasicObject(int index) {
		return parent.getSelectedUmlBasicObject(index);
	}

	protected int getSelectedUmlObjectsNumber() {
		return parent.getSelectedUmlObjectsNumber();
	}

	protected UmlPortDirection getStartingConnectionPort() {
		return getStartingObject().getCorrespondingConnectPort(mouse_pressed_point);
	}

	protected UmlBasicObject getStartingObject() {
		return mouse_pressed_object;
	}

	protected UmlBasicObject getUmlBasicObject(int index) {
		return parent.getUmlBasicObject(index);
	}

	protected int getUmlObjectsNumber() {
		return parent.getUmlObjectsNumber();
	}

	protected boolean isUmlConnectLineShouldBeSet() {
		if (mouse_pressed_point == null || mouse_released_point == null)
			return false;
		if (mouse_pressed_object == null && mouse_released_object == null)
			return false;
		return (!getStartingObject().equals(getEndingObject()) || !getStartingConnectionPort().equals(getEndingConnectionPort()));
	}

	protected void repaint() {
		parent.repaint();
	}

	protected void resetPressedReleased() {
		mouse_pressed_point = null;
		mouse_released_point = null;
		mouse_pressed_object_initial_location = null;
		mouse_pressed_object = null;
		mouse_released_object = null;
	}

	protected void setDraggedBoxCurrentPoint(Point current_point) {
		parent.setDraggedBoxCurrentPoint(current_point);
	}

	protected void setDraggedBoxPressedPoint(Point pressed_point) {
		parent.setDraggedBoxPressedPoint(pressed_point);
	}
}