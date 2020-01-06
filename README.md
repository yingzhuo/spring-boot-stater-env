[![JDK](http://img.shields.io/badge/JDK-v8.0-yellow.svg)](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
[![Build](http://img.shields.io/badge/Build-Maven_2-green.svg)](https://maven.apache.org/)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.yingzhuo/spring-boot-stater-env.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.github.yingzhuo%22%20AND%20a:%22spring-boot-stater-env%22)

# spring-boot-starter-env

### Download

* maven / gradle is highly recommended.

```xml
<dependency>
  <groupId>com.github.yingzhuo</groupId>
  <artifactId>spring-boot-stater-env</artifactId>
  <version>0.0.4</version>
</dependency>
```

### Usage

#### (1) use `toml` file as property source

```java
import org.springframework.boot.env.TomlPropertySourceFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:/config.toml", factory = TomlPropertySourceFactory.class)
public class ApplicationConfig {

    // ...
}
```

#### (2) use `yaml` file as property source

```java
import org.springframework.boot.env.YamlPropertySourceFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:/config.yaml", factory = YamlPropertySourceFactory.class)
public class ApplicationConfig {

    // ...
}
```

#### (3) use `hocon` file as property source **(highly recommended)**

```java
import org.springframework.boot.env.HoconPropertySourceFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:/config.conf", factory = HoconPropertySourceFactory.class)
public class ApplicationConfig {

    // ...
}
```

#### (4) use `AbstractConventionEnvironmentPostProcessor` load property-source file(s) implicitly

```java
import org.springframework.boot.env.support.AbstractConventionEnvironmentPostProcessor;
import org.springframework.core.Ordered;

public class MyEnvironmentPostProcessor extends AbstractConventionEnvironmentPostProcessor {

    private static final String[] PREFIX = {
            "classpath:my-project",
    };

    private static final String NAME = "my-project";

    public ConventionEnvironmentPostProcessor() {
        super(PREFIX, NAME, Ordered.HIGHEST_PRECEDENCE);
    }
}
```

**Do NOT** forget to register it. In your `classpath:/META-INF/spring.factories`:

```txt
org.springframework.boot.env.EnvironmentPostProcessor=my.project.MyEnvironmentPostProcessor
```

Now, file(s) will be loaded if exists.
 - `classpath:my-project.conf`
 - `classpath:my-project.toml`
 - `classpath:my-project.yml`
 - `classpath:my-project.yaml`
 - `classpath:my-project.properties`
 - `classpath:my-project.xml` ([dtd](http://java.sun.com/dtd/properties.dtd))

### Contributing

* Fork it
* Create your feature branch (git checkout -b my-new-feature)
* Commit your changes (git commit -am 'add some feature')
* Push to the branch (git push origin my-new-feature)
* Create new Pull Request

### Authors

* 应卓 - [github](https://github.com/yingzhuo)

### License

Apache 2.0 license. See [LICENSE](./LICENSE)