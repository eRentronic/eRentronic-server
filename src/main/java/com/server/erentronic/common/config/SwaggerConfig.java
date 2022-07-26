package com.server.erentronic.common.config;

import com.fasterxml.classmate.TypeResolver;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

	public static final String APPLICATION_JSON_CHARSET_UTF_8 = "application/json;charset=UTF-8";
	public static final String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";

	private final TypeResolver typeResolver;

	private ApiInfo swaggerInfo() {
		return new ApiInfoBuilder().title("eRentronic API")
			.description("eRentronic API Docs").build();
	}

	@Bean
	public Docket swaggerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
			.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(Pageable.class),
				typeResolver.resolve(Page.class)))
			.consumes(getConsumeContentTypes())
			.produces(getProduceContentTypes())
			.apiInfo(swaggerInfo())
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.server.erentronic"))
			.build()
			.useDefaultResponseMessages(false);
	}

	private Set<String> getConsumeContentTypes() {
		return new HashSet<>(List.of(APPLICATION_JSON_CHARSET_UTF_8,
			APPLICATION_X_WWW_FORM_URLENCODED));
	}

	private Set<String> getProduceContentTypes() {
		return new HashSet<>(List.of(APPLICATION_JSON_CHARSET_UTF_8));
	}

	@Getter
	@Setter
	@ApiModel
	private static class Page {

		@ApiModelProperty(value = "페이지 번호(0..N)")
		private Integer page;

		@ApiModelProperty(value = "페이지 크기", allowableValues = "range[0, 100]")
		private Integer size;

		@ApiModelProperty(value = "정렬(사용법: 컬럼명,ASC|DESC)")
		private List<String> sort;
	}
}
