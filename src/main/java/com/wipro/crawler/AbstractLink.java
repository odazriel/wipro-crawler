package com.wipro.crawler;

import java.net.URI;
import java.net.URISyntaxException;

abstract class AbstractLink implements Link {
    private final String url;
    private final String name;
    private final String domain;

    protected AbstractLink(String url, String name) throws URISyntaxException {
        if (url.endsWith("/") && url.length() > 1) {
            url = url.substring(0, url.length() - 1);
        }
        this.url = url.toLowerCase();
        this.name = name == null ? "" : name;
        this.domain = extractDomain(new URI(url));
    }

    private String extractDomain(URI uri) throws URISyntaxException {
        final String host = uri.getHost();

        if (host == null) {
            throw new URISyntaxException(uri.toString(), "Cannot retrieve host");
        } else {
            int index = 0;
            String candidate = host;
            while (index >= 0) {
                index = candidate.indexOf('.');
                String subCandidate = candidate.substring(index + 1);
                if (DomainSuffixes.getInstance().isDomainSuffix(subCandidate)) {
                    return candidate;
                }
                candidate = subCandidate;
            }
            return candidate;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getDomain() {
        return domain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractLink that = (AbstractLink) o;

        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return domain != null ? domain.equals(that.domain) : that.domain == null;

    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (domain != null ? domain.hashCode() : 0);
        return result;
    }
}
