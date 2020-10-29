/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
                _                   _                 _            _             _
 ___ _ __  _ __(_)_ __   __ _      | |__   ___   ___ | |_      ___| |_ __ _ _ __| |_       ___ _ ____   __
/ __| '_ \| '__| | '_ \ / _` |_____| '_ \ / _ \ / _ \| __|____/ __| __/ _` | '__| __|____ / _ \ '_ \ \ / /
\__ \ |_) | |  | | | | | (_| |_____| |_) | (_) | (_) | ||_____\__ \ || (_| | |  | ||_____|  __/ | | \ V /
|___/ .__/|_|  |_|_| |_|\__, |     |_.__/ \___/ \___/ \__|    |___/\__\__,_|_|   \__|     \___|_| |_|\_/
    |_|                 |___/
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
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
