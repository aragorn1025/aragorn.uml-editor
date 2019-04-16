package aragorn.xml.editor.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import aragorn.xml.editor.objects.UmlObject;

@SuppressWarnings("serial")
class CanvasArea extends Canvas {

	private ArrayList<UmlObject> list = new ArrayList<UmlObject>();

	MainFrame parent;

	private CanvasMouseAdapter mouse_adapter = null;

	CanvasArea(MainFrame parent) {
		super();
		this.parent = parent;
		setBackground(Color.BLACK);
	}

	void addUmlObject(UmlObject uml_object) {
		if (uml_object != null) {
			list.add(uml_object);
			repaint();
		}
	}

	/** @deprecated */
	UmlObject getUmlObject(int index) {
		return list.get(index);
	}

	/** @deprecated */
	int getUmlObjectsNumber() {
		return list.size();
	}

	@Override
	public final void paint(Graphics g) {
		g.setColor(Color.WHITE);
		for (UmlObject uml_object : list) {
			uml_object.draw(g, null);
		}
	}

	void setCanvasMouseAdapter(CanvasMouseAdapter mouse_adapter) { // TODO
		if (mouse_adapter == null)
			return;
		System.out.println("Here2");
		if (this.mouse_adapter == mouse_adapter)
			return;
		System.out.println("Here3");
		if (this.mouse_adapter != null) {
			removeMouseListener(this.mouse_adapter);
			removeMouseMotionListener(this.mouse_adapter);
			this.mouse_adapter = null;
		}
		this.mouse_adapter = mouse_adapter;
		addMouseListener(this.mouse_adapter);
		addMouseMotionListener(this.mouse_adapter);
		System.out.println("Here4");
	}
}