package confiuration;

import com.typesafe.config.Config;

import static com.typesafe.config.ConfigFactory.load;

public interface ConfigProvider {
    static Config readConfig() {
        return load("configuration.conf");
    }

    String BASE_URL = readConfig().getString("baseUrl");
}