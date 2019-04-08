package aragorn.xml.editor.gui;

import aragorn.gui.GuiFrame;
import aragorn.gui.GuiMenu;
import aragorn.gui.GuiMenuBar;
import aragorn.gui.GuiMenuItem;
import aragorn.gui.action.listener.CloseGuiFrameActionListener;

@SuppressWarnings("serial")
class MainMenuBar extends GuiMenuBar {

	MainMenuBar(MainFrame parent) {
		super(parent);
	}

	@Override
	protected void editMenuBar() {
		add(MainMenuBar.getFileMenu(parent));
		add(MainMenuBar.getEditMenu());
	}

	private static GuiMenu getFileMenu(GuiFrame parent) {
		GuiMenu val = new GuiMenu("File");
		val.add(new GuiMenuItem("Exit"));
		val.getItem(0).addActionListener(new CloseGuiFrameActionListener(parent));
		return val;
	}

	private static GuiMenu getEditMenu() {
		GuiMenu val = new GuiMenu("Edit");
		return val;
	}
}