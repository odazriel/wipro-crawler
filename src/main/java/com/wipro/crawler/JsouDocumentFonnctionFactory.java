package com.wipro.crawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class JsouDocumentFonnctionFactory implements DocumentConnectionFactory {

    @Override
    public Connection createConnection(String url) {
        return Jsoup.connect(url);
    }
}
