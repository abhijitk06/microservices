package com.abhijit.graphs.service;

import org.apache.log4j.Logger;

import io.advantageous.qbit.annotation.RequestMapping;
import io.advantageous.qbit.server.EndpointServerBuilder;
import io.advantageous.qbit.server.ServiceEndpointServer;

/**
 * 
 * @author abhijitk.connect@gmail.com (Abhijit Kumar)
 *
 */
public class GraphServiceController {
	
	/**
	 * Qbit server end-point for rest server.
	 * 
	 */

	public ServiceEndpointServer server = null;


	/**
	 * The logger reference.
	 */
	private static Logger logger = Logger.getLogger("GRAPH");
	
	/**
	 * Initialize the GraphServiceController. Create REST server and start it.
	 * 
	 */
	public GraphServiceController(){
		try {

			initializeMicroService();

		} catch (Exception ex) {
			System.out.println("Error in initializing service: " + ex);
			logger.error("Error in initializing the GraphServiceController ",ex);
			
		}
	}
	
	public void initializeMicroService() {

		server = EndpointServerBuilder
				.endpointServerBuilder()
				.setPort(6060)
				.setTimeoutSeconds(120)
				.build();
		
		server.initServices(new GraphCreationService()); //Adding services
				
		server.startServer();
		System.out.println("Qbit service end point started....");

	}

	public void terminate() {
		server.stop();
		System.out.println("GraphServiceController terminated successfully.....");
	}

	/**
	 * Sample service creation HTTP service.
	 * 
	 * <p>
	 * Access url to call this service
	 * http://localhost:6060/services/graphcreationservice/ping
	 * 
	 * 
	 */
	public static class GraphCreationService {
         //Test3
		/**
		 * Ping it
		 * 
		 * @return String "Ok"
		 */
		@RequestMapping("/ping")
		public String ping() {
			System.out.println("OK");
			logger.info("Received ping request");
			return "OK";
		}
	}




}
