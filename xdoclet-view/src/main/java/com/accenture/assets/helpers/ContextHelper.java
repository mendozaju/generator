package com.accenture.assets.helpers;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Provides help to instantiate classes.
 * 
 * @author adrian.musante
 *
 */
public class ContextHelper {
	
	private ContextHelper(){}
	
	/**
	 * Path of source context.
	 */
	private static final String LOCATION = "classpath:applicationContext-master.xml";  
	/**
	 * Context of Application.
	 */
	private static ApplicationContext context = new ClassPathXmlApplicationContext(LOCATION);
	
	/**
	 * Provides configuration for the application. 
	 * 
	 * <p>
	 * This method provides:
	 * <ul>
	 * <li>Bean factory methods for accessing application components. Inherited
	 * from {@link org.springframework.beans.factory.ListableBeanFactory}.
	 * <li>The ability to load file resources in a generic fashion. Inherited
	 * from the {@link org.springframework.core.io.ResourceLoader} interface.
	 * <li>The ability to publish events to registered listeners. Inherited from
	 * the {@link ApplicationEventPublisher} interface.
	 * <li>The ability to resolve messages, supporting internationalization.
	 * Inherited from the {@link MessageSource} interface.
	 * <li>Inheritance from a parent context. Definitions in a descendant
	 * context will always take priority. This means, for example, that a single
	 * parent context can be used by an entire web application, while each
	 * servlet has its own child context that is independent of that of any
	 * other servlet.
	 * </ul>
	 * 
	 * @return {@link ApplicationContext}
	 * 
	 */
	public static ApplicationContext getContext() {
		return context;
	}
	
	/**
	 * Return an instance, which may be shared or independent, of the specified
	 * bean.
	 * <p>
	 * This method allows a Spring BeanFactory to be used as a replacement for
	 * the Singleton or Prototype design pattern. Callers may retain references
	 * to returned objects in the case of Singleton beans.
	 * <p>
	 * Translates aliases back to the corresponding canonical bean name. Will
	 * ask the parent factory if the bean cannot be found in this factory
	 * instance.
	 * 
	 * @param name
	 *            the name of the bean to retrieve
	 * @return an instance of the bean
	 * @throws NoSuchBeanDefinitionException
	 *             if there is no bean definition with the specified name
	 * @throws BeansException
	 *             if the bean could not be obtained
	 */
	public static Object getBean(String name) throws BeansException {
		return context.getBean(name);
	}
	
	// Name of beans.
	// -----------------------------------------------------------------------
	
	public static final String FEATURES = "features";
	public static final String PROJECT_SERVICE = "projectService";
	public static final String PROJECT_CONFIGURATION = "projectConfiguration";
	
	public static final String CLASS_ELEMENT_TEMP = "temporalClassElement";
	
}
