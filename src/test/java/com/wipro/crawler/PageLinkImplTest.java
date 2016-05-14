package com.wipro.crawler;

import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class PageLinkImplTest {

    @Test
    public void validURLNoWWW() throws Exception {
        final Link link = new PageLinkImpl("http://twitter.com", "Twitter");
        assertEquals("Twitter", link.getName());
        assertEquals("twitter.com", link.getDomain());
        assertEquals("http://twitter.com", link.getUrl());
    }

    @Test
    public void validURLWithEndSlash() throws Exception {
        final Link link = new PageLinkImpl("http://twitter.com/", "Twitter");
        assertEquals("Twitter", link.getName());
        assertEquals("twitter.com", link.getDomain());
        assertEquals("http://twitter.com", link.getUrl());
    }

    @Test
    public void validURLWithWWW() throws Exception {
        final Link link = new PageLinkImpl("http://www.twitter.com", "Twitter");
        assertEquals("Twitter", link.getName());
        assertEquals("twitter.com", link.getDomain());
        assertEquals("http://www.twitter.com", link.getUrl());
    }

    @Test(expected = URISyntaxException.class)
    public void validURLNoProtocol() throws Exception {
        final Link link = new PageLinkImpl("www.twitter.com", "Twitter");
    }

    @Test(expected = URISyntaxException.class)
    public void invalidURL() throws Exception {
        final Link link = new PageLinkImpl("mailto:odazriel@gmail.com", "Mail");
    }
}