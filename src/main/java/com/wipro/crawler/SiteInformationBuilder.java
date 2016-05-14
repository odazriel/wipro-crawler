package com.wipro.crawler;

import java.util.ArrayList;
import java.util.Collection;

public class SiteInformationBuilder {
    private final PageLink siteLink;
    private Collection<Link> links;

    public SiteInformationBuilder(PageLink siteLink) {
        if (siteLink == null) {
            throw new IllegalArgumentException();
        } else {
            this.siteLink = siteLink;
        }
    }

    public SiteInformationBuilder withLinks(Collection<Link> links) {
        this.links = new ArrayList<>(links);
        return this;
    }

    public SiteInformation build() {
        final Collection<PageLink> pages = new ArrayList<>();
        final Collection<PageLink> externalLinks = new ArrayList<>();
        final Collection<StaticLink> staticContent = new ArrayList<>();

        for (final Link link : links) {
            if (link instanceof StaticLink) {
                staticContent.add((StaticLink) link);
            } else if (link instanceof PageLink) {
                if (link.getDomain().equals(siteLink.getDomain())) {
                    pages.add((PageLink) link);
                } else {
                    externalLinks.add((PageLink) link);
                }
            }
        }
        return new SiteInformationImpl(siteLink, pages, externalLinks, staticContent);
    }
}
