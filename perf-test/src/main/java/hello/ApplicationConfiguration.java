package hello;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.infinispan.client.hotrod.configuration.SaslQop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
public class ApplicationConfiguration {

   @Bean
   public org.infinispan.client.hotrod.configuration.Configuration customConfiguration(Environment env) {
      String hotrodAddress = env.getRequiredProperty("hotrod");

      ConfigurationBuilder c = new ConfigurationBuilder();
//            .security()
//            .authentication().enable()
//            .username("Jgz67JbrPT")
//            .password("10i79nYqPv")
//            .realm("ApplicationRealm")
//            .serverName("keycloak-jdg-server")
//            .saslMechanism("DIGEST-MD5")
//            .saslQop(SaslQop.AUTH)
      c.addServers(hotrodAddress);
      return c.build();
   }

}
