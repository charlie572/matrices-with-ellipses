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
		Element element;

		element = data.get(y).get(x);
        if (element.type == HORIZONTAL_ELLIPSIS) {
            add_column(x);
            add_column(x);
        } else if (element.type == DOWN_RIGHT_ELLIPSIS) {
        	add_row(x);
        	add_row(x);
        	add_column(y);
        	add_column(y);
        }

        if (x < data.get(0).size() - 1) {
        	Element right = data.get(y).get(x + 1);
        	if (right.type == HORIZONTAL_ELLIPSIS)
        		add_column(x);
        }
        
        if (y < data.size() - 1) {
            Element below = data.get(y + 1).get(x);
            if (below.type == VERTICAL_ELLIPSIS)
                add_row(y);
        }
        
        if (x > 0) {
        	Element left = data.get(y).get(x - 1);
        	if (left.type == HORIZONTAL_ELLIPSIS) {
        		add_column(x);
        		x++;
        	}
        }
        
        if (y > 0) {
        	Element above = data.get(y - 1).get(x);
        	if (above.type == VERTICAL_ELLIPSIS || above.type == DOWN_RIGHT_ELLIPSIS) {
        		add_row(y);
        		y++;
        	}
        }

        element = data.get(y).get(x);
        element.data = value;
	}
	
	private static void list_insert(List<Element> list, Element element, int index) {
		list.add(list.get(list.size() - 1));
		for (int i = list.size() - 2; i > index; i--) {
			list.set(i, list.get(i - 1));
		}
		list.set(index, element);
	}

	private static void list_insert(List<List<Element>> list, List<Element> row, int index) {
		list.add(list.get(list.size() - 1));
		for (int i = list.size() - 2; i > index; i--) {
			list.set(i, list.get(i - 1));
		}
		list.set(index, row);
	}
	
	private void add_column(int x) throws Exception {
		for (int y = data.size() - 1; y >= 0; y--) {
			Element new_element;
			if (data.get(y).get(0).type == VERTICAL_ELLIPSIS)
				new_element = new Element(0, VERTICAL_ELLIPSIS);
			else
				new_element = new Element(get(x, y), INTEGER);
			List<Element> row = data.get(y);
			list_insert(row, new_element, x);
		}
	}

	private void add_row(int y) throws Exception {
        List<Element> row = new ArrayList<Element>();
        for (int x = 0; x < data.get(y).size(); x++) {
			Element element;
			if (data.get(0).get(x).type == HORIZONTAL_ELLIPSIS)
				element = new Element(0, HORIZONTAL_ELLIPSIS);
			else
				element = new Element(get(x, y), INTEGER);
            row.add(element);
        }
        list_insert(data, row, y);
	}

	public void insert_row(int value, int y) throws Exception {
		if (y < data.size() - 1 && data.get(y + 1).get(0).type == VERTICAL_ELLIPSIS)
			add_row(y);
		if (y > 0 && data.get(y - 1).get(0).type == VERTICAL_ELLIPSIS) {
			add_row(y);
			y++;
		}

		for (int x = 0; x < data.get(0).size(); x++) {
			Element element = data.get(y).get(x);
			if (element.type == INTEGER)
				element.data = value;
		}
	}

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
