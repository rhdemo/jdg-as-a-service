package hello.commands.impl;

import java.lang.invoke.MethodHandles;
import java.time.Instant;
import java.util.UUID;
import java.util.stream.IntStream;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import hello.commands.Command;

@Component
public class LoadCommand implements Command {

   private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

   @Value("${payloads.clement}")
   private String payload;

   int numberOfEntries;

   @Value("${cache}")
   String cacheName;

   RemoteCacheManager rcm;

   public LoadCommand(RemoteCacheManager rcm) {
      this.rcm = rcm;
   }

   @Override
   public String getCommandName() {
      return "load";
   }

   @Override
   public StringBuilder invoke(StringBuilder stringBuilder) {
      stringBuilder.append("[" + Instant.now() + "] Load start");
      stringBuilder.append("\n");

      String key = UUID.randomUUID().toString();
      RemoteCache remoteCache = rcm.getCache(cacheName);

      IntStream.range(0, numberOfEntries)
            .forEach(index -> {
               String entryKey = "" + key + index;
               remoteCache.put(entryKey, payload);
               logger.debug("Inserting {}/{}", entryKey, payload);
            });
      stringBuilder.append("[" + Instant.now() + "] Load end, total entries added: " + numberOfEntries);
      stringBuilder.append("\n");
      return stringBuilder;
   }

   @Override
   public void setArgument(String argument) {
      this.numberOfEntries = Integer.valueOf(argument);
   }
}
