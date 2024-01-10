package site.ugaeng.localhosting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Application 실행 option 매핑 테스트")
class HostingArgsTest {

    @Test
    void option_mapping() {
        // given
        String arguments = "-p 8080 -name domain -timeout 30";
        String[] args = arguments.split(" ");

        // when
        HostingArgs params = new HostingArgs(args);

        // then
        assertAll(
                () -> assertThat(params.getHostingPort()).isEqualTo(8080),
                () -> assertThat(params.getDomainName()).isEqualTo("domain")
        );

    }
}