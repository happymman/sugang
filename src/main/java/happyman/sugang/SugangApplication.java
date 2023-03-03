package happyman.sugang;

import happyman.sugang.config.MybatisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(MybatisConfig.class)
@SpringBootApplication(scanBasePackages = "happyman.sugang")
public class SugangApplication {

	public static void main(String[] args) {
		SpringApplication.run(SugangApplication.class, args);
	}

}
