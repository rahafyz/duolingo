//package com.mapsa.duolingo.course;
//
//import com.google.common.collect.Lists;
//import com.snappbox.client.AppVersionDataTest;
//import com.snappbox.client.api.dto.AppVersionDTO;
//import com.snappbox.client.api.enumeration.AppVersionStatus;
//import com.snappbox.client.api.enumeration.ClientType;
//import com.snappbox.client.api.v1.AppVersionResource;
//import com.snappbox.client.domain.AppVersion;
//import com.snappbox.client.domain.Client;
//import com.snappbox.client.exception.handler.GeneralExceptionHandler;
//import com.snappbox.client.repository.AppVersionRepository;
//import com.snappbox.client.repository.ClientRepository;
//import com.snappbox.client.service.AppVersionService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.validation.Validator;
//
//import java.util.List;
//
//import static com.snappbox.client.AppVersionDataTest.*;
//import static org.hamcrest.Matchers.is;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@SpringBootTest(classes = EmbeddedRedisConfiguration.class)
//class AppVersionIntTests {
//
//    private MockMvc appVersionMvc;
//
//    @Autowired
//    private AppVersionService appVersionService;
//
//    @Autowired
//    private AppVersionRepository appVersionRepository;
//
//    @Autowired
//    private ClientRepository clientRepository;
//
//    @Autowired
//    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;
//
//    @Autowired
//    private MappingJackson2HttpMessageConverter jacksonMessageConverter;
//
//    @Autowired
//    private GeneralExceptionHandler exceptionTranslator;
//
//    @Autowired
//    private Validator validator;
//
//    private Client client;
//
//    @BeforeEach
//    public void setup() {
//        AppVersionResource feedbackController = new AppVersionResource(appVersionService);
//        this.appVersionMvc = MockMvcBuilders.standaloneSetup(feedbackController)
//                .setControllerAdvice(exceptionTranslator)
//                .setCustomArgumentResolvers(pageableArgumentResolver)
//                .setMessageConverters(jacksonMessageConverter)
//                .setValidator(validator).build();
//    }
//
//    @BeforeEach
//    void initTest() {
//        client = clientRepository.save(new Client()
//                .id(1L)
//                .name("BIKER")
//                .type(ClientType.BIKER));
//        appVersionRepository.deleteAll();
//    }
//
//    @Test
//    void createAppVersion() throws Exception {
//        AppVersionDTO appVersionDTO = createActiveAppVersionDto().setId(null);
//        long countBeforeAppVersion = appVersionRepository.count();
//        this.appVersionMvc.perform(post("/v1/app-version")
//                        .contentType(TestUtil.APPLICATION_JSON)
//                        .content(TestUtil.convertObjectToJsonBytes(appVersionDTO)))
//                .andExpect(status().isOk());
//        Long countAfterAppVersion = appVersionRepository.count();
//        List<AppVersion> appVersionList = Lists.newArrayList(appVersionRepository.findAll());
//        Assertions.assertEquals(countAfterAppVersion, countBeforeAppVersion + 1);
//        AppVersion appVersion = appVersionList.get(appVersionList.size() - 1);
//        assertThat(appVersion.getVersionUrl()).isEqualTo(appVersionDTO.getVersionUrl());
//        assertThat(appVersion.getVersionCode()).isEqualTo(appVersionDTO.getVersionCode());
//        assertThat(appVersion.getCities()).isEqualTo(appVersionDTO.getCities());
//        assertThat(appVersion.getDeliveryCategories()).isEqualTo(appVersionDTO.getDeliveryCategories());
//        assertThat(appVersion.getTargetAppVersion()).isEqualTo(appVersionDTO.getTargetAppVersion());
//    }
//
//    @Test
//    void createAppVersion_should_throw_exception_when_client_not_found() throws Exception {
//        AppVersionDTO appVersionDTO = createActiveAppVersionDto().setId(null).setClientAppType(ClientType.JEK);
//        long countBeforeAppVersion = appVersionRepository.count();
//        this.appVersionMvc.perform(post("/v1/app-version")
//                        .contentType(TestUtil.APPLICATION_JSON)
//                        .content(TestUtil.convertObjectToJsonBytes(appVersionDTO)))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isNotFound());
//        Long countAfterAppVersion = appVersionRepository.count();
//        Assertions.assertEquals(countAfterAppVersion, countBeforeAppVersion);
//    }
//
//    @Test
//    @Transactional
//    void createAppVersion_should_throw_exception_when_version_is_duplicate() throws Exception {
//        AppVersionDTO appVersionDTO = createActiveAppVersionDto().setId(null);
//        appVersionRepository.saveAndFlush(createActiveAppVersion().client(client).id(null).
//                versionCode(appVersionDTO.getVersionCode()));
//
//        long countBeforeAppVersion = appVersionRepository.count();
//        this.appVersionMvc.perform(post("/v1/app-version")
//                        .contentType(TestUtil.APPLICATION_JSON)
//                        .content(TestUtil.convertObjectToJsonBytes(appVersionDTO)))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isBadRequest());
//        Long countAfterAppVersion = appVersionRepository.count();
//        Assertions.assertEquals(countAfterAppVersion, countBeforeAppVersion);
//    }
//
//    @Test
//    void createBlockAppVersion_should_throw_exception_when_block_message_and_date_is_Null() throws Exception {
//        AppVersionDTO appVersionDTO = createBlockAppVersionDto().setId(null).setBlockMessage(null).setBlockStartDate(null);
//        long countBeforeAppVersion = appVersionRepository.count();
//        this.appVersionMvc.perform(post("/v1/app-version")
//                        .contentType(TestUtil.APPLICATION_JSON)
//                        .content(TestUtil.convertObjectToJsonBytes(appVersionDTO)))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isBadRequest());
//        Long countAfterAppVersion = appVersionRepository.count();
//        Assertions.assertEquals(countAfterAppVersion, countBeforeAppVersion);
//    }
//
//    @Test
//    void createBlockAppVersion_should_throw_exception_when_block_message_is_Null() throws Exception {
//        AppVersionDTO appVersionDTO = createBlockAppVersionDto().setId(null).setBlockMessage(null);
//        long countBeforeAppVersion = appVersionRepository.count();
//        this.appVersionMvc.perform(post("/v1/app-version")
//                        .contentType(TestUtil.APPLICATION_JSON)
//                        .content(TestUtil.convertObjectToJsonBytes(appVersionDTO)))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isBadRequest());
//        Long countAfterAppVersion = appVersionRepository.count();
//        Assertions.assertEquals(countAfterAppVersion, countBeforeAppVersion);
//    }
//
//    @Test
//    void createBlockAppVersion_should_throw_exception_when_target_version_is_Null() throws Exception {
//        AppVersionDTO appVersionDTO = createBlockAppVersionDto().setId(null).setTargetAppVersion(null);
//        long countBeforeAppVersion = appVersionRepository.count();
//        this.appVersionMvc.perform(post("/v1/app-version")
//                        .contentType(TestUtil.APPLICATION_JSON)
//                        .content(TestUtil.convertObjectToJsonBytes(appVersionDTO)))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isBadRequest());
//        Long countAfterAppVersion = appVersionRepository.count();
//        Assertions.assertEquals(countAfterAppVersion, countBeforeAppVersion);
//    }
//
//    @Test
//    void createBlockAppVersion_should_throw_exception_when_version_code_greater_than_target_version() throws Exception {
//        AppVersionDTO appVersionDTO = createBlockAppVersionDto().setId(null);
//        appVersionDTO.setVersionCode(appVersionDTO.getTargetAppVersion() + 1);
//        long countBeforeAppVersion = appVersionRepository.count();
//        this.appVersionMvc.perform(post("/v1/app-version")
//                        .contentType(TestUtil.APPLICATION_JSON)
//                        .content(TestUtil.convertObjectToJsonBytes(appVersionDTO)))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isBadRequest());
//        Long countAfterAppVersion = appVersionRepository.count();
//        Assertions.assertEquals(countAfterAppVersion, countBeforeAppVersion);
//    }
//
//
//    @Test
//    void createBlockAppVersion_should_throw_exception_when_target_version_not_found() throws Exception {
//        AppVersionDTO appVersionDTO = createBlockAppVersionDto().setId(null).setTargetAppVersion(Integer.MAX_VALUE);
//        long countBeforeAppVersion = appVersionRepository.count();
//        this.appVersionMvc.perform(post("/v1/app-version")
//                        .contentType(TestUtil.APPLICATION_JSON)
//                        .content(TestUtil.convertObjectToJsonBytes(appVersionDTO)))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isNotFound());
//        Long countAfterAppVersion = appVersionRepository.count();
//        Assertions.assertEquals(countAfterAppVersion, countBeforeAppVersion);
//    }
//
//    @Test
//    @Transactional
//    void createBlockAppVersion_should_throw_exception_when_target_version_not_active() throws Exception {
//        AppVersionDTO appVersionDTO = createBlockAppVersionDto().setId(null);
//        appVersionRepository.saveAndFlush(createActiveAppVersion().status(AppVersionStatus.INVALID).client(client).id(null).
//                versionCode(appVersionDTO.getVersionCode()));
//
//        long countBeforeAppVersion = appVersionRepository.count();
//        this.appVersionMvc.perform(post("/v1/app-version")
//                        .contentType(TestUtil.APPLICATION_JSON)
//                        .content(TestUtil.convertObjectToJsonBytes(appVersionDTO)))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isNotFound());
//        Long countAfterAppVersion = appVersionRepository.count();
//        Assertions.assertEquals(countAfterAppVersion, countBeforeAppVersion);
//    }
//
//    @Test
//    @Transactional
//    void createBlockAppVersion() throws Exception {
//        AppVersionDTO appVersionDTO = createBlockAppVersionDto().setId(null);
//        appVersionDTO.setTargetAppVersion(appVersionDTO.getVersionCode() + 1);
//        appVersionRepository.saveAndFlush(createActiveAppVersion().client(client).id(null).
//                versionCode(appVersionDTO.getTargetAppVersion()));
//
//        long countBeforeAppVersion = appVersionRepository.count();
//        this.appVersionMvc.perform(post("/v1/app-version")
//                        .contentType(TestUtil.APPLICATION_JSON)
//                        .content(TestUtil.convertObjectToJsonBytes(appVersionDTO)))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isOk());
//        Long countAfterAppVersion = appVersionRepository.count();
//        Assertions.assertEquals(countAfterAppVersion, countBeforeAppVersion + 1);
//        List<AppVersion> appVersionList = Lists.newArrayList(appVersionRepository.findAll());
//        Assertions.assertEquals(countAfterAppVersion, countBeforeAppVersion + 1);
//        AppVersion appVersion = appVersionList.get(appVersionList.size() - 1);
//        assertThat(appVersion.getVersionUrl()).isEqualTo(appVersionDTO.getVersionUrl());
//        assertThat(appVersion.getVersionCode()).isEqualTo(appVersionDTO.getVersionCode());
//        assertThat(appVersion.getCities()).isEqualTo(appVersionDTO.getCities());
//        assertThat(appVersion.getDeliveryCategories()).isEqualTo(appVersionDTO.getDeliveryCategories());
//        assertThat(appVersion.getTargetAppVersion()).isEqualTo(appVersionDTO.getTargetAppVersion());
//
//    }
//
//
//    @Test
//    void createWarningAppVersion_should_throw_exception_when_block_message_and_date_is_Null() throws Exception {
//        AppVersionDTO appVersionDTO = createWarningAppVersionDto().setId(null).setWarningMessage(null).setWarningStartDate(null);
//        long countBeforeAppVersion = appVersionRepository.count();
//        this.appVersionMvc.perform(post("/v1/app-version")
//                        .contentType(TestUtil.APPLICATION_JSON)
//                        .content(TestUtil.convertObjectToJsonBytes(appVersionDTO)))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isBadRequest());
//        Long countAfterAppVersion = appVersionRepository.count();
//        Assertions.assertEquals(countAfterAppVersion, countBeforeAppVersion);
//    }
//
//    @Test
//    void createWarningAppVersion_should_throw_exception_when_block_message_is_Null() throws Exception {
//        AppVersionDTO appVersionDTO = createWarningAppVersionDto().setId(null).setWarningMessage(null);
//        long countBeforeAppVersion = appVersionRepository.count();
//        this.appVersionMvc.perform(post("/v1/app-version")
//                        .contentType(TestUtil.APPLICATION_JSON)
//                        .content(TestUtil.convertObjectToJsonBytes(appVersionDTO)))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isBadRequest());
//        Long countAfterAppVersion = appVersionRepository.count();
//        Assertions.assertEquals(countAfterAppVersion, countBeforeAppVersion);
//    }
//
//    @Test
//    void createWarningAppVersion_should_throw_exception_when_target_version_is_Null() throws Exception {
//        AppVersionDTO appVersionDTO = createWarningAppVersionDto().setId(null).setTargetAppVersion(null);
//        long countBeforeAppVersion = appVersionRepository.count();
//        this.appVersionMvc.perform(post("/v1/app-version")
//                        .contentType(TestUtil.APPLICATION_JSON)
//                        .content(TestUtil.convertObjectToJsonBytes(appVersionDTO)))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isBadRequest());
//        Long countAfterAppVersion = appVersionRepository.count();
//        Assertions.assertEquals(countAfterAppVersion, countBeforeAppVersion);
//    }
//
//    @Test
//    void createWarningAppVersion_should_throw_exception_when_version_code_greater_than_target_version() throws Exception {
//        AppVersionDTO appVersionDTO = createWarningAppVersionDto().setId(null);
//        appVersionDTO.setVersionCode(appVersionDTO.getTargetAppVersion() + 1);
//        long countBeforeAppVersion = appVersionRepository.count();
//        this.appVersionMvc.perform(post("/v1/app-version")
//                        .contentType(TestUtil.APPLICATION_JSON)
//                        .content(TestUtil.convertObjectToJsonBytes(appVersionDTO)))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isBadRequest());
//        Long countAfterAppVersion = appVersionRepository.count();
//        Assertions.assertEquals(countAfterAppVersion, countBeforeAppVersion);
//    }
//
//
//    @Test
//    void createWarningAppVersion_should_throw_exception_when_target_version_not_found() throws Exception {
//        AppVersionDTO appVersionDTO = createWarningAppVersionDto().setId(null).setTargetAppVersion(Integer.MAX_VALUE);
//        long countBeforeAppVersion = appVersionRepository.count();
//        this.appVersionMvc.perform(post("/v1/app-version")
//                        .contentType(TestUtil.APPLICATION_JSON)
//                        .content(TestUtil.convertObjectToJsonBytes(appVersionDTO)))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isNotFound());
//        Long countAfterAppVersion = appVersionRepository.count();
//        Assertions.assertEquals(countAfterAppVersion, countBeforeAppVersion);
//    }
//
//    @Test
//    @Transactional
//    void createWarningAppVersion_should_throw_exception_when_target_version_not_active() throws Exception {
//        AppVersionDTO appVersionDTO = createWarningAppVersionDto().setId(null);
//        appVersionRepository.saveAndFlush(createActiveAppVersion().status(AppVersionStatus.INVALID).client(client).id(null).
//                versionCode(appVersionDTO.getVersionCode()));
//
//        long countBeforeAppVersion = appVersionRepository.count();
//        this.appVersionMvc.perform(post("/v1/app-version")
//                        .contentType(TestUtil.APPLICATION_JSON)
//                        .content(TestUtil.convertObjectToJsonBytes(appVersionDTO)))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isNotFound());
//        Long countAfterAppVersion = appVersionRepository.count();
//        Assertions.assertEquals(countAfterAppVersion, countBeforeAppVersion);
//    }
//
//    @Test
//    @Transactional
//    void createWarningAppVersion() throws Exception {
//        AppVersionDTO appVersionDTO = createWarningAppVersionDto().setId(null);
//        appVersionDTO.setTargetAppVersion(appVersionDTO.getVersionCode() + 1);
//        appVersionRepository.saveAndFlush(createActiveAppVersion().client(client).id(null).
//                versionCode(appVersionDTO.getTargetAppVersion()));
//
//        long countBeforeAppVersion = appVersionRepository.count();
//        this.appVersionMvc.perform(post("/v1/app-version")
//                        .contentType(TestUtil.APPLICATION_JSON)
//                        .content(TestUtil.convertObjectToJsonBytes(appVersionDTO)))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isOk());
//        Long countAfterAppVersion = appVersionRepository.count();
//        Assertions.assertEquals(countAfterAppVersion, countBeforeAppVersion + 1);
//        List<AppVersion> appVersionList = Lists.newArrayList(appVersionRepository.findAll());
//        Assertions.assertEquals(countAfterAppVersion, countBeforeAppVersion + 1);
//        AppVersion appVersion = appVersionList.get(appVersionList.size() - 1);
//        assertThat(appVersion.getVersionUrl()).isEqualTo(appVersionDTO.getVersionUrl());
//        assertThat(appVersion.getVersionCode()).isEqualTo(appVersionDTO.getVersionCode());
//        assertThat(appVersion.getCities()).isEqualTo(appVersionDTO.getCities());
//        assertThat(appVersion.getDeliveryCategories()).isEqualTo(appVersionDTO.getDeliveryCategories());
//        assertThat(appVersion.getTargetAppVersion()).isEqualTo(appVersionDTO.getTargetAppVersion());
//    }
//
//    @Test
//    void editAppVersion_should_throw_exception_when_id_not_found() throws Exception {
//        AppVersionDTO appVersionDTO = createActiveAppVersionDto();
//        this.appVersionMvc.perform(put("/v1/app-version")
//                        .contentType(TestUtil.APPLICATION_JSON)
//                        .content(TestUtil.convertObjectToJsonBytes(appVersionDTO)))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    @Transactional
//    void editAppVersion_should_throw_exception_when_target_version_is_duplicate() throws Exception {
//        AppVersion appVersion = appVersionRepository.saveAndFlush(createActiveAppVersion().client(client).id(null));
//        AppVersionDTO appVersionDTO = createActiveAppVersionDto().setId(appVersion.getId()).setVersionCode(appVersion.getVersionCode() + 1);
//        appVersionRepository.saveAndFlush(createActiveAppVersion().client(client).id(null).versionCode(appVersionDTO.getVersionCode()));
//        this.appVersionMvc.perform(put("/v1/app-version")
//                        .contentType(TestUtil.APPLICATION_JSON)
//                        .content(TestUtil.convertObjectToJsonBytes(appVersionDTO)))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    @Transactional
//    void editAppVersion() throws Exception {
//        AppVersion appVersion = appVersionRepository.saveAndFlush(createActiveAppVersion().client(client).id(null));
//        AppVersionDTO appVersionDTO = createActiveAppVersionDto().setId(appVersion.getId()).setVersionCode(appVersion.getVersionCode() + 1)
//                .setVersionUrl("TEST");
//        this.appVersionMvc.perform(put("/v1/app-version")
//                        .contentType(TestUtil.APPLICATION_JSON)
//                        .content(TestUtil.convertObjectToJsonBytes(appVersionDTO)))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isOk());
//        assertThat(appVersion.getVersionUrl()).isEqualTo(appVersionDTO.getVersionUrl());
//        assertThat(appVersion.getVersionCode()).isEqualTo(appVersionDTO.getVersionCode());
//        assertThat(appVersion.getCities()).isEqualTo(appVersionDTO.getCities());
//        assertThat(appVersion.getDeliveryCategories()).isEqualTo(appVersionDTO.getDeliveryCategories());
//        assertThat(appVersion.getTargetAppVersion()).isEqualTo(appVersionDTO.getTargetAppVersion());
//    }
//
//    @Test
//    @Transactional
//    void editBlockAppVersion_should_throw_exception_when_target_version_not_found() throws Exception {
//        AppVersion appVersion = appVersionRepository.saveAndFlush(AppVersionDataTest.createBlockAppVersion().client(client).id(null));
//        AppVersionDTO appVersionDTO = createBlockAppVersionDto().setId(appVersion.getId()).setVersionCode(appVersion.getVersionCode() + 1);
//        appVersionDTO.setTargetVersionId(Integer.MAX_VALUE).setTargetAppVersion(Integer.MAX_VALUE);
//        appVersionRepository.saveAndFlush(createActiveAppVersion().client(client).id(null));
//        this.appVersionMvc.perform(put("/v1/app-version")
//                        .contentType(TestUtil.APPLICATION_JSON)
//                        .content(TestUtil.convertObjectToJsonBytes(appVersionDTO)))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isNotFound());
//    }
//
//    @Transactional
//    @Test
//    void editBlockAppVersion_should_throw_exception_when_versionCode_greater_than_target() throws Exception {
//        AppVersion appVersion = appVersionRepository.saveAndFlush(AppVersionDataTest.createBlockAppVersion().client(client).id(null));
//        AppVersionDTO appVersionDTO = createBlockAppVersionDto().setId(appVersion.getId()).setVersionCode(appVersion.getVersionCode() + 1);
//        AppVersion target = appVersionRepository.saveAndFlush(AppVersionDataTest.createActiveAppVersion().client(client).id(null));
//        appVersionDTO.setTargetVersionId(target.getId()).setTargetAppVersion(target.getVersionCode() - 1).setVersionCode(target.getVersionCode() + 1);
//        this.appVersionMvc.perform(put("/v1/app-version")
//                        .contentType(TestUtil.APPLICATION_JSON)
//                        .content(TestUtil.convertObjectToJsonBytes(appVersionDTO)))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    @Transactional
//    void getAppVersionList() throws Exception {
//        List<AppVersion> appVersions = AppVersionDataTest.createAppVersionList();
//        appVersions.forEach(appVersion -> appVersionRepository.saveAndFlush(appVersion.client(client).id(null)));
//        this.appVersionMvc.perform(get("/v1/app-version")
//                        .param("status", AppVersionStatus.ACTIVE.name(), AppVersionStatus.BLOCKED.name())
//                        .param("client-type", ClientType.BIKER.name())
//                        .param("page", "0")
//                        .param("size", "10"))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(jsonPath("$.count").value(is(appVersions.size())))
//                .andExpect(jsonPath("$.appVersion.[*].versionCode").value(hasItem(appVersions.get(0).getVersionCode())))
//                .andExpect(jsonPath("$.appVersion.[*].versionUrl").value(hasItem(appVersions.get(0).getVersionUrl())))
//                .andExpect(jsonPath("$.appVersion.[*].targetAppVersion").value(hasItem(appVersions.get(0).getTargetAppVersion())))
//                .andExpect(jsonPath("$.appVersion.[*].cities").value(hasItem(appVersions.get(0).getCities())))
//                .andExpect(jsonPath("$.appVersion.[*].deliveryCategories").value(hasItem(appVersions.get(0).getDeliveryCategories())));
//    }
//
//    @Test
//    @Transactional
//    void checkAppVersionBlock() throws Exception {
//        AppVersion targetAppVersion = AppVersionDataTest.createActiveAppVersion();
//        AppVersion appVersion = AppVersionDataTest.createBlockAppVersion().versionCode(targetAppVersion.getVersionCode() - 1).
//                targetAppVersion(targetAppVersion.getVersionCode());
//        appVersionRepository.saveAndFlush(targetAppVersion.client(client).id(null));
//        appVersionRepository.saveAndFlush(appVersion.client(client).id(null));
//        this.appVersionMvc.perform(get("/v1/app-version/check-version/{versionCode}", appVersion.getVersionCode())
//                        .param("city", "Tehran")
//                        .param("client-type", ClientType.BIKER.name())
//                        .param("delivery-category", "BIKE")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.appVersionSupported").value(is(false)))
//                .andExpect(jsonPath("$.nextAppUrl").value(is(targetAppVersion.getVersionUrl())));
//    }
//
//    @Test
//    @Transactional
//    void checkAppVersionWarning() throws Exception {
//        AppVersion targetAppVersion = AppVersionDataTest.createActiveAppVersion();
//        AppVersion appVersion = AppVersionDataTest.createWarningAppVersion().versionCode(targetAppVersion.getVersionCode() - 1).
//                targetAppVersion(targetAppVersion.getVersionCode());
//        appVersionRepository.saveAndFlush(targetAppVersion.client(client).id(null));
//        appVersionRepository.saveAndFlush(appVersion.client(client).id(null));
//        this.appVersionMvc.perform(get("/v1/app-version/check-version/{versionCode}", appVersion.getVersionCode())
//                        .param("city", "Tehran")
//                        .param("client-type", ClientType.BIKER.name())
//                        .param("delivery-category", "BIKE")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.appVersionSupported").value(is(true)))
//                .andExpect(jsonPath("$.nextAppUrl").value(is(targetAppVersion.getVersionUrl())));
//    }
//
//
//}
