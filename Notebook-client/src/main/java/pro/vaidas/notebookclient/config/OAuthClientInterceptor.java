package pro.vaidas.notebookclient.config;

//@Component
//public class OAuthClientInterceptor implements ClientHttpRequestInterceptor {

//    private final OAuth2AuthorizedClientManager manager;
//    private final Authentication principal;
//    private final ClientRegistration clientRegistration;
//
//    public OAuthClientInterceptor(OAuth2AuthorizedClientManager manager, ClientRegistrationRepository clientRegistrationRepository) {
//        this.manager = manager;
//        this.principal = createPrincipal();
//        this.clientRegistration = clientRegistrationRepository.findByRegistrationId("springauth");
//    }
//
//    @Override
//    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
//        OAuth2AuthorizeRequest oAuth2AuthorizeRequest = OAuth2AuthorizeRequest
//                .withClientRegistrationId(clientRegistration.getRegistrationId())
//                .principal(createPrincipal())
//                .build();
//
//        OAuth2AuthorizedClient client = manager.authorize(oAuth2AuthorizeRequest);
//
//        if (isNull(client)){
//            throw new IllegalStateException("Missing credentials");
//        }
//
//        request.getHeaders().add(HttpHeaders.AUTHORIZATION,
//                "Bearer " + client.getAccessToken().getTokenValue());
//
//        return execution.execute(request, body);
//    }
//
//    private Authentication createPrincipal() {
//        return new Authentication() {
//            @Override
//            public Collection<? extends GrantedAuthority> getAuthorities() {
//                 return Collections.emptySet();
//            }
//
//            @Override
//            public Object getCredentials() {
//                return null;
//            }
//
//            @Override
//            public Object getDetails() {
//                return null;
//            }
//
//            @Override
//            public Object getPrincipal() {
//                return null;
//            }
//
//            @Override
//            public boolean isAuthenticated() {
//                return false;
//            }
//
//            @Override
//            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
//
//            }
//
//            @Override
//            public String getName() {
//                return null;
//            }
//        };
//    }
//}
