package site.ugaeng.localhosting.env;

import lombok.Getter;

@Getter
public class ClientArgs {

    private int localProcessPort;
    private String tunnelName;

    public ClientArgs(String[] args) {

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-p")) {
                this.localProcessPort = Integer.parseInt(args[i + 1]);
            }

            if (args[i].equals("-name")) {
                this.tunnelName = args[i + 1];
            }
        }
    }
}
