package com.lohika.jclub.reactive.newscrawler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RssPrinter {
    private RssReaderImpl reader;

    @Autowired
    public RssPrinter(RssReaderImpl reader) {
        this.reader = reader;
        System.out.println("Subscribing...");
        this.reader.getPublishSubject().subscribe(entry -> System.out.println(entry.getTitle()));
    }
}
