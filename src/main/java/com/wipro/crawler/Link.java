package com.wipro.crawler;

public interface Link {
    String getName();
    String getUrl();

    /**
     * Returns the Top Level Domain for the URL
     * @return the Top Level Domain name.
     */
    String getDomain();
}
