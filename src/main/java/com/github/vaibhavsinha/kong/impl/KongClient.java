package com.github.vaibhavsinha.kong.impl;

import com.github.vaibhavsinha.kong.api.admin.*;
import com.github.vaibhavsinha.kong.api.plugin.authentication.BasicAuthService;
import com.github.vaibhavsinha.kong.api.plugin.authentication.HmacAuthService;
import com.github.vaibhavsinha.kong.api.plugin.authentication.KeyAuthService;
import com.github.vaibhavsinha.kong.api.plugin.authentication.OAuth2Service;
import com.github.vaibhavsinha.kong.impl.helper.RetrofitServiceCreator;
import com.github.vaibhavsinha.kong.impl.service.plugin.authentication.BasicAuthServiceImpl;
import com.github.vaibhavsinha.kong.impl.service.plugin.authentication.HmacAuthServiceImpl;
import com.github.vaibhavsinha.kong.impl.service.plugin.authentication.KeyAuthServiceImpl;
import com.github.vaibhavsinha.kong.internal.admin.*;
import com.github.vaibhavsinha.kong.internal.plugin.authentication.RetrofitOAuth2Service;
import lombok.Data;

/**
 * Created by vaibhav on 12/06/17.
 */
@Data
public class KongClient {

    private ConsumerService consumerService;
    private ApiService apiService;
    private PluginService pluginService;
    private PluginRepoService pluginRepoService;
    private CertificateService certificateService;
    private SniService sniService;
    private UpstreamService upstreamService;
    private TargetService targetService;

    private BasicAuthService basicAuthService;
    private KeyAuthService keyAuthService;
    private HmacAuthService hmacAuthService;
    private OAuth2Service oAuth2Service;

    public KongClient(String adminUrl) {
        RetrofitServiceCreator creator = new RetrofitServiceCreator(adminUrl);

        consumerService = creator.create(ConsumerService.class, RetrofitConsumerService.class);
        apiService = creator.create(ApiService.class, RetrofitApiService.class);
        pluginService = creator.create(PluginService.class, RetrofitPluginService.class);
        pluginRepoService = creator.create(PluginRepoService.class, RetrofitPluginRepoService.class);

        certificateService = creator.create(CertificateService.class, RetrofitCertificateService.class);
        sniService = creator.create(SniService.class, RetrofitSniService.class);
        upstreamService = creator.create(UpstreamService.class, RetrofitUpstreamService.class);
        targetService = creator.create(TargetService.class, RetrofitTargetService.class);

        basicAuthService = new BasicAuthServiceImpl(adminUrl);
        keyAuthService = new KeyAuthServiceImpl(adminUrl);
        hmacAuthService = new HmacAuthServiceImpl(adminUrl);
        oAuth2Service = creator.create(OAuth2Service.class, RetrofitOAuth2Service.class);
    }

}
