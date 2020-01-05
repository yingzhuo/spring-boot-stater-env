package org.springframework.boot.env;

import org.springframework.boot.env.support.AbstractPropertySourceFactory;
import org.springframework.core.io.support.PropertySourceFactory;

/**
 * @author <a href="mailto:yingzhor@gmail.com">应卓</a>
 * @since 0.0.1
 */
public class YamlPropertySourceFactory extends AbstractPropertySourceFactory implements PropertySourceFactory {

    public YamlPropertySourceFactory() {
        super(new YamlPropertySourceLoader());
    }
}
