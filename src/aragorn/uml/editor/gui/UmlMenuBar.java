package aragorn.uml.editor.gui;

import javax.swing.JMenuBar;
import aragorn.gui.GuiFrame;
import aragorn.gui.GuiMenu;
import aragorn.gui.GuiMenuItem;
import aragorn.gui.action.listener.CloseGuiFrameActionListener;
import aragorn.uml.editor.gui.submode.ChangeBasicObjectNameSubmode;
import aragorn.uml.editor.gui.submode.GroupSubmode;
import aragorn.uml.editor.gui.submode.UngroupSubmode;

@SuppressWarnings("serial")
class UmlMenuBar extends JMenuBar {

	private static GuiMenu getEditMenu(UmlCanvas canvas_area) {
		GuiMenu val = new GuiMenu("Edit");
		val.add(new GuiMenuItem("Group"));
		val.getItem(0).addActionListener(new GroupSubmode(canvas_area));
		val.add(new GuiMenuItem("Ungroup"));
		val.getItem(1).addActionListener(new UngroupSubmode(canvas_area));
		val.addSeparator();
		val.add(new GuiMenuItem("Change Basic Object Name"));
		val.getItem(3).addActionListener(new ChangeBasicObjectNameSubmode(canvas_area));
		return val;
	}

	private static GuiMenu getFileMenu(GuiFrame parent) {
		GuiMenu val = new GuiMenu("File");
		val.add(new GuiMenuItem("Exit"));
		val.getItem(0).addActionListener(new CloseGuiFrameActionListener(parent));
		return val;
	}

	UmlMenuBar(UmlFrame parent, UmlCanvas canvas_area) {
		super();
		add(UmlMenuBar.getFileMenu(parent));
		add(UmlMenuBar.getEditMenu(canvas_area));
	}
}