package aragorn.xml.editor.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class CanvasActionListener implements ActionListener {

	static class Group extends CanvasActionListener {

		Group(CanvasArea parent) {
			super(parent);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			getParent().group();
		}
	}

	static class Ungroup extends CanvasActionListener {

		Ungroup(CanvasArea parent) {
			super(parent);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			getParent().ungroup();
		}
	}

	private CanvasArea parent;

	protected CanvasActionListener(CanvasArea parent) {
		this.parent = parent;
	}

	protected CanvasArea getParent() {
		return parent;
	}
}
