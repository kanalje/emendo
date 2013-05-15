package no.strong.emendo.listener.gaelistener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

import no.strong.emendo.data.User;
import no.strong.emendo.data.manager.UserManager;
import no.strong.emendo.util.AuthToken;

import com.google.appengine.api.channel.ChannelFailureException;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


@Path("/user")
public class GaeUserEvent {

	UserManager um = new UserManager();
	Gson gson = new Gson();
	
	private static ChannelService channelService = ChannelServiceFactory.getChannelService();

	@GET
	@Path("/{userId}")
	public String getUser(@PathParam("userId") String userId) {	
		System.out.println("::: Got user get request for id: " + userId);
		System.out.println("::: Response from get user: " + gson.toJson(um.get(userId)));
		return gson.toJson(um.get(userId));
	}
	
	@GET
	@Path("/channelToken/{clientId}")
	public String getChannelToken(@PathParam("clientId") String clientId) {	
		String token = createChannel(clientId);
		return token;
	}

	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Path("/login")
	public String login(String assertion) {

		//BAD_REQUEST 400
		if (assertion == null) {
			throw new WebApplicationException(400);
		}

		// Prepare the verification data
		Map<String, String> data = new HashMap<String, String>();
		data.put("assertion", assertion);
		data.put("audience", "http://emendo-dev.appspot.com");

		try {
			URL url = new URL("https://verifier.login.persona.org/verify");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("CONTENT-TYPE", "application/json");
			connection.setConnectTimeout(20000);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");

			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(gson.toJson(data));
			writer.close();

			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				Map<String, String> mappedResponse = gson.fromJson(reader.readLine(), new TypeToken<Map<String, String>>() {}.getType());
				System.out.println("::: mappedResponse.get(\"email\"): " + mappedResponse.get("email"));
				User user = um.getByEmail(mappedResponse.get("email"));
				if (user == null) {
					user = um.create(mappedResponse.get("email"));
				}

				return AuthToken.genereateAuthToken(user.getId(), user.getEmail());

			} else {
				System.out.print("Something went wrong. The responsecode from Persona was: ");
				System.out.println(connection.getResponseCode());
			}
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		throw new WebApplicationException(500);

	}

	/**
	 * Creates the Channel token for the user 
	 * @param userId The User whom the token is created for  
	 * @return The token string is returned
	 */
	public String createChannel(String userId){
		try{
			return channelService.createChannel(userId);
		} catch(ChannelFailureException channelFailureException){
			return null;
		} catch(Exception otherException){
			return null;
		}
	}
}
