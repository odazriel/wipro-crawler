package com.wipro.crawler;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

public class Crawler {

    private final DocumentConnectionFactory connectionFactory;
    private final LinksParser linksParser;

    public Crawler(DocumentConnectionFactory connectionFactory, LinksParser linksParser) {
        this.connectionFactory = connectionFactory;
        this.linksParser = linksParser;
    }

    public Collection<SiteInformation> crawl(String origin) throws URISyntaxException {
        return crawl(origin, Integer.MAX_VALUE);
    }

    public Collection<SiteInformation> crawl(String origin, int limit) throws URISyntaxException {
        final Map<String, SiteInformation> visitedSites = new HashMap<>();
        final Queue<PageLink> pagesToVisit = new LinkedList<>();
        pagesToVisit.add(new PageLinkImpl(origin, "Root"));

        while (!pagesToVisit.isEmpty() && visitedSites.size() < limit) {
            final PageLink siteLink = pagesToVisit.poll();

            Document document = null;
            try {
                document = connectionFactory.createConnection(siteLink.getUrl()).get();
            } catch (IOException e) {
                // Report to log that parsing of this site failed
            }

            if (document != null) {
                final Collection<Link> links = linksParser.parseLinks(document);
                final SiteInformation siteInformation = new SiteInformationBuilder(siteLink).withLinks(links).build();
                visitedSites.put(siteLink.getUrl(), siteInformation);

                pagesToVisit.addAll(
                        siteInformation.getPages()
                                .stream()
                                .filter(nextPage -> !visitedSites.containsKey(nextPage.getUrl()))
                                .collect(Collectors.toList()));
            }
        }
        return visitedSites.values();
    }
}
