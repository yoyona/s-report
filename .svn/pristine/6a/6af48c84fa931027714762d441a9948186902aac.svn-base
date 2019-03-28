package net.greatsoft.core;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
/**
 * @author :hn
 * @createDate:2016年6月3日
 * @description:打war包的步骤是：
 * 				1.将pom.xml中的packaging的jar改为war	
 * 				2.往pom.xml中添加节点：
 *					<dependency>
 *						<groupId>org.springframework.boot</groupId>
 *						<artifactId>spring-boot-starter-tomcat</artifactId>
 *						<scope>provided</scope>
 *					</dependency>
 *				3.写一个继承SpringBootServletInitializer的类，即当前类
 */
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(StartApplication.class);
	}

}
