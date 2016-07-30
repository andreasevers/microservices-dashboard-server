package be.ordina.msdashboard.nodes.aggregators.index;

import java.util.*;

import static be.ordina.msdashboard.config.Constants.CONFIGSERVER;
import static be.ordina.msdashboard.config.Constants.DISCOVERY;
import static be.ordina.msdashboard.config.Constants.DISK_SPACE;
import static be.ordina.msdashboard.config.Constants.HYSTRIX;

/**
 * @author Andreas Evers
 */
public class IndexProperties {

    private Map<String, String> requestHeaders = new HashMap<>();
    private List<String> filteredServices = Arrays.asList(HYSTRIX, DISK_SPACE, CONFIGSERVER, DISCOVERY);

    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public List<String> getFilteredServices() {
        return filteredServices;
    }
}
