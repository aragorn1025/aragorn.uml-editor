package aragorn.xml.editor.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

@SuppressWarnings("serial")
class CanvasArea extends Canvas {

	CanvasArea(MainFrame parent) {
		super();

		MouseInputAdapter adapter = new CanvasMouseInputAdapter(parent);

		addMouseListener(adapter);
		addMouseMotionListener(adapter);
		setBackground(Color.BLACK);
	}

	private static class CanvasMouseInputAdapter extends MouseInputAdapter {

		private MainFrame parent;

		CanvasMouseInputAdapter(MainFrame parent) {
			this.parent = parent;
		}

		@Override
		public void mouseClicked(MouseEvent event) {
			if (parent.getButtonPanel().getSelectedButton() == null)
				return;
			parent.getButtonPanel().getSelectedButton().clickedAction(event.getPoint());
		}
	}
}