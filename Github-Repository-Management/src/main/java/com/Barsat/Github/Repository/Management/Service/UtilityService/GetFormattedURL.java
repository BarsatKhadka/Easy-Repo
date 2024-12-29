package com.Barsat.Github.Repository.Management.Service.UtilityService;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Service
@Getter
@Setter
public class GetFormattedURL {

    public String getURL(String userUrl) {
        URI uri = null;
        URL url = null;

        //creating a url object
        try {
            url = new URL(userUrl);
        } catch (MalformedURLException e) {
            return null;
        }

        //making url object to uri
        try {
            uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(),
                    url.getQuery(), url.getRef());

        } catch (URISyntaxException e) {
            return null;
        }

        //returning uri is ascii string , why in ascii string you may ask. because ascii string will contain that unconventional letters.
        return uri.toASCIIString();

    }
}
