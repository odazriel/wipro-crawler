package com.wipro.crawler;

import org.junit.Test;

import static org.junit.Assert.*;

public class DomainSuffixesTest {
    @Test
    public void isDomainSuffix() throws Exception {
        assertTrue(DomainSuffixes.getInstance().isDomainSuffix("com"));
        assertTrue(DomainSuffixes.getInstance().isDomainSuffix("co.uk"));
        assertTrue(DomainSuffixes.getInstance().isDomainSuffix("net"));
        assertTrue(DomainSuffixes.getInstance().isDomainSuffix("org"));

        assertTrue(DomainSuffixes.getInstance().isDomainSuffix("COM"));
        assertTrue(DomainSuffixes.getInstance().isDomainSuffix("CO.UK"));
        assertTrue(DomainSuffixes.getInstance().isDomainSuffix("NET"));
        assertTrue(DomainSuffixes.getInstance().isDomainSuffix("ORG"));

        assertFalse(DomainSuffixes.getInstance().isDomainSuffix(".com"));
        assertFalse(DomainSuffixes.getInstance().isDomainSuffix(".net"));
        assertFalse(DomainSuffixes.getInstance().isDomainSuffix(""));
        assertFalse(DomainSuffixes.getInstance().isDomainSuffix(null));
    }

}