package com.wipro.crawler;

import java.net.URISyntaxException;

public class PageLinkImpl extends AbstractLink implements PageLink {
    public PageLinkImpl(String url, String name) throws URISyntaxException {
        super(url, name);
    }
}
