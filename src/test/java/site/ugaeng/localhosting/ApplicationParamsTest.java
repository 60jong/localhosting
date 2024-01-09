package site.ugaeng.localhosting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Application 실행 option 매핑 테스트")
class ApplicationParamsTest {

    @Test
    void option_mapping() {
        // given
        String arguments = "-p 8080 -name domain -timeout 30";
        String[] args = arguments.split(" ");

        // when
        ApplicationParams params = new ApplicationParams(args);

        // then
        assertAll(
                () -> assertThat(params.getLocalPort()).isEqualTo(8080),
                () -> assertThat(params.getDomainName()).isEqualTo("domain"),
                () -> assertThat(params.getTimeout()).isEqualTo(30)
        );

    }
}