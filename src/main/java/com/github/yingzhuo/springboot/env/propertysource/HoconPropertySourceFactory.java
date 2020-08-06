package com.github.yingzhuo.springboot.env.propertysource;

import com.github.yingzhuo.springboot.env.propertysource.loader.HoconPropertySourceLoader;

/**
 * @author 应卓
 * @since 1.6.33
 */
public class HoconPropertySourceFactory extends AbstractPropertySourceFactory {

    public HoconPropertySourceFactory() {
        super(new HoconPropertySourceLoader());
    }

}
