package aragorn.xml.editor.gui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.event.MouseInputAdapter;
import aragorn.xml.editor.objects.UmlBasicObject;
import aragorn.xml.editor.objects.UmlClass;
import aragorn.xml.editor.objects.UmlConnectionPort;
import aragorn.xml.editor.objects.UmlUseCase;

public class CanvasMouseAdapter extends MouseInputAdapter {

	static class AssociationLine extends CanvasMouseAdapter {

		private UmlBasicObject starting_object = null;

		private UmlConnectionPort starting_connection_port = null;

		AssociationLine(CanvasArea parent) {
			super(parent);
		}

		@Override
		public void mousePressed(MouseEvent event) {
			for (int i = 0; i < getParent().getUmlObjectsNumber(); i++) {
				if (getParent().getUmlBasicObject(i).isSurround(event.getPoint())) {
					starting_object = getParent().getUmlBasicObject(i);
					starting_connection_port = getParent().getUmlBasicObject(i).getCorrespondingConnectPort(event.getPoint());
					return;
				}
			}
			starting_object = null;
			starting_connection_port = null;
		}

		@Override
		public void mouseReleased(MouseEvent event) {
			if (starting_object == null || starting_connection_port == null)
				return;
			Point released_point = event.getPoint();
			// TODO
		}
	}

	static class Class extends CanvasMouseAdapter {

		Class(CanvasArea parent) {
			super(parent);
		}

		@Override
		public void mouseClicked(MouseEvent event) {
			getParent().addUmlBasicObject(new UmlClass(event.getPoint(), 0)); // TODO depth should be set
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
			Point released_point = event.getPoint();
			// TODO
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
			Point released_point = event.getPoint();
			// TODO
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
			double min_x = Math.min(getMousePressedPoint().getX(), event.getPoint().getX());
			double min_y = Math.min(getMousePressedPoint().getY(), event.getPoint().getY());
			double max_x = Math.max(getMousePressedPoint().getX(), event.getPoint().getX());
			double max_y = Math.max(getMousePressedPoint().getY(), event.getPoint().getY());
			getParent().setDraggedBlock(new Rectangle2D.Double(min_x, min_y, max_x - min_x, max_y - min_y));
			getParent().repaint();
		}

		@Override
		public void mousePressed(MouseEvent event) {
			super.defaultMousePressed(event);
		}

		@Override
		public void mouseReleased(MouseEvent event) {
			getParent().setDraggedBlock(null);
			Point mouse_released_point = event.getPoint();
			if (mouse_released_point.equals(getMousePressedPoint())) {
				resetMousePressedPoint();
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
			resetMousePressedPoint();
			getParent().repaint();
		}
	}

	static class UseCase extends CanvasMouseAdapter {

		UseCase(CanvasArea parent) {
			super(parent);
		}

		@Override
		public void mouseClicked(MouseEvent event) {
			getParent().addUmlBasicObject(new UmlUseCase(event.getPoint(), 0)); // TODO depth should be set
		}
	}

	private CanvasArea parent;

	private Point mouse_pressed_point = null;

	protected CanvasMouseAdapter(CanvasArea parent) {
		this.parent = parent;
	}

	protected void defaultMousePressed(MouseEvent event) {
		super.mousePressed(event);
		mouse_pressed_point = event.getPoint();
	}

	protected void defaultMouseReleased(MouseEvent event) {
		super.mouseReleased(event);
		// TODO
	}

	protected Point getMousePressedPoint() {
		return mouse_pressed_point;
	}

	protected CanvasArea getParent() {
		return parent;
	}

	protected void resetMousePressedPoint() {
		this.mouse_pressed_point = null;
	}
}