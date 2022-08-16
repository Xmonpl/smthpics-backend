package org.eu.xmon.smthpicsrest.object;

import com.google.gson.JsonObject;
import lombok.Builder;
import lombok.Data;

import java.util.Random;

@Builder
@Data
public class Embed {
    private String title;
    private String type;
    private String description;
    private String url;
    private String author;
    private String author_url;
    private String color;

    public String generateMeta(){
        final StringBuilder stringBuilder = new StringBuilder();
        if (type.startsWith("video/")){
           stringBuilder.append("<meta name=\"twitter:card\" content=\"player\">");
           stringBuilder.append("<meta name=\"twitter:player\" content=\"").append(url).append("\">");
           stringBuilder.append("<meta name=\"twitter:player:stream\" content=\"").append(url).append("\">");
           stringBuilder.append("<meta name=\"twitter:player:stream:content_type\" content=\"").append(type).append("\">");
        }else if (type.startsWith("image/")){
            stringBuilder.append("<meta name=\"twitter:card\" content=\"summary_large_image\">");
            stringBuilder.append("<meta name=\"twitter:image\" content=\"").append(url).append("\">");
            stringBuilder.append("<meta name=\"twitter:image:src\" content=\"").append(url).append("\">");
            stringBuilder.append("<meta property=\"og:image\" content=\"").append(url).append("\">");
        }
        stringBuilder.append("<meta name=\"theme-color\" content=\"").append(color).append("\">");
        stringBuilder.append("<meta property=\"og:title\" content=\"").append(title).append("\">");
        stringBuilder.append("<meta name=\"twitter:title\" content=\"").append(title).append("\">");
        stringBuilder.append("<meta name=\"twitter:description\" content=\"").append(description).append("\">");
        stringBuilder.append("<meta property=\"og:description\" content=\"").append(description).append("\">");
        stringBuilder.append("<link type=\"application/json+oembed\" href=\"").append(url.substring(0, url.lastIndexOf("."))).append(".json").append("\">");
        return stringBuilder.toString();
    }

    public String generateJson(){
        final JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", "link");
        jsonObject.addProperty("version", "1.0");
        jsonObject.addProperty("author_name", author);
        jsonObject.addProperty("author_url", author_url);
        jsonObject.addProperty("provider_name", title);
        jsonObject.addProperty("provider_url", author_url);
        return jsonObject.getAsString();
    }

    public Embed randomColor(){
        final int nextInt = new Random().nextInt(0xffffff + 1);
        this.setColor(String.format("#%06x", nextInt));
        return this;
    }
}
