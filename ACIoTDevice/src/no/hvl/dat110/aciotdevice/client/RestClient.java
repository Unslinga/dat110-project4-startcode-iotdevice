package no.hvl.dat110.aciotdevice.client;

import com.google.gson.Gson;
import no.hvl.dat110.ac.restservice.AccessEntry;
import no.hvl.dat110.ac.restservice.AccessLog;
import okhttp3.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

public class RestClient
{
    String host = "http://localhost:8080";
    OkHttpClient client;

    public RestClient()
    {
        client = new OkHttpClient();
    }

    private static String logpath = "/accessdevice/log";

    public void doPostAccessEntry(String message)
    {
        // TODO: implement a HTTP POST on the service to post the message
        RequestBody body = RequestBody.create(new Gson().toJson(new AccessMessage(message)),
                MediaType.parse("application/json"));
        Request request = new Request.Builder().url(host + logpath).post(body).build();

        try
        {
            client.newCall(request).execute();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static String codepath = "/accessdevice/code";

    public AccessCode doGetAccessCode()
    {
        AccessCode code = null;

        // TODO: implement a HTTP GET on the service to get current access code

        Request request = new Request.Builder().url(host + codepath).get().build();

        try
        {
            Response response = client.newCall(request).execute();
            code = new Gson().fromJson(response.body().string(), AccessCode.class);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return code;
    }
}
