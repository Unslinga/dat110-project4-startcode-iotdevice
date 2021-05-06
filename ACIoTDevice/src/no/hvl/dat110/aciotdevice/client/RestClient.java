package no.hvl.dat110.aciotdevice.client;

import com.google.gson.Gson;
import no.hvl.dat110.ac.restservice.AccessEntry;
import no.hvl.dat110.ac.restservice.AccessLog;

import static spark.Spark.get;
import static spark.Spark.post;

public class RestClient
{
	private AccessLog accessLog;

    public RestClient()
    {
        // TODO Auto-generated constructor stub
		accessLog = new AccessLog();
    }

    private static String logpath = "/accessdevice/log";

    public void doPostAccessEntry(String message)
    {

        // TODO: implement a HTTP POST on the service to post the message
        post(logpath, (req, res) ->
        {
        	Gson gson = new Gson();

			int id = accessLog.add(message);

            return gson.toJson(accessLog.get(id));
        });
    }

    private static String codepath = "/accessdevice/code";

    public AccessCode doGetAccessCode()
    {

        AccessCode code = null;

        // TODO: implement a HTTP GET on the service to get current access code
        get(codepath, (req, res) ->
        {
            Gson gson = new Gson();


            return "";
        });

        return code;
    }
}
