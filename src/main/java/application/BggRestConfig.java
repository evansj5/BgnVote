package application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.client.RestTemplate;

import model.BggGameResponseModel;
import model.BggGamesResponseModel;
import model.BggThingResponseModel;
import model.BggThingsResponseModel;

@Configuration
public class BggRestConfig {
	
	@Bean(name = "resttemplate1")
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(getMarshallingHttpMessageConverter1());
        converters.add(new FormHttpMessageConverter());
        converters.add(new StringHttpMessageConverter());
        restTemplate.setMessageConverters(converters);
		return restTemplate;
	}
	
	@Bean(name = "resttemplate2")
	public RestTemplate getRestTemplate2() {
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(getMarshallingHttpMessageConverter2());
        converters.add(new FormHttpMessageConverter());
        converters.add(new StringHttpMessageConverter());
        restTemplate.setMessageConverters(converters);
		return restTemplate;
	}
	
	/**
     * @return MarshallingHttpMessageConverter object which is responsible for
     *         marshalling and unMarshalling process
     */
    @Bean(name = "marshallingHttpMessageConverter1")
    public MarshallingHttpMessageConverter getMarshallingHttpMessageConverter1() {
 
        MarshallingHttpMessageConverter marshallingHttpMessageConverter = new MarshallingHttpMessageConverter();
        marshallingHttpMessageConverter.setMarshaller(getJaxb2Marshaller1());
        marshallingHttpMessageConverter.setUnmarshaller(getJaxb2Marshaller1());
        return marshallingHttpMessageConverter;
    }
    
    /**
     * @return MarshallingHttpMessageConverter object which is responsible for
     *         marshalling and unMarshalling process
     */
    @Bean(name = "marshallingHttpMessageConverter2")
    public MarshallingHttpMessageConverter getMarshallingHttpMessageConverter2() {
 
        MarshallingHttpMessageConverter marshallingHttpMessageConverter = new MarshallingHttpMessageConverter();
        marshallingHttpMessageConverter.setMarshaller(getJaxb2Marshaller2());
        marshallingHttpMessageConverter.setUnmarshaller(getJaxb2Marshaller2());
        return marshallingHttpMessageConverter;
    }
	
	@Bean(name = "jaxb2Marshaller1")
	public Jaxb2Marshaller getJaxb2Marshaller1() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setClassesToBeBound(new Class<?>[] {
        		BggGameResponseModel.class,
        		BggGamesResponseModel.class
        });
        
        return jaxb2Marshaller;
	}
	
	@Bean(name = "jaxb2Marshaller2")
	public Jaxb2Marshaller getJaxb2Marshaller2() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setClassesToBeBound(new Class<?>[] {
        		BggThingResponseModel.class,
        		BggThingsResponseModel.class
        });
        
        return jaxb2Marshaller;
	}
}
