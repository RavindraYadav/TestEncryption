package customapis;

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

import com.provar.core.model.base.api.ValueScope;
import com.provar.core.testapi.ITestExecutionContext;
import com.provar.core.testapi.annotations.*;
import com.thed.zephyr.cloud.rest.ZFJCloudRestClient;
import com.thed.zephyr.cloud.rest.client.JwtGenerator;

@TestApi( title="JWTGenerator"
        , summary=""
        , remarks=""
        , iconBase=""
        , defaultApiGroups={"My Test APIs"}
        )
@TestApiParameterGroups(parameterGroups={
	    @TestApiParameterGroup(groupName="inputs", title="Inputs"),
	    @TestApiParameterGroup(groupName="result", title="Result"),
	    })
public class JWTGenerator {
    
    @TestApiParameter(seq=1, 
            summary="Access Key from ZAPI",
            remarks="",
            mandatory=true,
            parameterGroup="inputs")
    public String AccessKey;
    
    @TestApiParameter(seq=2, 
            summary="Secret Key from ZAPI",
            remarks="",
            mandatory=true,
            parameterGroup="inputs")
    public String SecretKey;
    
    @TestApiParameter(seq=3, 
            summary="User Name of Jira/Zapi instance",
            remarks="",
            mandatory=true,
            parameterGroup="inputs")
    public String UserName;
    
    @TestApiParameter(seq=4, 
            summary="End point",
            remarks="",
            mandatory=true,
            parameterGroup="inputs")
    public String ResourceURL;
    
    @TestApiParameter(seq=5, 
            summary="Method",
            remarks="",
            mandatory=true,
            parameterGroup="inputs")
    public String Method;

    @TestApiParameter(seq=10, 
            summary="The name that the result will be stored under.",
            remarks="",
            mandatory=true,
            parameterGroup="result")
    public String jwtToken;

    @TestApiParameter(seq=11, 
            summary="The lifespan of the result.",
            remarks="",
            mandatory=true,
            parameterGroup="result",
            defaultValue="Test")
    public ValueScope resultScope;

    /** 
     * Used to write to the test execution log.
     */
    @TestLogger
    public Logger testLogger;
    
    /** 
     * Provides access to facilities, mainly to set and get variable values.
     */
    @TestExecutionContext
    public ITestExecutionContext testExecutionContext;
    
    @TestApiExecutor
    public void execute() throws URISyntaxException {

    	// Put our implementation logic here.
    	testLogger.info("Generating JWT Token for the URL " + this.getClass().getName());

        // Store the result (if appropriate).
    	String zephyrBaseUrl = "https://prod-api.zephyr4jiracloud.com/connect";
        
        String accessKey = AccessKey;
        String secretKey = SecretKey;
        String userName = UserName;
          
        ZFJCloudRestClient client = ZFJCloudRestClient.restBuilder(zephyrBaseUrl, accessKey, secretKey, userName).build();
        JwtGenerator jwtGenerator = client.getJwtGenerator();
      
        String createCycleUri = zephyrBaseUrl + ResourceURL;
//        		"/public/rest/api/1.0/cycles/search?versionId=10002&projectId=10000";
       
        URI uri = new URI(createCycleUri);
        int expirationInSec = 3600;
        String jwt = jwtGenerator.generateJWT(Method, uri,expirationInSec);
        //String jwt = jwtGenerator.generateJWT("PUT", uri, expirationInSec);
//        String jwt = jwtGenerator.generateJWT("POST", uri, expirationInSec);
        testLogger.info(jwt);
        System.out.println("FINAL API : " +uri.toString());
        System.out.println("JWT Token : " +jwt);    
    	String dummyResult = this.getClass().getName() + " result";
        testExecutionContext.setValue(jwtToken, jwt+"\n", resultScope);
        
    }
    
}
