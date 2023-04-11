import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Arrays;

public class MatrixWithEllipses {
	private ArrayList<List<Element>> data;
	
	public final static int INTEGER = 0;
	public final static int HORIZONTAL_ELLIPSIS = 1;
	public final static int VERTICAL_ELLIPSIS = 2;
	public final static int DOWN_RIGHT_ELLIPSIS = 3;
	public final static int UP_RIGHT_ELLIPSIS = 4;
	
	private static class Element {
		public int data;
		public int type;
		
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
		data.add(new ArrayList<Element>(Arrays.asList(new Element(0, INTEGER), new Element(0, HORIZONTAL_ELLIPSIS), new Element(0, INTEGER))));
		data.add(new ArrayList<Element>(Arrays.asList(new Element(0, VERTICAL_ELLIPSIS), new Element(0, DOWN_RIGHT_ELLIPSIS), new Element(0, VERTICAL_ELLIPSIS))));
		data.add(new ArrayList<Element>(Arrays.asList(new Element(0, INTEGER), new Element(0, HORIZONTAL_ELLIPSIS), new Element(0, INTEGER))));
	}

	public void insert_value(int value, int x, int y) throws Exception {
		Element element = data.get(y).get(x);
		switch (element.type) {
            case INTEGER:
            	element.data = value;
            	break;
            case HORIZONTAL_ELLIPSIS:
            	add_column(x);
            	add_column(x);
            	insert_value(value, x, y);
            	break;
		}
	}
	
	private static void list_insert(List<Element> list, Element element, int index) {
		list.add(list.get(list.size() - 1));
		for (int i = list.size() - 2; i > index; i--) {
			list.set(i, list.get(i - 1));
		}
		list.set(index, element);
	}
	
	private void add_column(int x) throws Exception {
		for (int y = data.size() - 1; y >= 0; y--) {
			List<Element> row = data.get(y);
			list_insert(row, new Element(get(x, y), INTEGER), x);
		}
	}

	public void insert_run(int value, int x1, int y1, int x2, int y2) {}

	public int get(int x, int y) throws Exception{
		Element element =  data.get(y).get(x);
		switch (element.type) {
            case INTEGER:
            	return element.data;
            case HORIZONTAL_ELLIPSIS:
            	return get(x - 1, y);
            case VERTICAL_ELLIPSIS:
            	return get(x, y - 1);
            case DOWN_RIGHT_ELLIPSIS:
            	return get(x - 1, y - 1);
            case UP_RIGHT_ELLIPSIS:
            	return get(x - 1, y + 1);
            default:
            	throw new Exception("Error in MatrixWithEllipses.get");
		}
	}
	
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
