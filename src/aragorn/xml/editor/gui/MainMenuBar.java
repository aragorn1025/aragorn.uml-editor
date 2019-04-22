package aragorn.xml.editor.gui;

import javax.swing.JMenuBar;
import aragorn.gui.GuiFrame;
import aragorn.gui.GuiMenu;
import aragorn.gui.GuiMenuItem;
import aragorn.gui.action.listener.CloseGuiFrameActionListener;

@SuppressWarnings("serial")
class MainMenuBar extends JMenuBar {

	private static GuiMenu getEditMenu(CanvasArea canvas_area) {
		GuiMenu val = new GuiMenu("Edit");
		val.add(new GuiMenuItem("Group"));
		val.getItem(0).addActionListener(new CanvasActionListener.Group(canvas_area));
		val.add(new GuiMenuItem("Ungroup"));
		val.getItem(1).addActionListener(new CanvasActionListener.Ungroup(canvas_area));
		val.addSeparator();
		val.add(new GuiMenuItem("Change Basic Object Name"));
		val.getItem(3).addActionListener(new CanvasActionListener.ChangeBasicObjectName(canvas_area));
		return val;
	}

	private static GuiMenu getFileMenu(GuiFrame parent) {
		GuiMenu val = new GuiMenu("File");
		val.add(new GuiMenuItem("Exit"));
		val.getItem(0).addActionListener(new CloseGuiFrameActionListener(parent));
		return val;
	}

	MainMenuBar(MainFrame parent, CanvasArea canvas_area) {
		super();
		add(MainMenuBar.getFileMenu(parent));
		add(MainMenuBar.getEditMenu(canvas_area));
	}
}