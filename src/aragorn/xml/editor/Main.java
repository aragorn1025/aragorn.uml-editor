package aragorn.xml.editor;

import aragorn.gui.GuiFrame;
import aragorn.xml.editor.gui.MainFrame;

class Main {

	public static void main(String[] args) {
		GuiFrame.setDefaultLookAndFeelDecorated(true);
		GuiFrame main_frame = new MainFrame();
		main_frame.setVisible(true);
	}
}