
import net.webservicex.GetWeather;
import net.webservicex.GetWeatherResponse;
import net.webservicex.ObjectFactory;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.client.core.WebServiceTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/validar-firma-soap.xml" })
public class Principal {

	@Autowired
	private WebServiceTemplate webService;
	
	private static transient Logger logger = Logger
			.getLogger(Principal.class);
	
	@Test
	public void invocarWs() throws Exception {
		GetWeather request = crearRequest("Argentina");
		GetWeatherResponse response=(GetWeatherResponse) invocarWebService(request);
		logger.info(response.getGetWeatherResult());
	}

	/**
	 * Crea el request para utilizar el servicio.
	 */
	private GetWeather crearRequest(String pais) {
		GetWeather request = new ObjectFactory().createGetWeather();
		request.setCountryName(pais);
		request.setCityName("");;
		return request;
	}

	/**
	 * Permite invocar el servicio.
	 */
	private Object invocarWebService(Object request) {
		Object retorno = webService.marshalSendAndReceive(request);
		return retorno;
	}

}
