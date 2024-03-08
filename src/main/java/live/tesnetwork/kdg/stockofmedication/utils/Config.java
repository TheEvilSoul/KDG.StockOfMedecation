package live.tesnetwork.kdg.stockofmedication.utils;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;

import java.util.Set;

public class Config {
    public final Dotenv dotenv;

    public Config() {
        dotenv = Dotenv.configure().load();
    }

    public Config(String filename) {
        dotenv = Dotenv.configure()
                .filename(filename)
                .load();
    }

    public String get(String key) {
        return dotenv.get(key);
    }

    public Integer getAsLong(String key) {
        return Integer.parseInt(this.get(key));
    }

    public Set<DotenvEntry> entries() {
        return dotenv.entries();
    }
}
