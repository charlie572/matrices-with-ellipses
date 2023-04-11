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
            "0 "   + helps +  "0\n" +
			velps + drelps + "0\n" +
			"0 "   + helps +  "0"
        );
		String actual = matrix.toString();
		
		assertEquals(expected, actual);
	}

}
