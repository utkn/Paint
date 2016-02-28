package paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class CanvasComponent extends JComponent implements MouseListener, MouseMotionListener {
	private final Canvas canvasRef;
	private final Brush brushRef;
	private ShapeData preview;
	private Point lastPoint;
	
	private boolean dragging = false;
	
	public CanvasComponent(Canvas canvas, Brush brush) {
		super();
		setFocusable(true);
		setPreferredSize(new Dimension(640, 480));
		
		AbstractAction undoAction = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(canvasRef.getShapes().isEmpty()) return;
				canvasRef.getShapes().remove(canvasRef.getShapes().size()-1);
				repaint();
			}
			
		};
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("control Z"), "Undo");
		getActionMap().put("Undo", undoAction);
		canvasRef = canvas;
		brushRef = brush;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		List<ShapeData> shapes = canvasRef.getShapes();
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, getWidth(), getHeight());

		for(ShapeData shape : shapes) {
			g2.setStroke(new BasicStroke(shape.getBorderWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g2.setColor(shape.getFillColor());
			g2.fill(shape.getShape());
			g2.setColor(shape.getBorderColor());
			g2.draw(shape.getShape());
		}
		
		if(preview != null && dragging) {
			g2.setStroke(new BasicStroke(preview.getBorderWidth(), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g2.setColor(preview.getFillColor());
			g2.fill(preview.getShape());
			g2.setColor(preview.getBorderColor());
			g2.draw(preview.getShape());
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(!dragging || preview == null) return;
		
		if(brushRef.getBrushMode() == Brush.Mode.PENCIL 
				|| brushRef.getBrushMode() == Brush.Mode.ERASER
				|| brushRef.getBrushMode() == Brush.Mode.FREE_SHAPE) {
			Path2D.Double path = (Path2D.Double) preview.getShape();
			path.lineTo(e.getX(), e.getY());
			lastPoint = e.getPoint();
			repaint();
			return;
		}
		
		if(brushRef.getBrushMode() == Brush.Mode.LINE) {
			((Line2D.Double)preview.getShape()).setLine(lastPoint.x, lastPoint.y, e.getX(), e.getY());
			repaint();
			return;
		}
		
		Shape pShape = preview.getShape();
		int endX = e.getX();
		int endY = e.getY();
		int startX = lastPoint.x;
		int startY = lastPoint.y;
		
		int width = Math.abs(endX - startX);
		int height = Math.abs(endY - startY);
		
		RectangularShape pShapeRect = (RectangularShape) pShape;
		pShapeRect.setFrame(Math.min(endX, startX), Math.min(endY, startY), width, height);
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		dragging = true;
		lastPoint = e.getPoint();
		Shape shape = null;
		Color fill = null;
		Color border = null;
		
		switch(brushRef.getBrushMode()) {
		case PENCIL:
		case ERASER:
		case FREE_SHAPE:
			shape = new Path2D.Double();
			((Path2D.Double)shape).moveTo(e.getX(), e.getY());
			border = (brushRef.getBrushMode() == Brush.Mode.ERASER) ? Color.WHITE : brushRef.getBorderColor();
			fill = (brushRef.getBrushMode() == Brush.Mode.FREE_SHAPE) ? brushRef.getFillColor() : new Color(0, 0, 0, 0);
			break;
		case LINE:
			shape = new Line2D.Double(e.getX(), e.getY(), e.getX(), e.getY());
			border = brushRef.getBorderColor();
			fill = brushRef.getFillColor();
			break;
		case ELLIPSE:
			shape = new Ellipse2D.Double(e.getX(), e.getY(), 0, 0);
			border = brushRef.getBorderColor();
			fill = brushRef.getFillColor();
			break;
		case RECTANGLE:
			shape = new Rectangle2D.Double(e.getX(), e.getY(), 0, 0);
			border = brushRef.getBorderColor();
			fill = brushRef.getFillColor();
		}
		preview = new ShapeData(shape, border, fill, brushRef.getBorderWidth());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		dragging = false;
		if(brushRef.getBrushMode() == Brush.Mode.FREE_SHAPE) {
			((Path2D.Double)preview.getShape()).closePath();
		}
		canvasRef.addShape(preview);
		preview = null;
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
