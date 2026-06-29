package com.saumrit.myspringbootwithjpa.configuration;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;

@Configuration
public class MyRabbitMqConfiguration {

//    private AmqpAdmin amqpAdmin;
//    @Value("${spring.rabbitmq.host}")
//    private String host;
//    @Value("${spring.rabbitmq.port}")
//    private String port;
//    @Value("${spring.rabbitmq.username}")
//    private String username;
//    @Value("${spring.rabbitmq.password}")
//    private String pass;
//    @Value("${spring.rabbitmq.virtual-host}")
//    private String virtualHost;


    public boolean startMyRabbit() throws NoSuchAlgorithmException {
//        AmqpAdmin amqpAdmin1= buildAmqpAdmin(host,port,username,pass,virtualHost);
//        TopicExchange myExchange= new TopicExchange("edu");
//        amqpAdmin1.declareExchange(myExchange);
//        return true;
//    }

//    @Bean
//    public boolean startMyRabbit(org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory) {
//        try {
//            // This forces a connection check using the correct credentials
//
//            connectionFactory.createConnection().close();
//            System.out.println("Successfully connected to RabbitMQ!");
//            return true;
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to connect to RabbitMQ", e);
//        }
//    }


//    public AmqpAdmin buildAmqpAdmin(String host, String port, String username, String pass, String virtualHost) throws NoSuchAlgorithmException{
//        ConnectionFactory connectionFactory= new ConnectionFactory();
//        connectionFactory.setHost(host);
//        connectionFactory.setPort(Integer.parseInt(port));
//        connectionFactory.setUsername(username);
//        connectionFactory.setPassword(pass);
//        connectionFactory.setVirtualHost(virtualHost);
//
//        CachingConnectionFactory cachingConnectionFactory= new CachingConnectionFactory(connectionFactory);
//        this.amqpAdmin= new RabbitAdmin(cachingConnectionFactory);
//        return this.amqpAdmin;
//    }

//    @Bean
//    ApplicationRunner init(RabbitTemplate rabbitTemplate) {
//        return args -> rabbitTemplate.getConnectionFactory().createConnection().close();
//    }
        return true;

    }

//    @Bean
//    public TopicExchange eduExchange() {
//        return new TopicExchange("edu");
//    }
//
//    // 2. Define the Queue
//    @Bean
//    public Queue studentQueue() {
//        return new Queue("student.add.queue", true); // true = durable queue
//    }
//
//    // 3. Bind them together using your Routing Key
//    @Bean
//    public Binding binding(Queue studentQueue, TopicExchange eduExchange) {
//        return BindingBuilder.bind(studentQueue)
//                .to(eduExchange)
//                .with("saumrit.edu.student.add")
//                ;
//    }
}
