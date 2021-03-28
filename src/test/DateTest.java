package test;

import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * 有时间待完善的1.8时间相关测试
 */
public class DateTest {

    @Test
    public void test1() {
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

    @Test
    public void test2() {
        System.out.println(Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles")).getTime());
    }
}
