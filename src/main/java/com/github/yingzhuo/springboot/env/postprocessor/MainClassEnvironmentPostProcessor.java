package com.github.yingzhuo.springboot.env.postprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author 应卓
 * @since 1.3.0
 */
public class MainClassEnvironmentPostProcessor extends AbstractConventionEnvironmentPostProcessor {

    @Override
    protected String getName(ConfigurableEnvironment environment, SpringApplication application) {
        return "main-class";
    }

    @Override
    protected String[] getLocationsPrefix(ConfigurableEnvironment environment, SpringApplication application) {
        final Class<?> mainClass = application.getMainApplicationClass();

        // 疑似是Spring的bug
        if (mainClass == null) {
            return new String[0];
        }

        return new String[]{
                "classpath:" + mainClass.getPackage().getName().replaceAll("\\.", "/") + "/" + mainClass.getSimpleName()
        };
    }

}
