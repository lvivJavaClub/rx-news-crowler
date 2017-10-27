package com.lohika.jclub.reactive.newscrawler;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import io.reactivex.subjects.PublishSubject;
import lombok.Getter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;

@Service
public class RssReaderImpl implements RssReader {
    @Getter
    private PublishSubject<SyndEntry> publishSubject = PublishSubject.create();

    @Override
    public SyndFeed read(RssSource source) throws IOException, FeedException {
        XmlReader xmlReader = new XmlReader(new URL(source.getUrl()));
        SyndFeedInput syndFeedInput = new SyndFeedInput();
        syndFeedInput.setAllowDoctypes(true);
        return syndFeedInput.build(xmlReader);
    }

    @Scheduled(fixedDelay = 5000)
    public void readFeed() throws IOException, FeedException {
        System.out.println(Thread.currentThread().getName() + ": Fetching feed...");
        SyndFeed feed = read(new RssSource("https://espreso.tv/rss"));
        System.out.println("Feed info: " + feed.getEntries().size() );
        //feed.getEntries().forEach(publishSubject::onNext);
        feed.getEntries().stream().map(SyndEntry::getTitle).forEach(System.out::println);
    }

}
