package com.shooterj.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 获取上下文bean
 */
@Component
public class AppUtil implements ApplicationContextAware {
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(AppUtil.class);

	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext _context)
			throws BeansException {
		context = _context;
	}
	
	/**
	 * 获取spring容器上下文。 
	 * @return  ApplicationContext
	 */
	public static ApplicationContext getApplicaitonContext(){
		return context;
	}

	/**
	 * 根据beanId获取配置在系统中的对象实例。
	 * 
	 * @param beanId
	 * @return Object
	 * @exception
	 * @since 1.0.0
	 */
	public static Object getBean(String beanId) {
		try{
			return context.getBean(beanId);
		}
		catch(Exception ex){
			LOGGER.debug("getBean:" + beanId +"," + ex.getMessage());
		}
		return null;
	}

	/**
	 * 获取Spring容器的Bean
	 * 
	 * @param beanClass
	 * @return T
	 * @exception
	 * @since 1.0.0
	 */
	public static <T> T getBean(Class<T> beanClass) {
		try{
			return  context.getBean(beanClass);
		}
		catch(Exception ex){
			LOGGER.debug("getBean:" + beanClass +"," + ex.getMessage());
		}
		return null;
	}
	
	/**
	 * 根据指定的接口或基类获取实现类列表。
	 * @param clazz
	 * @return
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Class> getImplClass(Class clazz) throws ClassNotFoundException{
		List<Class> list=new ArrayList<Class>();
		
		Map map= context.getBeansOfType(clazz);
		for(Object obj : map.values()){
			String name=obj.getClass().getName();
			int pos=name.indexOf("$$");
			if(pos>0){
				name=name.substring(0,name.indexOf("$$")) ; 
			}
			Class cls= Class.forName(name);
			
			list.add(cls);
		}
		return list;
	}
	
	
	/**
	 * 获取接口的实现类实例。
	 * @param clazz
	 * @return
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String,Object> getImplInstance(Class clazz) throws ClassNotFoundException{
		Map map= context.getBeansOfType(clazz);
		return map;
	}


	/**
	 * 发布事件。
	 * @param event 
	 * void
	 */
	public static void publishEvent(ApplicationEvent event){
		if(context!=null){
			context.publishEvent(event);
		}
	}
	
	/**
	 * 发布事件
	 * @param var
	 */
	public static void publishEvent(Object var){
		if(context!=null){
			context.publishEvent(var);
		}
	}
	
	/**
	 * 获取Classpath物理路径
	 * @return
	 */
	public static String getClasspath(){
		 String classPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		 LOGGER.info("classpath:"+classPath);
		 String rootPath  = "";
		  //windows下
		  if("\\".equals(File.separator)){   
		   rootPath  = classPath.substring(1);
		   rootPath = rootPath.replace("/", "\\");
		  }
		  //linux下
		  if("/".equals(File.separator)){   
		   rootPath  = classPath.substring(1);
		   rootPath = rootPath.replace("\\", "/");
		  }
		  LOGGER.info("rootPath:"+rootPath);
		  return rootPath;
	}
	
	/**
	 * 获取系统指定文件流
	 * @param fileName 文件名
	 * @return
	 */
	public static InputStream getFileInputStream(String fileName){
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
	}
	
}
