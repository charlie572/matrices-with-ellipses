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
	void get_function_works_in_top_left_corner_initially() {
		MatrixWithEllipses matrix = new MatrixWithEllipses();
		
		int expected = 0;
		int actual = matrix.get(0, 0);
		
		assertEquals(expected, actual);
	}
}
