package it.geobeyond.geoserver;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/*
 TODO: 
 remove workspace via REST
 */

public class WorkspaceTest {

	private DefaultHttpClient client;
	private final String username;
	private final String password;
	private final String host;
	private final int port;
	private final int timeout;

	public WorkspaceTest() {
		// TODO: aggiunta properties esternalizzate
		this.username = "admin";
		this.password = "geoserver";
		this.host = "localhost";
		this.port = 8888;
		this.timeout = 600;
	}

	private void setAuthorization() throws Exception {
		client.getCredentialsProvider().setCredentials(
				new AuthScope(this.host, this.port, AuthScope.ANY_SCHEME),
				new UsernamePasswordCredentials(this.username, this.password));
		 client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, timeout);
	}

	@Before
	public void before() throws Exception {
		client = new DefaultHttpClient();
		setAuthorization();
	}

	@After
	public void after() {
		client.getConnectionManager().shutdown();
	}

	@Test
	public void testListWorkspaces() throws Exception {
		System.out.println("\n\nLIST WORKSPACES");
		HttpGet request = new HttpGet("http://localhost:8888/rest/workspaces");
//		request.addHeader("Accept", "text/xml");
		request.addHeader("Content-type", "application/xml");
		HttpResponse response = client.execute(request);
		printResponse(response);
		
		int status = response.getStatusLine().getStatusCode();
		assertEquals(200, status);
	}
	
	@Test
	public void testDeleteWorkspace() throws Exception {
	
		System.out.println("\n\nDELETE WORKSPACE ---------------------------------------");

		HttpDelete req = new HttpDelete("http://localhost:8888/rest/workspaces/WORKSPACE_TEST/");
		HttpResponse response = client.execute(req);
		printResponse(response);

	}

	@Test
	public void testCreateWorkspace() throws Exception {
		System.out.println("\n\nCREATE WORKSPACE ---------------------------------------");

		HttpPost post = new HttpPost("http://localhost:8888/rest/workspaces");
		post.addHeader("Accept", "text/xml");
		post.addHeader("Content-Type", "text/xml");

		StringEntity entity = new StringEntity("<workspace><name>WORKSPACE_TEST</name></workspace>");
		post.setEntity(entity);

		HttpResponse response = client.execute(post);
		printResponse(response);
	}


	private void printResponse(final HttpResponse response) throws Exception{
		int status = response.getStatusLine().getStatusCode();
		System.out.println("STATUS: "+status);
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line = "";
		while ((line = rd.readLine()) != null) {
			System.out.println(line);
		}
	}
	
}

/*



# lettura dettagli del namespace
curl -u admin:geoserver -XGET -H 'Accept: text/xml' http://localhost:8888/rest/workspaces/caaml

# definizione nuovo DATASTORE
curl -u admin:geoserver -XPOST -T ESEMPI/caaml/caaml_datastore.xml -H 'Content-type: text/xml' http://localhost:8888/rest/workspaces/caaml/datastores

# aggiunta nuova FEATURE
# NOTA: il nome della featureType deve corrispondere alla tabella sul db
curl -u admin:geoserver -XPOST -H 'Content-type: text/xml' -d '<featureType><name>caaml_bulletin</name></featureType>' http://localhost:8888/rest/workspaces/caaml/datastores/caaml/featuretypes
# lettura feature aggiunta
curl  -u admin:geoserver -XGET http://localhost:8888/rest/workspaces/caaml/datastores/caaml/featuretypes/caaml_bulletin.xml


# aggiunta feature + tabella (ES: una tabella "annotations")
curl -u admin:geoserver -XPOST -T ESEMPI/caaml/annotations.xml -H 'Content-type: text/xml' http://localhost:8888/rest/workspaces/caaml/datastores/nyc/featuretypes


# LAYERS ----------------------------------------------------------------
# aggiunta stili (dovremmo definire i file .sld...)
curl -u admin:geoserver -XPUT -H 'Content-type: application/vnd.ogc.sld+xml' -d @caaml_bulletin.sld http://localhost:8888/rest/styles/caaml_bulletin

#aggiunta Layer
curl -u admin:geoserver -XPOST -d @ESEMPI/caaml/caaml_layerGroup.xml -H 'Content-type: text/xml' http://localhost:8888/rest/layergroups


# -------------------------------------

# Lista bullettin in JSON:
curl -X GET "http://localhost:8888/caaml/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=caaml:caaml_bulletin&maxFeatures=50&outputFormat=application/json"







*/



//List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
//nameValuePairs.add(new BasicNameValuePair("registrationid", "123456789"));
//post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
