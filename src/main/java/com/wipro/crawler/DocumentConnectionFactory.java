package com.wipro.crawler;

import org.jsoup.Connection;

public interface DocumentConnectionFactory {
    Connection createConnection(String url);
}
