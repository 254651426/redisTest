package com.yangjie.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.yangjie.entity.SysMenuEntity;
import com.yangjie.entity.User;
import com.yangjie.entity.meet;
import com.yangjie.service.UserService;
import com.yangjie.util.AES;
import com.yangjie.util.AcenterApi;
import com.yangjie.util.DxInterface;
import com.yangjie.util.HttpClientUtil;
import com.yangjie.util.JsonUtil;
import com.yangjie.util.MD5Util;
import com.yangjie.util.PageUtils;
import com.yangjie.util.R;
import com.yangjie.util.RedisUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RestController
public class UserController {

	@Resource
	RedisUtil redisUtil;

	@RequestMapping(value = "/dispatch", method = RequestMethod.GET)
	public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		redisUtil.set("name", "yj");
		System.out.println(redisUtil.get("name"));
		response.getWriter().print("你好");
	}

}
