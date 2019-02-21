package com.ry.core.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.ry.util.page.PageResults;


@Repository  
public class BaseDao<T, PK extends java.io.Serializable>{
	
	// 日志输出类
	protected static final Logger LOGGER = LoggerFactory.getLogger(BaseDao.class);
	// 泛型反射类
	private Class<T> entityClass;  
	
    protected SessionFactory sessionFactory;  
      
    public BaseDao() {  
        this.entityClass = null;  
        Class<?> c = getClass();  
        Type type = c.getGenericSuperclass();  
        if (type instanceof ParameterizedType) {  
            Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();  
            this.entityClass = (Class<T>) parameterizedType[0];  
        }  
    }  
      
    @Resource  
    public void setSessionFactory(SessionFactory sessionFactory) {  
        this.sessionFactory = sessionFactory;  
    }  
  
    protected Session getSession() {  
        return sessionFactory.getCurrentSession();  
    }  
    /*
     * 根据ID获取PO
    */
    public T get(PK id) {  
        Assert.notNull(id, "id is required");  
        return (T) getSession().get(entityClass, id);  
    }  
    /*
     * 保存PO
    */
    public PK save(T entity) {  
        Assert.notNull(entity, "entity is required");  
        return (PK) getSession().save(entity);  
    } 
    
    /*
     * 保存或更新PO
    */
    public void saveOrUpdate(T entity) {
    	getSession().saveOrUpdate(entity);
    }

    /*
     * 更新PO
    */
    public void update(T entity) {
    	getSession().update(entity);
    }
    
    /*
     * 更新PO
    */
    public void merge(T entity){
    	getSession().merge(entity);
    }
    
    /*
     * 根据id删除PO
    */
    public void delete(PK id) {
    	getSession().delete(this.get(id));
    }

    /*
     * 删除PO
    */
    public void deleteObject(T entity) {
    	getSession().delete(entity);
    }

    /*
     * 根据id判断PO是否存在
    */
    public boolean exists(PK id) {
    	return get(id) != null;
    }

    /*
     * 根据id加载PO
    */
    @SuppressWarnings("unchecked")
    public T load(PK id) {
    	return (T) getSession().load(this.entityClass, id);
    }
    

    /*
     * 删除所有
    */
    public void deleteAll(Collection<?> entities) {
	    if (entities == null)
	    return;
	    for (Object entity : entities) {
	    	getSession().delete(entity);
	    }
    }

    /**
     * @param <T> 查询多条记录
     * @param sql  sql语句
     * @param param 参数数组
     * @return
     */
     @SuppressWarnings("unchecked")
    public  <T> List<T> queryByHql(String sql,Object[] param){
          List<T> list=new ArrayList<T>();
          Query query=getSession().createQuery(sql);
          if(param!=null){
              for(int i=0;i<param.length;i++){
                    query.setParameter(i,param[i]);    
               }
           }
           list=query.list();
          return list;
      }
     
     /**
      * <根据HQL得到记录数>
      * @param hql HQL语句
      * @param values 不定参数的Object数组
      * @return 记录总数
      */
     public Long countByHql(String hql, Object[] values) {
         Query query = this.getSession().createQuery(hql);
         if(values != null){
             for(int i = 0; i < values.length; i++) {
                 query.setParameter(i, values[i]);
             }
         }
         return (Long) query.uniqueResult();
     }
     
     /**
      * <根据HQL得到记录数>
      * @param hql HQL语句
      * @param values 不定参数的Object数组
      * @return 汇总某个字段的数值
      */
    public Double countByHQLDouble(String hql, Object[] values) {
         Query query = this.getSession().createQuery(hql);
         if(values != null){
             for(int i = 0; i < values.length; i++) {
                 query.setParameter(i, values[i]);
             }
         }
         return (Double) query.uniqueResult();
     }
     
     /**
      * 根据SQL得到记录数
      * @param sql
      * @param values
      * @return
      */
     public BigInteger countBySql(String sql, Object[] values) {
         Query query = this.getSession().createSQLQuery(sql);
         if(values != null){
             for(int i = 0; i < values.length; i++) {
                 query.setParameter(i, values[i]);
             }
         }
         return (BigInteger) query.uniqueResult();
     }
  
     /**
      * <HQL分页查询>
      * @param hql HQL语句
      * @param countHql 查询记录条数的HQL语句
      * @param pageNo 下一页
      * @param pageSize 一页总条数
      * @param values 不定Object数组参数
      * @return PageResults的封装类，里面包含了页码的信息以及查询的数据List集合
      */
     public PageResults<T> findPageByFetchedHql(String hql, String countHql,
             int pageNo, int pageSize, Object[] values) {
         PageResults<T> retValue = new PageResults<T>();
         Query query = this.getSession().createQuery(hql);
         if(values != null){
             for(int i = 0; i < values.length; i++) {
                 query.setParameter(i, values[i]);
             }
         }
         int currentPage = pageNo > 1 ? pageNo : 1;
         retValue.setCurrentPage(currentPage);
         retValue.setPageSize(pageSize);
         if (countHql == null)
         {
             ScrollableResults results = query.scroll();
             results.last();
             retValue.setTotalCount(results.getRowNumber() + 1);// 设置总记录数
         }
         else
         {
             Long count = countByHql(countHql, values);
             retValue.setTotalCount(count.intValue());
         }
         retValue.resetPageNo();
         List<T> itemList = query.setFirstResult((currentPage - 1) * pageSize).setMaxResults(pageSize).list();
         if (itemList == null)
         {
             itemList = new ArrayList<T>();
         }
         retValue.setResults(itemList);
          
         return retValue;
     }
     
     /**
      * <执行Hql语句>
      * @param hqlString hql
      * @param values 不定参数数组
      * @see com.itv.launcher.util.IBaseDao#queryHql(java.lang.String, java.lang.Object[])
      */
     public void executeHql(String hqlString, Object[] values) {
         Query query = this.getSession().createQuery(hqlString);
         if (values != null)
         {
             for (int i = 0; i < values.length; i++)
             {
                 query.setParameter(i, values[i]);
             }
         }
         query.executeUpdate();
     }
  
     /**
      * <执行Sql语句>
      * @param sqlString sql
      * @param values 不定参数数组
      * @see com.itv.launcher.util.IBaseDao#querySql(java.lang.String, java.lang.Object[])
      */
     public void executeSql(String sqlString, Object[] values) {
         Query query = this.getSession().createSQLQuery(sqlString);
         if (values != null)
         {
             for (int i = 0; i < values.length; i++)
             {
                 query.setParameter(i, values[i]);
             }
         }
         query.executeUpdate();
     }
  
     /**
      * <根据HQL语句查找唯一实体>
      * @param hqlString HQL语句
      * @param values 不定参数的Object数组
      * @return 查询实体
      * @see com.itv.launcher.util.IBaseDao#getByHQL(java.lang.String, java.lang.Object[])
      */
     public T getByHQL(String hqlString, Object[] values) {
         Query query = this.getSession().createQuery(hqlString);
         if (values != null){
             for (int i = 0; i < values.length; i++){
                 query.setParameter(i, values[i]);
             }
         }
         return (T) query.uniqueResult();
     }
  
     /**
      * <根据SQL语句查找唯一实体>
      * @param sqlString SQL语句
      * @param values 不定参数的Object数组
      * @return 查询实体
      * @see com.itv.launcher.util.IBaseDao#getBySQL(java.lang.String, java.lang.Object[])
      */
     public T getBySQL(String sqlString, Object[] values) {
         Query query = this.getSession().createSQLQuery(sqlString);
         if (values != null){
             for (int i = 0; i < values.length; i++){
                 query.setParameter(i, values[i]);
             }
         }
         return (T) query.uniqueResult();
     }
  
     /**
      * <根据HQL语句，得到对应的list>
      * @param hqlString HQL语句
      * @param values 不定参数的Object数组
      * @return 查询多个实体的List集合
      * @see com.itv.launcher.util.IBaseDao#getListByHQL(java.lang.String, java.lang.Object[])
      */
     public List<T> getListByHQL(String hqlString, Object[] values) {
         Query query = this.getSession().createQuery(hqlString);
         if (values != null){
             for (int i = 0; i < values.length; i++){
                 query.setParameter(i, values[i]);
             }
         }
         return query.list();
     }
  
     /**
      * <根据SQL语句，得到对应的list>
      * @param sqlString HQL语句
      * @param values 不定参数的Object数组
      * @return 查询多个实体的List集合
      * @see com.itv.launcher.util.IBaseDao#getListBySQL(java.lang.String, java.lang.Object[])
      */
     public List getListBySQL(String sqlString, Object[] values ) {
         Query query = this.getSession().createSQLQuery(sqlString);
         if (values != null){
             for (int i = 0; i < values.length; i++){
                 query.setParameter(i, values[i]);
             }
         }
         return query.list();
     }
     
     /**
      * 原生SQL获取List<Map>
      * select id,name from member.
     * @author WKX
     * @param sql
     * @param values
     * @since 2016年1月12日 下午3:32:17
     */
    public List<Map<String,Object>> getListMapBySQL(String sql,Object[] values){
    	 SQLQuery query = this.getSession().createSQLQuery(sql);   
    	 if (values != null){
             for (int i = 0; i < values.length; i++){
                 query.setParameter(i, values[i]);
             }
         }
    	 query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
    	 List<Map<String,Object>> result = query.list();
    	 return result;
     }
    
    /**
     * <HQL分页查询>（注意：该方法不能再java中直接操作实体）
     * @param 原生 HQL语句
     * @param countHql 查询记录条数的HQL语句
     * @param pageNo 下一页
     * @param pageSize 一页总条数
     * @param values 不定Object数组参数
     * @return PageResults的封装类，里面包含了页码的信息以及查询的数据List集合
     */
    public PageResults<T> findPageByFetchedSql(String hql, String countHql,int pageNo, int pageSize, Object[] values) {
        PageResults<T> retValue = new PageResults<T>();
        SQLQuery query = this.getSession().createSQLQuery(hql);
        if(values != null){
            for(int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        int currentPage = pageNo > 1 ? pageNo : 1;
        retValue.setCurrentPage(currentPage);
        retValue.setPageSize(pageSize);
        if (countHql == null){
            ScrollableResults results = query.scroll();
            results.last();
            retValue.setTotalCount(results.getRowNumber() + 1);// 设置总记录数
        }else{
            BigInteger count = countBySql(countHql, values);
            retValue.setTotalCount(count.intValue());
        }
        retValue.resetPageNo();
        
        query.setFirstResult((currentPage - 1) * pageSize);
        query.setMaxResults(pageSize);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<T> list = query.list();
        
        retValue.setResults(list);
        return retValue;
    }
    
    /**
     * <SQL分页查询>
     * @author WKX
     * @param sql 原生 SQL语句
     * @param countSql countSql 查询记录条数的SQL语句
     * @return PageResults的封装类，里面包含了页码的信息以及查询的数据List集合
     * @since 2016年3月8日 下午4:31:39
     */
    public PageResults<Map<String,Object>> findPageMapByFetchedSql(String sql, String countSql,int pageNo, int pageSize, Object[] values) {
        PageResults<Map<String,Object>> retValue = new PageResults<Map<String,Object>>();
        SQLQuery query = this.getSession().createSQLQuery(sql);
        if(values != null){
            for(int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        int currentPage = pageNo > 1 ? pageNo : 1;
        retValue.setCurrentPage(currentPage);
        retValue.setPageSize(pageSize);
        if (countSql == null){
            ScrollableResults results = query.scroll();
            results.last();
            retValue.setTotalCount(results.getRowNumber() + 1);// 设置总记录数
        }else{
            BigInteger count = countBySql(countSql, values);
            retValue.setTotalCount(count.intValue());
        }
        retValue.resetPageNo();
        
        query.setFirstResult((currentPage - 1) * pageSize);
        query.setMaxResults(pageSize);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        List<Map<String,Object>> list = query.list();
        
        retValue.setResults(list);
        return retValue;
    }
}