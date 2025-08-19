package com.aluracursos.models;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    static String getApiKey() {
        Properties props = new Properties();
        try {
            FileInputStream input = new FileInputStream("config.properties");
            props.load(input);
            return props.getProperty("API_KEY");
        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar el archivo de configuracion", e);
        }
    }
}
