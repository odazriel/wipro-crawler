package com.wipro.crawler;

import org.jsoup.nodes.Document;

import java.net.URISyntaxException;
import java.util.Collection;

/**
 * An object that, given an HTML Document, can extract all links from it.
 * The links might be to other pages or to static content.
 */
public interface LinksParser {
    Collection<Link> parseLinks(Document document) throws URISyntaxException;
}
