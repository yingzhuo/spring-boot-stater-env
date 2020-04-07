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

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;

/**
 * @author 应卓
 * @since 1.1.1
 */
public class RandomValueEnvironmentPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        environment.getPropertySources().addFirst(new RandomValuePropertySource());
    }

    private static class RandomValuePropertySource extends PropertySource<Object> {

        private static final String PREFIX = "random.";

        private RandomValuePropertySource() {
            super("randomValue", new Object());
        }

        @Override
        public Object getProperty(String name) {
            try {
                return doGetProperty(name);
            } catch (NumberFormatException e) {
                return null;
            }
        }

        private Object doGetProperty(String name) {
            if (!name.startsWith(PREFIX)) {
                return null;
            }
            name = name.substring(PREFIX.length()).trim();

            if (name.startsWith("alphabetic(") && name.endsWith(")")) {
                name = name.substring("alphabetic(".length(), name.length() - 1);
                return RandomStringUtils.randomAlphabetic(Integer.parseInt(name));
            }

            if (name.startsWith("alphanumeric(") && name.endsWith(")")) {
                name = name.substring("alphanumeric(".length(), name.length() - 1);
                return RandomStringUtils.randomAlphanumeric(Integer.parseInt(name));
            }

            if (name.startsWith("numeric(") && name.endsWith(")")) {
                name = name.substring("numeric(".length(), name.length() - 1);
                return RandomStringUtils.randomNumeric(Integer.parseInt(name));
            }
            return null;
        }
    }
}
