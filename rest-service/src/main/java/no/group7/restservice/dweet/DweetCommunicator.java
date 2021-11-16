package no.group7.restservice.dweet;

import no.group7.restservice.entity.Poll;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DweetCommunicator {

    /**
     * This code is from: https://stackoverflow.com/a/66236352
     * Original poster (username on stackoverflow): dan1st
     * <p>
     * (only slightly modified to fit codebase, read 16.11.2021)
     */
    public static void sendPollToDweet(Poll poll) {
        URI uri = null;
        try {
            uri = new URI(new String("https://dweet.io/dweet/for/rest-service-group-7" +
                    "?id=" + poll.getId().toString() +
                    "&title=" + poll.getTitle() +
                    "&question=" + poll.getQuestion() +
                    "&startTime=" + poll.getStartTime().toString() +
                    "&endTime=" + poll.getEndTime().toString()).replace(" ", "%20"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            System.err.println("<dweet> Could not send poll to dweet.io");
        }

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)//get the body as a string (.then(r=>r.text()) in your code))
                .thenAccept(System.out::println);//print it (.then(console.log) in your code)

        System.out.println("<dweet> Sent posted poll to dweet.io");
    }
}
