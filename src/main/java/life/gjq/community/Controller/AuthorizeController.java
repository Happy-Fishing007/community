package life.gjq.community.Controller;

import life.gjq.community.dto.AccessTokenDTO;
import life.gjq.community.dto.GithubUser;
import life.gjq.community.mapper.UserMapper;
import life.gjq.community.model.User;
import life.gjq.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.setRedirect.uri}")
    private String setRedirectUri;

    @Autowired
    private GithubProvider githubProvider;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(setRedirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessTokenDTO(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);

        if (user != null) {
            //登录成功,写cookie和session
            request.getSession().setAttribute("user", user);
            User modelUser = new User();
            modelUser.setAvatarUrl(user.getAvatar_url());
            modelUser.setUser(String.valueOf(user.getId()));
            modelUser.setName(user.getName());
            String token = UUID.randomUUID().toString();
            modelUser.setToken(token);
            modelUser.setGmtCreate(System.currentTimeMillis());
            modelUser.setGmtModified(modelUser.getGmtCreate());
            userMapper.insert(modelUser);
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(60 * 60);
            response.addCookie(cookie);
            return "redirect:/";
        } else {
            //登录失败
            return "redirect:/";
        }

    }
}
