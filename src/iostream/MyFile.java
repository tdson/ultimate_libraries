package iostream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MyFile {

	// Phương thức đọc file và trả về một ArrayList, mỗi phần tử (String) là một
	// dòng trong file
	public ArrayList<String> readFile(String path) {
		ArrayList<String> text = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line = "";
			while ((line = br.readLine()) != null) {
				text.add(line.trim());
			}
			br.close();
			return text;
		} catch (FileNotFoundException e) {
			// Không tìm thấy file
			// e.printStackTrace();
			text.add("#Error: Không tìm thấy file.");
			return text;
		} catch (IOException e) {
			// Không thể đọc file
			// e.printStackTrace();
			text.add("#Error: Không thể đọc file.");
			return text;
		}
	}

	// Phương thức ghi nội dung từ một ArrayList xuống một file
	public void writeData(ArrayList<String> content, String path)
			throws IOException {
		File file = new File(path);
		file.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		for (String line : content) {
			bw.write(line);
			bw.newLine();
		}
		bw.close();
	}

	// Phương thức ghi nội dung từ một ArrayList xuống một file
	/*
	 * public void writeData(ArrayList<String> content, String path) throws
	 * IOException { File file = new File(path); if (!file.exists()) {
	 * file.createNewFile(); } else { BufferedWriter bw = new BufferedWriter(new
	 * FileWriter(file)); for (String line : content) { bw.write(line);
	 * bw.newLine(); } bw.close(); } }
	 */

	public static void main(String[] args) {
		MyFile inputText = new MyFile();
		ArrayList<String> text = inputText.readFile("D://_test.txt");
		for (int i = 0; i < text.size(); i++) {
			System.out.println(text.get(i));
		}
	}
}
