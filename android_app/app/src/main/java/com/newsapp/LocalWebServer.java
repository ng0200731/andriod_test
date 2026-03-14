package com.newsapp;

import android.content.Context;
import android.content.res.AssetManager;
import fi.iki.elonen.NanoHTTPD;
import java.io.IOException;
import java.io.InputStream;

public class LocalWebServer extends NanoHTTPD {
    private Context context;

    public LocalWebServer(Context context, int port) {
        super(port);
        this.context = context;
    }

    @Override
    public Response serve(IHTTPSession session) {
        String uri = session.getUri();
        if (uri.equals("/")) {
            uri = "/index.html";
        }
        
        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("webapp" + uri);
            
            String mimeType = getMimeType(uri);
            return newChunkedResponse(Response.Status.OK, mimeType, inputStream);
        } catch (IOException e) {
            return newFixedLengthResponse(Response.Status.NOT_FOUND, "text/plain", "File not found");
        }
    }

    private String getMimeType(String uri) {
        if (uri.endsWith(".html")) return "text/html";
        if (uri.endsWith(".js")) return "application/javascript";
        if (uri.endsWith(".css")) return "text/css";
        if (uri.endsWith(".json")) return "application/json";
        if (uri.endsWith(".png")) return "image/png";
        if (uri.endsWith(".jpg") || uri.endsWith(".jpeg")) return "image/jpeg";
        return "text/plain";
    }
}
