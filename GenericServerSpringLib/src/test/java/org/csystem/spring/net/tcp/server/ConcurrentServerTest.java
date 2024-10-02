package org.csystem.spring.net.tcp.server;

import lombok.extern.slf4j.Slf4j;
import org.csystem.spring.net.configuration.ExecutorServiceConfig;
import org.csystem.spring.net.configuration.ServerSocketConfig;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.io.*;
import java.net.Socket;
import java.util.stream.IntStream;

@Slf4j
@ContextConfiguration(classes = {ConcurrentServer.class, ExecutorServiceConfig.class, ServerSocketConfig.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class ConcurrentServerTest {

	private final static String LOCAL_HOST = "127.0.0.1";
	private final static int DEFAULT_PORT = 6767;
	private final static int CUSTOMIZED_PORT = 56767;
	private final static int CUSTOMIZED_BACKLOG = 1024;
	private final static String BEFORE_ACCEPT_MESSAGE = "BEFORE_ACCEPT_MESSAGE";
	private final static String INIT_RUNNABLE_MESSAGE = "INIT_RUNNABLE_MESSAGE";
	private final static String SOCKET_CONSUMER_MESSAGE = "SOCKET_CONSUMER_MESSAGE";
	private final static String EXCEPTION_CONSUMER_MESSAGE = "EXCEPTION_CONSUMER_MESSAGE";
	private final ApplicationContext m_applicationContext;
	private ConcurrentServer m_concurrentServer;

	private void socketConsumerCallback(Socket socket)
	{
		try (var outputStream = socket.getOutputStream()) {
			outputStream.write(SOCKET_CONSUMER_MESSAGE.getBytes());

		} catch (IOException ex) {
			log.error("socketConsumerCallback : {}", ex.getMessage());
		}
	}

	private void exceptionConsumerCallback(char[] expectedCharArray)
	{
		IntStream.range(0, EXCEPTION_CONSUMER_MESSAGE.length())
				.forEach(i -> expectedCharArray[i] = EXCEPTION_CONSUMER_MESSAGE.charAt(i));
	}

	private void initRunnableCallback(char[] expectedCharArray)
	{
		IntStream.range(0, INIT_RUNNABLE_MESSAGE.length())
				.forEach(i -> expectedCharArray[i] = INIT_RUNNABLE_MESSAGE.charAt(i));
	}

	private void beforeAcceptCallback(char[] expectedCharArray)
	{
		IntStream.range(0, BEFORE_ACCEPT_MESSAGE.length())
				.forEach(i -> expectedCharArray[i] = BEFORE_ACCEPT_MESSAGE.charAt(i));
	}

	private void delayThread()
	{
		try {
			Thread.sleep(200);
		} catch (InterruptedException ignored) {}
	}
	@Autowired
    ConcurrentServerTest(ApplicationContext applicationContext)
	{
        m_applicationContext = applicationContext;
    }

	@BeforeEach
	public void setUp()
	{
		m_concurrentServer = m_applicationContext.getBean(ConcurrentServer.class);
	}

	@AfterEach
	public void tearDown()
	{
		m_concurrentServer.stop();
	}

	@Order(1)
	@Test
	public void createAndStartServerWithDefaultPortAndBacklog_ThenConnectTest()
	{
		m_concurrentServer.start();
		delayThread();

		try (var socket = new Socket("localhost", DEFAULT_PORT)) {

			Assertions.assertEquals(LOCAL_HOST + ":" + DEFAULT_PORT,
					socket.getInetAddress().getHostAddress() + ":" + socket.getPort());

		} catch (IOException ex) {
			log.error("createAndStartServerWithDefaultPortAndBacklog_ThenConnectTest: {}",ex.getMessage());
		}

	}

	@Order(2)
	@Test
	public void createAndStartServerWithCustomizedPortAndBacklog_ThenConnectTest()
	{
		m_concurrentServer.setPort(CUSTOMIZED_PORT)
				.setBacklog(CUSTOMIZED_BACKLOG)
				.start();

		delayThread();

		try (var socket = new Socket("localhost", CUSTOMIZED_PORT)) {

			Assertions.assertEquals(LOCAL_HOST + ":" + CUSTOMIZED_PORT,
					socket.getInetAddress().getHostAddress() + ":" + socket.getPort());

		} catch (IOException ex) {
			log.error("createAndStartServerWithCustomizedPortAndBacklog_ThenConnectTest: {}",ex.getMessage());
		}
	}

	@Order(3)
	@Test
	public void createAndStartServerWithBeforeAcceptRunnable_ThenConnectAndCheckRunnableTest()
	{
		var expectedCharArray = new char[BEFORE_ACCEPT_MESSAGE.length()];

		m_concurrentServer.setBeforeAcceptRunnable(() -> beforeAcceptCallback(expectedCharArray)).start();
		delayThread();

		try (var socket = new Socket("localhost", DEFAULT_PORT)) {
			Assertions.assertArrayEquals(BEFORE_ACCEPT_MESSAGE.toCharArray(), expectedCharArray);

		} catch (IOException ex) {
			log.error("createAndStartServerWithBeforeAcceptRunnable_ThenConnectTest: {}",ex.getMessage());
		}
	}

	@Order(4)
	@Test
	public void createAndStartServerWithInitRunnable_ThenConnectAndCheckRunnableTest()
	{
		var expectedCharArray = new char[INIT_RUNNABLE_MESSAGE.length()];

		m_concurrentServer.setInitRunnable(() -> initRunnableCallback(expectedCharArray)).start();
		delayThread();

		try (var socket = new Socket("localhost", DEFAULT_PORT)) {
			Assertions.assertArrayEquals(INIT_RUNNABLE_MESSAGE.toCharArray(), expectedCharArray);

		} catch (IOException ex) {
			log.error("createAndStartServerWithInitRunnable_ThenConnectTest: {}",ex.getMessage());
		}
	}

	@Order(5)
	@Test
	public void createAndStartServerWithSocketConsumer_ThenConnectAndConfirmCommunicationTest()
	{
		m_concurrentServer.setClientSocketConsumer(this::socketConsumerCallback).start();
		delayThread();

		try (var socket = new Socket("localhost", DEFAULT_PORT)) {
			Assertions.assertArrayEquals(SOCKET_CONSUMER_MESSAGE.getBytes(),
					socket.getInputStream().readAllBytes());

		} catch (IOException ex) {
			log.error("createAndStartServerWithSocketConsumer_ThenConnectTest: {}",ex.getMessage());
		}

	}

	@Order(6)
	@Test
	public void createAndStartServerWithExceptionConsumer_ThenConnectAndStopServerWithExceptionTest()
	{
		var expectedCharArray = new char[EXCEPTION_CONSUMER_MESSAGE.length()];
		m_concurrentServer.setServerExceptionConsumer(ignored -> exceptionConsumerCallback(expectedCharArray))
				.start();

		delayThread();

		try (var socket = new Socket("localhost", DEFAULT_PORT)) {
			m_concurrentServer.stop();
			delayThread();
			Assertions.assertArrayEquals(EXCEPTION_CONSUMER_MESSAGE.toCharArray(), expectedCharArray);

		} catch (IOException ex) {
			log.error("createAndStartServerWithExceptionConsumer_ThenConnectTest: {}",ex.getMessage());
		}
	}

	@Order(7)
	@Test
	public void createAndStartServerWithAllFeatures_ThenConnectAndCheckResultsTest()
	{
		var expectedInitRunnableMessageArray = new char[INIT_RUNNABLE_MESSAGE.length()];
		var expectedBeforeAcceptRunnableMessageArray = new char[BEFORE_ACCEPT_MESSAGE.length()];

		m_concurrentServer.setPort(CUSTOMIZED_PORT)
						.setBacklog(CUSTOMIZED_BACKLOG)
						.setBeforeAcceptRunnable(() -> beforeAcceptCallback(expectedBeforeAcceptRunnableMessageArray))
				 		.setInitRunnable(() -> initRunnableCallback(expectedInitRunnableMessageArray))
						.setClientSocketConsumer(this::socketConsumerCallback)
						.start();

		delayThread();

		try (var socket = new Socket("localhost", CUSTOMIZED_PORT)) {
			Assertions.assertArrayEquals(INIT_RUNNABLE_MESSAGE.toCharArray(), expectedInitRunnableMessageArray);
			Assertions.assertArrayEquals(BEFORE_ACCEPT_MESSAGE.toCharArray(), expectedBeforeAcceptRunnableMessageArray);
			Assertions.assertArrayEquals(SOCKET_CONSUMER_MESSAGE.getBytes(), socket.getInputStream().readAllBytes());

		} catch (IOException ex) {
			log.error("createAndStartServerWithAllFeatures_ThenConnectAndStopServerWithExceptionTest: {}",ex.getMessage());
		}
	}

	@Order(8)
	@Test
	public void createAndStartServerWithDefaultPortAndBacklog_ThenConnectWithMultipleClients()
	{
		m_concurrentServer.start();
		delayThread();

		IntStream.range(0, 10).forEach(ignored -> {

			try(var socket = new Socket("localhost", DEFAULT_PORT)) {
				Assertions.assertEquals(LOCAL_HOST + ":" + DEFAULT_PORT,
						socket.getInetAddress().getHostAddress() + ":" + socket.getPort());

			} catch (IOException ex) {
				log.error("createAndStartServerWithDefaultPortAndBacklog_ThenConnectWithMultipleClients: {}",ex.getMessage());
			}
		});
	}

}
