package string;

import date_time.DateUtils;

public class Validate {

	// Username gom chu thuong,chu hoa, so, dau gach ngang("-","_"),do dai tu
	// 6->15
	public static boolean validateUserName(String userName) {
		if (userName == null)
			return false;
		return userName.matches("^[a-zA-Z0-9_-]{6,15}$");
	}

	// Username gom chu thuong,chu hoa, so,do dai tu 2->8
	public static boolean validateClassName(String className) {
		if (className == null)
			return false;
		return className.matches("^[a-zA-Z0-9]{2,8}$");
	}

	// Password gom chu thuong,chu hoa, so, dau gach ngang("-","_"),do dai tu
	// 6->15
	public static boolean validatePassword(String password) {
		if (password == null)
			return false;
		return true;
	}

	// So dien thoai bat dau bang so 0, tong cong co 9 hoac 10 so
	public static boolean validateSDT(String sDT) {
		if (sDT == null)
			return false;
		return sDT.matches("^(0\\d{9,10})$");
	}

	/*
	 * Dinh dang: A@B.C hoac A@B.C.D A va B gom chu cai (hoa+thuong),chu so, dau
	 * gach ngang("-","_") A co them dau "." C va D (co the co E,F,.... phia sau
	 * D)chi gom ky tu thuong ,co tong ky tu tu 2->6
	 */
	public static boolean validateEmail(String email) {
		if (email == null)
			return false;
		return email
				.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[a-z0-9]+)*(\\.[a-z]{2,6})$");
	}

	// id co it nhat 1 so (chi co ky tu so)
	public static boolean validateID(String id) {
		if (id == null)
			return false;
		return id.matches("^\\d+");
	}

	/*
	 * dinh dang 01/01/01 , trong do ngay tu 01->31(hoac 1->31), thang tu 1->12
	 * (hoac 01->12), nam co do dai tu 1->4 so.
	 */
	public static boolean validatedate(String date) {
		if (date == null)
			return false;
		return date
				.matches("^(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/(\\d{1,4})");
	}

	// dd/mm/yyyy hh:mm:ss
	public static boolean validateDateTime(String dateTime) {
		if (dateTime == null)
			return false;
		return dateTime
				.matches("^(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/(\\d{1,4})\\s([0-1][0-9]|[2][0-3]):([0-5][0-9]):([0-5][0-9])");
	}

	// name chi co chu hoa , chu thuong va ky tu trong
	public static boolean validateName(String name) {
		if (name == null)
			return false;
		return name.length() >= 6;
	}

	// validate du lieu so
	public static boolean validateNumber(String value) {
		if (value == null) return false;
		return value.matches("\\d+");
	}
	public static boolean validateDate(String ngayBatDau, String ngayKetThuc) {
		int check = DateUtils.convertToTimestamp(ngayKetThuc).compareTo(DateUtils.convertToTimestamp(ngayBatDau));
		// So sánh 2 đối tượng date1 và date2.
		// check < 0 nghĩa là date1 < date2
		// check = 0 nghĩa là date1 = date2
		// check > 0 nghĩa là date1 > date2
		if (check >= 0) {
			return true;
		}
		return false;
	}
}
