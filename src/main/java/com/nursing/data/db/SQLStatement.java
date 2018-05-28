package com.nursing.data.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Properties;

/**
 * Created by Jack on 2015/12/14. SQL语句工具类
 */
public class SQLStatement {
	
	/**
	 * 格式化SQL语句，并将最终SQL打印在控制台，以供参考
	 * @param format 要格式化的语句
	 * @param args  需要填入的参数
	 * @return 格式化后的SQL语句
	 */
	private static String format(String format, Object... args) {
		String result = String.format(format, args);
		System.out.println("--------------SQLStatement------------\n" + result);
		return result;
	}
	
	/**
	 * Get sql form Properties file
	 * @param file file name
	 * @return
	 */

	private static String getSql(String file) {
		// 获取SQL语句的文件的路径
		try {
			File FilePath = new File(SQLStatement.class.getClassLoader().getResource("../../SQL/" + file).getPath());
			String path = FilePath.getAbsolutePath();
			InputStreamReader reader = new InputStreamReader(new FileInputStream(path), "UTF-8");
			Properties props = new Properties();
			props.load(reader);
			reader.close();
			String sql = props.getProperty("SQL");
			return sql;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 保存jsp 网页传来的SQL语句
	 * @param sql SQL statements needed to save
	 * @param strdo Saved file name
	 * @return preseration outcomes
	 */
	public static String setSqlForJsp(String sql, String strdo) {
		// 获取SQL语句的文件的路径
		try {
			File FilePath = new File(SQLStatement.class.getClassLoader().getResource("../../SQL/" + strdo).getPath());
			String path = FilePath.getAbsolutePath();
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(path), "UTF-8");
			Properties props = new Properties();
			props.setProperty("SQL", sql);
			props.store(writer, "");
			writer.close();
			return "SQL保存成功";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "SQL保存出现问题";
		}
	}
	/**
	 * 
	 * @param file
	 * @return Save State（if you are successful）
	 */
	public static String getSqlForJsp(String file) {
		if (file == null || file.equals("")) {
			System.out.println(file);
			return "";
		}
		String sql = getSql(file);
		return sql;
	}

	/**
	 * 护士登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws IOException
	 */
	public static String loginSQL(String username, String password) {
		String sql = getSql("loginSQL.do");
		return format(sql, username, password);
	}

	/**
	 * 获取病人列表
	 * @param uid //用户ID
	 * @return
	 */
	public static String patientListSQL(String uid) {
		String sql = getSql("patientListSQL.do");
		return format(sql, uid);
	}

	/**
	 * 获取病人详情
	 * 
	 * @param patientID
	 * @param visitID
	 * @return 可执行SQL语句
	 */
	public static String patientDetailSQL(String patientID, String visitID) {
		String sql = getSql("patientDetailSQL.do");
		return format(sql, patientID, visitID);
	}

	/**
	 * 获取WARD_CODE，bed_no 用于获取上一床和下一床病人
	 * 
	 * @param patientID
	 * @param visitID
	 * @return 可执行SQL语句
	 */
	public static String wardCodeAndBedNoSQL(String patientID, String visitID) {
		String sql = getSql("wardCodeAndBedNoSQL.do");
		return format(sql, patientID, visitID);
	}

	/**
	 * 获取下一床病人详情
	 * 
	 * @param wardCode
	 * @param bedNo
	 * @return 可执行SQL语句
	 */
	public static String nextPatientDetailSQL(String wardCode, String bedNo) {
		String sql = getSql("nextPatientDetailSQL.do");
		return format(sql, wardCode, bedNo);
	}

	/**
	 * 获取上一床病人详情
	 * 
	 * @param wardCode
	 * @param bedNo
	 * @return 可执行SQL语句
	 */
	public static String previousPatientDetailSQL(String wardCode, String bedNo) {
		String sql = getSql("previousPatientDetailSQL.do");
		return format(sql, wardCode, bedNo);
	}

	/**
	 * 获取医嘱类别，如药疗、处置、护理、其他
	 * 
	 * @return 可执行SQL语句
	 */
	public static String orderTypeSQL() {
		String sql = getSql("orderTypeSQL.do");
		return format(sql);
	}

	/**
	 * 获取医嘱集合
	 * 
	 * @param patientID
	 * @param visitID
	 * @param ot 医嘱类型 如（药疗、处置、护理、其他）
	 * @param cl  长期或者临时
	 * @return 可执行SQL语句
	 */
	public static String orderListSQL(String patientID, String visitID, String ot, String cl) {
		String sql = getSql("orderListSQL.do");
		if (ot != null && !ot.equals("")) {
			sql = sql + "   and ORDER_CLASS='" + ot.toUpperCase() + "'";
		}
		if (cl != null && !cl.equals("")) {

			if (cl.equals("长期")) {
				cl = "1";
			} else if (cl.equals("临时")) {
				cl = "0";
			}
			sql = sql + "   and REPEAT_INDICATOR=" + cl;
		}
		sql = sql + "   ORDER BY zx_time DESC";
		return format(sql, patientID, visitID);
	}

	/**
	 * 获取入院评估
	 * 
	 * @return 可执行SQL语句
	 */
	public static String admissionAssessmentSQL() {
		String sql = getSql("admissionAssessmentSQL.do");
		return format(sql);
	}

	/**
	 * 根据itemID获取入院评估
	 * 
	 * @return 可执行SQL语句
	 */
	public static String admissionAssessmentSQL(String itemID) {
		String length = String.valueOf(itemID.length());
		String lengths = String.valueOf(itemID.length() + 2);
		String lengthsPlus2 = String.valueOf(itemID.length() + 4);
		String sql = getSql("admissionAssessmentSQLID.do");
		return format(sql, lengths, lengthsPlus2, length, itemID, lengths);
	}

	/**
	 * 获取护理列表
	 * 
	 * @param patientID
	 * @param visitID
	 * @return 可执行SQL语句
	 */
	public static String nursingSQL(String patientID, String visitID) {
		String sql = getSql("nursingSQL.do");
		return format(sql, patientID, visitID);
	}

	/**
	 * 根据护理类型和时间获取护理历史
	 * @param patientID
	 * @param visitID
	 * @param date 2015-12-15
	 * @param cls A：生命体征 ，B：出量 ，C：入量 ，D：其他 ，E：事故
	 * @return 可执行SQL语句
	 */
	public static String nursingDescSQL(String patientID, String visitID, String date, String cls) {
		String itemNameSQL = "";
		if (cls.equals("A")) {
			itemNameSQL = "AND vital_signs in ('腋下体温','脉搏','呼吸','心率','物理降温','血压')";
		} else if (cls.equals("B")) {
			itemNameSQL = "AND vital_signs in ('引流量','总出量','总尿量','咯血量','总痰量','大便次数')";
		} else if (cls.equals("C")) {
			itemNameSQL = "AND vital_signs in ('总入量')";
		} else if (cls.equals("D")) {
			itemNameSQL = "AND vital_signs in ('身高','体重')";
		}
		String sql = getSql("nursingDescSQL.do");
		return format(sql + " " + itemNameSQL, patientID, visitID, date);
	}

	/**
	 * 获取 护理类别项目名称
	 * @param cls  A：生命体征 ，B：出量 ，C：入量 ，D：其他 ，E：事故
	 * @return 可执行SQL语句
	 */
	public static String nursingTypeNameSQL(String cls) {
		return null;
	}

	/**
	 * 获取检查列表
	 * 
	 * @param testPatientID
	 * @param testVisitID
	 * @return 可执行SQL语句
	 */
	public static String checkListSQL(String testPatientID, String testVisitID) {
		String sql = getSql("checkListSQL.do");
		return format(sql, testPatientID, testVisitID);
	}

	/**
	 * 获取检验列表
	 * 
	 * @param pid
	 * @param vid
	 * @return 可执行SQL语句
	 */
	public static String inspectionList(String pid, String vid) {
		String sql = getSql("inspectionList.do");
		return format(sql, pid, vid);
	}

	/**
	 * 获取检验结果列表
	 * 
	 * @param pid
	 * @param vid
	 * @param jyid
	 * @return 可执行SQL语句
	 */
	public static String inspectionResultList(String pid, String vid, String jyid) {
		String sql = getSql("inspectionResultList.do");
		return format(sql, jyid);
	}

	/**
	 * 获取手术列表
	 * 
	 * @param pid
	 * @param vid
	 * @return 可执行SQL语句
	 */
	public static String operationListSQL(String pid, String vid) {
		String sql = getSql("operationListSQL.do");
		return format(sql, pid, vid);
	}

	/**
	 * 获取标本
	 * 
	 * @param pid
	 * @param vid
	 * @return 可执行SQL语句
	 */
	public static String specimenSQL(String pid, String vid) {
		String sql = getSql("specimenSQL.do");
		return format(sql, pid, vid);
	}

	/**
	 * 保存标本采取信息
	 * 
	 * @param testno 检验申请号
	 * @param uid 采样人用户ID
	 * @param time 采取时间
	 * @return 可执行SQL语句
	 */
	public static String specimenSaveSQL(String testno, String uid, String act) {
		String sql = getSql("specimenSaveSQL.do");
		return format(sql, testno, uid, act);
	}

	/**
	 * 医嘱执行单中的获取病人SQL语句
	 * 
	 * @param bid
	 * @param vid
	 * @return 可执行SQL语句
	 */
	public static String orderExecutionByBinren(String bid, String vid) {
		String bingrenSQL = getSql("orderExecutionByBinren.do");
		return format(bingrenSQL, bid, vid);
	}

	/**
	 * 医嘱执行单中的执行单获取语句
	 * 
	 * @param bid
	 * @param vid
	 * @param ot
	 * @param zt
	 * @param cl
	 * @return 可执行SQL语句
	 */
	public static String orderExecutionByOrder(String bid, String vid, String ot, String zt, String cl) {
		if (zt.equals("D")) {
			String sql = getSql("orderExecutionByOrderD.do");
			return format(sql, bid, vid);
		} else {
			String sql = getSql("orderExecutionByOrder.do");
			return format(sql, bid, vid, zt);
		}
	}

	/**
	 * 获取交接班时间
	 * 
	 * @param bid
	 * @param vid
	 * @return 可执行SQL语句
	 */
	public static String shiftingOfDutyTimes(String bid, String vid) {
		String sql = getSql("shiftingOfDutyTimes.do");
		return format(sql, bid, vid);
	}

	/**
	 * 获取交接班时间详情
	 * 
	 * @param bid
	 * @param vid
	 * @param jobDate
	 * @return 可执行SQL语句
	 */
	public static String shiftingOfDutyTimesDetail(String bid, String vid, String jobDate) {
		String sql = getSql("shiftingOfDutyTimesDetail.do");
		return format(sql, bid, vid, jobDate);
	}

	/**
	 * 获取教育列表
	 * 
	 * @return 可执行SQL语句
	 */
	public static String educationList() {
		return getSql("educationList.do");
	}

	/**
	 * 根据itemID获取教育列表
	 * 
	 * @param itemId
	 * @return 可执行SQL语句
	 */
	public static String educationListByItemID(String itemId) {
		String length = String.valueOf(itemId.length());
		String lengths = String.valueOf(itemId.length() + 2);
		String lengthsPlus2 = String.valueOf(itemId.length() + 4);
		String sql = getSql("educationListByItemID.do");
		return format(sql, lengths, lengthsPlus2, length, itemId, lengths);
	}
}
