package com.mapsa.duolingo.redis;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@EnableCaching
@Configuration
public class RedisConfiguration {

    @Value("${spring.redis.host}")
    private String REDIS_HOST;

    @Value("${spring.redis.port}")
    private int REDIS_PORT;

  /*  @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(REDIS_HOST);
        redisStandaloneConfiguration.setPort(REDIS_PORT);

        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
        jedisClientConfiguration.connectTimeout(Duration.ofSeconds(600000));

        return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration.build());
    }*/



    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(REDIS_HOST, REDIS_PORT);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }


   /* @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }*/

    /*@JsonIgnoreProperties(ignoreUnknown = true)
    class ResponseEntityMixin {
        @JsonCreator
        public ResponseEntityMixin(@JsonProperty("body") Object body,
                                   @JsonDeserialize(as = LinkedMultiValueMap.class) @JsonProperty("headers") MultiValueMap<String, String> headers,
                                   @JsonProperty("statusCodeValue") HttpStatus status) {
        }
    }

    class HttpStatusMixIn {

        @JsonCreator
        public static HttpStatus resolve(int statusCode) {
            return HttpStatus.NO_CONTENT;
        }
    }*/
}
