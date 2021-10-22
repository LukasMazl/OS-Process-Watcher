package cz.tul.ops.conf;

import java.util.HashMap;
import java.util.Map;

public class SimpleConfigurationParser implements ConfigurationParser {

    @Override
    public Map<ConfigParam, Object> parse(String[] args) {
        Map<ConfigParam, Object> config = new HashMap<>();
        for(String arg : args) {
            ParseConfig parsedValue = parseParamSingleValue(arg);
            if (parsedValue != null) {
                config.put(parsedValue.getObjectKey(), parsedValue.getResult());
            }
        }
        return config;
    }

    private ParseConfig parseParamSingleValue(String arg) {
        ConfigParam conParam = ConfigParam.getValue(arg);
        if(conParam == null)
            return null;

        String dataValue = arg.replace(conParam.getParam(), "");
        Object result = (dataValue.isEmpty())? true : dataValue;
        if(conParam.isArray()) {
            result = dataValue.split(",");
        }
        return new ParseConfig(conParam, result);
    }

    private class ParseConfig {
        private ConfigParam objectKey;
        private Object result;

        public ParseConfig(ConfigParam objectKey, Object result) {
            this.objectKey = objectKey;
            this.result = result;
        }

        public ConfigParam getObjectKey() {
            return objectKey;
        }

        public void setObjectKey(ConfigParam objectKey) {
            this.objectKey = objectKey;
        }

        public Object getResult() {
            return result;
        }

        public void setResult(Object result) {
            this.result = result;
        }
    }

}
