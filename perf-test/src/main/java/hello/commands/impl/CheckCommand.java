package hello.commands.impl;

import java.lang.invoke.MethodHandles;
import java.time.Instant;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import hello.commands.Command;

@Component
public class CheckCommand implements Command {

   private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

   RemoteCacheManager rcm;

   @Value("${cache}")
   String cacheName;

   public CheckCommand(RemoteCacheManager rcm) {
      this.rcm = rcm;
   }

   @Override
   public String getCommandName() {
      return "check";
   }

   @Override
   public StringBuilder invoke(StringBuilder stringBuilder) {
      stringBuilder.append("[" + Instant.now() + "] Check start");
      stringBuilder.append("\n");
      int numberOfEntries = rcm.getCache(cacheName).size();
      stringBuilder.append("[" + Instant.now() + "] Check end, Total entries: " + numberOfEntries);
      stringBuilder.append("\n");
      return stringBuilder;
   }
}
