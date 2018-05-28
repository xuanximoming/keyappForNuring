package com.nursing.servlet;

import com.nursing.data.db.DBDirectFormatJson;
import com.nursing.data.db.DBHelper;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Jack on 2015/12/16. 保存入院评估
 */
public class AdmissionAssessmentSaveServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setHeader("Content-type", "text/html;charset=UTF-8");
		String result = "{\n" + "    \"status\": %s\n" + "}";

		int status = 0;
		String wardCode = "";

		String bid = req.getParameter("bid");
		String vid = req.getParameter("vid");
		String saveid = req.getParameter("saveid");
		String saveok = req.getParameter("saveok");
		try {
			String pgSelectSQL = "select * from PAT_EVALUATION_M where patient_id='%s' and visit_id=%s and DICT_ID='02' and ITEM_ID='%s'";
			pgSelectSQL = String.format(pgSelectSQL, bid, vid, saveid);
			String pgSelectJson = DBDirectFormatJson.select(pgSelectSQL);
			if (!pgSelectJson.equals("[]")) {// 如果有数据，则跳过，执行保存详细项目
				JSONArray jsonArray = new JSONArray(pgSelectJson);
				JSONObject item = jsonArray.optJSONObject(0);
				wardCode = item.optString("DEPT_CODE");
			} else {
				String wardCodeJson = DBDirectFormatJson
						.select(String.format("select ward_code from pats_in_hospital where patient_id='%s'", bid));
				try {
					JSONArray jsonArray = new JSONArray(wardCodeJson);
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject item = jsonArray.optJSONObject(i);
						wardCode = item.optString("WARD_CODE");
					}
				} catch (Exception e) {
					System.out.println("WARD_CODE=" + e.toString());
				}
				/**
				 * 是否需要保存具有多个子项的父项数据
				try {
					String insertMainSQL = "INSERT INTO PAT_EVALUATION_M (patient_id,visit_id,DICT_ID,dept_code,RECORD_DATE,ITEM_ID,ITEM_NAME,ITEM_VALUE)\n"
							+ "VALUES ('%s',\n" + "        %s,\n" + "        '02',\n" + "        '%s',\n"
							+ "        to_date(to_char(sysdate,'yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd HH24:MI:SS'),\n"
							+ "        '%s',\n" + "        '%s',\n" + "        '')";
					insertMainSQL = String.format(insertMainSQL, bid, vid, wardCode, saveid, itemName(saveid));
					System.out.println("insertSQL=" + insertMainSQL);
					status = DBHelper.insert(insertMainSQL);
					System.out.println("status=" + status);
				} catch (Exception e) {
					System.out.println("insertMainSQL=" + e.toString());
				}
				*/
			}
		} catch (Exception ee) {
			System.out.println("pgSelectSQL=" + ee.toString());
			return;
		}
		//插入详细项目
		try {
			JSONArray jsonArray = new JSONArray(saveok);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject item = jsonArray.optJSONObject(i);
				String pgItemSelectSQL = "select * from PAT_EVALUATION_M where patient_id='%s' and visit_id=%s and DICT_ID='02' and ITEM_ID='%s'";
				String itemIDByItem = item.optString("itemid");
				if (itemIDByItem.length() % 2 != 0) {
					itemIDByItem = "0" + itemIDByItem;
				}
				pgItemSelectSQL = String.format(pgItemSelectSQL, bid, vid, itemIDByItem);
				String pgItemJson = DBDirectFormatJson.select(pgItemSelectSQL);
				if (!pgItemJson.equals("[]")) {
					String deleteItemSQL = "delete from PAT_EVALUATION_M where patient_id = '%s'\n"
							+ "and visit_id = '%s' and dict_id ='02' and item_id = '%s'";
					deleteItemSQL = String.format(deleteItemSQL, bid, vid, itemIDByItem);
					status = 0;
					status = DBHelper.delete(deleteItemSQL);
					System.out.println("delete status =" + status);
				}
				if ((pgItemJson.equals("[]") || status == 1) && item.optString("savetype").equals("1")) {
					String txt = item.optString("txt");
					String insertItemSQL = "INSERT INTO PAT_EVALUATION_M (patient_id,visit_id,DICT_ID,dept_code,RECORD_DATE,ITEM_ID,ITEM_NAME,ITEM_VALUE)\n"
							+ "VALUES ('%s',\n" + "        %s,\n" + "        '02',\n" + "        '%s',\n"
							+ "        to_date(to_char(sysdate,'yyyy-mm-dd HH24:MI:SS'),'yyyy-mm-dd HH24:MI:SS'),\n"
							+ "        '%s',\n" + "        '%s',\n" + "        '%s')";
					insertItemSQL = String.format(insertItemSQL, bid, vid, wardCode, itemIDByItem,
							itemName(itemIDByItem), txt);
					System.out.println("insertItemSQL=" + insertItemSQL);
					status = DBHelper.insert(insertItemSQL);
					System.out.println("inster status=" + status);
				}
			}
		} catch (Exception e) {
			System.out.println("insertItemSQL=" + e.toString());
		}

		resp.getWriter().write(String.format(result, status));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	public String itemName(String itemID) {
		String selectSQL = "select * from HIS_DICT_ITEM where dict_id=02 and item_id='%s'";
		selectSQL = String.format(selectSQL, itemID);
		String selectJson = DBDirectFormatJson.select(selectSQL);
		String ITEM_NAME = "";
		try {
			JSONArray jsonArray = new JSONArray(selectJson);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject item = jsonArray.optJSONObject(i);
				ITEM_NAME = item.optString("ITEM_NAME");
			}
		} catch (Exception e) {
			System.out.println("itemName=" + e.toString());
		}
		return ITEM_NAME;
	}

}
