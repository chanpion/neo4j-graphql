package com.chanpion.neo4j.graphql;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author April Chen
 * @date 2021/3/24 15:27
 */
public class TextUtils {


    public static String readText(URL url) {
        try {
            return readText(url.openStream());
        } catch (IOException e) {
            return null;
        }
    }

    public static String readText(InputStream inputStream) {
        try {
            StringBuilder content = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line);
            }
            return content.toString();
        } catch (IOException e) {
            return null;
        }
    }
}
