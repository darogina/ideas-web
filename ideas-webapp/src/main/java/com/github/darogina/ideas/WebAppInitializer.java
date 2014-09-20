package com.github.darogina.ideas;

import com.github.darogina.ideas.config.ApplicationConfig;
import com.github.darogina.ideas.config.DocumentationConfig;
import com.github.darogina.ideas.config.JpaConfig;
import com.github.darogina.ideas.config.ServletConfig;
import org.h2.server.web.WebServlet;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

//web.xml
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {ApplicationConfig.class, JpaConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {ServletConfig.class, DocumentationConfig.class};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

//        DelegatingFilterProxy securityFilterChain = new DelegatingFilterProxy("springSecurityFilterChain");

        return new Filter[] {characterEncodingFilter};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("defaultHtmlEscape", "true");
        registration.setInitParameter("spring.profiles.active", "default");
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
//        registerListener(servletContext);
//        registerDispatcherServlet(servletContext);
//
//        FilterRegistration charEncodingfilterReg = servletContext.addFilter("CharacterEncodingFilter", CharacterEncodingFilter.class);
//        charEncodingfilterReg.setInitParameter("encoding", "UTF-8");
//        charEncodingfilterReg.setInitParameter("forceEncoding", "true");
//        charEncodingfilterReg.addMappingForUrlPatterns(null, false, "/*");

        super.onStartup(servletContext);
//        //Database Console for managing the app's database
        ServletRegistration.Dynamic h2Servlet = servletContext.addServlet("h2console", WebServlet.class);
        h2Servlet.setLoadOnStartup(2);
        h2Servlet.addMapping("/console/database/*");
    }

//    private void registerDispatcherServlet(ServletContext servletContext) {
//        AnnotationConfigWebApplicationContext dispatcherContext = createContext(ServletConfig.class);
//
//        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("appServlet", new DispatcherServlet(dispatcherContext));
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/");
//    }
//
//
//    private void registerListener(ServletContext servletContext) {
//        XmlWebApplicationContext applicationContext = new XmlWebApplicationContext();
//        applicationContext.setConfigLocation("classpath*:applicationContext.xml");
//
//        servletContext.addListener(new ContextLoaderListener(applicationContext));
//    }

//    private AnnotationConfigWebApplicationContext createContext(final Class<?>... annotatedClasses) {
//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.register(annotatedClasses);
//        return context;
//    }
}
