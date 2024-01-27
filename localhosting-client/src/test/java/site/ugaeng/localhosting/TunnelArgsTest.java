package site.ugaeng.localhosting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site.ugaeng.localhosting.env.ClientArgs;

import java.io.IOException;
import java.net.Socket;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Application 실행 option 매핑 테스트")
class TunnelArgsTest {

    @Test
    void option_mapping() {
        // given
        String arguments = "-p 8080 -name domain -timeout 30";
        String[] args = arguments.split(" ");

        // when
        ClientArgs params = new ClientArgs(args);

        // then
        assertAll(
                () -> assertThat(params.getLocalProcessPort()).isEqualTo(8080),
                () -> assertThat(params.getTunnelName()).isEqualTo("domain")
        );

    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("15.164.62.252", 8080);
        System.out.println(socket.getKeepAlive());
    }
}