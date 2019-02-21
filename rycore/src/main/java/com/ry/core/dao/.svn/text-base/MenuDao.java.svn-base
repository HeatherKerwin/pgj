package com.ry.core.dao;

import java.util.List;
import java.util.Map;

import com.ry.core.entity.Menu;
import com.ry.util.page.PageResults;

public interface MenuDao {

	/**
	 * 得到分页菜单对象列表
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @date 2016年3月6日
	 * @author lvyanqin
	 */
	PageResults<Menu> getPageResults(Integer pageNo, Integer pageSize);
	
	 /**
	  * 分页显示资源菜单（里面有副级菜单的数据）
	 * @author ZY
	 * @param pageNo
	 * @param pageSize
	 * @since 2016年8月5日 下午3:12:14
	 */
	PageResults<Map<String,Object>> getPageResults1(Integer pageNo, Integer pageSize);
	
	/**
	 * 根据id获取menu集合对象
	 * @param id
	 * @return
	 * @date 2016年3月6日
	 * @author lvyanqin
	 */
	Menu getMenuById(Integer id);

	/**
	 * 更新菜单信息
	 * @param menu
	 * @return
	 * @date 2016年3月6日
	 * @author lvyanqin
	 */
	void updateMenu(Menu menu);

	/**
	 * 存菜单
	 * @date 2016年3月6日
	 * @author lvyanqin
	 */
	void saveMenu(Menu menu);

	/**
	 * 加载所有菜单数据
	 * @return
	 * @date 2016年3月8日
	 * @author lvyanqin
	 */
	List<Map<String, Object>> initMenu();

	/**
	 * 根据登录的用户Id的获取该用户对应的功能菜单
	 * @author KHC
	 * @param id
	 * @since 2016年8月4日 下午4:31:42
	 */
	List<Map<String, Object>> getMenuListById(Integer id);
	
	/**
	 * 根据父节点获取菜单列表
	 * @author KHC
	 * @param parentId
	 * @since 2017年1月12日 下午1:03:28
	 */
	List<Menu> getListByParentId(Integer parentId);
}
