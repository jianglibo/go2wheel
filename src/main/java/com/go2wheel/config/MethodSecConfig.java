package com.go2wheel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;


/**
 * securedEnabled = true, @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
 * prePostEnabled = true @PreAuthorize("isAnonymous()")
 * @author jianglibo@gmail.com
 *
 */

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass=true)
public class MethodSecConfig extends GlobalMethodSecurityConfiguration {
    
//    @Autowired
//    private MutableAclService aclService;
    
    /**
     * copy from super.
     */
//    @Override
//    protected AfterInvocationManager afterInvocationManager() {
//        AfterInvocationProviderManager invocationProviderManager = new AfterInvocationProviderManager();
//        ExpressionBasedPostInvocationAdvice postAdvice = new ExpressionBasedPostInvocationAdvice(getExpressionHandler());
//        PostInvocationAdviceProvider postInvocationAdviceProvider = new PostInvocationAdviceProvider(postAdvice);
//        List<AfterInvocationProvider> afterInvocationProviders = new ArrayList<AfterInvocationProvider>();
//        afterInvocationProviders.add(postInvocationAdviceProvider);
//        invocationProviderManager.setProviders(afterInvocationProviders);
//        return invocationProviderManager;
//    }
    
//    @Override
//    protected MethodSecurityExpressionHandler createExpressionHandler() {
//        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
//        expressionHandler.setPermissionEvaluator(new AclPermissionEvaluator(aclService));
//        return expressionHandler;
//    }
}

