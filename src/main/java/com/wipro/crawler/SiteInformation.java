package com.wipro.crawler;

import java.util.Collection;

public interface SiteInformation {
    PageLink getSiteLink();
    Collection<PageLink> getPages();
    Collection<PageLink> getExternalLinks();
    Collection<StaticLink> getStaticContent();
}
