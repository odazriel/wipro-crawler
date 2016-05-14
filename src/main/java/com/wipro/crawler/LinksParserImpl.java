package com.wipro.crawler;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashSet;

final class LinksParserImpl implements LinksParser {

    @Override
    public Collection<Link> parseLinks(Document document) {
        final Elements pages = document.select("a[href]");
        final Elements media = document.select("[src]");
        final Elements imports = document.select("link[href]");

        final Collection<Link> links = new HashSet<>(pages.size() + media.size() + imports.size());
        parsePageLinks(pages, links);
        parseMediaLinks(media, links);
        parseImportLinks(imports, links);

        return links;
    }

    private void parseMediaLinks(Elements media, Collection<Link> result) {
        for (final Element src : media) {
            final Link staticLink = createStaticLink(src.attr("abs:src"), src.tagName());
            if (staticLink != null) {
                result.add(staticLink);
            }
        }
    }

    private void parseImportLinks(Elements imports, Collection<Link> result) {
        for (final Element element : imports) {
            final Link rel = createStaticLink(element.attr("abs:href"), element.attr("rel"));
            if (rel != null) {
                result.add(rel);
            }
        }
    }

    private void parsePageLinks(Elements pages, Collection<Link> result) {
        for (final Element page : pages) {
            final String href = normalizeURL(page.attr("abs:href"));
            if (href != null && !href.isEmpty()) {
                final Link pageLink = createPageLink(href, page.text());
                if (pageLink != null) {
                    result.add(pageLink);
                }
            }
        }
    }

    private Link createStaticLink(String url, String name) {
        try {
            return new StaticLinkImpl(url, name);
        } catch (URISyntaxException e) {
            return null;
        }
    }

    private Link createPageLink(String url, String name) {
        try {
            return new PageLinkImpl(url, name);
        } catch (URISyntaxException e) {
            return null;
        }
    }

    private String normalizeURL(String url) {
        if (url != null && !url.isEmpty()) {
            int indexOfHashtag = url.indexOf('#');
            if (indexOfHashtag >= 0) {
                return url.substring(0, indexOfHashtag);
            }
        }
        return url;
    }
}
