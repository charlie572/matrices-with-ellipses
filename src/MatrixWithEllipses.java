import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class MatrixWithEllipses {
	private ArrayList<List<Element>> data;
	
	public final static int INTEGER = 0;
	public final static int HORIZONTAL_ELLIPSIS = 1;
	public final static int VERTICAL_ELLIPSIS = 2;
	public final static int DOWN_RIGHT_ELLIPSIS = 3;
	public final static int UP_RIGHT_ELLIPSIS = 4;
	
	private static class Element {
		private int data;
		private int type;
		
		public Element(int data, int type) {
			this.data = data;
			this.type = type;
		}
		
		@Override
		public String toString() {
			switch (type) {
                case INTEGER:
                	return "" + data + " ";
                case HORIZONTAL_ELLIPSIS:
                	return "\u22EF ";
                case VERTICAL_ELLIPSIS:
                	return "\u22EE";
                case DOWN_RIGHT_ELLIPSIS:
                	return "\u22F1 ";
                case UP_RIGHT_ELLIPSIS:
                	return "\u22F0 ";
                default:
                	return "";
			}
		}
	}
	
	public MatrixWithEllipses() {
		data = new ArrayList<List<Element>>();
		data.add(Arrays.asList(new Element(0, INTEGER), new Element(0, HORIZONTAL_ELLIPSIS), new Element(0, INTEGER)));
		data.add(Arrays.asList(new Element(0, VERTICAL_ELLIPSIS), new Element(0, DOWN_RIGHT_ELLIPSIS), new Element(0, VERTICAL_ELLIPSIS)));
		data.add(Arrays.asList(new Element(0, INTEGER), new Element(0, HORIZONTAL_ELLIPSIS), new Element(0, INTEGER)));
	}
	public void insert(int value, int x, int y) {}

	public int get(int x, int y) {}
	
	@Override
	public String toString() {
		String result = "";
		for (List<Element> row : data) {
			for (Element element : row) {
				result += element;
			}
			result += "\n";
		}

		return result;
	}
}
