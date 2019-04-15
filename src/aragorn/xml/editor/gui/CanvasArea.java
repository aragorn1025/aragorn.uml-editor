package aragorn.xml.editor.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.event.MouseInputAdapter;
import aragorn.xml.editor.objects.UmlObject;

@SuppressWarnings("serial")
class CanvasArea extends Canvas {

	private static class CanvasMouseInputAdapter extends MouseInputAdapter {

		private MainFrame parent;

		CanvasMouseInputAdapter(MainFrame parent) {
			this.parent = parent;
		}

		@Override
		public void mouseClicked(MouseEvent event) {
			if (parent.getSelectedButton() == null)
				return;
			parent.addUmlObject(parent.getSelectedButton().clickedAction(event.getPoint()));
		}

		@Override
		public void mouseDragged(MouseEvent event) {
			if (parent.getSelectedButton() == null)
				return;
			parent.addUmlObject(parent.getSelectedButton().draggedAction(event.getPoint()));
		}

		@Override
		public void mousePressed(MouseEvent event) {
			if (parent.getSelectedButton() == null)
				return;
			parent.addUmlObject(parent.getSelectedButton().pressedAction(event.getPoint()));
		}

		@Override
		public void mouseReleased(MouseEvent event) {
			if (parent.getSelectedButton() == null)
				return;
			parent.addUmlObject(parent.getSelectedButton().releasedAction(event.getPoint()));
		}
	}

	private ArrayList<UmlObject> list = new ArrayList<UmlObject>();

	CanvasArea(MainFrame parent) {
		super();
		setBackground(Color.BLACK);

		MouseInputAdapter adapter = new CanvasMouseInputAdapter(parent);
		addMouseListener(adapter);
		addMouseMotionListener(adapter);
	}

	void addUmlObject(UmlObject uml_object) {
		if (uml_object != null) {
			list.add(uml_object);
		}
	}
}