package site.ugaeng.localhosting;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HostingArgs {

    private ClientArgs clientArgs;
    private ServerArgs serverArgs;
}
