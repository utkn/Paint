package paint;

import java.util.ArrayList;
import java.util.List;

public class Canvas {
	private List<ShapeData> shapeDatas;
	
	public Canvas() {
		shapeDatas = new ArrayList<ShapeData>();
	}
	
	public void addShape(ShapeData shapeData) {
		shapeDatas.add(shapeData);
	}

	public List<ShapeData> getShapes() {
		return shapeDatas;
	}
}
