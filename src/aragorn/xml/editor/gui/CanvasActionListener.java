package aragorn.xml.editor.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

abstract class CanvasActionListener implements ActionListener {

	static class ChangeBasicObjectName extends CanvasActionListener {

		ChangeBasicObjectName(CanvasArea parent) {
			super(parent);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (getParent().getSelectedUmlObjectsNumber()) {
				case 0:
					getParent().getParent().echo("No basic object is selected.", JOptionPane.WARNING_MESSAGE);
					break;
				case 1:
					String name = JOptionPane.showInputDialog(getParent().getParent(), getMessage(), "Change Name", JOptionPane.QUESTION_MESSAGE);
					if (name != null) {
						getParent().getSelectedUmlBasicObject(0).setName(name);
					}
					break;
				default:
					getParent().getParent().echo("Too many basic objects are selected.", JOptionPane.WARNING_MESSAGE);
					break;
			}
		}

		private String getMessage() {
			String name = getParent().getSelectedUmlBasicObject(0).getName();
			if (name == null) {
				return new String("Please enter the new name:");
			} else {
				return String.format("The name of the selected is %s.%nPlease enter the new name:", name);
			}
		}
	}

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
