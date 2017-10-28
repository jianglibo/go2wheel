package com.go2wheel.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.katharsis.repository.filter.AbstractDocumentFilter;
import io.katharsis.repository.filter.DocumentFilterChain;
import io.katharsis.repository.filter.DocumentFilterContext;
import io.katharsis.repository.response.Response;
import io.katharsis.validation.ValidationModule;
import zipkin.reporter.Reporter;

@Configuration
public class KatharsisModuleConfig {
	
//	@Bean
//	public BraveModule braveModule() {
//		String serviceName = "exampleApp";
//		Endpoint localEndpoint = Endpoint.builder().serviceName(serviceName).build();
//		InheritableServerClientAndLocalSpanState spanState = new InheritableServerClientAndLocalSpanState(localEndpoint);
//		Brave.Builder builder = new Brave.Builder(spanState);
//		builder = builder.reporter(new LoggingReporter());
//		Brave brave = builder.build();
//		return BraveModule.newServerModule(brave);
//	}
	
	@Bean
	public ValidationModule validationModule() {
		return ValidationModule.newInstance();
	}
	
	public final class LoggingReporter implements Reporter<zipkin.Span> {

		private Logger logger = LoggerFactory.getLogger("jianglibo.nutchbuilder.traceReporter");

		@Override
		public void report(zipkin.Span span) {
			logger.info(span.toString());
		}
	}
	
//	@Bean
//	public MyDFilterModule1 myDFilterModule1() {
//		return new MyDFilterModule1();
//	}
//	
//	
//	@Bean
//	public MyDFilterModule myDFilterModule() {
//		return new MyDFilterModule();
//	}
	
	public static class MyDFilterModule extends AbstractDocumentFilter {

		@SuppressWarnings("unused")
		private DocumentFilterContext filterRequestContext;
		@SuppressWarnings("unused")
		private DocumentFilterChain chain;
		
		@Override
		public Response filter(DocumentFilterContext filterRequestContext, DocumentFilterChain chain) {
			@SuppressWarnings("unused")
			String a = "5";
			return super.filter(filterRequestContext, chain);
		}
		
	}
	
	public static class MyDFilterModule1 extends AbstractDocumentFilter {

		@SuppressWarnings("unused")
		private DocumentFilterContext filterRequestContext;
		@SuppressWarnings("unused")
		private DocumentFilterChain chain;
		
		@Override
		public Response filter(DocumentFilterContext filterRequestContext, DocumentFilterChain chain) {
			@SuppressWarnings("unused")
			String a = "6";
			return super.filter(filterRequestContext, chain);
		}
		
	}
}
