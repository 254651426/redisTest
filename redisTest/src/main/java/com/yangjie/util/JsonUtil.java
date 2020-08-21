package com.yangjie.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JsonUtil {
	 /**
     * ��һ��JSON �����ַ��ʽ�еõ�һ��java����
     * @param jsonString
     * @param pojoCalss
     * @return
     */
    public static Object getObject4JsonString(String jsonString,Class pojoCalss){
        Object pojo;
        JSONObject jsonObject = JSONObject.fromObject( jsonString );  
        pojo = JSONObject.toBean(jsonObject,pojoCalss);
        return pojo;
    }
    
    
	/**
	 * json 转list
	 * 
	 * @param jsonString
	 * @param pojoClass
	 * @return
	 */
	public static List<Long> getListtoJson(String jsonString,String key) {
		JSONArray jsonArray = JSONArray.fromObject(JSONObject.fromObject(jsonString).get(key));
		List<Long> list = new ArrayList();
		list = jsonArray;
		return list;

	}
	
    /**
     * ��json HASH���ʽ�л�ȡһ��map����map֧��Ƕ�׹���
     * @param jsonString
     * @return
     */
    @SuppressWarnings("rawtypes")
	public static Map<String,Object> getMap4Json(String jsonString){
        JSONObject jsonObject = JSONObject.fromObject( jsonString );  
        Iterator  keyIter = jsonObject.keys();
        String key;
        Object value;
        Map<String,Object> valueMap = new HashMap<String,Object>();

        while( keyIter.hasNext())
        {
            key = (String)keyIter.next();
            value = jsonObject.get(key); 
            valueMap.put(key, value);
        }
        
        return valueMap;
    }
    
    /**
     * ��json HASH���ʽ�л�ȡһ��map����map֧��Ƕ�׹���
     * @param jsonString
     * @return
     */
	public static List<Map<String,Object>> getlist4Json(String jsonString,String key11){
        JSONArray jsonObject = JSONArray.fromObject(JSONObject.fromObject(jsonString).getString(key11));  
        List<Map<String,Object>> valueMap = new ArrayList<Map<String,Object>>();
        valueMap =jsonObject;
        return valueMap;
    }
    
    
    /**
     * ��json�����еõ���Ӧjava����
     * @param jsonString
     * @return
     */
    public static Object[] getObjectArray4Json(String jsonString){
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        return jsonArray.toArray();
    }
    
    
    /**
     * ��json���󼯺ϱ��ʽ�еõ�һ��java�����б�
     * @param jsonString
     * @param pojoClass
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getList4Json(String jsonString, Class pojoClass){
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        JSONObject jsonObject;
        Object pojoValue;
        
        List list = new ArrayList();
        for ( int i = 0 ; i<jsonArray.size(); i++){
            jsonObject = jsonArray.getJSONObject(i);
            pojoValue = JSONObject.toBean(jsonObject,pojoClass);
            list.add(pojoValue);
        }
        return list;

    }
    
    /**
     * ��json�����н�����java�ַ�����
     * @param jsonString
     * @return
     */
    public static String[] getStringArray4Json(String jsonString){
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        String[] stringArray = new String[jsonArray.size()];
        for( int i = 0 ; i<jsonArray.size() ; i++ ){
            stringArray[i] = jsonArray.getString(i);
        }
        
        return stringArray;
    }
    
    /**
     * ��json�����н�����javaLong�Ͷ�������
     * @param jsonString
     * @return
     */
    public static Long[] getLongArray4Json(String jsonString){
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Long[] longArray = new Long[jsonArray.size()];
        for( int i = 0 ; i<jsonArray.size() ; i++ ){
            longArray[i] = jsonArray.getLong(i);
        }
        return longArray;
    }
    
    /**
     * ��json�����н�����java Integer�Ͷ�������
     * @param jsonString
     * @return
     */
    public static Integer[] getIntegerArray4Json(String jsonString){
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Integer[] integerArray = new Integer[jsonArray.size()];
        for( int i = 0 ; i<jsonArray.size() ; i++ ){
            integerArray[i] = jsonArray.getInt(i);
        }
        return integerArray;
    }
    

    
    /**
     * ��json�����н�����java Integer�Ͷ�������
     * @param jsonString
     * @return
     */
    public static Double[] getDoubleArray4Json(String jsonString){
        
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Double[] doubleArray = new Double[jsonArray.size()];
        for( int i = 0 ; i<jsonArray.size() ; i++ ){
            doubleArray[i] = jsonArray.getDouble(i);
            
        }
        return doubleArray;
    }
    
    
    /**
     * ��java����ת����json�ַ�
     * @param javaObj
     * @return
     */
    public static String getJsonString4JavaPOJO(Object javaObj){
        JSONObject json;
        json = JSONObject.fromObject(javaObj);
        return json.toString();
        
    }
    
    public static String getJsonValueByJsonKey(String jsonString,String key){
    	try {
        	JSONObject jsonObject = JSONObject.fromObject(jsonString);
        	return jsonObject.getString(key);
		} catch (Exception e) {
			return "";
		}
    }
    
    public static int getJsonIntValueByJsonKey(String jsonString,String key){
    	try {
        	JSONObject jsonObject = JSONObject.fromObject(jsonString);
        	return jsonObject.getInt(key);
		} catch (Exception e) {
			return 0;
		}
    }
    
    public static int getJsonIntValueByJsonKey(String jsonString,String key,int defaultValue){
    	try {
        	JSONObject jsonObject = JSONObject.fromObject(jsonString);
        	return jsonObject.getInt(key);
		} catch (Exception e) {
			return defaultValue;
		}
    }
    
    public static long getJsonLongValueByJsonKey(String jsonString,String key){
    	try {
        	JSONObject jsonObject = JSONObject.fromObject(jsonString);
        	return jsonObject.getLong(key);
		} catch (Exception e) {
			return 0;
		}
    }
    
    public static String getJsonString4JavaObj(String key,Object value){
    	HashMap<String,Object> obj = new HashMap<String, Object>();
    	obj.put(key, value);
    	return getJsonString4JavaPOJO(obj);
    }

    public static void main(String[] args) {
    	String str = getJsonString4JavaObj("ret",100);
    	System.out.println(str);
    	/*String jsonString = "{method:2001,body:[{name:\"feng\",value:\"qiang\"}]}";
    	Map<String,Object> map = getMap4Json(jsonString);
    	Set<Entry<String,Object>> set = map.entrySet();
    	Iterator<Entry<String,Object>> iter = set.iterator();
    	while(iter.hasNext()){
    		Entry<String,Object> entry = iter.next();
    		String key = entry.getKey();
    		Object valueObject = entry.getValue();
    		System.out.println(key+" : "+valueObject);
    	}*/
    	/*String arrRect1 = "[{\"rectID\":1,\"userID\":12345},{\"rectID\":2,\"userID\":234}]";
    	String arrStream1 = "[{\"streamID\":2,\"encMod\":0},{\"streamID\":1,\"encMod\":0},{\"streamID\":0,\"encMod\":0}]";
    	String arrRect2 = "[{\"rectID\":0,\"userID\":123},{\"rectID\":1,\"userID\":234}]";
    	String arrStream2 = "[{\"streamID\":1,\"encMod\":0},{\"streamID\":0,\"encMod\":0}]";
    	String arrModuld = "[{\"mouldid\":0,\"autoLoopTime\":30,\"arr_videoRect\":"+arrRect1+",\"arr_stream\":"+arrStream1+"},{\"mouldid\":1,\"autoLoopTime\":30,\"arr_videoRect\":"+arrRect2+",\"arr_stream\":"+arrStream2+"}]";
    	String jsonString = "{\"method\": 10030,\"body\":{\"roomID\":24,\"SpeechExcitation\":0,\"arr_mould\":"+arrModuld+"}}";
    	
    	System.out.println(jsonString);
    	
    	System.out.println("------------------------------");
    	
    	String reString = getJsonValueByJsonKey(jsonString, "body");
    	String roomId = getJsonValueByJsonKey(reString, "roomID");
    	System.out.println(reString);
    	System.out.println(roomId);
    	String arrModuldString = getJsonValueByJsonKey(reString,"arr_mould");
    	System.out.println(arrModuldString);
    	Object[] arrObjects = getObjectArray4Json(arrModuldString);
    	System.out.println(arrObjects[0]);
    	String moduid = getJsonValueByJsonKey(arrObjects[0].toString(), "mouldid");
    	System.out.println(moduid);
    	String arrStreamString = getJsonValueByJsonKey(arrObjects[0].toString(), "arr_stream");
    	System.out.println(arrStreamString);*/
    	//String jsonString = "{\"arrMouldUID\":[12,23,34]}";
    	//Integer[] jsonArr = getIntegerArray4Json(getJsonValueByJsonKey(jsonString, "arrMouldUID"));
    	//System.out.println(jsonArr[0]);
	}
    

}
