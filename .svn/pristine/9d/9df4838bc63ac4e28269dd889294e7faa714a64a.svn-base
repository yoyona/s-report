package net.greatsoft.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;


@SpringBootApplication
public class StartApplication{
	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}
	/**
	 * 设置超时时间
	 * @return
	 * @since 2018-10-26
	 */
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(ConfigurableEmbeddedServletContainer container) {				
				container.setSessionTimeout(1800);// 单位为S
			}
		};
	}

}
