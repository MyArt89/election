package cropx.election;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan("cropx")
@EntityScan("cropx")
@EnableJpaRepositories("cropx")
@EnableTransactionManagement
@SpringBootApplication
@Component
public class ElectionApplication
{
	@Autowired
	DataInitializer dataInitializer;
    public static void main(String[] args)
	{
		SpringApplication.run(ElectionApplication.class, args);
	}

	@Bean
	public void init()
	{
		dataInitializer.Initialize();
	}
}
