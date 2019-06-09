package aragorn.uml.editor;

import aragorn.gui.GuiFrame;
import aragorn.uml.editor.gui.UmlFrame;

class Main {

	public static void main(String[] args) {
		GuiFrame.setDefaultLookAndFeelDecorated(true);
		GuiFrame uml_frame = new UmlFrame();
		uml_frame.setVisible(true);
	}
}