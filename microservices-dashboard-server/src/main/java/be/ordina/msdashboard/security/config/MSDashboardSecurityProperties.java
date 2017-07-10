/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package be.ordina.msdashboard.security.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Properties for security.
 *
 * @author Andreas Evers
 * @author Kevin van Houtte
 */
public class MSDashboardSecurityProperties {

    private Map<String, List<String>> strategies = new LinkedHashMap<>();

    public Map<String, List<String>> getStrategies() {
        return strategies;
    }

    // TODO: Unit test!
    public Map<String, String> getStrategiesByAggregator() {
        Map<String, String> strategiesByAggregator = new HashMap<>();
        for (Map.Entry entry : strategies.entrySet()) {
            for (String aggregator : (List<String>) entry.getValue()) {
                if (strategiesByAggregator.containsKey(aggregator)) {
                    throw new IllegalStateException("Aggregator " + aggregator + "has two different security strategies configured");
                } else {
                    strategiesByAggregator.put(aggregator, (String) entry.getKey());
                }
            }
        }
        return strategiesByAggregator;
    }
}