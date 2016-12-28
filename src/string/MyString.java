package string;

import java.util.HashMap;
import java.util.Iterator;

public class MyString {

	// returns the reverse string
	private String reverseString(String str) {
		String result = "";
		for (int i = str.length() - 1; i >= 0; i--)
			result += str.charAt(i);
		return result;
	}

	// Changes case charactor
	private static String changeChar(String str) {
		String result = "";
		char tmp;
		for (int i = 0; i < str.length(); i++) {
			tmp = str.charAt(i);
			if (tmp >= 'a' && tmp <= 'z') {
				tmp -= 32;
			} else {
				if (tmp >= 'A' && tmp <= 'Z') {
					tmp += 32;
				}
			}
			result += tmp;
		}
		return result;
	}

	// Phương thức chuẩn hóa chuỗi
	private static String standardize(String str) {
		String result;
		// Cắt bỏ các ký tự space đầu và cuối chuỗi
		result = str.trim();
		// Xóa các ký tự space thừa ở giữa câu
		while (result.indexOf("  ") != -1) {
			result = result.replaceAll("  ", " ");
		}
		return result;
	}

	// Phương thức đếm số từ trong chuỗi (phải chuẩn hóa trước)
	private static int countWords(String str) {
		String[] data = str.split(" ");
		return data.length;
	}

	// Phương thức đếm tần suất xuất hiện các từ trong chuỗi
	private static HashMap wordFreq(String str) {
		// Đưa từng từ của xâu vào mảng
		String[] data = str.split(" ");

		// Khởi tạo map: key = từ, value = tần xuất
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		// Đưa các phần tử của mảng vào map
		for (int i = 0; i < data.length; i++) {
			// Sử dụng hashmap để lưu trữ "Từ" - "Tần xuất"
			String key = data[i];
			Integer value = 1;

			// Kiểm tra nếu từ đã có trong map rồi
			if (map.containsKey(key)) {
				// Lấy giá trị (tần số) của từ đó
				value = map.get(key);
				// Xóa phần tử đó đi
				map.remove(key);
				// Đưa phần tử vào lại với giá trị tần suất tăng lên 1
				map.put(key, ++value);
				// Nếu từ chưa có trong map thì chỉ put phần tử đó vào map
			} else {
				map.put(key, value);
			}
		}
		return map;
	}

	// Phương thức in ra tần suất xuất hiện từ trong chuỗi
	public void printWordsFreq(String stdStr) {
		HashMap map = wordFreq(stdStr);
		Iterator i = map.keySet().iterator();
		while (i.hasNext()) {
			String key = (String) i.next();
			System.out.println("- \"" + key + "\"" + " xuất hiện: "
					+ map.get(key) + " lần.");
		}
	}

	// Phương thức xóa 2 ký tự cuối của chuỗi (thường là ", ")
	public String trimComma(String str) {
		return str.substring(0, str.length() - 2) + ".";
	}
}
