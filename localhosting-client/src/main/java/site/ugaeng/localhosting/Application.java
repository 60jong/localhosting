package site.ugaeng.localhosting;

import lombok.extern.slf4j.Slf4j;
import site.ugaeng.localhosting.env.*;
import site.ugaeng.localhosting.exception.LocalhostingException;
import site.ugaeng.localhosting.exception.SocketClosedException;
import site.ugaeng.localhosting.forward.RequestForwardingTemplates;

import java.io.*;

@Slf4j
public class Application {

    public static void main(String[] args) {
        printLogoMessage();

        configure(args);

        try
        {
            var forwardingTemplate = RequestForwardingTemplates.getHttpRequestForwardingTemplate();

            log.info("Request Forwarding starts");
            forwardingTemplate.run();
        }
        catch (SocketClosedException socketClosedException)
        {
            log.error("Local Socket Closed from Tunneling server");
        }
        catch (LocalhostingException exception)
        {
            log.error("LocalhostingException = {}", exception);
        }
        finally
        {
            closeAllClosable();
            log.info("Safely Ended Localhosting");
        }
    }

    private static void closeAllClosable() {
        TunnelingServerConnector.closeConnection();
    }

    private static void printLogoMessage() {
        System.out.println(Message.logoMessage);
    }

    private static void configure(String[] args) {
        configEnvironmentProperties(args);
    }

    private static void configEnvironmentProperties(String[] args) {
        ClientArgs clientArgs = new ClientArgs(args);
        ServerArgs serverArgs = getServerArgs();

        // set Environment
        Environment.init(new HostingArgs(clientArgs, serverArgs));
    }

    private static ServerArgs getServerArgs() {
        return new ServerArgs("15.164.62.252:8080", "15.164.62.252:9000");
//        try (InputStream systemArgsReader = getServerArgsReader()) {
//            return new Yaml().load(systemArgsReader);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    private static InputStream getServerArgsReader() throws FileNotFoundException {
        final String systemEnvFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\localhosting.yml";

        return new FileInputStream(systemEnvFilePath);
    }
}