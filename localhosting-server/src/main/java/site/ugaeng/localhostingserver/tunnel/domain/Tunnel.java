package site.ugaeng.localhostingserver.tunnel.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Tunnel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Embedded
    private Address clientAddr;

    public Tunnel(String name, Address clientAddr) {
        this.name = name;
        this.clientAddr = clientAddr;
    }
}