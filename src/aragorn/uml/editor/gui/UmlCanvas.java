package aragorn.uml.editor.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import aragorn.math.geometry.Paintable;
import aragorn.uml.editor.object.UmlBasicObject;
import aragorn.uml.editor.object.UmlLineObject;
import aragorn.uml.editor.object.basic.CompositeObject;

@SuppressWarnings("serial")
public class UmlCanvas extends Canvas {

	private ArrayList<UmlLineObject> uml_line_objects = new ArrayList<>();

	private ArrayList<UmlBasicObject> uml_basic_objects = new ArrayList<>();

	private ArrayList<UmlBasicObject> selected_uml_basic_objects = new ArrayList<>();

	private UmlMode mode = null;

	private Rectangle2D.Double dragged_block = null;

	private int depth_counter = -1;

	private UmlFrame parent;

	UmlCanvas(UmlFrame parent) {
		super();
		this.parent = parent;
		setBackground(Color.BLACK);
	}

	void addSelectedUmlBasicObjects(UmlBasicObject uml_basic_object) {
		uml_basic_object.setSelected(true);
		selected_uml_basic_objects.add(uml_basic_object);
	}

	void addUmlBasicObject(UmlBasicObject uml_basic_object) {
		if (uml_basic_object != null) {
			uml_basic_object.setDepth(depth_counter);
			depth_counter = uml_basic_object.getDepth() - 1;
			uml_basic_objects.add(uml_basic_object);
			repaint();
		}
	}

	void addUmlLineObject(UmlLineObject uml_line_object) {
		if (uml_line_object != null) {
			uml_line_objects.add(uml_line_object);
			repaint();
		}
	}

	void clearSelectedUmlBasicObjects() {
		for (int i = 0; i < selected_uml_basic_objects.size(); i++) {
			selected_uml_basic_objects.get(i).setSelected(false);
		}
		selected_uml_basic_objects.clear();
	}

	@Override
	public UmlFrame getParent() {
		return parent;
	}

	UmlBasicObject getSelectedUmlBasicObject(int index) {
		return selected_uml_basic_objects.get(index);
	}

	int getSelectedUmlObjectsNumber() {
		return selected_uml_basic_objects.size();
	}

	UmlBasicObject getUmlBasicObject(int index) {
		return uml_basic_objects.get(index);
	}

	int getUmlObjectsNumber() {
		return uml_basic_objects.size();
	}

	void group() {
		if (selected_uml_basic_objects.size() == 0)
			return;
		CompositeObject composite_object = new CompositeObject(selected_uml_basic_objects);
		for (int i = 0; i < selected_uml_basic_objects.size(); i++) {
			uml_basic_objects.remove(selected_uml_basic_objects.get(i));
		}
		uml_basic_objects.add(composite_object);
		addSelectedUmlBasicObjects(composite_object);
		repaint();
	}

	@Override
	public final void paint(Graphics g) {
		Collections.sort(uml_basic_objects, Collections.reverseOrder());
		for (UmlBasicObject uml_object : uml_basic_objects) {
			uml_object.draw(g, null);
		}
		g.setColor(Color.WHITE);
		for (UmlLineObject uml_object : uml_line_objects) {
			uml_object.draw(g, null);
		}
		if (dragged_block == null)
			return;
		g.setColor(Color.GREEN);
		Paintable.drawRectangle(g, null, new Point2D.Double(dragged_block.getX(), dragged_block.getY()), dragged_block.getWidth(), dragged_block.getHeight());
	}

	void setCanvasMouseAdapter(UmlMode mouse_adapter) {
		if (mouse_adapter == null)
			return;
		if (this.mode == mouse_adapter)
			return;
		if (this.mode != null) {
			removeMouseListener(this.mode);
			removeMouseMotionListener(this.mode);
			this.mode = null;
		}
		this.mode = mouse_adapter;
		addMouseListener(this.mode);
		addMouseMotionListener(this.mode);
		clearSelectedUmlBasicObjects();
		repaint();
	}

	void setDraggedBlock(Rectangle2D.Double dragged_block) {
		this.dragged_block = dragged_block;
	}

	void ungroup() {
		if (selected_uml_basic_objects.size() != 1 || !selected_uml_basic_objects.get(0).isUngroupable())
			return;
		CompositeObject group = (CompositeObject) selected_uml_basic_objects.get(0);
		uml_basic_objects.remove(group);
		selected_uml_basic_objects.clear();
		for (UmlBasicObject sub_object : group.getSubObjects()) {
			uml_basic_objects.add(sub_object);
			selected_uml_basic_objects.add(sub_object);
		}
		repaint();
	}
}