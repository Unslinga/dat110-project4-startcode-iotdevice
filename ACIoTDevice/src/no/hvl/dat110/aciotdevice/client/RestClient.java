package no.hvl.dat110.aciotdevice.client;

import com.google.gson.Gson;
import no.hvl.dat110.ac.restservice.AccessEntry;
import no.hvl.dat110.ac.restservice.AccessLog;

public class RestClient
{
	private AccessLog accessLog;

    public RestClient()
    {
        // TODO Auto-generated constructor stub
    }

    private static String logpath = "/accessdevice/log";

    public void doPostAccessEntry(String message)
    {

        // TODO: implement a HTTP POST on the service to post the message

		// POST SENDE DATA : BODY -> AccessMessage
    }

    private static String codepath = "/accessdevice/code";

    public AccessCode doGetAccessCode()
    {

        AccessCode code = null;

        // TODO: implement a HTTP GET on the service to get current access code

		// GET HENTE DATA

        return code;
    }
}
