package com.wipro.crawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class DomainSuffixes {

    private static final String FILE_NAME = "domain_suffixes.config";
    private static final DomainSuffixes instance = new DomainSuffixes(FILE_NAME);

    private final Set<String> suffixes = new HashSet<>();

    private DomainSuffixes(String fileName) {
        try (final InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
                final BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                suffixes.add(line.toLowerCase());
            }
        } catch (IOException e) {
            // Report to log;
        }
    }

    public static DomainSuffixes getInstance() {
        return instance;
    }

    public boolean isDomainSuffix(String suffix) {
        return suffix != null && suffixes.contains(suffix.toLowerCase());
    }
}
