package aragorn.xml.editor.objects;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import aragorn.math.geometry.Coordinate2D;
import aragorn.math.geometry.Paintable;

public class UmlCompositeObject extends UmlBasicObject {

	private static class CompositeObject implements Paintable {

		private Paintable[] sub_objects;

		private CompositeObject(Paintable[] sub_objects) {
			if (sub_objects == null)
				throw new NullPointerException("The sub-objects should not be null.");
			if (sub_objects.length == 0)
				throw new NullPointerException("The sub-objects should not be nothing in.");
			this.sub_objects = new Paintable[sub_objects.length];
			for (int i = 0; i < this.sub_objects.length; i++) {
				this.sub_objects[i] = sub_objects[i];
			}
		}

		@Override
		public void draw(Graphics g, Coordinate2D c) {
			for (Paintable sub_object : sub_objects) {
				sub_object.draw(g, c);
			}
		}

		@Override
		public Rectangle2D.Double getBounds() {
			double x_min = sub_objects[0].getBounds().getMinX();
			double x_max = sub_objects[0].getBounds().getMaxX();
			double y_min = sub_objects[0].getBounds().getMinY();
			double y_max = sub_objects[0].getBounds().getMaxY();
			for (int i = 1; i < sub_objects.length; i++) {
				x_min = Math.min(x_min, sub_objects[i].getBounds().getMinX());
				x_max = Math.max(x_max, sub_objects[i].getBounds().getMaxX());
				y_min = Math.min(y_min, sub_objects[i].getBounds().getMinY());
				y_max = Math.max(y_max, sub_objects[i].getBounds().getMaxY());
			}
			return new Rectangle2D.Double(x_min, y_min, x_max - x_min, y_max - y_min);
		}
	}

	private static <N extends Paintable> Paintable[] getPaintableArray(ArrayList<N> sub_objects) {
		if (sub_objects == null)
			throw new NullPointerException("The sub-objects should not be null.");
		if (sub_objects.size() == 0)
			throw new NullPointerException("The sub-objects should not be nothing in.");
		Paintable[] val = new Paintable[sub_objects.size()];
		for (int i = 0; i < sub_objects.size(); i++) {
			val[i] = sub_objects.get(i);
		}
		return val;
	}

	private UmlBasicObject[] sub_objects;

	/** Do shallow copy */
	public UmlCompositeObject(ArrayList<UmlBasicObject> sub_objects) {
		super((new UmlCompositeObject.CompositeObject(UmlCompositeObject.getPaintableArray(sub_objects))).getBounds());
		this.sub_objects = new UmlBasicObject[sub_objects.size()];
		for (int i = 0; i < this.sub_objects.length; i++) {
			this.sub_objects[i] = sub_objects.get(i);
		}
	}

	@Override
	protected Paintable getIcon() {
		return new UmlCompositeObject.CompositeObject(sub_objects);
	}

	public UmlBasicObject[] getSubObjects() {
		return sub_objects;
	}

	@Override
	protected boolean isSelected() {
		return sub_objects[0].isSelected();
	}

	@Override
	protected boolean isSurround(Point2D.Double point) {
		for (UmlBasicObject sub_object : sub_objects) {
			if (sub_object.isSurround(point)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isUngroupable() {
		return true;
	}

	@Override
	public void setSelected(boolean selected) {
		super.setSelected(false);
		for (UmlBasicObject sub_object : sub_objects) {
			sub_object.setSelected(selected);
		}
	}
}