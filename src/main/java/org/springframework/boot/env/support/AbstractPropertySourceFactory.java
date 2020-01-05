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
package org.springframework.boot.env.support;

import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author <a href="mailto:yingzhor@gmail.com">应卓</a>
 * @since 0.0.1
 */
public abstract class AbstractPropertySourceFactory implements PropertySourceFactory {

    private final PropertySourceLoader loader;

    public AbstractPropertySourceFactory(PropertySourceLoader loader) {
        this.loader = loader;
    }

    private String getPropertySourceName(String name) {
        if (name == null || name.isEmpty()) {
            return UUID.randomUUID().toString().replaceAll("-", "");
        }
        return name;
    }

    @Override
    public final PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        List<PropertySource<?>> list = loader.load(getPropertySourceName(name), resource.getResource());

        if (list.isEmpty()) {
            return new EmptyPropertySource();
        } else if (list.size() == 1) {
            return list.get(0);
        } else {
            throw new IOException("multiple document is NOT supported yet.");
        }
    }

    protected static class EmptyPropertySource extends PropertySource<Object> {
        public EmptyPropertySource() {
            super("empty", new Object());
        }

        @Override
        public Object getProperty(String name) {
            return null;
        }
    }

}
