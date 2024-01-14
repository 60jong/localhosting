package site.ugaeng.localhosting.env;

import lombok.Getter;

@Getter
public class ClientArgs {

    private int hostingPort;
    private String domainName;

    public ClientArgs(String[] args) {

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-p")) {
                this.hostingPort = Integer.parseInt(args[i + 1]);
            }

            if (args[i].equals("-name")) {
                this.domainName = args[i + 1];
            }
        }
    }
}
