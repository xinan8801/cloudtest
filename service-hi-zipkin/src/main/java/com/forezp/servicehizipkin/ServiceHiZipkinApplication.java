package com.forezp.servicehizipkin;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@RestController
public class ServiceHiZipkinApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceHiZipkinApplication.class, args);
	}

	private static final Logger LOG = Logger.getLogger(ServiceHiZipkinApplication.class.getName());

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}

	@RequestMapping("hi")
	public String callHome(){
		LOG.log(Level.INFO,"calling trace service-hi-zipkin   ");
		return restTemplate.getForObject("http://localhost:8989/miya",String.class);
	}

	@RequestMapping("/info")
	public String info(){
		LOG.log(Level.INFO, "calling trace service-hi-zipkin ");

		return "i'm service-hi-zipkin";

	}

	@Bean
	public AlwaysSampler defaultSampler(){
		return new AlwaysSampler();
	}
}