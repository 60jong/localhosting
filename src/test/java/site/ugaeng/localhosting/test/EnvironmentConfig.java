package site.ugaeng.localhosting.test;

import site.ugaeng.localhosting.env.ClientArgs;
import site.ugaeng.localhosting.env.HostingArgs;
import site.ugaeng.localhosting.env.ServerArgs;
import site.ugaeng.localhosting.env.Environment;

public class EnvironmentConfig {

    public static void setupEnvironment() {
        ClientArgs clientArgs = new ClientArgs(new String[] {"-p", "8080"});
        ServerArgs serverArgs = new ServerArgs("localhost:9000");
        HostingArgs hostingArgs = new HostingArgs(clientArgs, serverArgs);
        Environment.init(hostingArgs);
    }
}
