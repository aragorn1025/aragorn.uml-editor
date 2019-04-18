package aragorn.xml.editor.gui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.event.MouseInputAdapter;
import aragorn.xml.editor.objects.UmlClass;
import aragorn.xml.editor.objects.UmlUseCase;

public class CanvasMouseAdapter extends MouseInputAdapter {

	static class AssociationLine extends CanvasMouseAdapter {

		AssociationLine(CanvasArea parent) {
			super(parent);
		}

		@Override
		public void mouseDragged(MouseEvent event) {
			// TODO
		}

		@Override
		public void mousePressed(MouseEvent event) {
			// TODO
		}

		@Override
		public void mouseReleased(MouseEvent event) {
			// TODO
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
		public void mouseDragged(MouseEvent event) {
			// TODO
		}

		@Override
		public void mousePressed(MouseEvent event) {
			// TODO
		}

		@Override
		public void mouseReleased(MouseEvent event) {
			// TODO
		}
	}

	static class GeneralizationLine extends CanvasMouseAdapter {

		GeneralizationLine(CanvasArea parent) {
			super(parent);
		}

		@Override
		public void mouseDragged(MouseEvent event) {
			// TODO
		}

		@Override
		public void mousePressed(MouseEvent event) {
			// TODO
		}

		@Override
		public void mouseReleased(MouseEvent event) {
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
			Point released_point = event.getPoint();
			double min_x = Math.min(pressed_point.getX(), released_point.getX());
			double min_y = Math.min(pressed_point.getY(), released_point.getY());
			double max_x = Math.max(pressed_point.getX(), released_point.getX());
			double max_y = Math.max(pressed_point.getY(), released_point.getY());
			getParent().setDraggedBlock(new Rectangle2D.Double(min_x, min_y, max_x - min_x, max_y - min_y));
			getParent().repaint();
		}

		Point pressed_point = null;

		@Override
		public void mousePressed(MouseEvent event) {
			pressed_point = event.getPoint();
		}

		@Override
		public void mouseReleased(MouseEvent event) {
			getParent().setDraggedBlock(null);
			Point released_point = event.getPoint();
			if (released_point.equals(pressed_point)) {
				pressed_point = null;
				return;
			}
			getParent().clearSelectedUmlBasicObjects();
			double min_x = Math.min(pressed_point.getX(), released_point.getX());
			double min_y = Math.min(pressed_point.getY(), released_point.getY());
			double max_x = Math.max(pressed_point.getX(), released_point.getX());
			double max_y = Math.max(pressed_point.getY(), released_point.getY());
			Rectangle2D.Double bounds = new Rectangle2D.Double(min_x, min_y, max_x - min_x, max_y - min_y);
			for (int i = 0; i < getParent().getUmlObjectsNumber(); i++) {
				if (getParent().getUmlBasicObject(i).isIn(bounds)) {
					getParent().addSelectedUmlBasicObjects(getParent().getUmlBasicObject(i));
				}
			}
			getParent().repaint();
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

	protected CanvasMouseAdapter(CanvasArea parent) {
		this.parent = parent;
	}

	protected CanvasArea getParent() {
		return parent;
	}
}
