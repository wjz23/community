package com.mforum.controller;

import com.mforum.dto.AccessTokenDTO;
import com.mforum.dto.GithubUser;
import com.mforum.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientID;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.Redirect.uri}")
    private String redirectUri;
    @RequestMapping("/callback")
    public String CallBack(@RequestParam(name = "code")String code, @RequestParam(name = "state")String state, HttpSession session){
        AccessTokenDTO accessTokenDTO = setaccesstokenDTO(code,state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        if (githubUser!=null){
            session.setAttribute("user",githubUser);
            System.out.println(githubUser);
            return "redirect:/";
        }else {
            return "redirect:/";
        }
    }

    private AccessTokenDTO setaccesstokenDTO(String code,String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(clientID);
        accessTokenDTO.setClient_secret(clientSecret);
        return accessTokenDTO;
    }
}
