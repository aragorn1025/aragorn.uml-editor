package aragorn.xml.editor.objects;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;
import aragorn.math.geometry.Oval;
import aragorn.math.geometry.Paintable;

public class UmlUseCase extends UmlBasicObject {

	public static final Paintable BUTTON_ICON = new UmlUseCase(new Point(0, 1), new Dimension(8, 6));

	public static final String NAME = "use case";
	
	/**
	 * The default size of the object.<br>
	 * Strongly recommend to set the width as the multiple of 2 and set the height as the multiple of 2. TODO the javadoc should be rewrite correctly.
	 */
	private final static Dimension DEFAULT_SIZE = new Dimension(84, 48);

	public UmlUseCase(Point reference_point) {
		this(reference_point, UmlUseCase.DEFAULT_SIZE);
	}

	private UmlUseCase(Point reference_point, Dimension size) {
		super(reference_point, size);
	}

	@Override
	protected Paintable getIcon() {
		return new Oval(new Point2D.Double(getBounds().getCenterX(), getBounds().getCenterY()), getBounds().getWidth(), getBounds().getHeight());
	}
}