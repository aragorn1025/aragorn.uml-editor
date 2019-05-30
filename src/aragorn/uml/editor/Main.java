package aragorn.uml.editor;

import aragorn.gui.GuiFrame;
import aragorn.uml.editor.gui.MainFrame;

class Main {

	public static void main(String[] args) {
		GuiFrame.setDefaultLookAndFeelDecorated(true);
		GuiFrame main_frame = new MainFrame();
		main_frame.setVisible(true);
	}
}