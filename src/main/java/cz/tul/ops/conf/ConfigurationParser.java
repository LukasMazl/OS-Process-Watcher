package cz.tul.ops.conf;

import java.util.Map;

public interface ConfigurationParser {

    Map<ConfigParam, Object> parse(String[] args);
}
