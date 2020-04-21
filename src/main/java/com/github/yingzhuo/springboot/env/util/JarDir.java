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
package com.github.yingzhuo.springboot.env.util;

import org.springframework.boot.system.ApplicationHome;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.Serializable;

/**
 * @author <a href="mailto:yingzhor@gmail.com">应卓</a>
 * @since 1.1.2
 */
public final class JarDir implements Serializable {

    private final ApplicationHome home;

    public static JarDir of() {
        return new JarDir();
    }

    public static JarDir of(Class<?> sourceClass) {
        return new JarDir(sourceClass);
    }

    private JarDir() {
        this.home = new ApplicationHome();
    }

    private JarDir(Class<?> sourceClass) {
        this.home = new ApplicationHome(sourceClass);
    }

    public File getDir(String child) {
        if (child == null) {
            return this.home.getDir();
        }
        return new File(home.getDir(), child);
    }

    public Resource getDirAsResource(String child) {
        return new FileSystemResource(getDir(child));
    }

    public String getDirAsResourceLocation(String child) {
        return "file:" + getDir(child).getAbsolutePath();
    }

    @Override
    public String toString() {
        return this.home.getDir().getAbsolutePath();
    }

}
