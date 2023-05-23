package pro.vaidas.notebookclient.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.client.RestTemplateBuilderConfigurer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class RestTemplateBuilderConfig {

    @Value("${rest.template.rootUrl}")
    String rootUrl;

//    @Bean
//    OAuth2AuthorizedClientManager  auth2AuthorizedClientManager(
//            ClientRegistrationRepository clientRegistrationRepository,
//            OAuth2AuthorizedClientService oAuth2AuthorizedClientService){
//
//        var authorizedClientProvider = OAuth2AuthorizedClientProviderBuilder.builder()
//                .clientCredentials()
//                .build();
//
//        var authorizedClientManager = new AuthorizedClientServiceOAuth2AuthorizedClientManager
//                (clientRegistrationRepository, oAuth2AuthorizedClientService);
//
//        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);
//        return authorizedClientManager;
//    }
//


@Bean
RestTemplateBuilder restTemplateBuilder(RestTemplateBuilderConfigurer configurer
//        ,
//                                        OAuthClientInterceptor interceptor
){
        assert rootUrl != null;

        return configurer.configure(new RestTemplateBuilder())
//                .additionalInterceptors(interceptor)
                .uriTemplateHandler(new DefaultUriBuilderFactory(rootUrl));
    }
}
