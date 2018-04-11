package hello;

import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.client.hotrod.configuration.ConfigurationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hello.commands.CommandParser;

@SpringBootApplication(scanBasePackages = "hello")
@Import(ApplicationConfiguration.class)
@RestController
public class Application {

   @Autowired
   private CommandParser parser;

   @RequestMapping("/")
   public String home(@RequestParam(value = "cmds", required = false) String commands) {
      StringBuilder sb = new StringBuilder();

      parser.obtainCommands(commands).stream()
            .forEach(command -> command.invoke(sb));

      return sb.toString();
   }

   public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
   }

}
