package aragorn.xml.editor.gui;

import java.awt.Point;
import java.awt.event.MouseEvent;
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
			for (int i = 0; i < getParent().getUmlObjectsNumber(); i++) {
				if (getParent().getUmlBasicObject(i).isSurround(event.getPoint())) {
					getParent().setSelectedUmlBasicObject(i);
					return;
				}
			}
			getParent().setSelectedUmlBasicObject(CanvasArea.NO_SELECTED_UML_BASIC_OBJECT);
		}

		@Override
		public void mouseDragged(MouseEvent event) {
			// TODO
		}

		Point pressed_point = null;

		@Override
		public void mousePressed(MouseEvent event) {
			pressed_point = event.getPoint();
		}

		@Override
		public void mouseReleased(MouseEvent event) {
			Point released_point = event.getPoint();
			if (released_point.equals(pressed_point)) {
				pressed_point = null;
				return;
			}
			System.out.println("drag"); // TODO
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

	public CanvasArea getParent() {
		return parent;
	}
}
