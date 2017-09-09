package application;

import java.util.Collections;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerConfiguration {
	
	@Bean(name = "org.dozer.Mapper")
	public DozerBeanMapper mapper() {
		DozerBeanMapper dozerBean = new DozerBeanMapper();
		dozerBean.setMappingFiles(Collections.singletonList("dozerMappings.xml"));
		return dozerBean;
	}
}
