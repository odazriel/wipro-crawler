package com.wipro.crawler;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

public class SiteInformationBuilderTest {

    @Test
    public void withLinks() throws Exception {
        final PageLink siteLink = new PageLinkImpl("http://gowatermelon.com", "Home Page");
        final PageLinkImpl link1 = new PageLinkImpl("http://api.gowatermelon.com", "API");
        final PageLinkImpl link2 = new PageLinkImpl("http://google.com", "search");
        final PageLinkImpl link3 = new PageLinkImpl("https://api.gowatermelon.com", "Secured API");
        final StaticLinkImpl link4 = new StaticLinkImpl("http://gowatermelon.com/images/1.jpeg", "Image 1");
        final StaticLinkImpl link5 = new StaticLinkImpl("http://code.google.com/scripts/1.js", "Script");
        final Collection<Link> links = Arrays.asList(
                link1, link2, link3, link4, link5
        );
        final SiteInformation actual = new SiteInformationBuilder(siteLink).withLinks(links).build();
        final SiteInformation expected = new SiteInformationImpl(siteLink,
                Arrays.asList(link1, link3), Arrays.asList(link2), Arrays.asList(link4, link5));
        assertEquals(expected, actual);
    }
}