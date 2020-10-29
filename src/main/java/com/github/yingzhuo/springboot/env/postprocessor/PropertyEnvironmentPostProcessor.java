/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                _                   _                 _            _             _
 ___ _ __  _ __(_)_ __   __ _      | |__   ___   ___ | |_      ___| |_ __ _ _ __| |_       ___ _ ____   __
/ __| '_ \| '__| | '_ \ / _` |_____| '_ \ / _ \ / _ \| __|____/ __| __/ _` | '__| __|____ / _ \ '_ \ \ / /
\__ \ |_) | |  | | | | | (_| |_____| |_) | (_) | (_) | ||_____\__ \ || (_| | |  | ||_____|  __/ | | \ V /
|___/ .__/|_|  |_|_| |_|\__, |     |_.__/ \___/ \___/ \__|    |___/\__\__,_|_|   \__|     \___|_| |_|\_/
    |_|                 |___/
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package com.github.yingzhuo.springboot.env.postprocessor;

import com.github.yingzhuo.springboot.env.util.JarDir;
import org.springframework.boot.SpringApplication;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author 应卓
 * @since 1.3.0
 */
public class PropertyEnvironmentPostProcessor extends AbstractPostProcessor {

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
