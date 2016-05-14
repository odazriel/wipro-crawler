package com.wipro.crawler;

import java.util.Collection;
import java.util.Collections;

final class SiteInformationImpl implements SiteInformation {

    private final PageLink siteLink;
    private final Collection<PageLink> pages;
    private final Collection<PageLink> externalLinks;
    private final Collection<StaticLink> staticContent;

    public SiteInformationImpl(
            PageLink siteLink, Collection<PageLink> pages, Collection<PageLink> externalLinks, Collection<StaticLink> staticContent) {
        this.siteLink = siteLink;
        this.pages = pages;
        this.externalLinks = externalLinks;
        this.staticContent = staticContent;
    }

    @Override
    public PageLink getSiteLink() {
        return siteLink;
    }

    @Override
    public Collection<PageLink> getPages() {
        return Collections.unmodifiableCollection(pages);
    }

    @Override
    public Collection<PageLink> getExternalLinks() {
        return Collections.unmodifiableCollection(externalLinks);
    }

    @Override
    public Collection<StaticLink> getStaticContent() {
        return Collections.unmodifiableCollection(staticContent);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        appendLink(builder, siteLink);
        builder.append('\n');
        appendLinkCollection(builder, "Pages", pages);
        appendLinkCollection(builder, "External Pages", externalLinks);
        appendLinkCollection(builder, "Static Content", staticContent);

        return builder.toString();
    }

    private void appendLinkCollection(StringBuilder builder, String headline, Collection<? extends Link> links) {
        builder.append(headline).append('\n');
        for (final Link link : links) {
            builder.append('\t');
            appendLink(builder, link);
            builder.append('\n');
        }
    }

    private void appendLink(StringBuilder builder, Link link) {
        if (!link.getName().isEmpty()) {
            builder.append(link.getName()).append(": ");
        }
        builder.append(link.getUrl());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SiteInformationImpl that = (SiteInformationImpl) o;

        if (siteLink != null ? !siteLink.equals(that.siteLink) : that.siteLink != null) return false;
        if (pages != null ? !pages.equals(that.pages) : that.pages != null) return false;
        if (externalLinks != null ? !externalLinks.equals(that.externalLinks) : that.externalLinks != null)
            return false;
        return staticContent != null ? staticContent.equals(that.staticContent) : that.staticContent == null;

    }

    @Override
    public int hashCode() {
        int result = siteLink != null ? siteLink.hashCode() : 0;
        result = 31 * result + (pages != null ? pages.hashCode() : 0);
        result = 31 * result + (externalLinks != null ? externalLinks.hashCode() : 0);
        result = 31 * result + (staticContent != null ? staticContent.hashCode() : 0);
        return result;
    }
}
