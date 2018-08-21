package com.example.rest;

import org.apache.commons.lang.StringUtils;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Path("myresource")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@QueryParam("name") String name) {
        return "Got it!";
    }

    @GET
    @Path("write")
    @Produces(MediaType.TEXT_PLAIN)
    public String write(@QueryParam("size") @DefaultValue("1024") Integer size, @QueryParam("repeat") @DefaultValue("1048576") Integer repeat, @QueryParam("path") @DefaultValue("data") String path) {
        File file = new File(path);
        file.mkdirs();
        String data = StringUtils.repeat("x", size);
        long t1 = System.currentTimeMillis();
        try (FileWriter fileWriter = new FileWriter(path + "/test.txt")){
            for (int i = 0; i < repeat; ++i) {
                fileWriter.write(data);
            }
            fileWriter.flush();
        } catch (IOException e) {
            return e.getMessage();
        }
        long t2 = System.currentTimeMillis();
        return "" + (t2 - t1);
    }
}
