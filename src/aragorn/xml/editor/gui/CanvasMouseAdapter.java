package aragorn.xml.editor.gui;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;
import aragorn.xml.editor.objects.UmlClass;
import aragorn.xml.editor.objects.UmlObject;
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
			addUmlObject(new UmlClass(event.getPoint()));
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
			// TODO
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

	static class UseCase extends CanvasMouseAdapter {

		UseCase(CanvasArea parent) {
			super(parent);
		}

		@Override
		public void mouseClicked(MouseEvent event) {
			addUmlObject(new UmlUseCase(event.getPoint()));
		}
	}

	private CanvasArea parent;

	protected CanvasMouseAdapter(CanvasArea parent) {
		this.parent = parent;
	}

	protected void addUmlObject(UmlObject uml_object) {
		parent.addUmlObject(uml_object);
	}
}
