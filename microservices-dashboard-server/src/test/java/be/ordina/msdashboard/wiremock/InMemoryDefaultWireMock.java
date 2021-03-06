/*
 * Copyright 2012-2017 the original author or authors.
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
package be.ordina.msdashboard.wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.common.SingleRootFileSource;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class InMemoryDefaultWireMock {

	private final static int HTTP_PORT = 8088;
	private final static int HTTPS_PORT = 8089;

	private WireMockServer eurekaServer;
	private WireMockServer secureServer;

	@PostConstruct
	public void startServers() throws IOException {
		WireMockConfiguration eurekaServerConfig = wireMockConfig()
				.notifier(new ConsoleNotifier(true))
				.port(HTTP_PORT)
				.fileSource(new SingleRootFileSource("src/test/resources/mocks/default/eureka"));
		eurekaServer = new WireMockServer(eurekaServerConfig);
		eurekaServer.start();

		WireMockConfiguration secureConfig = wireMockConfig()
				.notifier(new ConsoleNotifier(true))
				.httpsPort(HTTPS_PORT)
				.fileSource(new SingleRootFileSource("src/test/resources/mocks/default/secure"));
		secureServer = new WireMockServer(secureConfig);
		secureServer.start();

	}

	@PreDestroy
	public void stopServers() {
		eurekaServer.stop();
		secureServer.stop();
	}
}
