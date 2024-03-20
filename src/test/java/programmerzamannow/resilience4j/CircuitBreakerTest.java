package programmerzamannow.resilience4j;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class CircuitBreakerTest {

    public void callMe(){
        log.info("Call me");
        throw new IllegalArgumentException("Ups");
    }

    @Test
    void circuitBreaker() {
    //default 100 kalau 50% error maka state OPEN
        CircuitBreaker circuitBreaker = CircuitBreaker.ofDefaults("pzn");

        for (int i = 0; i < 200; i++) {
            try {
                Runnable runnable = CircuitBreaker.decorateRunnable(circuitBreaker, () -> callMe());
                runnable.run();
            }catch (Exception e){
                log.error("Error : {}",e.getMessage());
            }
        }

    }
}
