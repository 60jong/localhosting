package site.ugaeng.localhosting;

import lombok.Getter;

@Getter
public class ApplicationParams {

    private int localPort;
    private String domainName;
    private int timeout;

    public ApplicationParams(String[] args) {

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-p")) {
                this.localPort = Integer.parseInt(args[i + 1]);
            }

            if (args[i].equals("-name")) {
                this.domainName = args[i + 1];
            }

            if (args[i].equals("-timeout")) {
                this.timeout = Integer.parseInt(args[i + 1]);
            }
        }
    }
}
