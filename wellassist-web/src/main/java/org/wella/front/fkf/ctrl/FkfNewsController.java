/*   1:    */ package org.wella.front.fkf.ctrl;
/*   2:    */ 
/*   3:    */ import com.alibaba.fastjson.JSONObject;
/*   4:    */ import java.util.ArrayList;
/*   5:    */ import java.util.HashMap;
/*   6:    */ import java.util.Map;
/*   7:    */ import javax.servlet.http.HttpServletRequest;
/*   8:    */ import javax.servlet.http.HttpServletResponse;
/*   9:    */ import org.springframework.beans.factory.annotation.Autowired;
/*  10:    */ import org.springframework.stereotype.Controller;
/*  11:    */ import org.springframework.ui.Model;
/*  12:    */ import org.springframework.web.bind.annotation.RequestMapping;
/*  13:    */ import org.wella.common.ctrl.BaseController;
/*  14:    */ import org.wella.common.mapper.CommonMapper;
/*  15:    */ import org.wella.common.utils.CommonUtil;
/*  16:    */ import org.wella.common.utils.ConstantUtil;
/*  17:    */ import org.wella.common.utils.ConvertUtil;
/*  18:    */ import org.wella.common.vo.MyInfo;
/*  19:    */ import org.wella.front.mapper.NewsMapper;
/*  20:    */ 
/*  21:    */ @Controller
/*  22:    */ public class FkfNewsController
/*  23:    */   extends BaseController
/*  24:    */ {
/*  25:    */   @Autowired
/*  26:    */   private NewsMapper newsMapper;
/*  27:    */   
/*  28:    */   @RequestMapping({"front/fkf/FkfNewsController-xxList"})
/*  29:    */   public String news_list(HttpServletRequest request, HttpServletResponse response, Model model)
/*  30:    */   {
/*  31: 44 */     MyInfo myInfo = getMyInfo(request);
/*  32:    */     
/*  33: 46 */     Map<String, Object> param = getConditionParam(request);
/*  34: 47 */     param.put("userId", myInfo.getUserId());
/*  35:    */     
/*  36: 49 */     ArrayList<Map<String, Object>> newsList = this.newsMapper.getNewsList(param);
/*  37: 50 */     ConvertUtil.convertDataBaseMapToJavaMap(newsList);
/*  38:    */     
/*  39: 52 */     model.addAttribute("newsList", newsList);
/*  40:    */     
/*  41: 54 */     int totalCount = this.newsMapper.getNewsListCount(param);
/*  42:    */     
/*  43: 56 */     setPagenationInfo(request, totalCount, Integer.parseInt(param.get("page").toString()));
/*  44:    */     
/*  45: 58 */     param.put("strsql", "SELECT COUNT(*) FROM wa_info WHERE is_read = 0 AND user_id = " + myInfo.getUserId());
/*  46:    */     
/*  47: 60 */     model.addAttribute("noReadCount", Integer.valueOf(this.commonMapper.simpleSelectReturnInt(param)));
/*  48: 61 */     model.addAttribute("totalCount", Integer.valueOf(totalCount));
/*  49:    */     
/*  50: 63 */     model.addAttribute("parentMenuNo", "3");
/*  51: 64 */     model.addAttribute("childMenuNo", "1");
/*  52: 65 */     model.addAttribute("userName", myInfo.getUserName());
/*  53:    */     
/*  54: 67 */     return "front/fkf/news/xxList";
/*  55:    */   }
/*  56:    */   
/*  57:    */   @RequestMapping(value={"front/fkf/FkfNewsController-setIsRead"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*  58:    */   public void setIsRead(HttpServletRequest request, HttpServletResponse response, Model model)
/*  59:    */   {
/*  60: 80 */     String ret = "-1";
/*  61: 81 */     JSONObject obj = new JSONObject();
/*  62: 82 */     obj.put("content", ConstantUtil.MSG_PARAM_ERR);
/*  63:    */     
/*  64:    */ 
/*  65: 85 */     String ids = CommonUtil.GetRequestParam(request, "ids", "");
/*  66:    */     try
/*  67:    */     {
/*  68: 90 */       MyInfo myInfo = getMyInfo(request);
/*  69: 92 */       if (!ids.equals(""))
/*  70:    */       {
/*  71: 94 */         String strsql = "UPDATE wa_info SET is_read = 1 WHERE tx_id in (" + ids + ") AND user_id = '" + myInfo.getUserId() + "'";
/*  72:    */         
/*  73: 96 */         Map<String, Object> param = new HashMap();
/*  74: 97 */         param.put("strsql", strsql);
/*  75:    */         
/*  76: 99 */         this.commonMapper.simpleUpdate(param);
/*  77:    */         
/*  78:101 */         ret = "1";
/*  79:102 */         obj.put("content", ConstantUtil.MSG_SUCCESS);
/*  80:    */       }
/*  81:    */     }
/*  82:    */     catch (Exception e)
/*  83:    */     {
/*  84:105 */       ret = "-2";
/*  85:106 */       obj.put("content", ConstantUtil.MSG_FAILS);
/*  86:    */     }
/*  87:110 */     obj.put("status", ret);
/*  88:111 */     echoJSON(response, obj);
/*  89:    */   }
/*  90:    */   
/*  91:    */   @RequestMapping(value={"front/fkf/FkfNewsController-delXx"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*  92:    */   public void delXx(HttpServletRequest request, HttpServletResponse response, Model model)
/*  93:    */   {
/*  94:124 */     String ret = "-1";
/*  95:125 */     JSONObject obj = new JSONObject();
/*  96:126 */     obj.put("content", ConstantUtil.MSG_PARAM_ERR);
/*  97:    */     
/*  98:    */ 
/*  99:129 */     String ids = CommonUtil.GetRequestParam(request, "ids", "");
/* 100:    */     try
/* 101:    */     {
/* 102:134 */       MyInfo myInfo = getMyInfo(request);
/* 103:136 */       if (!ids.equals(""))
/* 104:    */       {
/* 105:138 */         String strsql = "";
/* 106:140 */         if (ids.equals("0")) {
/* 107:141 */           strsql = "DELETE FROM wa_info WHERE user_id = '" + myInfo.getUserId() + "'";
/* 108:    */         } else {
/* 109:143 */           strsql = "DELETE FROM wa_info WHERE tx_id in (" + ids + ") AND user_id = '" + myInfo.getUserId() + "'";
/* 110:    */         }
/* 111:147 */         Map<String, Object> param = new HashMap();
/* 112:148 */         param.put("strsql", strsql);
/* 113:    */         
/* 114:150 */         this.commonMapper.simpleUpdate(param);
/* 115:    */         
/* 116:152 */         ret = "1";
/* 117:153 */         obj.put("content", ConstantUtil.MSG_SUCCESS);
/* 118:    */       }
/* 119:    */     }
/* 120:    */     catch (Exception e)
/* 121:    */     {
/* 122:156 */       ret = "-2";
/* 123:157 */       obj.put("content", ConstantUtil.MSG_FAILS);
/* 124:    */     }
/* 125:161 */     obj.put("status", ret);
/* 126:162 */     echoJSON(response, obj);
/* 127:    */   }
/* 128:    */ }



/* Location:           C:\Users\Administrator\Desktop\wella\WEB-INF\classes\

 * Qualified Name:     org.FkfNewsController

 * JD-Core Version:    0.7.0.1

 */