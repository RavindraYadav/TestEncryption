package pageobjects;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
 
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import java.util.logging.Logger;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.provar.core.testapi.annotations.*;
import com.thed.zephyr.cloud.rest.ZFJCloudRestClient;
import com.thed.zephyr.cloud.rest.client.JwtGenerator;

@Page( title="JWT Generator"                                
     , summary=""
     , relativeUrl=""
     , connection="Demo"
     )             
public class JWTGenerator {

			public Logger testLogger;
			
			public void execut() throws URISyntaxException{
			
			String zephyrBaseUrl = "https://prod-api.zephyr4jiracloud.com/connect";
                             
                                String accessKey = "MDg0NzY1NzgtNThhOS0zZGE1LThlOTktZTcxMjAyM2Y4YTk2IGFkbWluIGFkbWlu";
                                String secretKey = "prqDXOOACulKuba-j3xkJBM4DrK9NKMk_figW32J0EI";
                                String userName = "admin";
                                  
                                ZFJCloudRestClient client = ZFJCloudRestClient.restBuilder(zephyrBaseUrl, accessKey, secretKey, userName).build();
                                JwtGenerator jwtGenerator = client.getJwtGenerator();
                              
                                String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/cycles/search?versionId=10002&projectId=10000";
                               
                                URI uri = new URI(createCycleUri);
                                int expirationInSec = 360;
                                String jwt = jwtGenerator.generateJWT("GET", uri,3600);
                                //String jwt = jwtGenerator.generateJWT("PUT", uri, expirationInSec);
                                //String jwt = jwtGenerator.generateJWT("POST", uri, expirationInSec);
                               
                               testLogger.info("FINAL API : " +uri.toString());
                               testLogger.info("JWT Token : " +jwt);    

}
}
