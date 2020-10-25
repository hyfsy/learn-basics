package test;

import java.time.Clock;
import java.time.Instant;
import java.util.Optional;
import java.util.TimeZone;

/**
 * 有时间待完善的1.8时间相关测试
 */
public class DateTest {

    public static void main(String[] args) {
        Instant now = Instant.now();
        System.out.println(now);

        System.out.println(System.currentTimeMillis());

        TimeZone timezone = TimeZone.getDefault();
        System.out.println(timezone);

        Clock clock = Clock.systemUTC();
        Clock clock1 = Clock.systemDefaultZone();
        System.out.println(clock);
        System.out.println(clock1);
    }
}
