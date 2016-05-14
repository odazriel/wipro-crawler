package com.wipro.crawler;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Collection;

public class Main {
    public static void main(String[] args)
            throws URISyntaxException, FileNotFoundException, UnsupportedEncodingException {
        if (args.length < 2) {
            System.out.println("Expecting two argument - the URL to crawl from and result file");
        } else {
            final Crawler crawler = new Crawler(new JsouDocumentFonnctionFactory(), new LinksParserImpl());
            final Collection<SiteInformation> result;
            if (args.length == 2) {
                result = crawler.crawl(args[0]);
            } else {
                result = crawler.crawl(args[0], Integer.parseInt(args[2]));
            }
            writeResult(args[1], result);
        }
    }

    private static void writeResult(String filePath, Collection<SiteInformation> result)
            throws FileNotFoundException, UnsupportedEncodingException {
        try (final PrintWriter printWriter = new PrintWriter(filePath, "UTF-8")) {
            result.forEach(printWriter::println);
        }
    }
}
