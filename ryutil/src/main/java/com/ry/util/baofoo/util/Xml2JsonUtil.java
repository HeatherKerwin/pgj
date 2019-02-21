package com.ry.util.baofoo.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import net.sf.json.JSONObject;  
  
public class Xml2JsonUtil {  
	
	public static  String xmlToJSON(String xml) {  
        JSONObject obj = new JSONObject();  
        try {  
            InputStream is = new ByteArrayInputStream(xml.getBytes("utf-8"));  
            SAXBuilder sb = new SAXBuilder();  
            Document doc = sb.build(is);  
            Element root = doc.getRootElement();  
            Map map=iterateElement(root);  
           obj.put(root.getName(),map);  
           return obj.toString();  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
  
  
@SuppressWarnings({ "unchecked", "rawtypes" })  
    private static Map  iterateElement(Element root) {  
        List childrenList = root.getChildren();  
        Element element = null;  
        Map map = new HashMap();  
        List list = null;  
        for (int i = 0; i < childrenList.size(); i++) {  
            list = new ArrayList();  
            element = (Element) childrenList.get(i);  
            if(element.getChildren().size()>0){  
                if(root.getChildren(element.getName()).size()>1){  
                    if (map.containsKey(element.getName())) {  
                        list = (List) map.get(element.getName());  
                    }  
                    list.add(iterateElement(element));  
                    map.put(element.getName(), list);  
                }else{  
                    map.put(element.getName(), iterateElement(element));  
                }  
            }else {  
                if(root.getChildren(element.getName()).size()>1){  
                    if (map.containsKey(element.getName())) {  
                        list = (List) map.get(element.getName());  
                    }  
                    list.add(element.getTextTrim());  
                    map.put(element.getName(), list);  
                }else{  
                    map.put(element.getName(), element.getTextTrim());  
                }  
            }  
        }  
          
        return map;  
    }

public static void main(String[] args) throws Exception  
{         
    String xml="<ROOT>"+  
    "<Status>00</Status>"+  
    "<ErrorMsg></ErrorMsg>"+  
    "<Data>"+  
       "<Row>"+  
          "<PERSONID>35020500200610000000000701355116</PERSONID>"+  
          "<XM>吴聪楠</XM><SFZH>350624198908052530</SFZH>"+  
       "</Row>"+  
       "<Row>"+  
          "<PERSONID>35020500200610000000000701355117</PERSONID>"+  
          "<XM>吴聪楠2</XM><SFZH>350624198908052531</SFZH>"+  
          "</Row>"+  
      "</Data>"+  
"</ROOT>";  

      
    String str=Xml2JsonUtil.xmlToJSON(xml);  
  System.out.println(str);  
}
}  