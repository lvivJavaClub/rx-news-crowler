package com.lohika.jclub.reactive.newscrawler;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/")
public class RssController {
    @Autowired
    private RssReader rssReader;

    @GetMapping
    public SyndFeed getRssFeed() throws IOException, FeedException {
//        SyndFeed feed = rssReader.read(new RssSource("https://espreso.tv/rss"));
// --       SyndFeed feed = rssReader.read(new RssSource("http://www.pravda.com.ua/rss-info/"));
//        SyndFeed feed = rssReader.read(new RssSource("https://zaxid.net/rss/1.xml"));
// --        SyndFeed feed = rssReader.read(new RssSource("http://zik.ua/rss/export.rss"));
// --        SyndFeed feed = rssReader.read(new RssSource("https://hromadske.ua/rss"));
        SyndFeed feed = rssReader.read(new RssSource("http://tvoemisto.tv/rss/news.rss"));
        feed.getEntries().forEach(e -> System.out.println("\t" + e.getTitle() + "\n" + e.getDescription().getValue()));

        return feed;
    }

}
