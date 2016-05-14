package com.wipro.crawler;

import org.easymock.EasyMock;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import static org.junit.Assert.*;

public class CrawlerTest {

    @Test
    public void crawl() throws Exception {
        final DocumentConnectionFactory connectionFactory = EasyMock.createNiceMock(DocumentConnectionFactory.class);
        final Connection connection = EasyMock.createNiceMock(Connection.class);
        final Connection apiConnection = EasyMock.createNiceMock(Connection.class);
        final Document document = EasyMock.createNiceMock(Document.class);
        final Document apiDocument = EasyMock.createNiceMock(Document.class);

        EasyMock.expect(connectionFactory.createConnection("http://gowatermelon.com")).andReturn(connection);
        EasyMock.expect(connectionFactory.createConnection("http://api.gowatermelon.com")).andReturn(apiConnection);
        EasyMock.expect(connection.get()).andReturn(document);
        EasyMock.expect(apiConnection.get()).andReturn(apiDocument);

        final Collection<Link> links = Collections.singletonList(new PageLinkImpl("http://api.gowatermelon.com", "API"));
        final Collection<Link> apiLinks = Collections.singletonList(new PageLinkImpl("http://gowatermelon.com", "Home"));
        final LinksParser linksParser = EasyMock.createNiceMock(LinksParser.class);
        EasyMock.expect(linksParser.parseLinks(document)).andReturn(links);
        EasyMock.expect(linksParser.parseLinks(apiDocument)).andReturn(apiLinks);

        EasyMock.replay(connectionFactory, connection, apiConnection, document, apiDocument, linksParser);

        final Crawler crawler = new Crawler(connectionFactory, linksParser);
        final Collection<SiteInformation> actual = crawler.crawl("http://gowatermelon.com");

        assertEquals(2, actual.size());
        assertEquals(1, actual.stream().filter(site -> site.getSiteLink().getUrl().equals("http://gowatermelon.com")).count());
        assertEquals(1, actual.stream().filter(site -> site.getSiteLink().getUrl().equals("http://api.gowatermelon.com")).count());
    }
}