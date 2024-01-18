package site.ugaeng.localhosting.env;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ServerArgs {

    private String proxyServerAddr;
    private String tunnelRegisterServerAddr;
}
