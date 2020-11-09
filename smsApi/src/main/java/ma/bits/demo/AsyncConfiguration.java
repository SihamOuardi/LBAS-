package ma.bits.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import ma.bits.demo.dao.EpsSmsLogRepository;
import ma.bits.demo.entities.EpsSmsLog;

import java.util.List;
import java.util.concurrent.Executor;
@Configuration
@EnableAsync
public class AsyncConfiguration {
	   
    @Value("${corePoolSize}")
    private int corePoolSize;
    @Value("${maxPoolSize}")
    private int maxPoolSize;
    @Value("${queueCapacity}")
    private int queueCapacity;
    @Autowired	
	EpsSmsLogRepository smsRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncConfiguration.class);
    @Bean (name = "taskExecutor")
    public Executor taskExecutor() {
        LOGGER.debug("Creating Async Task Executor");
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		// List<EpsSmsLog> list= smsRepository.getSmsLogsToBeSend('N','U');
		// queueCapacity=list.size();
		 System.out.println("queueCapacity= "+queueCapacity);
        System.out.println("*****************configuration threads***********************************");
        System.out.println(corePoolSize+" "+maxPoolSize+" "+queueCapacity);
        
     /*   @Bean
        public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory(AmazonSQSAsync amazonSQS) {
            SimpleMessageListenerContainerFactory factory = new SimpleMessageListenerContainerFactory();
            factory.setAmazonSqs(amazonSQS);
            factory.setMaxNumberOfMessages(10);
            factory.setAutoStartup(true);
            factory.setWaitTimeOut(20);

            return factory;
        }*/
        
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);       
        executor.setThreadNamePrefix("SmsThread-");
        executor.setAllowCoreThreadTimeOut(false);
        executor.initialize();
      //  executor.execute(, startTimeout);
        System.out.println("*****************END configuration threads***********************************");
        return executor;
    }
}