package no.group7.restservice.dweet;

import no.group7.restservice.entity.Poll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DweetCommunicator {

    private static Logger logger = LoggerFactory.getLogger(DweetCommunicator.class);

    /**
     * This code is from: https://stackoverflow.com/a/66236352
     * Original poster (username on stackoverflow): dan1st
     * <p>
     * (only slightly modified to fit codebase, removed comments, read 16.11.2021)
     */
    public static void sendPollToDweet(Poll poll) {
        URI uri = null;
        try {
            uri = new URI(new String("https://dweet.io/dweet/for/rest-service-group-7" +
                    "?id=" + poll.getId().toString() +
                    "&title=" + poll.getTitle() +
                    "&question=" + poll.getQuestion() +
                    "&startTime=" + poll.getStartTime().toString() +
                    "&endTime=" + poll.getEndTime().toString() +
                    "&numYes=" + poll.getNum_yes() +
                    "&numNo=" + poll.getNum_no() +
                    "&isClosed=" + poll.isClosed() +
                    "&isPublic=" + poll.isPublic()).replace(" ", "%20"));
        } catch (Exception e) {
            logger.error("<dweet> Could not send poll to dweet.io (most likely some fields were null / not enough info)");
            return;
        }

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println);

        logger.info("<dweet> Sent posted poll to dweet.io");
    }
}
