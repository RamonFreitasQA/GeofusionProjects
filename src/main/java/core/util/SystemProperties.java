package core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class SystemProperties {
    private static final Logger log = LoggerFactory.getLogger(SystemProperties.class);
    private static Properties properties = System.getProperties();

    // -------------------------- OTHER METHODS --------------------------
    public static void set(String nomeProperties, String valorProperties) {
        clear(nomeProperties);
        System.setProperty(nomeProperties, valorProperties);
        log.debug("Gravando... " + nomeProperties + " = " + valorProperties);
    }

    public static String get(String nomeProperties) {
        String valor = properties.getProperty(nomeProperties);
        log.debug("Pegando valor... " + nomeProperties + " = " + valor);
        return valor;
    }

    public static void clear(String nomeProperites) {
        properties.remove(nomeProperites);
    }

    // -------------------------- END OF METHODS --------------------------
}
