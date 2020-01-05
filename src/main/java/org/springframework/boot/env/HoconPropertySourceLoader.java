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

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.*;

/**
 * @author <a href="mailto:yingzhor@gmail.com">应卓</a>
 * @since 0.0.2
 */
public class HoconPropertySourceLoader implements PropertySourceLoader {

    @Override
    public String[] getFileExtensions() {
        return new String[]{"conf"};
    }

    @Override
    public List<PropertySource<?>> load(String name, Resource resource) throws IOException {
        Config config = ConfigFactory.parseURL(resource.getURL());

        Map<String, Object> result = new LinkedHashMap<>();
        buildFlattenedMap(result, config.root().unwrapped(), null);
        if (result.isEmpty()) {
            return Collections.emptyList();
        }

        return Collections.singletonList(new MapPropertySource(name, result));
    }

    private void buildFlattenedMap(Map<String, Object> result, Map<String, Object> source, String root) {
        boolean rootHasText = (null != root && !root.trim().isEmpty());

        source.forEach((key, value) -> {
            String path;

            if (rootHasText) {
                if (key.startsWith("[")) {
                    path = root + key;
                } else {
                    path = root + "." + key;
                }
            } else {
                path = key;
            }

            if (value instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) value;
                buildFlattenedMap(result, map, path);
            } else if (value instanceof Collection) {
                @SuppressWarnings("unchecked")
                Collection<Object> collection = (Collection<Object>) value;
                int count = 0;
                for (Object object : collection) {
                    buildFlattenedMap(result, Collections.singletonMap("[" + (count++) + "]", object), path);
                }
            } else {
                result.put(path, null == value ? "" : value);
            }
        });
    }

}
