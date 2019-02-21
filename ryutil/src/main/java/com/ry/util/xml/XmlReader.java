package com.ry.util.xml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlReader {
	
	/**
	 * 获取路径
	 */
	public static Document getDocument() {
		Document document = null;
		try {
//			InputStream xmlConfig = XmlReader.class.getResourceAsStream("/config.properties");
//			Properties properties = new Properties();
//			properties.load(xmlConfig);
//			xmlConfig.close();
//			String path = properties.get("xmlPath").toString();
//			properties = null;
			
			SAXReader saxReader = new SAXReader();
			InputStream in = XmlReader.class.getResourceAsStream("/configXML.xml"); 
			document = saxReader.read(in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}
	
	/**
	 * 一个参数：group的type值
	 * 根据root节点下一级的type名称获取该级的所有数据
	 * @param name	group的唯一标识type的名称
	 */
	public static Map<String, Object> getByType1(String name) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Document document = getDocument();
			Element root = document.getRootElement();
			result = parseValueByName(root, name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 传俩个参数：group的type值，element的type值
	 * 根据group的唯一标识type和element的唯一标识获取数据type
	 */
	public static Map<String, Object> getByType2(String groupType, String eleType) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Document document = getDocument();
			Element root = document.getRootElement();
			result = parseValueByName(root, groupType);
			result = (Map<String, Object>) ((Map<String, Object>) result.get(groupType)).get(eleType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 传三个参数：group的type值，element的type值，ele的type值
	 */
	public static Map<String, Object> getByType3(String groupType, String eleType, String eType) {
		Map<String, Object> resultMap = getByType2(groupType, eleType);
		if (!"".equals(eType)) {
			resultMap = (Map<String, Object>) resultMap.get(eType);
		}
		return resultMap;
	}

	/**
	 * 获取xml配置中的所有数据
	 */
	public static Map<String, Object> getAllValue() {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Document document = getDocument();
			Element root = document.getRootElement();
			result = parseAllValue(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据名称获取其下面的数据
	 * @param root	根节点下的element元素
	 * @param name	group的唯一标识type名称
	 */
	public static Map<String, Object> parseValueByName(Element root, String name) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<Element> groupList = root.elements("group");
			if (groupList != null && groupList.size() > 0) {
				Iterator<Element> it = groupList.iterator();
				while (it.hasNext()) {
					Element e = it.next();
					if (name.equals(e.attribute("type").getValue())) {
						Map<String, Object> nodeMap = new HashMap<String, Object>();
						List<Attribute> attrList = e.attributes();
						for (Attribute attr : attrList) {
							nodeMap.put(attr.getName(), attr.getValue());
						}
						List<Element> eList = e.elements();
						if (eList != null && eList.size() > 0) {
							Iterator<Element> eit = eList.iterator();
							while (eit.hasNext()) {
								Element ele = eit.next();
								Map<String, Object> childMap = parseNode(ele);
								nodeMap.put(ele.attribute("type").getValue(), childMap);
							}
						}
						resultMap.put(e.attribute("type").getValue(), nodeMap);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	/**
	 * 获取xml的所有配置
	 * @param root	根节点下的element元素
	 */
	public static Map<String, Object> parseAllValue(Element root) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<Element> groupList = root.elements("group");
			if (groupList != null && groupList.size() > 0) {
				Iterator<Element> it = groupList.iterator();
				while (it.hasNext()) {
					Element e = it.next();
					Map<String, Object> nodeMap = new HashMap<String, Object>();
					List<Attribute> attrList = e.attributes();
					for (Attribute attr : attrList) {
						nodeMap.put(attr.getName(), attr.getValue());
					}
					List<Element> eList = e.elements();
					if (eList != null && eList.size() > 0) {
						Iterator<Element> eit = eList.iterator();
						while (eit.hasNext()) {
							Element ele = eit.next();
							Map<String, Object> childMap = parseNode(ele);
							nodeMap.put(ele.attribute("type").getValue(), childMap);
						}
					}
					resultMap.put(e.attribute("type").getValue(), nodeMap);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	/**
	 * 解析group下的子节点
	 */
	public static Map<String, Object> parseNode(Element ele) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Attribute> attrList = ele.attributes();
		for (Attribute attr : attrList) {
			map.put(attr.getName(), attr.getValue());
		}
		List<Element> eleList = ele.elements();
		if (eleList != null && eleList.size() > 0) {
			Iterator<Element> it = eleList.iterator();
			while (it.hasNext()) {
				Element e = it.next();
				Map<String, Object> childMap = parseNode(e);
				map.put(e.attribute("type").getValue(), childMap);
			}
		} else {
			map.put("score", ele.getTextTrim());
		}
		return map;
	}

	public static void main(String[] args) {
//		System.out.println(getAllValue().toString());
		System.out.println(getByType3("remark", "service", "below_80").toString());
//		Map<String, Object> getByName = getValueByName("margin");
//		System.out.println(((Map<String, Object>) getByName.get("margin")).get("org").toString());
//		Map<String, Object> getAllValue = getAllValue();
//		System.out.println(getAllValue.get("limit").toString());
//		Map<String, Object> getValueByTwoType = getByGroupAndEleType("orderRefused", "below_10");
//		System.out.println(getValueByTwoType.get("score").toString());
	}
}
