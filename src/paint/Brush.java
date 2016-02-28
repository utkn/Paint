package paint;

import java.awt.Color;

public class Brush {
	public enum Mode {
		PENCIL, LINE, RECTANGLE, ELLIPSE, FREE_SHAPE, POLYGON, ERASER
	}
	
	private Mode brushMode;
	private Color borderColor;
	private Color fillColor;
	private int borderWidth;
	
	public Brush() {
		setBrushMode(Mode.PENCIL);
		setBorderColor(Color.BLACK);
		setFillColor(Color.WHITE);
		setBorderWidth(5);
	}

	public Mode getBrushMode() {
		return brushMode;
	}

	public void setBrushMode(Mode brushMode) {
		this.brushMode = brushMode;
	}

	public Color getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	public int getBorderWidth() {
		return borderWidth;
	}

	public void setBorderWidth(int borderWidth) {
		this.borderWidth = borderWidth;
	}
}
