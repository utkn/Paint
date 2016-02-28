package paint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class UIComponent extends JComponent {
	private Brush brushRef;
	private JRadioButton pencilMode;
	private JRadioButton rectMode;
	private JRadioButton lineMode;
	private JRadioButton ellipseMode;
	private JRadioButton eraserMode;
	private JRadioButton freeShapeMode;
	
	private JPanel colorChooser;
	private JButton borderColorBtn;
	private JButton fillColorBtn;
	private ButtonGroup modeGroup;
	
	private JSlider borderSlider;
	
	public UIComponent(Brush brush) {
		super();
		setPreferredSize(new Dimension(130, 480));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		brushRef = brush;
		pencilMode = new JRadioButton("Pencil");
		lineMode = new JRadioButton("Line");
		rectMode = new JRadioButton("Rectangle");
		ellipseMode = new JRadioButton("Ellipse");
		freeShapeMode = new JRadioButton("Free Shape");
		eraserMode = new JRadioButton("Eraser");
		
		modeGroup = new ButtonGroup();
		modeGroup.add(pencilMode);
		modeGroup.add(lineMode);
		modeGroup.add(rectMode);
		modeGroup.add(ellipseMode);
		modeGroup.add(freeShapeMode);
		modeGroup.add(eraserMode);
		pencilMode.setSelected(true);

		colorChooser = new JPanel();
		colorChooser.setLayout(new FlowLayout());
		
		borderColorBtn = new JButton();
		borderColorBtn.setOpaque(true);
		borderColorBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		borderColorBtn.setPreferredSize(new Dimension(50, 50));
		borderColorBtn.setBackground(blendAlpha(brushRef.getBorderColor(), Color.WHITE));
		
		fillColorBtn = new JButton();
		fillColorBtn.setOpaque(true);
		fillColorBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		fillColorBtn.setPreferredSize(new Dimension(50, 50));
		fillColorBtn.setBackground(blendAlpha(brushRef.getFillColor(), Color.WHITE));
		colorChooser.add(fillColorBtn);
		colorChooser.add(borderColorBtn);
		
		borderSlider = new JSlider(0, 20, brushRef.getBorderWidth());
		borderSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				brushRef.setBorderWidth(borderSlider.getValue());
			}
		});
		
		add(pencilMode);
		add(lineMode);
		add(rectMode);
		add(ellipseMode);
		add(freeShapeMode);
		add(eraserMode);
		add(borderSlider);
		add(colorChooser);
		
		borderColorBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(null, "Choose a border color", brushRef.getBorderColor());
				if(color == null) return;
				brushRef.setBorderColor(color);
				borderColorBtn.setBackground(blendAlpha(color, Color.WHITE));
			}
		});
		
		fillColorBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(null, "Choose a fill color", brushRef.getBorderColor());
				if(color == null) return;
				brushRef.setFillColor(color);
				fillColorBtn.setBackground(blendAlpha(color, Color.WHITE));
			}
		});
		
		pencilMode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				brushRef.setBrushMode(Brush.Mode.PENCIL);
			}
		});
		
		lineMode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				brushRef.setBrushMode(Brush.Mode.LINE);
			}
		});
		
		rectMode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				brushRef.setBrushMode(Brush.Mode.RECTANGLE);
			}
		});
		
		ellipseMode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				brushRef.setBrushMode(Brush.Mode.ELLIPSE);
			}
		});
		
		freeShapeMode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				brushRef.setBrushMode(Brush.Mode.FREE_SHAPE);
			}
		});
		
		eraserMode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				brushRef.setBrushMode(Brush.Mode.ERASER);
			}
		});
	}
	
	public static Color blendAlpha (Color color, Color bg) {
		float a = (float)color.getAlpha()/255;
		return new Color(
				(1.f - a) * ((float)bg.getRed()/255.f) + a * ((float)color.getRed()/255.f),
				(1.f - a) * ((float)bg.getGreen()/255.f) + a * ((float)color.getGreen()/255.f),
				(1.f - a) * ((float)bg.getBlue()/255.f) + a * ((float)color.getBlue()/255.f)
			);
	}
}
