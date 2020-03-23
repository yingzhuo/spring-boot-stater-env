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
package org.springframework.boot.env;

import org.springframework.boot.env.support.AbstractConventionEnvironmentPostProcessor;

/**
 * @author <a href="mailto:yingzhor@gmail.com">应卓</a>
 * @since 0.0.4
 */
public class ConventionEnvironmentPostProcessor extends AbstractConventionEnvironmentPostProcessor {

    private static final String[] DEFAULT_PREFIX = {
            "file:config/property",
            "file:config/property-source",
            "file:property",
            "file:property-source",
            "classpath:config/property",
            "classpath:config/property-source",
            "classpath:property",
            "classpath:property-source",
            "classpath:META-INF/property",
            "classpath:META-INF/property-source",
    };

    private static final String NAME = "property-source";

    public ConventionEnvironmentPostProcessor() {
        super(getPrefix(), NAME);
    }

    private static String[] getPrefix() {
        String envValue = System.getenv("CONVENTION_PROPERTY_SOURCE");

        if (envValue == null || envValue.trim().isEmpty()) {
            return DEFAULT_PREFIX;
        }

        String[] prefix = envValue.split(",");
        for (int i = 0; i < prefix.length; i++) {
            prefix[i] = prefix[i].trim();
        }

        return prefix;
    }

}
