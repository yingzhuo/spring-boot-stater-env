package com.github.yingzhuo.springboot.env.postprocessor;

import com.github.yingzhuo.springboot.env.util.JarDir;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author 应卓
 * @since 1.3.0
 */
public interface ConventionEnvironmentPostProcessors {

    static class Property extends AbstractConventionEnvironmentPostProcessor {
        @Override
        protected String getName(ConfigurableEnvironment environment, SpringApplication application) {
            return "property";
        }

        @Override
        protected String[] getLocationsPrefix(ConfigurableEnvironment environment, SpringApplication application) {
            final Class<?> mainClass = application.getMainApplicationClass();
            return new String[]{
                    JarDir.of(mainClass).getDirAsResourceLocation("config/property"),
                    JarDir.of(mainClass).getDirAsResourceLocation(".config/property"),
                    JarDir.of(mainClass).getDirAsResourceLocation("_config/property"),
                    JarDir.of(mainClass).getDirAsResourceLocation("property"),
                    "file:config/property",
                    "file:.config/property",
                    "file:_config/property",
                    "file:property",
                    "classpath:config/property",
                    "classpath:.config/property",
                    "classpath:_config/property",
                    "classpath:property",
                    "classpath:META-INF/property"
            };
        }
    }

    static class Git extends AbstractConventionEnvironmentPostProcessor {
        @Override
        protected String getName(ConfigurableEnvironment environment, SpringApplication application) {
            return "git";
        }

        @Override
        protected String[] getLocationsPrefix(ConfigurableEnvironment environment, SpringApplication application) {
            return new String[]{"classpath:git", "classpath:META-INF/git", "classpath:config/git"};
        }
    }

}
