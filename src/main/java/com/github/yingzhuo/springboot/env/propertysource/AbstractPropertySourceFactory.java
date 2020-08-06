package com.github.yingzhuo.springboot.env.propertysource;

import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author 应卓
 * @since 1.3.0
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
