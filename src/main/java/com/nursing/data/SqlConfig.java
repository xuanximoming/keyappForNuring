package com.nursing.data;

import java.io.File;
import com.nursing.data.db.SQLStatement;

/**
 * @author Ukey zhang E-mail yeshentianyue@sina.com
 * @version 创建时间：2017年10月14日 下午12:44:25 模块sql管理类 for sql.jsp
 */
public class SqlConfig {
	/**
	 *  SAVE statement
	 * @param sql
	 * @param strdo
	 * @return
	 */
	public static String setSql(String sql, String strdo) {
		return SQLStatement.setSqlForJsp(sql, strdo);
	}

	public static String getSql(String file) {
		return SQLStatement.getSqlForJsp(file);
	}

	public static String CreateHtml(String filename) {

		String HtmlCode = "";
		// 获取SQL语句的文件的路径
		File FilePath = new File(SQLStatement.class.getClassLoader().getResource("../../SQL").getPath());
		String path = FilePath.getAbsolutePath();
		// 获得指定文件对象
		File file = new File(path);
		// 获得该文件夹内的所有文件
		File[] array = file.listFiles();
		HtmlCode += "<p style=\"width: 100%;\">\n";
		for (int i = 0; i < array.length; i++) {
			// 如果是文件
			if (array[i].isFile()) {
				if (filename == null) {
					filename = "";
				}
				String Code = "";
				if (i % 5 == 0 && i > 0) {
					HtmlCode += "</p>\n";
					HtmlCode += "<p style=\"width: 100%;\">\n";
				}
				if (filename.trim().equals(array[i].getName().trim())) {
					Code = "<label style=\"display:inline-block;width:19%;\"><input checked=\"true\" name=\"Fruit\" "
							+ "type=\"radio\" value=\"" + array[i].getName() + "\" />" + array[i].getName()
							+ "</label>\n" + "<input id=\"strdo\" type=\"hidden\" name=\"strdo\" value=\""
							+ array[i].getName() + "\">\n";
				} else {
					Code = "<label style=\"display:inline-block;width:19%;\"><input name=\"Fruit\" "
							+ "type=\"radio\" value=\"" + array[i].getName() + "\" />" + array[i].getName()
							+ "</label>\n";
				}
				HtmlCode = HtmlCode + Code;
			}
		}
		HtmlCode += "</p>";
		return HtmlCode;
	}
}
