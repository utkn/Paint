package paint;

import java.awt.Color;
import java.awt.Shape;

public class ShapeData {
	private Shape shape;
	private Color borderColor;
	private Color fillColor;
	private int borderWidth;
	
	public ShapeData(Shape shape, Color borderColor, Color fillColor, int borderWidth) {
		this.setShape(shape);
		this.setBorderColor(borderColor);
		this.setFillColor(fillColor);
		this.setBorderWidth(borderWidth);
	}

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
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
