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
import org.springframework.core.Ordered;

/**
 * @author <a href="mailto:yingzhor@gmail.com">应卓</a>
 * @since 0.0.4
 */
public class ConventionEnvironmentPostProcessor extends AbstractConventionEnvironmentPostProcessor {

    private static final String[] PREFIX = {
            "file:config/property-source",
            "file:property-source",
            "classpath:config/property-source",
            "classpath:property-source",
            "classpath:META-INF/property-source",
    };

    private static final String NAME = "property-source";

    public ConventionEnvironmentPostProcessor() {
        super(PREFIX, NAME, Ordered.HIGHEST_PRECEDENCE);
    }

}
