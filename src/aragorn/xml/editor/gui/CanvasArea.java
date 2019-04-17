package aragorn.xml.editor.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import aragorn.math.geometry.Paintable;
import aragorn.xml.editor.objects.UmlBasicObject;
import aragorn.xml.editor.objects.UmlConnectionLine;
import aragorn.xml.editor.objects.UmlObject;

@SuppressWarnings("serial")
class CanvasArea extends Canvas {

	public static final int NO_SELECTED_UML_BASIC_OBJECT = -1;

	private ArrayList<UmlConnectionLine> uml_connection_lines = new ArrayList<>();

	private ArrayList<UmlBasicObject> uml_basic_objects = new ArrayList<>();

	private int selected_uml_basic_object_index = NO_SELECTED_UML_BASIC_OBJECT;

	/** TODO maybe should be remove. */
	@SuppressWarnings("unused")
	private MainFrame parent;

	private CanvasMouseAdapter mouse_adapter = null;

	CanvasArea(MainFrame parent) {
		super();
		this.parent = parent;
	}

	void addUmlBasicObject(UmlBasicObject uml_basic_object) {
		if (uml_basic_object != null) {
			uml_basic_objects.add(uml_basic_object);
			repaint();
		}
	}

	UmlBasicObject getUmlBasicObject(int index) {
		return uml_basic_objects.get(index);
	}

	int getUmlObjectsNumber() {
		return uml_basic_objects.size();
	}

	@Override
	public final void paint(Graphics g) {
		g.setColor(Color.BLACK);
		Paintable.fillRectangle(g, null, new Point2D.Double(), getWidth(), getHeight());
		g.setColor(Color.WHITE);
		for (UmlObject uml_object : uml_basic_objects) {
			uml_object.draw(g, null);
		}
		for (UmlObject uml_object : uml_connection_lines) {
			uml_object.draw(g, null);
		}
	}

	void setCanvasMouseAdapter(CanvasMouseAdapter mouse_adapter) {
		if (mouse_adapter == null)
			return;
		if (this.mouse_adapter == mouse_adapter)
			return;
		if (this.mouse_adapter != null) {
			removeMouseListener(this.mouse_adapter);
			removeMouseMotionListener(this.mouse_adapter);
			this.mouse_adapter = null;
		}
		this.mouse_adapter = mouse_adapter;
		addMouseListener(this.mouse_adapter);
		addMouseMotionListener(this.mouse_adapter);
	}

	public void setSelectedUmlBasicObject(int index) {
		if (index == this.selected_uml_basic_object_index)
			return;
		if (this.selected_uml_basic_object_index != CanvasArea.NO_SELECTED_UML_BASIC_OBJECT) {
			uml_basic_objects.get(this.selected_uml_basic_object_index).setSelected(false);
		}
		this.selected_uml_basic_object_index = index;
		if (this.selected_uml_basic_object_index != CanvasArea.NO_SELECTED_UML_BASIC_OBJECT) {
			uml_basic_objects.get(this.selected_uml_basic_object_index).setSelected(true);
		}
		repaint();
	}
}