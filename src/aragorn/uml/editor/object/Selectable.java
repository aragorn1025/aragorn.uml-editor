package aragorn.uml.editor.object;

import java.awt.Point;
import java.awt.geom.Point2D;
import aragorn.util.MathVector2D;

public interface Selectable {

	public abstract boolean isSelected();

	public default boolean isSurround(Point point) {
		return isSurround(new Point2D.Double(point.getX(), point.getY()));
	}

	public abstract boolean isSurround(Point2D.Double point);

	public abstract boolean isSurroundedBy(UmlDraggedBox bounds);

	public abstract void move(MathVector2D vector);

	public abstract void setSelected(boolean selected);
}