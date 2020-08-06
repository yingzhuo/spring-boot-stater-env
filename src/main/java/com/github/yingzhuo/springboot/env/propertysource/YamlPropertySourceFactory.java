package com.github.yingzhuo.springboot.env.propertysource;

import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.io.support.PropertySourceFactory;

/**
 * @author 应卓
 * @since 1.3.0
 */
public class YamlPropertySourceFactory extends AbstractPropertySourceFactory implements PropertySourceFactory {

    public YamlPropertySourceFactory() {
        super(new YamlPropertySourceLoader());
    }

}
