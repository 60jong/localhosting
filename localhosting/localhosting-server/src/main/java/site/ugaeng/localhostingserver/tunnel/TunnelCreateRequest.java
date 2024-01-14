package site.ugaeng.localhostingserver.tunnel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TunnelCreateRequest {

    @Nullable
    private String domainName;
}