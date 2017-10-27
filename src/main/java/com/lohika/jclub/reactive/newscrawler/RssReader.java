package com.lohika.jclub.reactive.newscrawler;

import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;

import java.io.IOException;

public interface RssReader {
    SyndFeed read(RssSource source) throws IOException, FeedException;
}
