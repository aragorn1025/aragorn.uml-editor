package aragorn.xml.editor.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import aragorn.gui.GuiFrame;
import aragorn.gui.GuiPanel;

@SuppressWarnings("serial")
public class MainFrame extends GuiFrame {

	private static class ContentPane extends GuiPanel {

		private ContentPane() {
			this(10);
		}

		private ContentPane(int margin) {
			setDefaultMargin(0);
			CanvasArea canvas_area = new CanvasArea();
			ButtonPanel button_panel = new ButtonPanel(canvas_area);
			addComponent(button_panel, 0, 0, 1, 1, 0, 1, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL);
			addComponent(canvas_area, 1, 0, 1, 1, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(margin, 0, margin, margin));
		}
	}

	public MainFrame() {
		super(new Dimension(800, 450), false);
		setContentPane(new MainFrame.ContentPane());
		setTitle("XML Editor");
		setJMenuBar(new MainMenuBar(this));
	}
}