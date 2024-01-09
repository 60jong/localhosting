package site.ugaeng.localhosting.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void null_string() {
        // given
        String text = null;

        // when
        boolean hasText = StringUtils.hasText(text);

        // then
        Assertions.assertThat(hasText).isFalse();
    }

    @Test
    void empty_string() {
        // given
        String text = "";

        // when
        boolean hasText = StringUtils.hasText(text);

        // then
        Assertions.assertThat(hasText).isFalse();
    }

    @Test
    void valid_string() {
        // given
        String text = "text";

        // when
        boolean hasText = StringUtils.hasText(text);

        // then
        Assertions.assertThat(hasText).isTrue();
    }
}