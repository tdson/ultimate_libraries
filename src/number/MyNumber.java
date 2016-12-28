package number;

import java.text.DecimalFormat;

public class MyNumber {
	// Phương thức làm tròn số với digit số chữ số thập phân
	public float round(float number, int digit) {
		String pattern = ".";
		for (int i = 0; i < digit; i++) {
			pattern += "#";
		}
		DecimalFormat df = new DecimalFormat(pattern);
		return Float.parseFloat(df.format(number));
	}
}
