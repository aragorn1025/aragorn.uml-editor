package aragorn.xml.editor.gui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.swing.event.MouseInputAdapter;
import aragorn.util.MathVector2D;
import aragorn.xml.editor.objects.UmlAssociationLine;
import aragorn.xml.editor.objects.UmlBasicObject;
import aragorn.xml.editor.objects.UmlClass;
import aragorn.xml.editor.objects.UmlCompositionLine;
import aragorn.xml.editor.objects.UmlConnectionPort;
import aragorn.xml.editor.objects.UmlGeneralizationLine;
import aragorn.xml.editor.objects.UmlUseCase;

class CanvasMouseAdapter extends MouseInputAdapter {

	static class AssociationLine extends CanvasMouseAdapter {

		AssociationLine(CanvasArea parent) {
			super(parent);
		}

		@Override
		public void mousePressed(MouseEvent event) {
			defaultMousePressed(event);
		}

		@Override
		public void mouseReleased(MouseEvent event) {
			defaultMouseReleased(event);
			if (!isUmlConnectLineShouldBeSet())
				return;
			getParent().addUmlConnectionLine(new UmlAssociationLine(getStartingObject(), getStartingConnectionPort(), getEndingObject(), getEndingConnectionPort()));
		}
	}

	static class Class extends CanvasMouseAdapter {

		Class(CanvasArea parent) {
			super(parent);
		}

		@Override
		public void mouseClicked(MouseEvent event) {
			getParent().addUmlBasicObject(new UmlClass(event.getPoint()));
		}
	}

	static class CompositionLine extends CanvasMouseAdapter {

		CompositionLine(CanvasArea parent) {
			super(parent);
		}

		@Override
		public void mousePressed(MouseEvent event) {
			defaultMousePressed(event);
		}

		@Override
		public void mouseReleased(MouseEvent event) {
			defaultMouseReleased(event);
			if (!isUmlConnectLineShouldBeSet())
				return;
			getParent().addUmlConnectionLine(new UmlCompositionLine(getStartingObject(), getStartingConnectionPort(), getEndingObject(), getEndingConnectionPort()));
		}
	}

	static class GeneralizationLine extends CanvasMouseAdapter {

		GeneralizationLine(CanvasArea parent) {
			super(parent);
		}

		@Override
		public void mousePressed(MouseEvent event) {
			defaultMousePressed(event);
		}

		@Override
		public void mouseReleased(MouseEvent event) {
			defaultMouseReleased(event);
			if (!isUmlConnectLineShouldBeSet())
				return;
			getParent().addUmlConnectionLine(new UmlGeneralizationLine(getStartingObject(), getStartingConnectionPort(), getEndingObject(), getEndingConnectionPort()));
		}
	}

	static class Select extends CanvasMouseAdapter {

		Select(CanvasArea parent) {
			super(parent);
		}

		@Override
		public void mouseClicked(MouseEvent event) {
			getParent().clearSelectedUmlBasicObjects();
			for (int i = 0; i < getParent().getUmlObjectsNumber(); i++) {
				if (getParent().getUmlBasicObject(i).isSurround(event.getPoint())) {
					getParent().addSelectedUmlBasicObjects(getParent().getUmlBasicObject(i));
				}
			}
			getParent().repaint();
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
				// set dragged block
				double min_x = Math.min(getMousePressedPoint().getX(), event.getPoint().getX());
				double min_y = Math.min(getMousePressedPoint().getY(), event.getPoint().getY());
				double max_x = Math.max(getMousePressedPoint().getX(), event.getPoint().getX());
				double max_y = Math.max(getMousePressedPoint().getY(), event.getPoint().getY());
				getParent().setDraggedBlock(new Rectangle2D.Double(min_x, min_y, max_x - min_x, max_y - min_y));
			}
			getParent().repaint();
		}

		@Override
		public void mousePressed(MouseEvent event) {
			defaultMousePressed(event);
		}

		@Override
		public void mouseReleased(MouseEvent event) {
			if (getParent().getSelectedUmlObjectsNumber() == 1 && getParent().getSelectedUmlBasicObject(0).isSurround(event.getPoint())) {

			} else {
				getParent().setDraggedBlock(null);
				Point mouse_released_point = event.getPoint();
				if (mouse_released_point.equals(getMousePressedPoint())) {
					resetPressedReleased();
					return;
				}
				getParent().clearSelectedUmlBasicObjects();
				double min_x = Math.min(getMousePressedPoint().getX(), mouse_released_point.getX());
				double min_y = Math.min(getMousePressedPoint().getY(), mouse_released_point.getY());
				double max_x = Math.max(getMousePressedPoint().getX(), mouse_released_point.getX());
				double max_y = Math.max(getMousePressedPoint().getY(), mouse_released_point.getY());
				Rectangle2D.Double bounds = new Rectangle2D.Double(min_x, min_y, max_x - min_x, max_y - min_y);
				for (int i = 0; i < getParent().getUmlObjectsNumber(); i++) {
					if (getParent().getUmlBasicObject(i).isSurroundedBy(bounds)) {
						getParent().addSelectedUmlBasicObjects(getParent().getUmlBasicObject(i));
					}
				}
				resetPressedReleased();
				getParent().repaint();
			}
		}
	}

	static class UseCase extends CanvasMouseAdapter {

		UseCase(CanvasArea parent) {
			super(parent);
		}

		@Override
		public void mouseClicked(MouseEvent event) {
			getParent().addUmlBasicObject(new UmlUseCase(event.getPoint()));
		}
	}

	private CanvasArea parent;

	private UmlBasicObject mouse_pressed_object = null;

	private Point2D.Double mouse_pressed_object_initial_location = null;

	private Point mouse_pressed_point = null;

	private Point mouse_released_point = null;

	private UmlBasicObject mouse_released_object = null;

	protected CanvasMouseAdapter(CanvasArea parent) {
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

	protected UmlConnectionPort getEndingConnectionPort() {
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

	protected CanvasArea getParent() {
		return parent;
	}

	protected UmlConnectionPort getStartingConnectionPort() {
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