package com.yangjie.util;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/AcenterApi")
public class AcenterApi {

	// /*
	// * 查询会议列表
	// */
	// public PageUtils listPermRoom(String mcu, String page, String username,
	// String row, String mname) throws Exception {
	// PageUtils p = new PageUtils();
	// TreeMap<String, Object> apiparamsMap = new TreeMap<String, Object>();
	// TreeMap<String, String> m = new TreeMap<String, String>();
	// List<Map<String, Object>> user = new ArrayList<Map<String, Object>>();
	// apiparamsMap.put("method", 900101);
	// apiparamsMap.put("AUTH_UID",
	// ResourceBundle.getValue("acenter.username"));
	// apiparamsMap.put("AUTH_PASS",
	// ResourceBundle.getValue("acenter.password"));
	// apiparamsMap.put("IA_USERID", username); // 用户id
	// apiparamsMap.put("IA_ROW", row); // 每页显示的行数
	// apiparamsMap.put("IA_PAGE", Integer.parseInt(page) - 1); //
	// if (mname != null && !mname.equals("") && !mname.equals("null")) {
	// apiparamsMap.put("IA_ROOMNAME", mname); //
	// }
	// apiparamsMap.put("STATE", "3"); //
	// JSONObject jsonObject = JSONObject.fromObject(apiparamsMap);
	// m.put("data", jsonObject.toString());
	// UrlEncodedFormEntity formEntity = HttpClientUtil.getPostFormEntity(m);
	// String res = HttpClientUtil.doHttpPost("http://" + mcu + ":8082" +
	// "/acenter/dbcall.action?", formEntity);
	// JSONObject j = JSONObject.fromObject(res);
	// String ret = j.getString("ret");
	// String totalPages = j.getString("totalPages");
	// String totalcount = j.getString("totalcount");
	// p.setTotalPage(Integer.valueOf(totalPages));
	// p.setTotalCount(Integer.valueOf(totalcount));
	// p.setCurrPage(Integer.parseInt(page));
	// if (ret.equals("1")) {
	// JSONArray arr_mould = JSONArray.fromObject(j.getString("arr_mould"));
	// p.setList(arr_mould);
	// return p;
	// }
	// return p;
	// }
	//

	/*
	 * 会议登录
	 */
	public String Login(String username, String password) throws Exception {
		TreeMap<String, Object> apiparamsMap = new TreeMap<String, Object>();
		TreeMap<String, String> m = new TreeMap<String, String>();
		apiparamsMap.put("method", 600001);
		apiparamsMap.put("username", username);
		apiparamsMap.put("password", password);
		JSONObject jsonObject = JSONObject.fromObject(apiparamsMap);
		m.put("data", jsonObject.toString());
		String ret = null;
		try {
			UrlEncodedFormEntity formEntity = HttpClientUtil.getPostFormEntity(m);
			String res = HttpClientUtil.doHttpPost("http://localhost:8082/acenter/dbcall.action?", formEntity);
			JSONObject j = JSONObject.fromObject(res);
			ret = j.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "-1";
		}
		return ret;
	}

	/*
	 * 组织创建
	 */
	public String createGroup(String json, String sKey) throws Exception {
		TreeMap<String, Object> apiparamsMap = new TreeMap<String, Object>();
		TreeMap<String, String> m = new TreeMap<String, String>();
		Map<String, Object> result = new HashMap<String, Object>();
		apiparamsMap.put("method", 900012);
		apiparamsMap.put("AUTH_UID", ResourceBundle.getValue("acenter.username"));
		apiparamsMap.put("AUTH_PASS", ResourceBundle.getValue("acenter.password"));
		apiparamsMap.put("IA_DEPTNAME", "test9"); // 组织名称
		apiparamsMap.put("IA_PARENT_DEPT_ID", "1");// pid
		apiparamsMap.put("sKey", sKey);
		JSONObject jsonObject = JSONObject.fromObject(apiparamsMap);
		m.put("data", jsonObject.toString());
		String ret = null;
		String serverip = ResourceBundle.getValue("acenter.ip");
		try {
			UrlEncodedFormEntity formEntity = HttpClientUtil.getPostFormEntity(m);
			String res = HttpClientUtil.doHttpPost(serverip + "/acenter/dbcall.action?", formEntity);
			JSONObject j = JSONObject.fromObject(res);
			if (Integer.parseInt(j.getString("ret")) > 1) {
				result.put("code", 0x0200);
				result.put("message", "请求成功");
				result.put("gid", j.getString("ret"));
			} else {
				result.put("code", 0xffff);
				result.put("message", "未知错误");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("code", 0xffff);
			result.put("message", "未知错误");
		}
		return JSONObject.fromObject(result).toString();
	}

	/*
	 * 组织修改
	 */
	public String modifyGroup(String json, String sKey) throws Exception {
		TreeMap<String, Object> apiparamsMap = new TreeMap<String, Object>();
		TreeMap<String, String> m = new TreeMap<String, String>();
		Map<String, Object> result = new HashMap<String, Object>();
		apiparamsMap.put("method", 900012);
		apiparamsMap.put("AUTH_UID", ResourceBundle.getValue("acenter.username"));
		apiparamsMap.put("AUTH_PASS", ResourceBundle.getValue("acenter.password"));
		apiparamsMap.put("IA_DEPTNAME", "test10"); // 部门名称
		apiparamsMap.put("IA_PARENT_DEPT_ID", "1"); // pid
		apiparamsMap.put("IA_DEPTID", "294"); // 部门id
		apiparamsMap.put("sKey", sKey);
		JSONObject jsonObject = JSONObject.fromObject(apiparamsMap);
		m.put("data", jsonObject.toString());
		String serverip = ResourceBundle.getValue("acenter.ip");
		try {
			UrlEncodedFormEntity formEntity = HttpClientUtil.getPostFormEntity(m);
			String res = HttpClientUtil.doHttpPost(serverip + "/acenter/dbcall.action?", formEntity);
			JSONObject j = JSONObject.fromObject(res);
			if (j.getString("ret").equals("1")) {
				result.put("code", 0x0200);
				result.put("message", "请求成功");
			} else {
				result.put("code", 0xffff);
				result.put("message", "未知错误");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("code", 0xffff);
			result.put("message", "未知错误");
		}
		return JSONObject.fromObject(result).toString();
	}

	/*
	 * 组织删除
	 */
	public String deleteGroup(String json, String sKey) throws Exception {
		TreeMap<String, Object> apiparamsMap = new TreeMap<String, Object>();
		TreeMap<String, String> m = new TreeMap<String, String>();
		Map<String, Object> result = new HashMap<String, Object>();
		apiparamsMap.put("method", 900107);
		apiparamsMap.put("AUTH_UID", ResourceBundle.getValue("acenter.username"));
		apiparamsMap.put("AUTH_PASS", ResourceBundle.getValue("acenter.password"));
		apiparamsMap.put("IA_DEPTID", "294"); // 部门id
		apiparamsMap.put("sKey", sKey);
		JSONObject jsonObject = JSONObject.fromObject(apiparamsMap);
		m.put("data", jsonObject.toString());
		String serverip = ResourceBundle.getValue("acenter.ip");
		try {
			UrlEncodedFormEntity formEntity = HttpClientUtil.getPostFormEntity(m);
			String res = HttpClientUtil.doHttpPost(serverip + "/acenter/dbcall.action?", formEntity);
			JSONObject j = JSONObject.fromObject(res);
			if (j.getString("ret").equals("1")) {
				result.put("code", 0x0200);
				result.put("message", "请求成功");
			} else {
				result.put("code", 0xffff);
				result.put("message", "未知错误");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("code", 0xffff);
			result.put("message", "未知错误");
		}
		return JSONObject.fromObject(result).toString();
	}

	/*
	 * 创建用户
	 */
	public String createUser(String json, String sKey) throws Exception {
		TreeMap<String, Object> apiparamsMap = new TreeMap<String, Object>();
		TreeMap<String, String> m = new TreeMap<String, String>();
		Map<String, Object> result = new HashMap<String, Object>();
		int level = 0;
		apiparamsMap.put("method", 900011);
		apiparamsMap.put("AUTH_UID", ResourceBundle.getValue("acenter.username"));
		apiparamsMap.put("AUTH_PASS", ResourceBundle.getValue("acenter.password"));
		apiparamsMap.put("IA_USERNAME", "yj");// 账户
		apiparamsMap.put("IA_U_NICKNAME", "yj");// 昵称
		apiparamsMap.put("IA_DEPTID", "1");// 部门
		String skey = getsKey();
		apiparamsMap.put("IA_U_PASSWORD", AES.Decrypt(Base64.getDecoder().decode(""), sKey));// 密码
		apiparamsMap.put("IA_U_TELEPHONE", "18971477097");// 电话
		apiparamsMap.put("IA_U_CREATED_TIME", "");// 有效开始时间
		apiparamsMap.put("IA_U_EXPIRED_TIME", "");// 有效结束时间
		apiparamsMap.put("sKey", sKey);
		JSONObject jsonObject = JSONObject.fromObject(apiparamsMap);
		m.put("data", jsonObject.toString());
		String ret = null;
		String serverip = ResourceBundle.getValue("acenter.ip");
		try {
			UrlEncodedFormEntity formEntity = HttpClientUtil.getPostFormEntity(m);
			String res = HttpClientUtil.doHttpPost(serverip + "/acenter/dbcall.action?", formEntity);
			JSONObject j = JSONObject.fromObject(res);
			if (Integer.parseInt(j.getString("ret")) > 1) {
				result.put("code", 0x0200);
				result.put("message", "请求成功");
				result.put("snid", 18);
			} else {
				result.put("code", 0xffff);
				result.put("message", "未知错误");
			}
			if (level == 3) {
				authorize(j.getString("ret"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "-1";
		}
		return JSONObject.fromObject(result).toString();
	}

	// 给用户设置管理权限
	public String authorize(String userid) throws Exception {
		TreeMap<String, Object> apiparamsMap = new TreeMap<String, Object>();
		TreeMap<String, String> m = new TreeMap<String, String>();
		apiparamsMap.put("method", 900127);
		apiparamsMap.put("AUTH_UID", ResourceBundle.getValue("acenter.username"));
		apiparamsMap.put("AUTH_PASS", ResourceBundle.getValue("acenter.password"));
		apiparamsMap.put("USERID", userid);// userid
		apiparamsMap.put("JURISDICTION", "1,5");// userid
		JSONObject jsonObject = JSONObject.fromObject(apiparamsMap);
		m.put("data", jsonObject.toString());
		String ret = null;
		String serverip = ResourceBundle.getValue("acenter.ip");
		try {
			UrlEncodedFormEntity formEntity = HttpClientUtil.getPostFormEntity(m);
			String res = HttpClientUtil.doHttpPost(serverip + "/acenter/dbcall.action?", formEntity);
			JSONObject j = JSONObject.fromObject(res);
			ret = j.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "-1";
		}
		return ret;
	}

	/*
	 * 用户修改
	 */
	public String modifyUser(String json, String sKey) throws Exception {
		TreeMap<String, Object> apiparamsMap = new TreeMap<String, Object>();
		TreeMap<String, String> m = new TreeMap<String, String>();
		Map<String, Object> result = new HashMap<String, Object>();
		apiparamsMap.put("method", 900011);
		apiparamsMap.put("AUTH_UID", ResourceBundle.getValue("acenter.username"));
		apiparamsMap.put("AUTH_PASS", ResourceBundle.getValue("acenter.password"));
		apiparamsMap.put("IA_USERNAME", "yj");// 账户
		apiparamsMap.put("IA_U_TRUENAME", "yj");// 真实姓名
		apiparamsMap.put("IA_U_PASSWORD", "2");// 密码
		apiparamsMap.put("IA_U_TELEPHONE", "");// 电话
		apiparamsMap.put("sKey", sKey);
		JSONObject jsonObject = JSONObject.fromObject(apiparamsMap);
		m.put("data", jsonObject.toString());
		String ret = null;
		String serverip = ResourceBundle.getValue("acenter.ip");
		try {
			UrlEncodedFormEntity formEntity = HttpClientUtil.getPostFormEntity(m);
			String res = HttpClientUtil.doHttpPost(serverip + "/acenter/dbcall.action?", formEntity);
			JSONObject j = JSONObject.fromObject(res);
			if (j.getString("ret").equals("1")) {
				result.put("code", 0x0200);
				result.put("message", "请求成功");
			} else {
				result.put("code", 0xffff);
				result.put("message", "未知错误");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "-1";
		}
		return JSONObject.fromObject(result).toString();
	}

	/*
	 * 用户删除
	 */
	public String deleteUser(String json, String sKey) throws Exception {
		TreeMap<String, Object> apiparamsMap = new TreeMap<String, Object>();
		TreeMap<String, String> m = new TreeMap<String, String>();
		Map<String, Object> result = new HashMap<String, Object>();
		apiparamsMap.put("method", 900108);
		apiparamsMap.put("AUTH_UID", ResourceBundle.getValue("acenter.username"));
		apiparamsMap.put("AUTH_PASS", ResourceBundle.getValue("acenter.password"));
		apiparamsMap.put("IA_USERNAME", "");// 账户
		apiparamsMap.put("IA_USERID", "1952");// 账户
		apiparamsMap.put("sKey", sKey);
		JSONObject jsonObject = JSONObject.fromObject(apiparamsMap);
		m.put("data", jsonObject.toString());
		String ret = null;
		String serverip = ResourceBundle.getValue("acenter.ip");
		try {
			UrlEncodedFormEntity formEntity = HttpClientUtil.getPostFormEntity(m);
			String res = HttpClientUtil.doHttpPost(serverip + "/acenter/dbcall.action?", formEntity);
			JSONObject j = JSONObject.fromObject(res);
			if (j.getString("ret").equals("1")) {
				result.put("code", 0x0200);
				result.put("message", "请求成功");
			} else if (j.getString("ret").equals("-2")) {
				result.put("code", 0x0427);
				result.put("message", "用户不存在");
			} else {
				result.put("code", 0xffff);
				result.put("message", "未知错误");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("code", 0xffff);
			result.put("message", "未知错误");
		}
		return JSONObject.fromObject(result).toString();
	}

	/*
	 * 创建会议室
	 */
	public String createroom(String json, String sKey) throws Exception {
		TreeMap<String, Object> apiparamsMap = new TreeMap<String, Object>();
		TreeMap<String, String> m = new TreeMap<String, String>();
		Map<String, Object> result = new HashMap<String, Object>();
		int mt_type = 0; // 会议类型1:即时会议 2:预约会议 如果是即时会议 开始开始时间要提前5分钟
		apiparamsMap.put("method", 900102);
		apiparamsMap.put("AUTH_UID", ResourceBundle.getValue("acenter.username"));
		apiparamsMap.put("AUTH_PASS", ResourceBundle.getValue("acenter.password"));
		apiparamsMap.put("IA_ROOMNAME", "");// 会议室名称
		apiparamsMap.put("MEETING_SERIAL", "");// cid
		if (mt_type == 1) {
			apiparamsMap.put("START_VALID_DATE", DateUtil.timeStampaddfivemin(""));// 会议室开始时间
		} else {
			apiparamsMap.put("START_VALID_DATE", "");// 会议室开始时间
		}
		apiparamsMap.put("IA_MEETING_PERIOD", 0);// 0一次性会议 1永久性会议
		apiparamsMap.put("END_VALID_DATE", "");// 会议室结束时间
		apiparamsMap.put("IA_CREATID", "");// 创建人
		apiparamsMap.put("IA_R_PWD_NORMAL", "");// 会议密码
		apiparamsMap.put("DEPT_ID", "");// 部门id

		apiparamsMap.put("mute", "");// 静音入会（默认否） 1是 0否
		apiparamsMap.put("allowMute", "");// 允许参会人解除静音(默认是)
		apiparamsMap.put("noHost", "");// 无主持人入会
		apiparamsMap.put("noHost", "");// 通过会议号入会

		apiparamsMap.put("sKey", sKey);
		JSONObject jsonObject = JSONObject.fromObject(apiparamsMap);
		m.put("data", jsonObject.toString());
		String ret = null;
		String serverip = ResourceBundle.getValue("acenter.ip");
		try {
			UrlEncodedFormEntity formEntity = HttpClientUtil.getPostFormEntity(m);
			String res = HttpClientUtil.doHttpPost(serverip + "/acenter/dbcall.action?", formEntity);
			JSONObject j = JSONObject.fromObject(res);
			ret = j.toString();
			if (Integer.parseInt(j.getString("ret")) > 1) {
				// 设置主持人
				createroom(ret, "");
				// 会议室人员授权
				String[] user = Shuzuhebing.hebing(null, null); // 暂时NULL
				roomUserAuth(ret, user);
				result.put("code", 0x0200);
				result.put("message", "请求成功");
				result.put("webrtcUrl", j.getString("webrtcUrl"));
			} else {
				result.put("code", 0xffff);
				result.put("message", "未知错误");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "-1";
		}
		return JSONObject.fromObject(result).toString();
	}

	/*
	 * 修改会议室
	 */
	public String modifyroom(String json, String sKey) throws Exception {
		TreeMap<String, Object> apiparamsMap = new TreeMap<String, Object>();
		TreeMap<String, String> m = new TreeMap<String, String>();
		Map<String, Object> result = new HashMap<String, Object>();
		int mt_type = 0; // 会议类型1:即时会议 2:预约会议 如果是即时会议 开始开始时间要提前5分钟
		apiparamsMap.put("method", 900014);
		apiparamsMap.put("AUTH_UID", ResourceBundle.getValue("acenter.username"));
		apiparamsMap.put("AUTH_PASS", ResourceBundle.getValue("acenter.password"));
		apiparamsMap.put("IA_ROOMNAME", "");// 会议室名称
		apiparamsMap.put("MEETING_SERIAL", "");// cid
		apiparamsMap.put("START_VALID_DATE", "");// 会议室开始时间
		apiparamsMap.put("END_VALID_DATE", "");// 会议室结束时间
		apiparamsMap.put("IA_CREATID", "");// 创建人
		apiparamsMap.put("IA_R_PWD_NORMAL", "");// 会议密码
		apiparamsMap.put("DEPT_ID", "");// 部门id

		apiparamsMap.put("mute", "");// 静音入会（默认否） 1是 0否
		apiparamsMap.put("allowMute", "");// 允许参会人解除静音(默认是)
		apiparamsMap.put("noHost", "");// 无主持人入会
		apiparamsMap.put("noHost", "");// 通过会议号入会

		apiparamsMap.put("sKey", sKey);
		JSONObject jsonObject = JSONObject.fromObject(apiparamsMap);
		m.put("data", jsonObject.toString());
		String ret = null;
		String serverip = ResourceBundle.getValue("acenter.ip");
		try {
			UrlEncodedFormEntity formEntity = HttpClientUtil.getPostFormEntity(m);
			String res = HttpClientUtil.doHttpPost(serverip + "/acenter/dbcall.action?", formEntity);
			JSONObject j = JSONObject.fromObject(res);
			if (j.getString("ret").equals("1")) {
				// 会议室人员授权
				String[] user = Shuzuhebing.hebing(null, null); // 暂时NULL
				roomUserAuth(ret, user);
				// 删除会议室人员授权
				delroomUserAuth(ret, null);

				result.put("code", 0x0200);
				result.put("message", "请求成功");
			} else {
				result.put("code", 0xffff);
				result.put("message", "未知错误");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "-1";
		}
		return JSONObject.fromObject(result).toString();
	}

	/**
	 * 设置主持人
	 * 
	 * @return
	 * @throws Exception
	 */
	public String sethost(String roomid, String username) throws Exception {
		TreeMap<String, Object> apiparamsMap = new TreeMap<String, Object>();
		TreeMap<String, String> m = new TreeMap<String, String>();
		apiparamsMap.put("method", 900015);
		apiparamsMap.put("AUTH_UID", ResourceBundle.getValue("acenter.username"));
		apiparamsMap.put("AUTH_PASS", ResourceBundle.getValue("acenter.password"));
		apiparamsMap.put("IA_ROOMID", roomid);// 会议室id
		apiparamsMap.put("IA_PK_USER", username);// 用户名
		apiparamsMap.put("IA_USER_TYPE", "1");// 角色1 = 主持人 2 = 普通用户 3 = 助理
		JSONObject jsonObject = JSONObject.fromObject(apiparamsMap);
		m.put("data", jsonObject.toString());
		String ret = null;
		String serverip = ResourceBundle.getValue("acenter.ip");
		try {
			UrlEncodedFormEntity formEntity = HttpClientUtil.getPostFormEntity(m);
			String res = HttpClientUtil.doHttpPost(serverip + "/acenter/dbcall.action?", formEntity);
			JSONObject j = JSONObject.fromObject(res);
			ret = j.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "-1";
		}
		return ret;
	}

	/**
	 * 会议给人员授权
	 * 
	 * @return
	 * @throws Exception
	 */
	public String roomUserAuth(String rommid, String user[]) throws Exception {
		TreeMap<String, Object> apiparamsMap = new TreeMap<String, Object>();
		TreeMap<String, String> m = new TreeMap<String, String>();
		apiparamsMap.put("method", 900017);
		apiparamsMap.put("AUTH_UID", ResourceBundle.getValue("acenter.username"));
		apiparamsMap.put("AUTH_PASS", ResourceBundle.getValue("acenter.password"));
		apiparamsMap.put("IA_ROOMID", rommid);// 会议室id
		apiparamsMap.put("arrUseIDName", user);//
		JSONObject jsonObject = JSONObject.fromObject(apiparamsMap);
		m.put("data", jsonObject.toString());
		String ret = null;
		String serverip = ResourceBundle.getValue("acenter.ip");
		try {
			UrlEncodedFormEntity formEntity = HttpClientUtil.getPostFormEntity(m);
			String res = HttpClientUtil.doHttpPost(serverip + "/acenter/dbcall.action?", formEntity);
			JSONObject j = JSONObject.fromObject(res);
			ret = j.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "-1";
		}
		return ret;
	}

	/**
	 * 删除会议给人员授权
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delroomUserAuth(String rommid, String user[]) throws Exception {
		TreeMap<String, Object> apiparamsMap = new TreeMap<String, Object>();
		TreeMap<String, String> m = new TreeMap<String, String>();
		apiparamsMap.put("method", 900109);
		apiparamsMap.put("AUTH_UID", ResourceBundle.getValue("acenter.username"));
		apiparamsMap.put("AUTH_PASS", ResourceBundle.getValue("acenter.password"));
		apiparamsMap.put("IA_ROOMID", rommid);// 会议室id
		apiparamsMap.put("arrUseIDName", user);//
		JSONObject jsonObject = JSONObject.fromObject(apiparamsMap);
		m.put("data", jsonObject.toString());
		String ret = null;
		String serverip = ResourceBundle.getValue("acenter.ip");
		try {
			UrlEncodedFormEntity formEntity = HttpClientUtil.getPostFormEntity(m);
			String res = HttpClientUtil.doHttpPost(serverip + "/acenter/dbcall.action?", formEntity);
			JSONObject j = JSONObject.fromObject(res);
			ret = j.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "-1";
		}
		return ret;
	}

	/*
	 * 删除会议室
	 */
	public String deleteroom(String json, String sKey) throws Exception {
		TreeMap<String, Object> apiparamsMap = new TreeMap<String, Object>();
		TreeMap<String, String> m = new TreeMap<String, String>();
		Map<String, Object> result = new HashMap<String, Object>();
		apiparamsMap.put("method", 91015);
		apiparamsMap.put("AUTH_UID", ResourceBundle.getValue("acenter.username"));
		apiparamsMap.put("AUTH_PASS", ResourceBundle.getValue("acenter.password"));
		apiparamsMap.put("cid", "");// 会议室id
		apiparamsMap.put("sKey", sKey);
		JSONObject jsonObject = JSONObject.fromObject(apiparamsMap);
		m.put("data", jsonObject.toString());
		String ret = null;
		String serverip = ResourceBundle.getValue("acenter.ip");
		try {
			UrlEncodedFormEntity formEntity = HttpClientUtil.getPostFormEntity(m);
			String res = HttpClientUtil.doHttpPost(serverip + "/acenter/dbcall.action?", formEntity);
			JSONObject j = JSONObject.fromObject(res);
			if (j.getString("ret").equals("1")) {
				result.put("code", 0x0200);
				result.put("message", "请求成功");
			} else {
				result.put("code", 0xffff);
				result.put("message", "未知错误");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "-1";
		}
		return JSONObject.fromObject(result).toString();
	}

	/*
	 * 硬件终端新增
	 */
	public String tmnlcreate(String json, String sKey) throws Exception {
		TreeMap<String, Object> apiparamsMap = new TreeMap<String, Object>();
		TreeMap<String, String> m = new TreeMap<String, String>();
		Map<String, Object> result = new HashMap<String, Object>();
		apiparamsMap.put("method", 91006);
		apiparamsMap.put("AUTH_UID", ResourceBundle.getValue("acenter.username"));
		apiparamsMap.put("AUTH_PASS", ResourceBundle.getValue("acenter.password"));
		apiparamsMap.put("gid", "");// 所属独立组织ID
		apiparamsMap.put("device", "");// 硬终端信息
		apiparamsMap.put("model", "");// 设备型号
		apiparamsMap.put("ucode", "");// 硬终端唯一编号（SN）
		apiparamsMap.put("mac", "");// MAC地址 mac/mac2至少有一项不为空
		apiparamsMap.put("mac2", "");// 无线MAC地址 mac/mac2至少有一项不为空
		apiparamsMap.put("tmnl_type", "");// 终端类型
		apiparamsMap.put("name", "");// 设备名称
		JSONObject jsonObject = JSONObject.fromObject(apiparamsMap);
		m.put("data", jsonObject.toString());
		String ret = null;
		String serverip = ResourceBundle.getValue("acenter.ip");
		try {
			UrlEncodedFormEntity formEntity = HttpClientUtil.getPostFormEntity(m);
			String res = HttpClientUtil.doHttpPost(serverip + "/acenter/dbcall.action?", formEntity);
			JSONObject j = JSONObject.fromObject(res);
			if (j.getString("ret").equals("1")) {
				result.put("code", 0x0200);
				result.put("message", "请求成功");
			} else {
				result.put("code", 0xffff);
				result.put("message", "未知错误");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("code", 0xffff);
			result.put("message", "未知错误");
		}
		return JSONObject.fromObject(result).toString();
	}

	/*
	 * 硬件终端解绑/绑定
	 */
	public String tmnlupdate(String json, String isUpdate) throws Exception {
		TreeMap<String, Object> apiparamsMap = new TreeMap<String, Object>();
		TreeMap<String, String> m = new TreeMap<String, String>();
		Map<String, Object> result = new HashMap<String, Object>();
		apiparamsMap.put("method", 91007);
		apiparamsMap.put("AUTH_UID", ResourceBundle.getValue("acenter.username"));
		apiparamsMap.put("AUTH_PASS", ResourceBundle.getValue("acenter.password"));
		apiparamsMap.put("ucode", "");// 硬终端唯一编号
		apiparamsMap.put("nid", "");// 硬终端的用户账号
		apiparamsMap.put("isUpdate", isUpdate);// isUpdate =1 解绑 isUpdate=0 绑定
		JSONObject jsonObject = JSONObject.fromObject(apiparamsMap);
		m.put("data", jsonObject.toString());
		String ret = null;
		String serverip = ResourceBundle.getValue("acenter.ip");
		try {
			UrlEncodedFormEntity formEntity = HttpClientUtil.getPostFormEntity(m);
			String res = HttpClientUtil.doHttpPost(serverip + "/acenter/dbcall.action?", formEntity);
			JSONObject j = JSONObject.fromObject(res);
			if (j.getString("ret").equals("1")) {
				result.put("code", 0x0200);
				result.put("message", "请求成功");
			} else {
				result.put("code", 0xffff);
				result.put("message", "未知错误");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("code", 0xffff);
			result.put("message", "未知错误");
		}
		return JSONObject.fromObject(result).toString();
	}

	/*
	 * 硬件终端删除
	 */
	public String tmnldelete(String json, String sKey) throws Exception {
		TreeMap<String, Object> apiparamsMap = new TreeMap<String, Object>();
		TreeMap<String, String> m = new TreeMap<String, String>();
		Map<String, Object> result = new HashMap<String, Object>();
		apiparamsMap.put("method", 91008);
		apiparamsMap.put("AUTH_UID", ResourceBundle.getValue("acenter.username"));
		apiparamsMap.put("AUTH_PASS", ResourceBundle.getValue("acenter.password"));
		apiparamsMap.put("ucode", "");// 硬终端唯一编号
		JSONObject jsonObject = JSONObject.fromObject(apiparamsMap);
		m.put("data", jsonObject.toString());
		String ret = null;
		String serverip = ResourceBundle.getValue("acenter.ip");
		try {
			UrlEncodedFormEntity formEntity = HttpClientUtil.getPostFormEntity(m);
			String res = HttpClientUtil.doHttpPost(serverip + "/acenter/dbcall.action?", formEntity);
			JSONObject j = JSONObject.fromObject(res);
			if (j.getString("ret").equals("1")) {
				result.put("code", 0x0200);
				result.put("message", "请求成功");
			} else {
				result.put("code", 0xffff);
				result.put("message", "未知错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", 0xffff);
			result.put("message", "未知错误");
		}
		return JSONObject.fromObject(result).toString();
	}

	/*
	 * 固定会议室创建
	 */
	public String vroomcreate(String json, String sKey) throws Exception {
		TreeMap<String, Object> apiparamsMap = new TreeMap<String, Object>();
		TreeMap<String, String> m = new TreeMap<String, String>();
		Map<String, Object> result = new HashMap<String, Object>();
		apiparamsMap.put("method", 91009);
		apiparamsMap.put("AUTH_UID", ResourceBundle.getValue("acenter.username"));
		apiparamsMap.put("AUTH_PASS", ResourceBundle.getValue("acenter.password"));
		apiparamsMap.put("name", "");// 会议室名称
		apiparamsMap.put("capacity", "");// 会议室最大参会人数
		apiparamsMap.put("gid", "");// 所属独立组织ID (企业 idid)
		apiparamsMap.put("vroom_type", "");// 会议室类型 1:固定会议室 0:虚拟会议室 默认: 0
		apiparamsMap.put("accs_permission", "");// 访问权限 1:公开 0:私有
		JSONObject jsonObject = JSONObject.fromObject(apiparamsMap);
		m.put("data", jsonObject.toString());
		String ret = null;
		String cid = null;
		String serverip = ResourceBundle.getValue("acenter.ip");
		try {
			UrlEncodedFormEntity formEntity = HttpClientUtil.getPostFormEntity(m);
			String res = HttpClientUtil.doHttpPost(serverip + "/acenter/dbcall.action?", formEntity);
			JSONObject j = JSONObject.fromObject(res);
			ret = j.toString();
			cid = j.getString("cid");
			if (!j.getString("cid").equals("")) {
				apiparamsMap.put("cid", j.getString("cid"));//
				m.put("data", jsonObject.toString());
				UrlEncodedFormEntity formEntity1 = HttpClientUtil.getPostFormEntity(m);
				res = HttpClientUtil.doHttpPost(serverip + "/acenter/dbcall.action?", formEntity1);
				j = JSONObject.fromObject(res);
				ret = j.toString();
				if (j.getString("ret").equals("1")) {
					result.put("code", 0x0200);
					result.put("message", "请求成功");
					result.put("message", cid);
				} else {
					result.put("code", 0xffff);
					result.put("message", "未知错误");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", 0xffff);
			result.put("message", "未知错误");
		}
		return JSONObject.fromObject(result).toString();
	}

	/*
	 * 固定会议室修改
	 */
	public String vroommodify(String json, String sKey) throws Exception {
		TreeMap<String, Object> apiparamsMap = new TreeMap<String, Object>();
		TreeMap<String, String> m = new TreeMap<String, String>();
		Map<String, Object> result = new HashMap<String, Object>();
		apiparamsMap.put("method", 910010);
		apiparamsMap.put("AUTH_UID", ResourceBundle.getValue("acenter.username"));
		apiparamsMap.put("AUTH_PASS", ResourceBundle.getValue("acenter.password"));
		apiparamsMap.put("cid", "");// 固定会议室会议号
		apiparamsMap.put("name", "");// 会议室名称
		JSONObject jsonObject = JSONObject.fromObject(apiparamsMap);
		m.put("data", jsonObject.toString());
		String serverip = ResourceBundle.getValue("acenter.ip");
		try {
			UrlEncodedFormEntity formEntity = HttpClientUtil.getPostFormEntity(m);
			String res = HttpClientUtil.doHttpPost(serverip + "/acenter/dbcall.action?", formEntity);
			JSONObject j = JSONObject.fromObject(res);
			if (j.getString("ret").equals("1")) {
				result.put("code", 0x0200);
				result.put("message", "请求成功");
			} else {
				result.put("code", 0xffff);
				result.put("message", "未知错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", 0xffff);
			result.put("message", "未知错误");
		}
		return JSONObject.fromObject(result).toString();
	}

	/*
	 * 固定会议室删除
	 */
	public String vroomdelete(String json, String sKey) throws Exception {
		TreeMap<String, Object> apiparamsMap = new TreeMap<String, Object>();
		TreeMap<String, String> m = new TreeMap<String, String>();
		Map<String, Object> result = new HashMap<String, Object>();
		apiparamsMap.put("method", 910011);
		apiparamsMap.put("AUTH_UID", ResourceBundle.getValue("acenter.username"));
		apiparamsMap.put("AUTH_PASS", ResourceBundle.getValue("acenter.password"));
		apiparamsMap.put("cid", "");// 固定会议室会议号
		JSONObject jsonObject = JSONObject.fromObject(apiparamsMap);
		m.put("data", jsonObject.toString());
		String serverip = ResourceBundle.getValue("acenter.ip");
		try {
			UrlEncodedFormEntity formEntity = HttpClientUtil.getPostFormEntity(m);
			String res = HttpClientUtil.doHttpPost(serverip + "/acenter/dbcall.action?", formEntity);
			JSONObject j = JSONObject.fromObject(res);
			if (j.getString("ret").equals("1")) {
				result.put("code", 0x0200);
				result.put("message", "请求成功");
			} else {
				result.put("code", 0xffff);
				result.put("message", "未知错误");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "-1";
		}
		return JSONObject.fromObject(result).toString();
	}
	
	/*
	 * 获取直播地址
	 */
	public String livefind(String json, String sKey) throws Exception {
		TreeMap<String, Object> apiparamsMap = new TreeMap<String, Object>();
		TreeMap<String, String> m = new TreeMap<String, String>();
		Map<String, Object> result = new HashMap<String, Object>();
		apiparamsMap.put("method", 91013);
		apiparamsMap.put("AUTH_UID", ResourceBundle.getValue("acenter.username"));
		apiparamsMap.put("AUTH_PASS", ResourceBundle.getValue("acenter.password"));
		apiparamsMap.put("cid", "");// 会议号
		apiparamsMap.put("password", "");// 直播密码
		JSONObject jsonObject = JSONObject.fromObject(apiparamsMap);
		m.put("data", jsonObject.toString());
		String serverip = ResourceBundle.getValue("acenter.ip");
		try {
			UrlEncodedFormEntity formEntity = HttpClientUtil.getPostFormEntity(m);
			String res = HttpClientUtil.doHttpPost(serverip + "/acenter/dbcall.action?", formEntity);
			JSONObject j = JSONObject.fromObject(res);
			if (j.getString("ret").equals("1")) {
				result.put("code", 0x0200);
				result.put("message", "请求成功");
				result.put("url",j.getString("url"));
			} else {
				result.put("code", 0xffff);
				result.put("message", "未知错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", 0xffff);
			result.put("message", "未知错误");
		}
		return JSONObject.fromObject(result).toString();
	}
	
	/*
	 * 组织归并
	 */
	public String contactmovegroupstart(String json, String sKey) throws Exception {
		TreeMap<String, Object> apiparamsMap = new TreeMap<String, Object>();
		TreeMap<String, String> m = new TreeMap<String, String>();
		Map<String, Object> result = new HashMap<String, Object>();
		apiparamsMap.put("method", 91014);
		apiparamsMap.put("AUTH_UID", ResourceBundle.getValue("acenter.username"));
		apiparamsMap.put("AUTH_PASS", ResourceBundle.getValue("acenter.password"));
		apiparamsMap.put("pnid", "");// 父组织企业管理员
		apiparamsMap.put("nid", "");//企业管理员nid
		JSONObject jsonObject = JSONObject.fromObject(apiparamsMap);
		m.put("data", jsonObject.toString());
		String serverip = ResourceBundle.getValue("acenter.ip");
		try {
			UrlEncodedFormEntity formEntity = HttpClientUtil.getPostFormEntity(m);
			String res = HttpClientUtil.doHttpPost(serverip + "/acenter/dbcall.action?", formEntity);
			JSONObject j = JSONObject.fromObject(res);
			if (j.getString("ret").equals("1")) {
				result.put("code", 0x0200);
				result.put("message", "请求成功");
			} else {
				result.put("code", 0xffff);
				result.put("message", "未知错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", 0xffff);
			result.put("message", "未知错误");
		}
		return JSONObject.fromObject(result).toString();
	}
	
	

	/*
	 * 公共调用接口
	 */
	public String commapi(String method, String json, String sKey) throws Exception {
		TreeMap<String, Object> apiparamsMap = new TreeMap<String, Object>();
		TreeMap<String, String> m = new TreeMap<String, String>();
		apiparamsMap.put("method", method);
		apiparamsMap.put("info", JSONObject.fromObject(json));
		apiparamsMap.put("sKey", sKey);
		JSONObject jsonObject = JSONObject.fromObject(apiparamsMap);
		m.put("data", jsonObject.toString());
		String serverip = ResourceBundle.getValue("acenter.ip");
		// m.put("sKey", "dfbe7aa31e483e9a");
		String ret = null;
		try {
			UrlEncodedFormEntity formEntity = HttpClientUtil.getPostFormEntity(m);
			String res = HttpClientUtil.doHttpPost(serverip + "/acenter/dbcall.action?", formEntity);
			JSONObject j = JSONObject.fromObject(res);
			ret = j.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "-1";
		}
		return ret;
	}

	/*
	 * getsKey
	 */
	public static String getsKey() throws Exception {
		TreeMap<String, Object> apiparamsMap = new TreeMap<String, Object>();
		TreeMap<String, String> m = new TreeMap<String, String>();
		apiparamsMap.put("method", "14016");
		JSONObject jsonObject = JSONObject.fromObject(apiparamsMap);
		m.put("data", jsonObject.toString());
		String serverip = ResourceBundle.getValue("acenter.ip");
		// m.put("sKey", "dfbe7aa31e483e9a");
		String ret = null;
		try {
			UrlEncodedFormEntity formEntity = HttpClientUtil.getPostFormEntity(m);
			String res = HttpClientUtil.doHttpPost(serverip + "/acenter/dbcall.action?", formEntity);
			JSONObject j = JSONObject.fromObject(res);
			ret = j.getString("sKey");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "-1";
		}
		return ret;
	}

	/**
	 * 登录
	 */
	public String Login() throws Exception {
		TreeMap<String, Object> apiparamsMap = new TreeMap<String, Object>();
		TreeMap<String, String> ChildapiparamsMap = new TreeMap<String, String>();
		apiparamsMap.put("jsonrpc", "2.0");
		apiparamsMap.put("id", "2");
		apiparamsMap.put("method", "sso.login");
		ChildapiparamsMap.put("user", "admin");
		ChildapiparamsMap.put("passwd", "AD9B87EDA5C963BC9ADFF24E3781DF14");
		ChildapiparamsMap.put("clienttype", "pc");
		apiparamsMap.put("params", ChildapiparamsMap);
		JSONObject result = new JSONObject();
		result.putAll(apiparamsMap);
		HttpClientUtil h = new HttpClientUtil();
		String show = h.sendPost("http://localhost:8082/api/dispatch", result.toString());
		System.out.println(show);
		return null;
	}

	/**
	 * 企业开户
	 */
	public String opencard() throws Exception {
		TreeMap<String, Object> apiparamsMap = new TreeMap<String, Object>();
		TreeMap<String, String> GroupInfo = new TreeMap<String, String>();
		TreeMap<String, String> UserInfo = new TreeMap<String, String>();
		List<TreeMap<String, String>> l = new ArrayList<TreeMap<String, String>>();
		apiparamsMap.put("jsonrpc", "2.0");
		apiparamsMap.put("id", "2");
		apiparamsMap.put("method", "contact.openAcct");
		UserInfo.put("name", "admin");
		UserInfo.put("nid", "1");
		UserInfo.put("expire", "1");
		UserInfo.put("password", "123456");
		UserInfo.put("level", "1");
		UserInfo.put("mobile", "18971477097");
		UserInfo.put("email", "254615426@qq.com");
		GroupInfo.put("gname", "网动");
		GroupInfo.put("pgid", "1");
		GroupInfo.put("capacity", "11");
		l.add(UserInfo);
		l.add(GroupInfo);
		apiparamsMap.put("params", l);
		JSONObject result = new JSONObject();
		result.putAll(apiparamsMap);
		HttpClientUtil h = new HttpClientUtil();
		String show = h.sendPost("http://localhost:8082/api/dispatch", result.toString());
		System.out.println(show);
		return null;
	}

	public static void main(String[] args) throws Exception {
		AcenterApi a = new AcenterApi();
		System.out.println(a.getsKey());
		// String sKey = getsKey();
		// a.opencard();
		// a.Login();
		// a.Login("admin", "AD9B87EDA5C963BC9ADFF24E3781DF14");

		// String json =
		// "{\"jsonrpc\":\"2.0\",\"id\":18,\"method\":\"contact.openAcct\",\"params\":{\"user\":{\"name\":\"测试用户1112\",\"nid\":\"12333214\",\"expire\":\"2019-11-14
		// 11:36:20\",\"password\":\"AD9B87EDA5C963BC9ADFF24E3781DF14\",\"level\":\"3\",\"mobile\":\"18086036068\",\"email\":\"200908373@qq.com\"},\"group\":{\"gname\":\"测试组1112\",\"pgid\":\"\",\"capacity\":\"100\"}}}";
		// JSONObject result = new JSONObject();
		// result = JSONObject.fromObject(json.toString());
		// System.out.println(result);
		// System.out.println(result.get("params"));
		// JSONObject jsonstr = (JSONObject) result.get("params");
		// JSONObject jsonuser = (JSONObject) jsonstr.get("user");
		// System.out.println(jsonuser.get("name"));
	}

}
