package com.github.yingzhuo.springboot.env.propertysource;

import com.github.yingzhuo.springboot.env.propertysource.loader.TomlPropertySourceLoader;
import org.springframework.core.io.support.PropertySourceFactory;

/**
 * @author 应卓
 * @since 1.3.0
 */
public class TomlPropertySourceFactory extends AbstractPropertySourceFactory implements PropertySourceFactory {

    public TomlPropertySourceFactory() {
        super(new TomlPropertySourceLoader());
    }

}
