package life.gjq.community.HelloController;

import life.gjq.community.dto.AccessTokenDTO;
import life.gjq.community.dto.GithubUser;
import life.gjq.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.plaf.nimbus.State;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

   @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name= "state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("8e4830e0bbed6056eab0");
        accessTokenDTO.setClient_secret("0c9a59e26dcd195e64358488dd2cb6dff7ee0670");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessTokenDTO(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
