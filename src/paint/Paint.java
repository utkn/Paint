package paint;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Paint {
	private Brush brush;
	private Canvas canvas;
	
	private JFrame window;
	private JPanel panel;
	private CanvasComponent canvasComponent;
	private UIComponent uiComponent;
	
	public static void main(String[] args) {
		new Paint();
	}
	
	public Paint() {
		brush = new Brush();
		canvas = new Canvas();
		
		canvasComponent = new CanvasComponent(canvas, brush);
		uiComponent = new UIComponent(brush);
		
		
		window = new JFrame("Çizici");
		panel = new JPanel();
		
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panel.add(canvasComponent);
		panel.add(uiComponent);
		panel.addMouseListener(canvasComponent);
		panel.addMouseMotionListener(canvasComponent);
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		
		window.add(panel);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setVisible(true);
	}
}
