package com.wipro.crawler;

import java.net.URISyntaxException;

class StaticLinkImpl extends AbstractLink implements StaticLink {
    public StaticLinkImpl(String url, String name) throws URISyntaxException {
        super(url, name);
    }
}
