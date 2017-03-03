package com.abhijit.graphs.service;

/**
 * 
 * @author abhijitk.connect@gmail.com (Abhijit Kumar)
 *
 */

public class GraphService {

	/**
	 * Handle for the graph controller.
	 */
	private GraphServiceController graphController;

	/**
	 * boolean to track the service start/stop status
	 */
	private volatile boolean isStopped;

	/**
	 * Singleton instance for graph service.
	 */
	public static final GraphService singletonInstance = new GraphService();



	public synchronized void startService(){
		try {
			this.graphController = new GraphServiceController();
			singletonInstance.waitInfinetly();

		} catch (Exception ex) {

		}
	}

	private void waitInfinetly() throws InterruptedException {
		while (!isStopped) {
			synchronized (this) {
				this.wait();
			}
		}
	}

	public synchronized void stopService(){
		try {
			graphController.terminate();
			isStopped = true;
		} finally {
			notifyOnStopService();
			System.out.println("GraphService Stopped successfully. It can not be re-started again...............................");
		}
	}
    public static void main(String[] args) {

        try {
        
            System.out.println("Starting the GraphService...................................");
            singletonInstance.startService();
            System.out.println("GraphService Stopped main thread released...............................");

        } catch (Exception ex) {
            System.out.println("Exception in starting graph service" + ex);
            try {
                singletonInstance.stopService();
            } catch (Exception e) {
              System.out.println(
                        "Error in gracefully stopping the Graph Service after startup failure."+ e);
            }
        }

    }

    private void notifyOnStopService() {

        synchronized (this) {
            this.notifyAll();
        }

    }

}
