package org.launchcode;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// Loads WebSecurityConfig class in our existing ApplicationInitializer and adds into the getRootConfigClasses() method
public class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { WebSecurityConfig.class };
    }
    @Override
    protected Class<?>[] getServletConfigClasses() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

}
