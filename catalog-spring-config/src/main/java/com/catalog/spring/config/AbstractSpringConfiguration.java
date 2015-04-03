/*
 * Copyright (c) 2015. All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the Apache License, Version 2.0 (the "License")
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * You should have received a copy of the "License" with this file.
 * If not, please obtain a copy here http://www.apache.org/licenses/LICENSE-2.0
 */

package com.catalog.spring.config;

import com.catalog.spring.config.exception.SpringConfigException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AbstractSpringConfiguration implements BeanFactoryPostProcessor, BeanPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext;
    private Map<Class<?>, Method> returnTypeToDependencyInjector;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Method m = returnTypeToDependencyInjector.get(bean.getClass());
        if (m != null) {
            try {
                m.invoke(this, bean);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new SpringConfigException(e.getMessage(), e);
            }
        }
        return bean;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        returnTypeToDependencyInjector = createMapOfDependencyInjectors();
    }

    protected ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    protected <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    private Map<Class<?>, Method> createMapOfDependencyInjectors() {
        Map<Class<?>, Method> map = new HashMap<>();
        Method[] methods = getClass().getMethods();
        for (Method method : methods) {
            if (method.getAnnotation(DependencyInjector.class) != null) {
                map.put(method.getReturnType(), method);
            }
        }
        return map;
    }
}
