/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *                 _                   _                 _            _             _
 *  ___ _ __  _ __(_)_ __   __ _      | |__   ___   ___ | |_      ___| |_ __ _ _ __| |_ ___ _ __       ___ _ ____   __
 * / __| '_ \| '__| | '_ \ / _` |_____| '_ \ / _ \ / _ \| __|____/ __| __/ _` | '__| __/ _ \ '__|____ / _ \ '_ \ \ / /
 * \__ \ |_) | |  | | | | | (_| |_____| |_) | (_) | (_) | ||_____\__ \ || (_| | |  | ||  __/ | |_____|  __/ | | \ V /
 * |___/ .__/|_|  |_|_| |_|\__, |     |_.__/ \___/ \___/ \__|    |___/\__\__,_|_|   \__\___|_|        \___|_| |_|\_/
 *     |_|                 |___/
 *
 * https://github.com/yingzhuo/spring-boot-stater-env
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package com.github.yingzhuo.springboot.env;

import com.github.yingzhuo.springboot.env.support.AbstractConventionEnvironmentPostProcessor;
import com.github.yingzhuo.springboot.env.util.JarDir;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author <a href="mailto:yingzhor@gmail.com">应卓</a>
 * @since 0.0.4
 */
public class ConventionEnvironmentPostProcessor extends AbstractConventionEnvironmentPostProcessor {

    public ConventionEnvironmentPostProcessor() {
    }

    @Override
    protected String getName(ConfigurableEnvironment environment, SpringApplication application) {
        return "property-source";
    }

    @Override
    protected String[] getLocationsPrefix(ConfigurableEnvironment environment, SpringApplication application) {
        final Class<?> mainClass = application.getMainApplicationClass();
        final String packageName = mainClass.getPackage().getName().replaceAll("\\.", "/");

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
                "classpath:META-INF/property",
                "classpath:" + packageName + "/property"
        };
    }

}
