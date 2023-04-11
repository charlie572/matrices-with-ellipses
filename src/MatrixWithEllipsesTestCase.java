import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MatrixWithEllipsesTestCase {
	// ellipsis unicode characters and trailing space
    // the vertical ellipsis take up two characters, so has no trailing space
    final String helps = "\u22EF ";
    final String velps = "\u22EE";
    final String urelps = "\u22F0 ";
    final String drelps = "\u22F1 ";

	@Test
	void matrix_filled_with_zeroes_by_default() {
		MatrixWithEllipses matrix = new MatrixWithEllipses();
		
		String expected = (
            "0 "   + helps +  "0 \n" +
			velps + drelps +  velps + "\n" +
			"0 "   + helps +  "0 \n"
        );
		String actual = matrix.toString();
		
		assertEquals(expected, actual);
	}

	@Test
	void get_function_works_in_top_left_corner_initially() throws Exception {
		MatrixWithEllipses matrix = new MatrixWithEllipses();
		
		int expected = 0;
		int actual = matrix.get(0, 0);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void insert_value_function_works_in_top_left_corner() throws Exception {
		MatrixWithEllipses matrix = new MatrixWithEllipses();
		matrix.insert_value(5, 0, 0);
		
		int expected = 5;
		int actual = matrix.get(0, 0);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void get_function_works_on_horizontal_ellipsis_initially() throws Exception {
		MatrixWithEllipses matrix = new MatrixWithEllipses();
		
		int expected = 0;
		int actual = matrix.get(1, 0);
		
		assertEquals(expected, actual);
	}

	@Test
	void insert_value_function_works_on_horizontal_ellipsis() throws Exception {
		MatrixWithEllipses matrix = new MatrixWithEllipses();
		matrix.insert_value(5, 1, 0);
		
		int expected = 5;
		int actual = matrix.get(1, 0);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void insert_value_function_modifies_array_properly_on_horizontal_ellipsis() throws Exception {
		MatrixWithEllipses matrix = new MatrixWithEllipses();
		matrix.insert_value(5, 1, 0);
		
		String expected = (
			"0 " +  "5 " +  "0 " +  helps +  "0 \n" +
			"0 " +  "0 " +  "0 " +  helps +  "0 \n" +
			velps + velps + velps + drelps + velps + "\n" +
			"0 " +  "0 " +   "0 " +  helps +  "0 \n"
		);
		String actual = matrix.toString();
		
		assertEquals(expected, actual);
	}

	@Test
	void insert_value_function_left_corner_modifies_array_correctly() throws Exception {
		MatrixWithEllipses matrix = new MatrixWithEllipses();
		matrix.insert_value(5, 0, 0);
		
		String expected = (
			"5 " +  "0 " +  helps +  "0 \n" +
			"0 " +  "0 " +  helps +  "0 \n" +
			velps + velps + drelps +  velps + "\n" +
			"0 " +  "0 " +  helps +  "0 \n"
		);
		String actual = matrix.toString();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void insert_in_top_right_corner() throws Exception {
		MatrixWithEllipses matrix = new MatrixWithEllipses();
		matrix.insert_value(5, 2, 0);
		
		String expected = (
			"0 " +  helps +  "0 " +   "5 \n" +
			"0 " +  helps +  "0 " +   "0 \n" +
			velps + drelps + velps +  velps + "\n" +
			"0 " +  helps +  "0 " +   "0 \n"
		);
		String actual = matrix.toString();
		
		assertEquals(expected, actual);
	}

	@Test
	void insert_in_bottom_left_corner() throws Exception {
		MatrixWithEllipses matrix = new MatrixWithEllipses();
		matrix.insert_value(5, 0, 2);
		
		String expected = (
			"0 " +  "0 " +  helps +  "0 \n" +
			velps + velps + drelps + velps + "\n" +
			"0 " +  "0 " +  helps +  "0 \n" +
			"5 " +  "0 " +  helps +  "0 \n"
		);
		String actual = matrix.toString();
		
		assertEquals(expected, actual);
	}

	@Test
	void insert_in_bottom_right_corner() throws Exception {
		MatrixWithEllipses matrix = new MatrixWithEllipses();
		matrix.insert_value(5, 2, 2);
		
		String expected = (
			"0 " +  helps +   "0 " +   "0 \n" +
			velps + drelps +  velps +  velps + "\n" +
			"0 " +  helps +   "0 " +   "0 \n" +
			"0 " +  helps +   "0 " +   "5 \n"
		);
		String actual = matrix.toString();
		
		assertEquals(expected, actual);
	}
}
